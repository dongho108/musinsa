package com.musinsa.dao;

import java.util.List;

public interface Store<T> {

    T save(T t);

    T findById(Long id);

    List<T> findAll();
}
