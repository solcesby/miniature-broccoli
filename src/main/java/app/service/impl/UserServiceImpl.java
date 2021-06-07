package app.service.impl;

import app.entity.user.User;
import app.repository.UserRepository;
import app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAll() {
        return null;
    }

    @Override
    @Transactional
    public User save(User userToSave) {
        return null;
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return null;
    }

    @Override
    @Transactional
    public User update(User user) {
        return null;
    }

    @Override
    @Transactional
    public User deleteById(Long id) {
        return null;
    }
}
