package com.dap.coffee.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ERole {

    ADMIN(0),
    USER(1);

    private final int value;

    private ERole(int value){
        this.value = value;
    }

    @JsonValue
    public int getValue(){
        return this.value;
    }

    public static ERole from(int value){
        return Arrays.stream(values())
                .filter(eRole -> eRole.value == value)
                .findFirst()
                .orElse(null);
    }
}
