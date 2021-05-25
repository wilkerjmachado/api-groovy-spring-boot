package com.example.apigroovyspringboot.api

import com.example.apigroovyspringboot.model.AbstractEntity
import com.example.apigroovyspringboot.service.BaseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

abstract class BaseApi<E extends AbstractEntity, S extends BaseService<E>> {

    protected abstract S getService()

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "")
    ResponseEntity<E> save(@Valid @RequestBody E entidade) {

        ResponseEntity.ok(this.getService().save(entidade)) as ResponseEntity<E>
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity<E> findById(@PathVariable("id") Long id) {

        ResponseEntity.ok(this.getService().findById(id)) as ResponseEntity<E>

    }
}
