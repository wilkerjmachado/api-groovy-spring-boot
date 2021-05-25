package com.example.apigroovyspringboot

import com.example.apigroovyspringboot.api.AccountApi
import com.example.apigroovyspringboot.api.TransactionApi
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import static org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class ApiGroovySpringBootApplicationTests {

	@Autowired
	private AccountApi accountApi

	@Autowired
	private TransactionApi transactionApi

	@Test
	void contextLoads() {

		assertThat(accountApi).isNotNull()

		assertThat(transactionApi).isNotNull()

	}

}
