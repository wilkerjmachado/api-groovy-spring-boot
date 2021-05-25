package com.example.apigroovyspringboot.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate

import javax.persistence.*

@Entity
class Transaction extends AbstractEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    Account account

    @ManyToOne(optional = false)
    @JoinColumn(name = "operation_type_id")
    @JsonProperty("operation_type")
    OperationType operationType

    @Column(nullable = false)
    BigDecimal amount

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    Date eventDate

    @PrePersist
    void onPrePersist() {

        this.eventDate = new Date()
    }
}
