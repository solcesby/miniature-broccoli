package app.service.impl;

import app.entity.user.User;
import app.repository.UserRepository;
import app.repository.impl.UserRepositoryImpl;
import app.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User save(User userToSave) {
        return userRepository.save(userToSave);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}
