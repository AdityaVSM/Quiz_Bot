package Models;

public class UserModel {
    String name;
    int matchesPlayed;
    int score;

    public UserModel(String name) {
        this.name = name;
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

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", score=" + score +
                '}';
    }
}
