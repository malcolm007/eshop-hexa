package com.global.eshophexa.infrastructure.batch.job;

import com.global.eshophexa.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CategoryBatchProcessor implements ItemProcessor<Category, Category> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBatchProcessor.class);

    @Override
    public Category process(final Category category){
        LOGGER.info("{} processed !", category);
        return category;
    }
}
