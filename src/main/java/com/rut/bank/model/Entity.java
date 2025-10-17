package com.rut.bank.model;

import java.io.Serializable;

public abstract class Entity<ID> implements Serializable {
    private static final long serialVersionUID = 1L;

    private ID id;

    public ID getID() {
        return id;
    }

    public void setID(ID id) {
        this.id = id;
    }
}
