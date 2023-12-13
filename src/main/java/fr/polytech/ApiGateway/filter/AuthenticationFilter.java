package fr.polytech.ApiGateway.filter;

import fr.polytech.ApiGateway.config.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {

            logger.warn("The header is {}", exchange.getRequest());
            ServerHttpRequest request = null;

            if (validator.isSecured.test(exchange.getRequest())) {

                logger.warn("The header is {}", exchange.getRequest().getHeaders().toString());

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return chain.filter(exchange);
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    String username = jwtUtils.extractUsername(authHeader);
                    UserDetails user = userDetailsService.loadUserByUsername(username);
                    if (jwtUtils.validateToken(authHeader, user)) {
                        request = exchange.getRequest().mutate()
                                .header("username", username)
                                .header("role", user.getAuthorities().toString())
                                .build();
                    }
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        }));
    }




    public static class Config {

    }
}
