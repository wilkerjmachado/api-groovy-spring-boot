package com.example.apigroovyspringboot.repository


import com.example.apigroovyspringboot.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository extends JpaRepository<Transaction, Long>{

}