package app.repository;

import app.entity.BasicEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.SerializationFeature.CLOSE_CLOSEABLE;

public abstract class JsonFileRepository {

    protected final File jsonFile;
    protected final ObjectMapper mapper = new ObjectMapper();

    protected JsonFileRepository(File jsonFile) {
        this.jsonFile = jsonFile;
        mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(CLOSE_CLOSEABLE, true);
    }

    @SneakyThrows
    public <T extends BasicEntity> List<T> getAll() {
        return readJson();
    }

    @SneakyThrows
    public <T extends BasicEntity> Optional<T> save(final T toSave) {
        final List<T> list = readJson();
        final Long id = generateId(list);
        toSave.setId(id);
        list.add(toSave);

        writeJson(list);

        return Optional.of(toSave);
    }

    @SneakyThrows
    public <T extends BasicEntity> Optional<T> getById(final Long id) {
        final List<T> list = readJson();
        final List<T> foundElement = list.stream().filter(e -> id.equals(e.getId()))
                .collect(Collectors.toList());

        if (foundElement.isEmpty()) {
            System.out.printf("Element with id: %s not found!%n", id);
            return Optional.empty();
        } else if (foundElement.size() > 1) {
            System.out.printf("Element with id: %s found multiple times!%n", id);
            return Optional.empty();
        }

        return Optional.ofNullable(foundElement.get(0));
    }

    @SneakyThrows
    public <T extends BasicEntity> Optional<T> update(final T element) {
        final List<T> list = readJson();
        final int index = list.indexOf(element);

        list.set(index, element);

        writeJson(list);

        return Optional.of(element);
    }

    @SneakyThrows
    public <T extends BasicEntity> void deleteById(final Long id) {
        final List<T> list = readJson();
        final List<T> foundElements = list.stream().filter(e -> id.equals(e.getId()))
                .collect(Collectors.toList());

        if (foundElements.isEmpty()) {
            System.out.printf("Element with id: %s not found!%n", id);
        } else if (foundElements.size() > 1) {
            System.out.printf("Element with id: %s found multiple times!%n", id);
        }

        final var toDelete = foundElements.get(0);

        list.remove(toDelete);

        writeJson(list);
    }

    protected abstract <T extends BasicEntity> List<T> readJson() throws IOException;

    public <T extends BasicEntity> void writeJson(List<T> list) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, list);
    }

    /**
     * Checks if file not exist and not empty
     *
     * @return {@code false} if file exist or not empty.
     * {@code true} if file don't exist and is empty.
     */
    protected boolean checkIfFileNotExistsAndEmpty() {
        return !jsonFile.exists() && jsonFile.length() == 0;
    }

    private <T extends BasicEntity> Long generateId(final List<T> list) {
        if (list.isEmpty()) {
            return 1L;
        } else {
            return list.get(list.size() - 1).getId() + 1L;
        }
    }
}
