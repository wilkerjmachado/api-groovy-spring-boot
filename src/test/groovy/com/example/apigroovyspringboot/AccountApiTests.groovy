package com.example.apigroovyspringboot

import com.example.apigroovyspringboot.model.Account
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension

import static org.assertj.core.api.Assertions.assertThat

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountApiTests {

    @Autowired
    private TestRestTemplate restTemplate

    @Test
    @Order(1)
    void testAddAccount() {

        String documentNumber = "12345678900"

        Account account = new Account(documentNumber: documentNumber)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/accounts", account, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.document_number).isEqualTo(documentNumber)

        assertThat(responseEntity.body.id).isNotNull()
    }

    @Test
    void testAddAccount2() {

        String documentNumber = "12345600000"

        Account account = new Account(documentNumber: documentNumber)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/accounts", account, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.document_number).isEqualTo(documentNumber)

        assertThat(responseEntity.body.id).isNotNull()
    }

    @Test
    void testAddAccountBlank() {

        String documentNumber = ""

        Account account = new Account(documentNumber: documentNumber)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/accounts", account, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.errors).isNotNull()
    }

    @Test
    @Order(2)
    void testAddAccountDuplicated() {

        String documentNumber = "12345678900"

        Account account = new Account(documentNumber: documentNumber)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/accounts", account, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.message).isNotNull()
    }
}
