package com.dh.clinicadental.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface IService<T> {

    T create(T stu);
    T read(Long id);
    T update(T stu);
    void delete(Long id);
    Collection<T> getAll();

}
