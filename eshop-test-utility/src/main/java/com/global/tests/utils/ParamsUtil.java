package com.global.tests.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@UtilityClass
public class ParamsUtil {

    public static Map<String, Object> concatenateMap(Stream<Map<String, Object>> stream) {
        BinaryOperator<Object> mergeFunction = (k, v) -> {
            throw new AssertionError("duplicate values for key: " + k);
        };

        return stream.filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .collect(HashMap::new,
                        accumulateAcceptingNullValues(mergeFunction),
                        HashMap::putAll);
    }

    private static BiConsumer<HashMap<String, Object>, Map.Entry<String, Object>> accumulateAcceptingNullValues(BinaryOperator<Object> mergeFunction) {

        return (accumulator, entry) -> {
            Object value = accumulator.containsKey(entry.getKey()) ?
                    mergeFunction.apply(accumulator.get(entry.getKey()), entry.getValue()) :
                    entry.getValue();
            accumulator.put(entry.getKey(), value);
        };
    }
}
