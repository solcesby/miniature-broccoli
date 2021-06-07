package app.repository;

import app.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<List<User>> getAll();

    Optional<User> save(User userToSave);

    Optional<User> getById(Long id);

    Optional<User> getByEmail(String email);

    Optional<User> update(User user);

    Optional<User> deleteById(Long id);

}
