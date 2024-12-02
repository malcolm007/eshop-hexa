package com.global.tests.scenarios.annotation;

import org.junit.jupiter.params.ParameterizedTest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ParameterizedTest(name = "[{index}] - {0}")
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ScenarioParametrizedTest {
}
