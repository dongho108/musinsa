package com.musinsa.dao;

import java.util.List;
import java.util.Optional;

public interface Store<T> {

    T save(T t);

    Optional<T> findById(Long id);

    List<T> findAll();

    void clear();
}
