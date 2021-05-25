package com.example.apigroovyspringboot

import com.example.apigroovyspringboot.model.Account
import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.repository.OperationTypeRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionApiTests {

    @Autowired
    private TestRestTemplate restTemplate

    private Account account

    @Autowired
    private OperationTypeRepository operationTypeRepository

    def operationTypes = [:]

    @BeforeAll
    void init() {

        String documentNumber = "12345678900"

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/accounts", new Account(documentNumber: documentNumber), Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        account = new Account(id: responseEntity.body.id)

        operationTypeRepository.findAll().each {

            if(it.id == 1){

                operationTypes.compraAVista = it

            }else if(it.id == 2){

                operationTypes.compraParcelada = it

            }else if(it.id == 3){

                operationTypes.saque = it

            }else if(it.id == 4){

                operationTypes.pagamento = it

            }
        }

    }

    @Test
    void testAddTransactionCompraAVista() {

        BigDecimal amount = 100

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.compraAVista, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.id).isNotNull()

        assertThat(responseEntity.body.eventDate).isNotNull()

        assertThat(responseEntity.body.amount).is(amount * -1)
    }

    @Test
    void testAddTransactionCompraParcelada() {

        BigDecimal amount = 150

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.compraParcelada, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.id).isNotNull()

        assertThat(responseEntity.body.eventDate).isNotNull()

        assertThat(responseEntity.body.amount).is(amount * -1)
    }

    @Test
    void testAddTransactionSaque() {

        BigDecimal amount = 50

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.saque, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.id).isNotNull()

        assertThat(responseEntity.body.eventDate).isNotNull()

        assertThat(responseEntity.body.amount).is(amount * -1)
    }

    @Test
    void testAddTransactionPagamento() {

        BigDecimal amount = 50

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.pagamento, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        assertThat(responseEntity.body.id).isNotNull()

        assertThat(responseEntity.body.eventDate).isNotNull()

        assertThat(responseEntity.body.amount).is(amount)
    }

    @Test
    void testAddTransactionEmptyAccount() {

        BigDecimal amount = 50

        Transaction transaction = new Transaction(operationType: operationTypes.pagamento, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)

        assertThat(responseEntity.body).isNotNull()
    }

    @Test
    void testAddTransactionEmptyOperationType() {

        BigDecimal amount = 50

        Transaction transaction = new Transaction(account: account, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)

        assertThat(responseEntity.body).isNotNull()

    }

}
