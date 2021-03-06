package Work;

import Models.QuestionsModel;

import Models.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import swing.Questions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class GenerateQuestions {
    HashMap<String, String> questionAndAnsHashMap = new HashMap<>();
    HashMap<String,ArrayList<String>> questionAndOptionsHashMap = new HashMap<>();
    ArrayList<QuestionsModel> questionObjects= new ArrayList<>();
    String api;
    String name;
    ArrayList<Long> userData;
    UserModel user;

    public GenerateQuestions(String api,String name, ArrayList<Long> userData, UserModel user) {
        this.api = api;
        this.name = name;
        this.userData = userData;
        this.user = user;
    }

    public ArrayList<String> randomiseOptions(String question, String correctOption,ArrayList<String> tempOptionsArr){
        ArrayList<String> temp = new ArrayList<>(4);
        Random rand = new Random();

        int answerPos = rand.nextInt(4);

        int index = 0;
        for(int i=0 ;i<4; i++){
            if(i == answerPos){
                temp.add(correctOption);
            }else{
                temp.add(tempOptionsArr.get(index));
                index++;
            }
        }
        questionObjects.add(new QuestionsModel(question,temp,correctOption,answerPos));
        return temp;
    }

    public void generateQuestionsAndOptions() throws IOException, ParseException {
        //Establishing connection
        URL url = new URL(api);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        int responseCode = connection.getResponseCode();

        //getting JSON data
        String data = "";
        if (responseCode != 200) {
            throw new RuntimeException("Http response code : " + responseCode);
        } else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                data += sc.nextLine();
            }
            sc.close();
        }

        //parsing JSON data
        JSONObject object = (JSONObject) new JSONParser().parse(data);
        JSONArray resArr = (JSONArray) object.get("results"); //JSON array of results

        //populating question and options HashMaps
        for (int i = 0; i < resArr.size(); i++) {
            JSONObject eachResultObject = (JSONObject) resArr.get(i); //each result object
            String question = eachResultObject.get("question").toString(); //question from each result object
            String correctAns = eachResultObject.get("correct_answer").toString();  //correct answer from each result object

            questionAndAnsHashMap.put(question, correctAns);

            JSONArray incorrectOptionsArray = (JSONArray) eachResultObject.get("incorrect_answers"); //getting incorrect options json array
            ArrayList<String> tempOptionsArr = new ArrayList<>();
            for (int j = 0; j < incorrectOptionsArray.size(); j++) {
                tempOptionsArr.add((String) incorrectOptionsArray.get(j)); //adds incorrect answers to tempOptionsArr (size=3 options)
            }

            ArrayList<String> randomizedOptionsArray = new ArrayList<>();
            randomizedOptionsArray = randomiseOptions(question, correctAns,tempOptionsArr);   //randomizes options with correct ans (size = 4 options)
            questionAndOptionsHashMap.put(question,randomizedOptionsArray);     //put ready option arrayList to  questionAndOptionsHashMap

        }
        Questions questionsPage = new Questions(questionObjects,name,userData, user);
    }

}

