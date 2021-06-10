package app.service.impl;

import app.entity.user.User;
import app.exception.UserNotFoundException;
import app.repository.UserRepository;
import app.repository.impl.UserRepositoryImpl;
import app.service.UserService;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
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
        return userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    @SneakyThrows
    public User update(User user) {
        return userRepository.update(user).orElseThrow(() -> new UserNotFoundException(user.getId()));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
