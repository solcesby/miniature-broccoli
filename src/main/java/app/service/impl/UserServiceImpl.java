package app.service.impl;

import app.entity.user.User;
import app.exception.UserNotFoundException;
import app.repository.UserRepository;
import app.repository.impl.UserRepositoryImpl;
import app.service.UserService;
import lombok.SneakyThrows;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        return userRepository.getAll().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User userToSave) {
        return userRepository.save(userToSave).get();
    }

    @Override
    @SneakyThrows
    public User getById(Long id) {
        return userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @SneakyThrows
    public User getByEmail(String email) {
        return userRepository.getByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User update(User user) {
        return userRepository.update(user).get();
    }

    @Override
    @SneakyThrows
    public User deleteById(Long id) {
        return userRepository.deleteById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
