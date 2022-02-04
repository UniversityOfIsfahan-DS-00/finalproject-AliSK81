public class Suggestion implements Comparable<Suggestion> {
    User user;
    int score;

    public Suggestion(User user, int score) {
        this.user = user;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Suggest{" +
                "score=" + score +
                ", user=" + user +
                '}';
    }

    @Override
    public int compareTo(Suggestion o) {
        return o.score - this.score;
    }
}
