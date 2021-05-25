package com.example.apigroovyspringboot.repository

import com.example.apigroovyspringboot.model.OperationType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationTypeRepository extends JpaRepository<OperationType, Long>{

}