package com.global.eshophexa.controllers.categories.get_category_by_id.providers;

import com.global.tests.scenarios.specific.StandardApiScenario;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Stream;

import static com.global.eshophexa.controllers.categories.get_category_by_id.filenames.BadRequestScenarioFileNames.CATEGORY_ID_POORLY_FORMATTED_API_INPUT;
import static com.global.eshophexa.controllers.categories.get_category_by_id.filenames.BadRequestScenarioFileNames.CATEGORY_ID_POORLY_FORMATTED_API_RESPONSE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@UtilityClass
public class GetCategoryByIdScenarioProvider {

    List<StandardApiScenario> apiScenarios() {
        return Stream.of(badRequestsScenarios())
                .flatMap(List::stream)
                .toList();
    }

    private List<StandardApiScenario> badRequestsScenarios() {
        return List.of(
                StandardApiScenario.builder()
                                   .description("category_id malformaté => 400")
                        .inputFilePath(CATEGORY_ID_POORLY_FORMATTED_API_INPUT)
                        .expectedResponseFilePath(CATEGORY_ID_POORLY_FORMATTED_API_RESPONSE)
                        .expectedHttpStatus(BAD_REQUEST)
                        .build()
        );
    }
}
