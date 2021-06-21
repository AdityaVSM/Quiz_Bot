package Models;

import java.util.ArrayList;

public class Questions{
    String question;
    ArrayList<String> options;
    String correctAns;
    int correctAnsIndex;

    public Questions(String question, ArrayList<String> options, String correctAns, int correctAnsIndex) {
        this.question = question;
        this.options = options;
        this.correctAns = correctAns;
        this.correctAnsIndex = correctAnsIndex;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", options=" + options +
                ", correctAns='" + correctAns + '\'' +
                ", correctAnsIndex=" + correctAnsIndex + '\n'+
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public int getCorrectAnsIndex() {
        return correctAnsIndex;
    }

}