package Models;

import java.util.ArrayList;

public class Questions{
    ArrayList<String> questions;
    ArrayList<String> options;
    String correctAns;
    int correctAnsIndex;

    public Questions(ArrayList<String> questions, ArrayList<String> options, String correctAns, int correctAnsIndex) {
        this.questions = questions;
        this.options = options;
        this.correctAns = correctAns;
        this.correctAnsIndex = correctAnsIndex;
    }

    public ArrayList<String> getQuestions() {
        return questions;
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

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public void setCorrectAnsIndex(int correctAnsIndex) {
        this.correctAnsIndex = correctAnsIndex;
    }
}