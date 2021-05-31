package app.repository.impl;

import app.entity.User;
import app.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private static final File USERS_FILE = new File("src/main/resources/db/users.json");

    public UserRepositoryImpl() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        return readJson();
    }

    @Override
    @SneakyThrows
    public User save(final User userToSave) {
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

        return userToSave;
    }

    @Override
    @SneakyThrows
    public User getById(final Long id) {
        final List<User> userList = readJson();
        final List<User> foundUsers = userList.stream().filter(u -> id.equals(u.getId()))
                .collect(Collectors.toList());

        if (foundUsers.size() == 0) {
            System.out.printf("User with id: %s not found!%n", id);
            return null;
        } else if (foundUsers.size() > 1) {
            System.out.printf("User with id: %s found multiple times!%n", id);
            return null;
        }

        return foundUsers.get(0);
    }

    @Override
    @SneakyThrows
    public User getByEmail(final String email) {
        final List<User> userList = readJson();
        final List<User> foundUsers = userList.stream().filter(u -> email.equals(u.getEmail())).collect(Collectors.toList());

        if (foundUsers.size() == 0) {
            System.out.printf("User with email: %s not found!%n", email);
            return null;
        } else if (foundUsers.size() > 1) {
            System.out.printf("User with email: %s found multiple times!%n", email);
            return null;
        }

        return foundUsers.get(0);
    }

    @Override
    @SneakyThrows
    public User update(final User user) {
        final List<User> userList = readJson();
        final User updatedUser = getById(user.getId());
        final int index = userList.indexOf(updatedUser);

        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setRole(user.getRole());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        userList.set(index, updatedUser);

        mapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, userList);

        return updatedUser;
    }

    @Override
    @SneakyThrows
    public User deleteById(final Long id) {
        final List<User> userList = readJson();
        final User userToDelete = getById(id);

        userList.remove(userToDelete);

        mapper.writerWithDefaultPrettyPrinter().writeValue(USERS_FILE, userList);

        return userToDelete;
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

        final User[] userArray = mapper.readValue(USERS_FILE, User[].class);
        return Arrays.stream(userArray).collect(Collectors.toList());
    }
}
