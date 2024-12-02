package com.global.tests.integration.model;

import java.util.Map;

public record ApiInput (
        Map<String, Object> pathVariables,
        Map<String, Object> requestParams,
        Map<String, Object> requestBody
) {}
