package com.ccb.model.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    PRIVACY("Privacy"),
    OTHER("Other");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
