package com.company;

import com.company.Answer;

import java.util.ArrayList;

public class UserQuestionnaire {
    private String name;
    private String surname;
    private int age;
    private ArrayList<Answer> answer;
    UserInfo ui = new UserInfo();

    public UserQuestionnaire(String name, String surname, int age, ArrayList<Answer> answer) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.answer = answer;
    }

    public class UserInfo{
        private UserInfo() {
        }

        public String getName() {
            return UserQuestionnaire.this.name;
        }

        public String getSurname() {
            return UserQuestionnaire.this.surname;
        }

        public int getAge() {
            return UserQuestionnaire.this.age;
        }
    }

    public UserInfo getUserInfo() {
        return ui;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
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
