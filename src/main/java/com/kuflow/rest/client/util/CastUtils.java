/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.util;

public interface CastUtils {
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

    public static <T> String cannotCastMsg(Object obj, Class<T> clazz) {
        return "Cannot cast " + obj.getClass().getName() + " to " + clazz.getName();
    }
}
