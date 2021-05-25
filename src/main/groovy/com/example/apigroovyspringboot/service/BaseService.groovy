package com.example.apigroovyspringboot.service

import com.example.apigroovyspringboot.exception.NotFoundException
import com.example.apigroovyspringboot.exception.ServiceException
import com.example.apigroovyspringboot.model.AbstractEntity
import com.example.apigroovyspringboot.util.ThrowUtil
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

abstract class BaseService<E extends AbstractEntity> {

    @Transactional
    List<E> findAll(){

        this.getRepository().findAll()
    }

    @Transactional
    E findById(Long id){

        this.getRepository().findById(id).orElseThrow(NotFoundException::new)
    }

    @Transactional
    E save(E entity) {

        this.validate(entity)

        try {

            this.getRepository().save(entity)

        } catch (e) {

            throw ServiceException.newInstance("Erro ao salvar registro! ${e.message}")
        }
    }

    @Transactional
    void excluir(Long id) {

        this.validateDelete(this.getRepository().getById(id))

        this.getRepository().deleteById(id)
    }

    void validateDelete(E entity) {

        ThrowUtil.serviceExceptionThrow(!entity.asBoolean(), "Nenhum dado enviado!")
    }

    void validate(E entity) {

        ThrowUtil.serviceExceptionThrow(!entity.asBoolean(), "Nenhum dado enviado!")
    }

    protected abstract JpaRepository<E, Long> getRepository()

}
