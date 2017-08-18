package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class VoteService {
    private static VoteService ourInstance = new VoteService();

    Map<String,UserQuestionnaire> userAnswers = new HashMap<>();
    ArrayList<Answer> allAnswers = new ArrayList<Answer>();

    private VoteService() {

    }

    public static VoteService getInstance() {
        return ourInstance;
    }

    public void addUserAnswers(String user, String name, String surname, int age, ArrayList<Answer> answer){
        userAnswers.put(user,new UserQuestionnaire(name,surname,age,answer));
        answer.stream().forEach(allAnswers::add);
    }

    public boolean isUserVoted(String user){
        return userAnswers.containsKey(user);
    }

    public StringBuilder getTotalStat(){
        StringBuilder sb = new StringBuilder("");
        allAnswers.stream()
                .collect(groupingBy(t->t.getQuestion()+":"+t.getAnswer(),summingInt(t->1)))
                .entrySet()
                .stream()
                .sorted((t1,t2)->t1.getKey().compareTo(t2.getKey()))
                .forEach(t->sb.append("<BR>Question "+t.getKey().replace(":",", choice ")+" =  "+t.getValue()+" time(s)"));
        return sb;
    }

    public StringBuilder getUserStat(String user){
        return new StringBuilder(userAnswers.get(user).toString());
    }
}
