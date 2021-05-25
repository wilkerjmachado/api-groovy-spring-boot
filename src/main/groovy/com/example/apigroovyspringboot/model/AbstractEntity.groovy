package com.example.apigroovyspringboot.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        AbstractEntity that = (AbstractEntity) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
