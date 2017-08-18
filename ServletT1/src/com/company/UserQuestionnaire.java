package com.company;

import com.company.Answer;

import java.util.ArrayList;

public class UserQuestionnaire {
    private String name;
    private String surname;
    private int age;
    private ArrayList<Answer> answer;

    public UserQuestionnaire(String name, String surname, int age, ArrayList<Answer> answer) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.answer = answer;
    }

    public ArrayList<Answer> getAnswers() {
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder answers = new StringBuilder("");
        getAnswers().stream()
                .forEach(answers::append);
        return "<BR>" + answers.toString();
    }
}
