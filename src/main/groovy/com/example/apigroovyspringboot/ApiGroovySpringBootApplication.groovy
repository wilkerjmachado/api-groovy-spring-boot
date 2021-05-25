package com.example.apigroovyspringboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.example.apigroovyspringboot.repository"])
class ApiGroovySpringBootApplication {

	static void main(String[] args) {

		SpringApplication.run(ApiGroovySpringBootApplication, args)
	}

}
