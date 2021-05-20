package com.mercadojob.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.mercadojob.controller")
@SpringBootApplication
public class MercadojobApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MercadojobApplication.class, args);
	}

}
