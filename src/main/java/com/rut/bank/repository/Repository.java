package com.rut.bank.repository;

import com.rut.bank.model.Entity;

import java.util.List;
import java.util.Optional;

public interface Repository <ID, E extends Entity<ID>> {
    List<E> findALL();
    Optional<E> findById(ID id);
    E save(E e);
    E update(E e);
    void delete(E e);

}
