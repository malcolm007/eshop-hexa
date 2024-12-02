package com.global.tests.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;


public class JsonHelper {

    private final ClassLoader classLoader;

    private final JsonMapper jsonMapper;

    public JsonHelper() {
        this.classLoader = JsonHelper.class.getClassLoader();
        this.jsonMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }
    @SneakyThrows
    public String prettyPrint(String jsonContent) {
        JsonNode rootNode = jsonMapper.readTree(jsonContent);
        return asJsonString(rootNode);
    }

    @SneakyThrows
    public String asJsonString(Object object) {
        return jsonMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public <T> T deserializeJsonFile(String jsonFilePath, Class<T> type) {
        String fileContent = jsonFromFile(jsonFilePath);
        JsonNode jsonNode = jsonMapper.readTree(fileContent);
        return jsonMapper.convertValue(jsonNode, type);
    }

    @SneakyThrows
    public String jsonFromFile(String jsonFilePath) {
        URL resource = classLoader.getResource(jsonFilePath);
        if (resource == null) {
            throw new IOException("Can not find file : " + jsonFilePath);
        }
        return IOUtils.toString(resource, UTF_8);
    }
}
