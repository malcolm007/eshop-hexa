package com.global.eshophexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class EshopHexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopHexaApplication.class, args);
	}

}
