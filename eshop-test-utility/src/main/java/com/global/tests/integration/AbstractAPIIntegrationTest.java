package com.global.tests.integration;

import com.global.tests.utils.JsonHelper;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

@Execution(ExecutionMode.CONCURRENT)
public class AbstractAPIIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected JsonHelper jsonHelper;

}
