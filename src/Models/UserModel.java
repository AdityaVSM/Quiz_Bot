package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap<String,ArrayList<Long>> getData(){
        HashMap<String, ArrayList<Long>> existingUsers = new HashMap<>();
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("users.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject existingObject = (JSONObject) obj;
        JSONArray existingUserArray = (JSONArray) existingObject.get("users");
        for(int i=0; i<existingUserArray.size(); i++){
            JSONObject userInList = (JSONObject) existingUserArray.get(i);
            ArrayList<Long> scoreAndMatchesPlayed = new ArrayList<>();
            scoreAndMatchesPlayed.add((Long) userInList.get("score"));
            scoreAndMatchesPlayed.add((Long) userInList.get("Matches played"));
            existingUsers.put(userInList.get("name").toString(), scoreAndMatchesPlayed);
        }
        return existingUsers;
    }

    public ArrayList<Long> getScoreAndMatchesPlayed(){
        return getData().get(this.name);
    }

    public void storeData() throws IOException, ParseException {

    }
}
