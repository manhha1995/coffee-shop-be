package com.dap.coffee.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ModelMapperUtils {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private ModelMapperUtils() {
    }

    public static <T> T toObject(Object obj, Class<T> type) {
        if (obj == null) {
            return null;
        }
        T t = null;
        try {
            t = MODEL_MAPPER.map(obj, type);
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
        }
        return t;
    }

    public static <T> T newObject(T obj, Class<T> type) {
        if (obj == null) {
            return null;
        }
        T t = null;
        try {
            t = type.newInstance();
            MODEL_MAPPER.map(obj, t);
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
        }
        return t;
    }

    public static <I, O> List<O> toListObject(List<I> objs, Class<O> type) {
        if (CollectionUtils.isEmpty(objs)) {
            return Collections.emptyList();
        }
        List<O> result = new ArrayList<>();
        for (I obj : objs) {
            O t = toObject(obj, type);
            result.add(t);
        }
        return result;
    }

    public static Object mergeChanges(Object source, Object target) {
        MODEL_MAPPER.map(source, target);
        return target;
    }
}
