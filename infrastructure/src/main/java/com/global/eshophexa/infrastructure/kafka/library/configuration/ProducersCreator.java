package com.global.eshophexa.infrastructure.kafka.library.core;

import com.global.eshophexa.infrastructure.kafka.library.annotations.EShopPush;
import com.global.eshophexa.infrastructure.kafka.library.exception.KafkaCOnfigurationException;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaProperties;
import com.global.eshophexa.infrastructure.kafka.library.models.Producer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Configuration
public class ProducersCreator implements BeanDefinitionRegistryPostProcessor {

    private final KafkaProperties kafkaProperties;

    public ProducersCreator(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        List<Producer> allProducer = new ArrayList<>();
        discoverAllProducer(registry, ((beanDefinition, beanName) -> {
            try {
                Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
                if (EShopPushService.class.isAssignableFrom(beanClass)) {
                    allProducer.add(registerProducer(beanClass));
                }
            } catch (ClassNotFoundException e) {
                throw new KafkaCOnfigurationException("error configuring kafka producers !", e);
            }
        }));


    }

    private void discoverAllProducer(BeanDefinitionRegistry registry,
                                     BiConsumer<BeanDefinition, String> biConsumer) {
        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            var beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getBeanClassName() != null && !beanDefinition.isAbstract()) {
                biConsumer.accept(beanDefinition, beanDefinitionName);
            }
        }
    }

    private Producer registerProducer(Class<?> beanClass) {
        var annotations = beanClass.getAnnotationsByType(EShopPush.class);
        var producerCode = annotations[0].code();
        return kafkaProperties.getProducerByName(producerCode).orElseThrow(
                () -> new KafkaCOnfigurationException("Producer properties not found !"));

    }
}