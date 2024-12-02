package com.global.tests.assertions;

import org.springframework.http.ResponseEntity;

public enum Assertions {
    ;
    public static JsonAssert assertThat(String actualJsonContent) {
        return new JsonAssert(actualJsonContent);
    }

    public static JsonAssert assertThat(ResponseEntity<String> responseEntity) {
        return new JsonAssert(responseEntity.getBody());
    }
}
