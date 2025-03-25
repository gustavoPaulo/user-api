package br.com.billing.userapi.model.enums;

public enum UserType {

    ADMIN("Admin"),
    DEFAULT("Default");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
