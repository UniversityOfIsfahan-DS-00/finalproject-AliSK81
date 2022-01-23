import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinkedIn {
    // map users to their id
    private final Map<String, User> users;

    public LinkedIn(List<User> users) {
        if (users.isEmpty())
            this.users = new HashMap<>();
        else
            this.users = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    private void getDegrees(User user, HashMap<User, Integer> connections, int degree, int n) {

        if (n == 0)
            return;

        for (String id : user.getConnectionId()) {

            User connection = users.get(id);
            Integer deg = connections.get(connection);

            if (deg == null || degree < deg) {
                connections.put(connection, degree);
            }

            getDegrees(connection, connections, degree + 1, n - 1);
        }
    }

    private HashMap<User, Integer> getDegrees(User user, int maxDegree) {
        HashMap<User, Integer> degrees = new HashMap<>();
        getDegrees(user, degrees, 1, maxDegree);
        // remove user himself (with degree 0)
        degrees.remove(user);
        return degrees;
    }

    // to show topN connections sorted by score from degree 1 to maxDegree
    public void showSuggestions(User user, int maxDegree, int topN) {
        HashMap<User, Integer> degrees = getDegrees(user, maxDegree);

        System.out.println("User:");
        System.out.println(user);

        PriorityQueue<Suggestion> suggestions = new PriorityQueue<>();

        degrees.forEach((suggest, degree) -> {
            if (degree != 1) {
                int score = user.score(suggest, degree);

                if (score != 0)
                    suggestions.add(new Suggestion(suggest, score));
            }
        });

        if (suggestions.isEmpty()) {
            System.out.println("\nNo Suggestions!");
            return;
        }

        System.out.println("\nSuggestions:");

        for (int i = 1; i <= topN && !suggestions.isEmpty(); i++) {
            System.out.println((i > 9 ? "[" : "[0") + i + "] " +
                    suggestions.poll()
            );
        }

//        System.out.println("\nDegrees:");
//        degrees.forEach((u, deg) -> System.out.println("Deg[" + deg + "] " + u));
    }

    // get user by id from users map
    public User getUserById(String userID) {
        return users.get(userID);
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void connect(User user1, User user2) {
        user1.getConnectionId().add(user2.getId());
        user2.getConnectionId().add(user1.getId());
    }

    public void disconnect(User user1, User user2) {
        user1.getConnectionId().remove(user2.getId());
        user2.getConnectionId().remove(user1.getId());
    }

    public boolean idExist(String userID) {
        return users.containsKey(userID);
    }

    public void showUserConnections(User user) {

        if (user.getConnectionId().isEmpty()) {
            System.out.println("No Connections!");
            return;
        }

        System.out.println("Connections:");
        user.getConnectionId().forEach(connectionID -> System.out.println(users.get(connectionID)));
    }
}
