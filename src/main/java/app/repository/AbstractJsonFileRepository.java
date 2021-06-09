package app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.SerializationFeature.CLOSE_CLOSEABLE;

public abstract class AbstractJsonFileRepository<T> {

    protected final File jsonFile;
    protected final ObjectMapper mapper = new ObjectMapper();

    protected AbstractJsonFileRepository(File jsonFile) {
        this.jsonFile = jsonFile;
        mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(CLOSE_CLOSEABLE, true);
    }

    protected abstract List<T> readJson() throws IOException;

    public void writeJson(List<T> list) throws IOException {
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
}
