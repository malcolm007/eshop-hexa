package com.global.eshophexa.controllers.categories.tests;

import com.global.eshophexa.controllers.CategoryController;
import com.global.eshophexa.exceptions.handler.EshopExceptionHandler;
import com.global.eshophexa.infrastructure.security.filters.JwtAuthenticationFilter;
import com.global.eshophexa.mappers.CategoryMapper;
import com.global.eshophexa.ports.incoming.CategoryUseCases;
import com.global.tests.integration.StandardControllerUnitTest;
import com.global.tests.scenarios.annotation.ScenarioParametrizedTest;
import com.global.tests.scenarios.specific.StandardApiScenario;
import com.global.tests.utils.JsonHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@WebMvcTest(controllers = CategoryController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        classes = JwtAuthenticationFilter.class,
                        type = FilterType.ASSIGNABLE_TYPE)})
@Import({
        JsonHelper.class
})
public class CategoryControllerUnitTests {

    @MockBean
    CategoryUseCases categoryUseCases;

    @MockBean
    CategoryMapper categoryMapper;

    @SpyBean
    EshopExceptionHandler eshopExceptionHandler;


    @Nested
    @DisplayName("get category by id - unit tests :=> operations/{operation_id}")
    public class GetCategoryByIdUnitTests extends StandardControllerUnitTest<CategoryController> {

        @SneakyThrows
        @ScenarioParametrizedTest
        @MethodSource("com.global.eshophexa.controllers.categories.get_category_by_id.providers.GetCategoryByIdScenarioProvider#apiScenarios")
        void getOperationById_should_return_HTTP_OK_according_to(StandardApiScenario scenario) {
            execute_standard_unit_test_scenario_on_the_following(scenario);
        }

        @Override
        protected String endpointUrl() {
            return """
                    /categories/{category_id}
                    """;
        }

        @Override
        protected Object[] getControllerAdvices() {
            return new Object[] {eshopExceptionHandler};
        }
    }
}
