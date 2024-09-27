package com.dap.coffee.exception;

import lombok.Getter;

import java.util.function.Supplier;

public class UnauthorizedException extends RuntimeException{
    @Getter
    private final String errorField;

    public UnauthorizedException(String errorField, String message) {
        super(message);
        this.errorField = errorField;
    }

    public static UnauthorizedException of(String message) {
        return of(null, message);
    }

    public static UnauthorizedException of(String errorField, String message) {
        return new UnauthorizedException(errorField, message);
    }

    public static Supplier<UnauthorizedException> ofSupplier(String message) {
        return ofSupplier(null, message);
    }

    public static Supplier<UnauthorizedException> ofSupplier(String errorField, String message) {
        return () -> of(errorField, message);
    }

    public static void throwNow(String errorField, String message) throws UnauthorizedException {
        throw of(errorField, message);
    }
}
