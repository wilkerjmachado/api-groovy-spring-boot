package com.example.apigroovyspringboot

import com.example.apigroovyspringboot.model.Account
import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.repository.AccountRepository
import com.example.apigroovyspringboot.repository.OperationTypeRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
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
class TransactionAccountApiTests {

    @Autowired
    private TestRestTemplate restTemplate

    private Account account

    @Autowired
    private OperationTypeRepository operationTypeRepository

    @Autowired
    private AccountRepository accountRepository

    def operationTypes = [:]

    @BeforeAll
    void init() {

        String documentNumber = "12345678900"

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/accounts", new Account(documentNumber: documentNumber), Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        account = new Account(id: responseEntity.body.id, availableCreditLimit: responseEntity.body.available_credit_limit)

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
    @Order(1)
    void testAddTransactionLimitException() {

        BigDecimal amount = 10000

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.compraAVista, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)

        assertThat(responseEntity.body).isNotNull()

    }

    @Test
    @Order(2)
    void testAddTransactionNegativeOk() {

        BigDecimal amount = 1000

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.compraAVista, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        Account accountTransaction = this.accountRepository.findById(this.account.id).get()

        assertThat(accountTransaction.availableCreditLimit).is(this.account.availableCreditLimit.subtract(amount))

        this.account = accountTransaction

    }

    @Test
    @Order(3)
    void testAddTransactionPositiveOk() {

        BigDecimal amount = 500

        Transaction transaction = new Transaction(account: account, operationType: operationTypes.pagamento, amount: amount)

        ResponseEntity responseEntity = this.restTemplate.postForEntity("/transactions", transaction, Map.class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(responseEntity.body).isNotNull()

        Account accountTransaction = this.accountRepository.findById(this.account.id).get()

        assertThat(accountTransaction.availableCreditLimit).is(this.account.availableCreditLimit.add(amount))

        this.account = accountTransaction

    }

}
