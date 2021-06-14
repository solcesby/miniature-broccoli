package app.service;

import java.util.List;

public interface Service<T, ID> {

    List<T> getAll();

    T save(T productToSave);

    T getById(ID id);

    T update(T product);

    void deleteById(ID id);

}
