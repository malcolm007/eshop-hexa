package com.global.tests.utils;

import com.global.tests.integration.model.ApiInput;
import com.global.tests.scenarios.specific.StandardApiScenario;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.stream.Stream;

public enum ScenarioUrlHelper {
    ;

    public static <T extends StandardApiScenario> String getEffectiveUrl(T scenario,
                                                                         String endpointUrl,
                                                                         JsonHelper jsonHelper) {
        ApiInput apiInput = jsonHelper.deserializeJsonFile(scenario.getInputFilePath(),
                ApiInput.class);

        Map<String, Object> urlParams = urlParamsFrom(apiInput);

        return UriComponentsBuilder.fromPath(endpointUrl)
                .buildAndExpand(urlParams)
                .toUriString()
                .trim()
                .replace("\n", "");
    }

    private static Map<String, Object> urlParamsFrom(ApiInput apiInput) {
        return ParamsUtil.concatenateMap(Stream.of(apiInput.pathVariables(), apiInput.requestParams(), apiInput.requestBody()));
    }
}
