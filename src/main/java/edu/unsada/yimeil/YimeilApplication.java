package edu.unsada.yimeil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@SpringBootApplication(scanBasePackages = "edu.unsada.yimeil")
@EnableJpaRepositories(basePackages = "edu.unsada.yimeil.repository")
@EntityScan(basePackages = "edu.unsada.yimeil.models")
public class YimeilApplication {
	public static void main(String[] args) {
		SpringApplication.run(YimeilApplication.class, args);
	}
}
