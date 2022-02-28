/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class MapUtils {

    public static Map<String, Serializable> toMapSerializable(Map<String, Object> d) {
        Map<String, Serializable> values = new HashMap<>();
        for (Map.Entry<String, Object> entry : d.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Serializable) {
                values.put(entry.getKey(), (Serializable) value);
            }
        }

        return values;
    }
}
