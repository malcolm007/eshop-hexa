package com.global.tests.scenarios.specific;

import com.global.tests.scenarios.base.Scenario;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder
@Getter
public class StandardApiScenario extends Scenario {

    private final String inputFilePath;
    private final String expectedResponseFilePath;
    private final HttpStatus expectedHttpStatus;

}
