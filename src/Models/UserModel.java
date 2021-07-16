package Models;

import java.io.*;

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
class ObjectFileProcessing extends UserModel{
    public ObjectFileProcessing(String name,int score) {
        super(name);
        this.name = name;
        this.score = score;
    }

    public void storeObject(UserModel user){
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("UsersFile.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(user);
            objOps.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayObjects(){
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("EmpFile.txt");
            objIs = new ObjectInputStream(fileIs);
            UserModel user = (UserModel) objIs.readObject();
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
