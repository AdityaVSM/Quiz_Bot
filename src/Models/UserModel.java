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

    public HashMap<String,ArrayList<Long>> getData() throws IOException {
        HashMap<String, ArrayList<Long>> existingUsers = new HashMap<>();
        JSONParser parser = new JSONParser();
        Object obj = null;

        BufferedReader br = new BufferedReader(new FileReader("users.json"));
        if (br.readLine() != null) {
            try {
                obj = parser.parse(new FileReader("users.json"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject existingObject = (JSONObject) obj;
            JSONArray existingUserArray = (JSONArray) existingObject.get("users");
            for (Object o : existingUserArray) {
                JSONObject userInList = (JSONObject) o;
                ArrayList<Long> scoreAndMatchesPlayed = new ArrayList<>();
                scoreAndMatchesPlayed.add((Long) userInList.get("score"));
                scoreAndMatchesPlayed.add((Long) userInList.get("Matches played"));
                existingUsers.put(userInList.get("name").toString(), scoreAndMatchesPlayed);
            }
            return existingUsers;
        }else{
            return null;
        }
    }

    public ArrayList<Long> getScoreAndMatchesPlayed() throws IOException {
        if(getData()!=null && getData().containsKey(this.name) ){
            return getData().get(this.name);
        }
        ArrayList<Long> temp = new ArrayList<>();
        temp.add(0L);
        temp.add(0L);
        this.setMatchesPlayed(0);
        this.setScore(0);
        return temp;
    }

    public void storeData(UserModel user) throws IOException {
        HashMap<String,ArrayList<Long>> existingUsers = new HashMap<>();
        if(getData() == null){
            existingUsers.put(user.name,user.getScoreAndMatchesPlayed());
        }else{
            existingUsers = getData();
            existingUsers.put(user.name,user.getScoreAndMatchesPlayed());
        }
        JSONObject users = new JSONObject();
        JSONArray userArray = new JSONArray();
        for(HashMap.Entry<String, ArrayList<Long>> eachUserHashMapValue : existingUsers.entrySet()){
            JSONObject eachUserJSONObject = new JSONObject();

            String name = eachUserHashMapValue.getKey();
            ArrayList<Long> data = eachUserHashMapValue.getValue();

            eachUserJSONObject.put("name",name);
            eachUserJSONObject.put("score", data.get(0));
            eachUserJSONObject.put("Matches played", data.get(1));
            userArray.add(eachUserJSONObject);
        }
        users.put("users",userArray);


        try(FileWriter fileWriter = new FileWriter(new File("users.json"))){
            fileWriter.write(users.toJSONString());
        }catch (Exception e){
            e.getMessage();
        }


    }
}
