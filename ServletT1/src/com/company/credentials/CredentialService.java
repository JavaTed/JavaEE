package com.company.credentials;

import com.company.JSONService;

import java.io.File;

public class CredentialService {
    private JSONService<Credentials> js = new JSONService<>();
    private Credentials credentials;

    public CredentialService(String fn) {

        File result = new File(fn);
        credentials = js.fromJSON(result, Credentials.class);
        System.out.println("com.company.Answer.CredentialService has been intialized");
    }

    public boolean checkCredentials(String login, String password){
        return credentials.getUsers().isExists(login,password);
    }
}
