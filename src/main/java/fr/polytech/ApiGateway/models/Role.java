package fr.polytech.ApiGateway.models;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_RECRUTEUR("ROLE_RECRUTEUR"),
    ROLE_CANDIDAT("ROLE_CANDIDAT");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Aucun rôle trouvé pour la valeur : " + value);
    }
}
