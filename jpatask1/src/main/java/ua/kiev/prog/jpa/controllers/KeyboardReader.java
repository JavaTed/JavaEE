package ua.kiev.prog.jpa.controllers;

import java.io.BufferedReader;
import java.io.IOException;

class IncorrectTextData extends RuntimeException{
    public IncorrectTextData(String msg){
        super(msg);
    }
}

public class KeyboardReader {

    static public String readStrInfo(BufferedReader d, String hint, String pattern){
        return readStrInfo(d,hint,pattern,false);
    }

    static public Integer readIntInfo(BufferedReader d, String hint, boolean canBeEmpty){
        String str = readStrInfo(d,hint,"[0-9]+",canBeEmpty);
        try {
            int res = Integer.parseInt(str);
            return res;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Please reenter");
            return readIntInfo(d,hint,canBeEmpty);
        }
    }

    static public Double readDoubleInfo(BufferedReader d, String hint, boolean canBeEmpty){
        String str = readStrInfo(d,hint,"[0-9.]+",canBeEmpty);
        try {
            Double res = Double.parseDouble(str);
            return res;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Please reenter");
            return readDoubleInfo(d,hint,canBeEmpty);
        }
    }

    static public String readStrInfo(BufferedReader d, String hint, String pattern, boolean canBeEmpty)  {
        try {
            System.out.println(hint+":");
            String valStr = d.readLine();
            if (valStr.length()==0 && !canBeEmpty)
                throw new IncorrectTextData("Empty "+hint);
            else if (!valStr.matches(pattern)&& pattern.length()>0 && valStr.length()>0)
                throw new IncorrectTextData("Incorrect "+hint);
            return valStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (IncorrectTextData e) {
            e.printStackTrace();
            System.out.println("Please reenter");
            return readStrInfo(d,hint,pattern,canBeEmpty);
        }
    }
}
