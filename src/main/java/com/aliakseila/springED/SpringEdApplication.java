package com.aliakseila.springED;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aliakseila.springED"})
@EnableCaching
public class SpringEdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEdApplication.class, args);
	}

}
