package com.global.eshop.scenarios.enums.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
@Getter
public enum DayEnum {
    MONDAY("MON", "Monday"),
    TUESDAY("TUE", "Tuesday");


    private final String code;
    private final String value;


    public static DayEnum from(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        String valueUpperCase = value.toUpperCase();

        return switch (valueUpperCase) {
            case "MON" -> MONDAY;
            case "TUE" -> TUESDAY;
            default -> throw new IllegalArgumentException("Invalid Day value [%s]".formatted(value));
        };
    }

}
