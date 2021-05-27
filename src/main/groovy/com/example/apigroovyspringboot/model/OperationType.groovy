package com.example.apigroovyspringboot.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class OperationType extends AbstractEntity{

    @Column(length = 30, nullable = false)
    String description

    @Column(length = 1, nullable = false)
    int value

    boolean isWithdraw(){

        this.value < 0
    }
}
