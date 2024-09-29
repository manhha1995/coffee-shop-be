package com.dap.coffee.exception;

import lombok.Getter;

import java.util.function.Supplier;

public class DuplicatedException extends Exception {

    private static final long serialVersionUID = -6129951984467636499L;

    @Getter
    private final String errorField;

    public DuplicatedException(String errorField, String message) {
        super(message);
        this.errorField = errorField;
    }

    public static DuplicatedException of(String errorField, String message) {
        return new DuplicatedException(errorField, message);
    }

    public static DuplicatedException of(String message) {
        return of(null, message);
    }

    public Supplier<DuplicatedException> ofSupplier(String errorField, String message) {
        return () -> of(errorField, message);
    }

    public Supplier<DuplicatedException> ofSupplier(String message) {
        return ofSupplier(null,message);
    }

    public static void throwNow(String errorField, String message) throws DuplicatedException {
        throw of(errorField, message);
    }

    public static void throwNow(String message) throws DuplicatedException {
        throwNow(null, message);
    }
}
