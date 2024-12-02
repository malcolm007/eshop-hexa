package com.global.eshophexa.infrastructure.batch.config;

import com.global.eshophexa.infrastructure.batch.job.CategoryBatchProcessor;
import com.global.eshophexa.infrastructure.batch.job.ImportCategoriesJobNotificationListener;
import com.global.eshophexa.models.Category;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ImportConfigBatchConfig {

    @Value("${batch.input-file}")
    private String inputFilePath;


    @Bean
    public FlatFileItemReader<Category> reader() {
        BeanWrapperFieldSetMapper<Category> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Category.class);
        return new FlatFileItemReaderBuilder<Category>()
                .name("category item reader")
                .resource(new ClassPathResource(inputFilePath))
                .delimited()
                .names("id", "name")
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Category> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Category>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO CATEGORY_ENTITY (name) VALUES (:name)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public CategoryBatchProcessor processor() {
        return new CategoryBatchProcessor();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,
                      JdbcBatchItemWriter<Category> writer) {
        return new StepBuilder("step 1", jobRepository)
                .<Category, Category>chunk(10, platformTransactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importCategoryJob(JobRepository jobRepository, ImportCategoriesJobNotificationListener listener, Step step1) {
        return new JobBuilder("import Categories Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}
