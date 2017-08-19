package com.company;

import javax.servlet.http.HttpServletRequest;

public class RequestAttributeService {

    public static void setAttributes(HttpServletRequest request, String user) {
        VoteService vs = VoteService.getInstance();
        UserQuestionnaire.UserInfo ui = vs.getUserInfo(user);
        request.setAttribute("name",ui.getName());
        request.setAttribute("surname",ui.getSurname());
        request.setAttribute("age",ui.getAge());
    }
}