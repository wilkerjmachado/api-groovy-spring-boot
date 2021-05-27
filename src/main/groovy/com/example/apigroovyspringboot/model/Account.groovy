package com.example.apigroovyspringboot.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Account extends AbstractEntity{

    @Column(length = 11, nullable = false, unique = true)
    @JsonProperty("document_number")
    @NotBlank(message = "Document number is mandatory")
    String documentNumber

    @JsonProperty("available_credit_limit")
    BigDecimal availableCreditLimit

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "account")
    List<Transaction> transactions

}
