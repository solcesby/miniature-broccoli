package app.repository.impl;

import app.entity.user.User;
import app.repository.AbstractJsonFileRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserJsonFileRepository<T> extends AbstractJsonFileRepository<User> {
    public UserJsonFileRepository(File jsonFile) {
        super(jsonFile);
    }

    @Override
    public List<User> readJson() throws IOException {
        if (checkIfFileNotExistsAndEmpty()) {
            jsonFile.createNewFile();
            return new ArrayList<>();
        }
        return mapper.readValue(jsonFile, new TypeReference<>() {
        });
    }
}
