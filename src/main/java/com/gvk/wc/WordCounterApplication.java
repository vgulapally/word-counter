package com.gvk.wc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WordCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordCounterApplication.class, args);
	}

}
