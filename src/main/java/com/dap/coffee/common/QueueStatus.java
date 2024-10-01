package com.dap.coffee.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QueueStatus {
    public static final String NEW_ORDER = "NEW_ORDER";
    public static final String IN_PROGRESS = "IN_PROGRESS";

    public static final String READY = "READY";
    public static final String CANCELED = "CANCELED";
}
