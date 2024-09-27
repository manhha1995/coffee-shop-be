package com.dap.coffee.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    private ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok (String message , T data){
        return new ApiResponse<>(true,"Success", message,data);
    }

    public static <T> ApiResponse<T> ok(String message) {
        return ok(message, null);
    }

    public static <T> ApiResponse<T> warning(String message, T data) {
        return new ApiResponse<>(true, "WARNING", message, data);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(false, "ERROR", message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return error(message, null);
    }
}
