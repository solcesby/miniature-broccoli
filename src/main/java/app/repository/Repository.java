package app.repository;

import java.util.List;
import java.util.Optional;


public interface Repository<T, ID> {

    List<T> getAll();

    Optional<T> save(T userToSave);

    Optional<T> getById(ID id);

    Optional<T> update(T user);

    void deleteById(ID id);
}
