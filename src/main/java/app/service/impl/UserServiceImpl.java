package app.service.impl;

import app.entity.user.User;
import app.exception.UserNotFoundException;
import app.repository.impl.UserRepository;
import app.service.UserService;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepository();
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User save(User userToSave) {
        return userRepository.save(userToSave).orElseThrow();
    }

    @Override
    @SneakyThrows
    public User getById(Long id) {
        return (User) userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @SneakyThrows
    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user).orElseThrow();
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
