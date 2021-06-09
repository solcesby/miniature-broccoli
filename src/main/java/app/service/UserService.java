package app.service;

import app.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    User save(User userToSave);

    User getById(Long id);

    Optional<User> getByEmail(String email);

    User update(User user);

    User deleteById(Long id);

}
