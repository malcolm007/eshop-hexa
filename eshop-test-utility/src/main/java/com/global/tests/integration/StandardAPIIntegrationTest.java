package com.global.tests.integration;

import com.global.tests.scenarios.specific.StandardApiScenario;
import com.global.tests.utils.ScenarioUrlHelper;
import org.springframework.http.ResponseEntity;

import static com.global.tests.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class StandardAPIIntegrationTest<T extends StandardApiScenario> extends AbstractAPIIntegrationTest {

    protected void run_a_standrad_api_test_case_on_the_following(T scenario) {
        // given
        String effectiveUrl = ScenarioUrlHelper.getEffectiveUrl(scenario, endpointUrl(), jsonHelper);

        //when
        ResponseEntity<String> actualResponse = restTemplate.getForEntity(effectiveUrl, String.class);

        //then
        assertThat(actualResponse.getStatusCode()).isEqualTo(scenario.getExpectedHttpStatus());
        assertThat(actualResponse).isEqualTo(scenario.getExpectedResponseFilePath());
    }

    protected abstract String endpointUrl();
}
