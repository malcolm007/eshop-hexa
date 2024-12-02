package com.global.eshop.scenarios.enums.provider;

import com.global.eshop.scenarios.enums.base.DayEnum;
import com.global.eshop.scenarios.enums.scenario.DayEnumScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.global.eshop.scenarios.enums.base.DayEnum.MONDAY;

public class DayEnumScenarioProvider {

    public static List<DayEnumScenario> dayEnumScenarios() {
        return Stream.of(nullValueScenario(),
                        mondayEnumScenario())
                .flatMap(List::stream)
                .toList();
    }

    private static List<DayEnumScenario> nullValueScenario() {
        return List.of(
                DayEnumScenario.builder_()
                        .description(description(null, null))
                        .input(null)
                        .expected(null)
                        .build()
        );
    }

    private static List<DayEnumScenario> mondayEnumScenario() {
        List<DayEnumScenario> scenarios = new ArrayList<>();
        DayEnum expected = MONDAY;

        for (String m : List.of("m", "M")) {
            for (String o : List.of("o", "O")) {
                for (String n : List.of("n", "N")) {
                    String input = m + o + n;
                    scenarios.add(DayEnumScenario.builder_()
                            .description(description(input, expected))
                            .input(input)
                            .expected(expected)
                            .build());
                }
            }
        }
        return scenarios;
    }


    private static String description(String input, DayEnum expected) {
        return "input [%s] => expected [%s]".formatted(input, expected == null ? null : expected.getValue());
    }
}
