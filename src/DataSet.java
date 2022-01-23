import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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

    private int rnd(int n, int s) {
        return (int) (Math.random() * n + s);
    }

    public Collection<User> randomDataSet(int n) {

        HashMap<String, User> users = new HashMap<>();

        List<String> fnames = readLines("inputdata/fnames.txt");
        List<String> lnames = readLines("inputdata/lnames.txt");
        List<String> universityLocation = readLines("inputdata/university.txt");
        List<String> field = readLines("inputdata/field.txt");
        List<String> specialties = readLines("inputdata/specialties.txt");
        List<String> workplace = readLines("inputdata/workplace.txt");

        for (int i = 1; i <= n; i++) {

            String id = String.valueOf(i);
            assert false;

            String name = fnames.get(rnd(fnames.size(), 0)) + " " +
                    lnames.get(rnd(lnames.size(), 0));

            String date = rnd(20, 1365) + "/" + rnd(12, 1) + "/" + rnd(31, 1);

            String uni = universityLocation.get(rnd(universityLocation.size(), 0));
            String fie = field.get(rnd(field.size(), 0));
            String wor = workplace.get(rnd(workplace.size(), 0));

            HashSet<String> spe = new HashSet<>();
            for (int j = 0; j < Math.random() * 3 + 1; j++) {
                spe.add(specialties.get(rnd(specialties.size(), 0)));
            }

            HashSet<String> con = new HashSet<>();
            for (int j = 0; j < Math.random() * 2; j++) {
                String cc = String.valueOf(rnd(n - 1, 1));
                if (!cc.equals(String.valueOf(i))) {
                    con.add(cc);
                }
            }
            User user = new User(id, name, date, uni, fie, wor, spe, con);
            users.put(user.getId(), user);
        }

        users.forEach((userID, user) -> {
            user.getConnectionId().forEach(connectionID -> {
                User connection = users.get(connectionID);
                connection.getConnectionId().add(userID);
            });
        });

        return users.values();
    }

    private List<String> readLines(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                return Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}