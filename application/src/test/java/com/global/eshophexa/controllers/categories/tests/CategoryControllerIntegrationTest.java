package com.global.eshophexa.controllers.categories.tests;

import com.global.tests.integration.StandardAPIIntegrationTest;
import com.global.tests.scenarios.annotation.ScenarioParametrizedTest;
import com.global.tests.scenarios.specific.StandardApiScenario;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@Disabled
@SpringBootTest
@DirtiesContext
public class CategoryControllerIntegrationTest extends StandardAPIIntegrationTest<StandardApiScenario> {

    @Override
    protected String endpointUrl() {
        return """
                    /categories/{category_id}
                    """;
    }

    @ScenarioParametrizedTest
    @MethodSource("")
    void testOk() {

    }
}
