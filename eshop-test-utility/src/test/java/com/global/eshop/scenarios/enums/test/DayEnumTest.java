package com.global.eshop.scenarios.enums.test;

import com.global.eshop.scenarios.enums.base.DayEnum;
import com.global.tests.scenarios.annotation.ScenarioParametrizedTest;
import com.global.tests.scenarios.specific.EnumScenario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DayEnumTest {

    @ScenarioParametrizedTest
    @MethodSource("com.global.eshop.scenarios.enums.provider.DayEnumScenarioProvider#dayEnumScenarios")
    void from_should_return_expected_value(EnumScenario<DayEnum> scenario) {
        //when
        DayEnum actual = DayEnum.from(scenario.getInput());

        //Then
        assertThat(actual).isEqualTo(scenario.getExpected());
    }

    @Test
    void from_should_throw_an_exception_when_the_input_is_not_valid() {
        //given
        String input = "invalid input";

        //when - then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> DayEnum.from(input))
                .withMessage("Invalid Day value [invalid input]");
    }

}
