package fr.polytech.ApiGateway.models;

public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_RECRUTEUR("ROLE_RECRUTEUR"),
    ROLE_CANDIDAT("ROLE_CANDIDAT");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
