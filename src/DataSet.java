import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class DataSet {

    private final ObjectMapper mapper;
    private File file;

    public DataSet(String path) {
        mapper = new ObjectMapper();
        file = new File(path);
    }

    public List<User> readFile() throws IOException {
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    public void saveFile(Collection<User> users) throws IOException {
        mapper.writeValue(file, users);
    }

    public void setPath(String path) {
        file = new File(path);
    }

}