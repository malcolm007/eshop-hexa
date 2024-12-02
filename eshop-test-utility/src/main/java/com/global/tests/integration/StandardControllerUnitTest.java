package com.global.tests.integration;

import com.global.tests.assertions.Assertions;
import com.global.tests.scenarios.specific.StandardApiScenario;
import com.global.tests.utils.JsonHelper;
import com.global.tests.utils.ScenarioUrlHelper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class StandardControllerUnitTest<T> {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected JsonHelper jsonHelper;

    @SpyBean
    protected T myController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController)
                .setControllerAdvice(getControllerAdvices())
                .defaultResponseCharacterEncoding(UTF_8)
                .build();
    }

    protected final void execute_standard_unit_test_scenario_on_the_following(StandardApiScenario scenario) throws Exception {
        //given
        String effectiveURL = ScenarioUrlHelper.getEffectiveUrl(scenario, endpointUrl(), jsonHelper);

        //when - then
        MvcResult mvcResult = mockMvc.perform(requestBuilder(effectiveURL))
                .andDo(print())
                .andExpect(status().is(scenario.getExpectedHttpStatus().value()))
                .andExpect(content().encoding(UTF_8))
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getContentAsString(UTF_8))
                .isEqualTo(scenario.getExpectedResponseFilePath());
    }


    protected abstract String endpointUrl();
    protected abstract Object[] getControllerAdvices();


    private static MockHttpServletRequestBuilder requestBuilder(String effectiveUrl) {
        return get(effectiveUrl).with(setRequestServerPort());
    }

    private static RequestPostProcessor setRequestServerPort() {
        return request -> {
            request.setServerPort(8080);
            return request;
        };
    }
}
