package com.global.eshophexa.infrastructure.kafka.library.configuration;

import com.global.eshophexa.infrastructure.kafka.library.annotations.EShopPush;
import com.global.eshophexa.infrastructure.kafka.library.core.EShopPushService;
import com.global.eshophexa.infrastructure.kafka.library.core.KafkaPusherImpl;
import com.global.eshophexa.infrastructure.kafka.library.exception.KafkaCOnfigurationException;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaProperties;
import com.global.eshophexa.infrastructure.kafka.library.models.Producer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Configuration
public class ProducersCreator implements BeanDefinitionRegistryPostProcessor, Ordered, EnvironmentAware {

    private Environment environment;
    private KafkaProperties kafkaProperties;

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
        createBeans(allProducer, registry);

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
    private void createBeans(List<Producer> allProducer, BeanDefinitionRegistry registry) {
        for(Producer producer : allProducer) {
            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(KafkaPusherImpl.class)
                    .addConstructorArgReference("kafkaTemplate")
                    .addConstructorArgValue(producer)
                    .getBeanDefinition();
            registry.registerBeanDefinition(producer.getName() + "_Producer", beanDefinition);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.kafkaProperties = Binder.get(environment)
                .bind("eshop.kafka", Bindable.of(KafkaProperties.class))
                .orElseThrow(IllegalStateException::new);
    }
}