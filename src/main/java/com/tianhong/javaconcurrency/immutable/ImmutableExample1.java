package com.tianhong.javaconcurrency.immutable;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private static Map<Integer, Integer> map = Maps.newHashMap();
    static {
        map.put(1, 2);
        map.put(3, 4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
    }
}
