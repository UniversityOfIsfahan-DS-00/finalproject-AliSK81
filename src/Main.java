import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        DataSet dataSet = new DataSet("users.json");
        List<User> users = dataSet.readFile();

        LinkedIn linked = new LinkedIn(users);

        User user = linked.getUserById("3");

        if (user != null) {
            linked.showSuggestions(user, 5, 20);

        } else {
            System.out.println("Wrong Id");
        }

//        dataSet.setPath("users3.json");
//        dataSet.saveFile(linked.getUsers());
    }
}
