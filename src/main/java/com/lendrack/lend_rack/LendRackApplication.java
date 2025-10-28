package com.lendrack.lend_rack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LendRackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendRackApplication.class, args);
	}

}
