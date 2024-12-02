package com.global.eshophexa;

import com.global.eshophexa.infrastructure.kafka.library.models.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties(KafkaProperties.class)
public class EshopHexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopHexaApplication.class, args);
	}

}
