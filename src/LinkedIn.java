import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinkedIn {
    // map users to their id
    private final Map<String, User> users;

    public LinkedIn(List<User> users) {
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
            int score = user.score(suggest, degree);
            suggestions.add(new Suggestion(suggest, score));
        });

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
    public User getUserById(String id) {
        return users.get(id);
    }

    public Collection<User> getUsers() {
        return users.values();
    }

}
