package org.example.dao;

import java.util.List;
import java.util.UUID;

public interface BaseDAO<T> {
    List<T> getAll();

    T update(T t);

    T create(T t);

    T getById(UUID uuid);

    void delete(UUID uuid);
}
