package com.global.eshop.scenarios.enums.tests;

import com.global.tests.Sana;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class Tests {

    @Test
    void test() {
        List<Sana> sanas = List.of(new Sana(1L, "Sana", "Maghraoui"));
        Sana expected = new Sana(1L, "Sana", "Maghraoui1");

        Assertions.assertThat(sanas).usingRecursiveComparison().comparingOnlyFields("id", "lastName")
                .isEqualTo(expected);
    }
}
