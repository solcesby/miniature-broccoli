package app.repository.impl;

import app.entity.user.User;
import app.repository.JsonFileRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository extends JsonFileRepository {

    public UserRepository() {
        super(new File("src/main/resources/db/users.json"));
    }

    @SneakyThrows
    public Optional<User> getByEmail(final String email) {
        final List<User> userList = readJson();
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
    protected List<User> readJson() throws IOException {
        if (checkIfFileNotExistsAndEmpty()) {
            jsonFile.createNewFile();
            return new ArrayList<>();
        }
        return mapper.readValue(jsonFile, new TypeReference<>() {
        });
    }
}
