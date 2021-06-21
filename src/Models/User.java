package Models;

public class User {
    String name;
    int matchesPlayed;
    int score;

    public User(String name, int matchesPlayed, int score) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", score=" + score +
                '}';
    }
}
