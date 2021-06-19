import Models.Questions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class GenerateQuestions {
    ArrayList<String> questionsArrayList = new ArrayList<>();
    HashMap<String, String> questionAndAnsHashMap = new HashMap<>();
    ArrayList<Object> options = new ArrayList<>();
    String score;
    String attempts;
    int answerPos;
    String api;

    public GenerateQuestions(String api) {
        this.api = api;
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

        //populating question and options array
        for (int i = 0; i < resArr.size(); i++) {
            JSONObject eachResultObject = (JSONObject) resArr.get(i); //each result object
            String question = eachResultObject.get("question").toString(); //question from each result object
            String correctAns = eachResultObject.get("correct_answer").toString();  //correct answer from each result object
            questionAndAnsHashMap.put(question, correctAns);

            JSONArray incorrectOptionsArray = (JSONArray) eachResultObject.get("incorrect_answers"); //getting incorrect options
            for (int j = 0; j < incorrectOptionsArray.size(); j++) {
                options.add(incorrectOptionsArray.get(j));
            }
        }
        //System.out.println(questionHashMap);
        questionsArrayList.addAll(questionAndAnsHashMap.keySet());  //adds questions from questionAndAnsHashMap to questionsArrayList
        //System.out.println(questionsArrayList);

    }

    public static void main(String[] args) throws IOException, ParseException {
        GenerateQuestions q1 = new GenerateQuestions("https://opentdb.com/api.php?amount=10&category=9&difficulty=easy&type=multiple");
        q1.generateQuestionsAndOptions();
    }
}

