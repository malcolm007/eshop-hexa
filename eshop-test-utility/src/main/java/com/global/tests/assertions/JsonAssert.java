package com.global.tests.assertions;

import com.global.tests.utils.JsonHelper;
import lombok.SneakyThrows;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonAssert extends AbstractAssert<JsonAssert, String> {

    private final JsonHelper jsonHelper = new JsonHelper();

    JsonAssert(String actualJsonContent) {
        super(actualJsonContent, JsonAssert.class);
    }

    @SneakyThrows
    public void isEqualTo(String expectedJsonFilePath) {
        String actualJsonContent = jsonHelper.prettyPrint(actual);
        String expectedJson = jsonHelper.jsonFromFile(expectedJsonFilePath);
        String expectedJsonContent = jsonHelper.prettyPrint(expectedJson);

        assertThat(actualJsonContent).isEqualTo(expectedJsonContent);
    }
}
