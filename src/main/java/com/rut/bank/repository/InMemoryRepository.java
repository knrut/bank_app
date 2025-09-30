package com.rut.bank.repository;

import com.rut.bank.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    private final List<E> db = new ArrayList<>();

    @Override
    public List<E> findALL() {
        return db;
    }

    @Override
    public Optional<E> findById(ID id) {
        return db.stream()
                .filter(e -> id.equals(e.getID()))
                .findFirst();
    }

    @Override
    public E save(E e) {
        db.add(e);
        return e;
    }

    @Override
    public E update(E e) {
        delete(e);
        db.add(e);
        return e;
    }

    @Override
    public void delete(E e) {
        db.remove(e);
    }
}
