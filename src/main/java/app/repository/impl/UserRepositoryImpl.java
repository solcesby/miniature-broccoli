package app.repository.impl;

import app.entity.user.User;
import app.repository.UserRepository;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private static final File USERS_FILE = new File("src/main/resources/db/users.json");
    private final UserJsonFileRepository<User> jsonFileRepository = new UserJsonFileRepository<>(USERS_FILE);

    @Override
    @SneakyThrows
    public List<User> getAll() {
        return jsonFileRepository.readJson();
    }

    @Override
    @SneakyThrows
    public Optional<User> save(final User userToSave) {
        final List<User> userList = jsonFileRepository.readJson();
        final Long id = generateId(userList);

        userToSave.setId(id);
        userList.add(userToSave);

        jsonFileRepository.writeJson(userList);

        return Optional.of(userToSave);
    }

    @Override
    @SneakyThrows
    public Optional<User> getById(final Long id) {
        final List<User> userList = jsonFileRepository.readJson();
        final List<User> foundUsers = userList.stream().filter(u -> id.equals(u.getId()))
                .collect(Collectors.toList());

        if (foundUsers.isEmpty()) {
            System.out.printf("User with id: %s not found!%n", id);
            return Optional.empty();
        } else if (foundUsers.size() > 1) {
            System.out.printf("User with id: %s found multiple times!%n", id);
            return Optional.empty();
        }

        return Optional.ofNullable(foundUsers.get(0));
    }

    @Override
    @SneakyThrows
    public Optional<User> getByEmail(final String email) {
        final List<User> userList = jsonFileRepository.readJson();
        userList.forEach(System.out::println);
        final List<User> foundUsers = userList.stream().filter(u -> email.equals(u.getEmail()))
                .collect(Collectors.toList());

        if (foundUsers.isEmpty()) {
            System.out.printf("User with email: %s not found!%n", email);
            return Optional.empty();
        } else if (foundUsers.size() > 1) {
            System.out.printf("User with email: %s found multiple times!%n", email);
            return Optional.empty();
        }

        return Optional.ofNullable(foundUsers.get(0));
    }

    @Override
    @SneakyThrows
    public Optional<User> update(final User user) {
        final List<User> userList = jsonFileRepository.readJson();
        final var updatedUser = new User(user);
        final int index = userList.indexOf(updatedUser);

        userList.set(index, updatedUser);

        jsonFileRepository.writeJson(userList);

        return Optional.of(updatedUser);
    }

    @Override
    @SneakyThrows
    public Optional<User> deleteById(final Long id) {
        final List<User> userList = jsonFileRepository.readJson();
        final List<User> foundUsers = userList.stream().filter(u -> id.equals(u.getId()))
                .collect(Collectors.toList());

        if (foundUsers.isEmpty()) {
            System.out.printf("User with id: %s not found!%n", id);
            return Optional.empty();
        } else if (foundUsers.size() > 1) {
            System.out.printf("User with id: %s found multiple times!%n", id);
            return Optional.empty();
        }

        final var userToDelete = foundUsers.get(0);

        userList.remove(userToDelete);

        jsonFileRepository.writeJson(userList);

        return Optional.ofNullable(userToDelete);
    }

    private Long generateId(List<User> userList) {
        if (userList.isEmpty()) {
            return 1L;
        } else {
            return userList.get(userList.size() - 1).getId() + 1L;
        }
    }
}
