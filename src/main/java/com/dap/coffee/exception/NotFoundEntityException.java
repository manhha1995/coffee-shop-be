package com.dap.coffee.exception;

import lombok.Getter;

import java.util.function.Supplier;

public class NotFoundEntityException extends Exception{
    private static final long serialVersionUID = -718639735490655218L;

    @Getter
    private final String errorField;

    private NotFoundEntityException(String errorField, String message) {
        super(message);
        this.errorField = errorField;
    }

    public static NotFoundEntityException of(String message) {
        return of(null, message);
    }

    public static NotFoundEntityException of(String errorField, String message) {
        return new NotFoundEntityException(errorField, message);
    }

    public static Supplier<NotFoundEntityException> ofSupplier(String message) {
        return ofSupplier(null, message);
    }

    public static Supplier<NotFoundEntityException> ofSupplier(String errorField, String message) {
        return () -> of(errorField, message);
    }

    public static void throwNow(String errorField, String message) throws NotFoundEntityException {
        throw of(errorField, message);
    }
}
