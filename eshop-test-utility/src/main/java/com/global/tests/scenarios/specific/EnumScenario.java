package com.global.tests.scenarios.specific;

import com.global.tests.scenarios.base.Scenario;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "builder_")
@Getter
public class EnumScenario<T extends Enum<T>> extends Scenario {

    private final String input;
    private final T expected;
}
