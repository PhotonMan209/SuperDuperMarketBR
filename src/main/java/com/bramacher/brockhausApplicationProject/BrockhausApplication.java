package com.bramacher.brockhausApplicationProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.bramacher.brockhausApplicationProject")
@EntityScan(basePackages = "com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket")
public class BrockhausApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrockhausApplication.class, args);
	}
}
