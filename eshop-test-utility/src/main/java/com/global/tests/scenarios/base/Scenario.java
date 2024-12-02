package com.global.tests.scenarios.base;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.params.provider.Arguments;

@SuperBuilder
@Getter
public class Scenario implements Arguments {

    protected final String description;

    @Override
    public Object[] get() {
        return new Object[]{this};
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
