package com.rut.bank.repository;

import com.rut.bank.model.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InFileRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    private final File file;
    private List<E> db = new ArrayList<>();

    public InFileRepository(String filePath) {
        this.file = new File(filePath);
        loadFromFile();
    }

    @Override
    public List<E> findALL() {
        return new ArrayList<>(db);
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
        saveToFile();
        return e;
    }

    @Override
    public E update(E e) {
        delete(e);
        db.add(e);
        saveToFile();
        return e;
    }

    @Override
    public void delete(E e) {
        db.removeIf(x -> x.getID().equals(e.getID()));
        saveToFile();
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(db);
        } catch (IOException ex) {
            throw new RuntimeException("Error saving to file", ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            db = (List<E>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            db = new ArrayList<>();
        }
    }
}
