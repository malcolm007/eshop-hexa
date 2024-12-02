package com.global.eshop.scenarios.enums.scenario;

import com.global.eshop.scenarios.enums.base.DayEnum;
import com.global.tests.scenarios.specific.EnumScenario;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "builder_")
@Getter
public class DayEnumScenario extends EnumScenario<DayEnum> {
}
