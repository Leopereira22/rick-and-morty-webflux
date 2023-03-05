package com.pastorin.webrickandmortyapi.util;

public class ParamUtil {

    public static void addParam(StringBuilder uri, String name, Object value) {
        if (value != null) {
            uri.append("?").append(name).append("=").append(value);
        }
    }
}
