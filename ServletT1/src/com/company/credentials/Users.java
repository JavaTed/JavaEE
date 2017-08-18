package com.company.credentials;

import java.util.Arrays;

class Users {
     private User[] user;

     public boolean isExists(String login, String password){
          return Arrays.stream(user)
                  .filter(t->t.getLogin().equals(login)&&t.getPassword().equals(password))
                  .findFirst()
                  .map(t->true)
                  .orElse(false);

     }
}
