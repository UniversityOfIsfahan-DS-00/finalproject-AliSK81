import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static DataSet dataSet;
    static LinkedIn linked;

    public static void main(String[] args) throws IOException {

        dataSet = new DataSet("users2.json");

        List<User> users = dataSet.readFile();

        linked = new LinkedIn(users);

        mainMenu();
    }

    static void mainMenu() throws IOException {

        System.out.println("\nMain Menu:");
        System.out.println("1) Register");
        System.out.println("2) Login");
        System.out.println("3) Save & Exit");

        switch (sc.nextInt()) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> {
                dataSet.setPath("users3.json");
                dataSet.saveFile(linked.getUsers());
                return;
            }
        }
        mainMenu();
    }

    static void register() {
        System.out.print("Enter an Id:");
        String userID = sc.next();

        if (linked.idExist(userID)) {
            System.out.println("Id Exist!");
            return;
        }
        System.out.println("Enter Info:");
        System.out.println("name, dateOfBirth, universityLocation, field, workplace");

        User user = new User(userID, sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), null);

        System.out.println("Enter Specialties Separated by Space:");
        user.setSpecialties(
                new HashSet<>(Arrays.asList(sc.useDelimiter("\n").next().split(" ")))
        );

        linked.addUser(user);
        System.out.println("User Added.");
    }

    static void login() {
        System.out.print("Enter Id:");
        String userID = sc.next();
        User user = linked.getUserById(userID);

        if (user == null) {
            System.out.println("Wrong Id!");
            return;
        }
        userMenu(user);
    }

    static void userMenu(User user) {

        System.out.printf("\nUser{id='%s', name='%s'}\n", user.getId(), user.getName());
        System.out.println("1) Top 20 Suggestions");
        System.out.println("2) Show Connections");
        System.out.println("3) Connect");
        System.out.println("4) Disconnect");
        System.out.println("5) Edit Suggestion Rates");
        System.out.println("6) Logout");

        int in = sc.nextInt();

        switch (in) {
            case 1 -> linked.showSuggestions(user, 5, 20);

            case 2 -> linked.showUserConnections(user);

            case 3, 4 -> {
                System.out.print("Enter an ID:");
                String connectionID = sc.next();

                if (connectionID.equals(user.getId())) {
                    System.out.println("It's You!");
                    break;
                }

                User connection = linked.getUserById(connectionID);

                if (connection == null) {
                    System.out.println("Wrong Id!");
                    break;
                }

                if (in == 3) linked.connect(user, connection);
                else linked.disconnect(user, connection);

                System.out.println("Done.");
            }

            case 5 -> {
                System.out.println("Current Rates:");
                System.out.println(user.getRates());

                System.out.println("\nEdit Rates:");
                System.out.println("1) Reset Defaults");
                System.out.println("2) Customize Rates");
                System.out.println("3) Back");

                in = sc.nextInt();

                if (in != 2) {
                    if (in == 1)
                        user.setRates(new Rates());
                    break;
                }

                System.out.println("\nEnter new Rates:");
                System.out.println("dateOfBirth, universityLocation, field, workplace, specialties, degree");
                user.setRates(
                        new Rates(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt())
                );
                System.out.println("Done.");
            }
            case 6 -> {
                return;
            }
        }
        userMenu(user);
    }
}
