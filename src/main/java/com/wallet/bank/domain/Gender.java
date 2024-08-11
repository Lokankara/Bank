package com.wallet.bank.domain;

import lombok.Getter;

public enum Gender {
    MALE("Mr.", "m"), FEMALE("Ms.", "f");

    @Getter
    private final String greeting;
    private final String stringLine;

    Gender(String greeting, String stringLine) {
        this.greeting = greeting;
        this.stringLine = stringLine;
    }

    public static Gender parse(String stringLine) {
        for (Gender gender : values()) {
            if (stringLine.equals(gender.stringLine)) {
                return gender;
            }
        }
        return null;
    }
}
