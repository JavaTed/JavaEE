package com.company;

import java.io.File;

public class Answer {
    private final int question;
    private final int answer;

    public Answer(String user,int question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }


    @Override
    public String toString() {
        return
                "Question=" + question +
                ", choice=" + answer +
                "<BR>";
    }

}
