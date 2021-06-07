package app.repository.impl;

import app.entity.user.User;
import app.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private static final File USERS_FILE = new File("src/main/resources/db/users.json");

    public UserRepositoryImpl() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @Override
    @SneakyThrows
    public Optional<List<User>> getAll() {
        return Optional.ofNullable(readJson());
    }

    @Override
    @SneakyThrows
    public Optional<User> save(final User userToSave) {
        if (!USERS_FILE.exists()) {
            var ignored = USERS_FILE.createNewFile();
        } else if (USERS_FILE.length() == 0) {
            userToSave.setId(1L);

            mapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, userToSave);
        } else {
            final List<User> userList = readJson();

            userToSave.setId(userList.get(userList.size() - 1).getId() + 1L);

            userList.add(userToSave);

            mapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, userList);
        }

        return Optional.ofNullable(userToSave);
    }

    @Override
    @SneakyThrows
    public Optional<User> getById(final Long id) {
        final List<User> userList = readJson();
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
        final List<User> userList = readJson();
        final List<User> foundUsers = userList.stream().filter(u -> email.equals(u.getEmail())).collect(Collectors.toList());

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
        final List<User> userList = readJson();
        final User updatedUser = new User(user);
        final int index = userList.indexOf(updatedUser);

        userList.set(index, updatedUser);

        mapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, userList);

        return Optional.ofNullable(updatedUser);
    }

    @Override
    @SneakyThrows
    public Optional<User> deleteById(final Long id) {
        final List<User> userList = readJson();
        final List<User> foundUsers = userList.stream().filter(u -> id.equals(u.getId()))
                .collect(Collectors.toList());

        if (foundUsers.isEmpty()) {
            System.out.printf("User with id: %s not found!%n", id);
            return Optional.empty();
        } else if (foundUsers.size() > 1) {
            System.out.printf("User with id: %s found multiple times!%n", id);
            return Optional.empty();
        }

        final User userToDelete = foundUsers.get(0);

        userList.remove(userToDelete);

        mapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, userList);

        return Optional.ofNullable(userToDelete);
    }

    private void checkIfFileExistsAndNotEmpty() throws IOException {
        if (!USERS_FILE.exists()) {
            var ignored = USERS_FILE.createNewFile();
        } else if (USERS_FILE.length() == 0) {
            throw new IOException();
        }
    }

    private List<User> readJson() throws IOException {
        checkIfFileExistsAndNotEmpty();

        return mapper.readValue(USERS_FILE, new TypeReference<List<User>>() {
        });
    }
}
