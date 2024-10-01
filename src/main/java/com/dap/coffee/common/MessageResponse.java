package com.dap.coffee.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResponse {

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
}
