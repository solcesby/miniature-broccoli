package app.service.impl;

import app.entity.user.User;
import app.exception.UserNotFoundException;
import app.repository.UserRepository;
import app.repository.impl.UserRepositoryImpl;
import app.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public List<User> getAll() {
        log.info("getting all users");
        return userRepository.getAll();
    }

    @Override
    public User save(User userToSave) {
        log.info("saving user {}", userToSave);
        return userRepository.save(userToSave).orElseThrow();
    }

    @Override
    @SneakyThrows
    public User getById(Long id) {
        log.info("getting user with id {}", id);
        return userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        log.info("getting user with email {}", email);
        return userRepository.getByEmail(email);
    }

    @Override
    @SneakyThrows
    public User update(User user) {
        log.info("updating user {}", user);
        return userRepository.update(user).orElseThrow(() -> new UserNotFoundException(user.getId()));
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting user with id {}", id);
        userRepository.deleteById(id);
    }
}
