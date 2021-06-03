package app.service;

import app.entity.user.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User save(User userToSave);

    User getById(Long id);

    User getByEmail(String email);

    User update(User user);

    User deleteById(Long id);

}
