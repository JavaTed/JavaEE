package views;

import controllers.FlatInputFactory;
import controllers.KeyboardReader;
import models.FlatModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    static private BufferedReader d = new BufferedReader(new InputStreamReader(System.in));

    private static String getMenuChoice(){
        return KeyboardReader.readStrInfo(d,getMenu(),"^(0|1|2|3|4)$");
    }
    private static String getMenu(){
        return new StringBuffer()
                .append("Your choice: \n")
                .append(" (0) Exit\n")
                .append(" (1) Add new Flat\n")
                .append(" (2) Delete flat\n")
                .append(" (3) View list\n")
                .append(" (4) View list with filter")
                .toString();
    }
    private static String getFilterChoice(){
        return KeyboardReader.readStrInfo(d, getFilterMenu(),"^(0|1|2|3|4)$",true);
    }
    private static String getFilterMenu(){
        return new StringBuffer()
                .append("    Please select filter: \n")
                .append("     (0 or ENTER) No filter ID\n")
                .append("     (1) models.Flat ID\n")
                .append("     (2) Price less than\n")
                .append("     (3) Price more than\n")
                .append("     (4) City")
                .toString();
    }

    private static String getFilterPar(String filterType){
        String par = "";
        switch (filterType){
            case "0":
                break;
            case "1":
                par = KeyboardReader.readStrInfo(d,"Enter models.Flat ID#","[0-9]+");
                break;
            case "2":
                par = KeyboardReader.readDoubleInfo(d,"Enter high price boundary",false).toString();
                break;
            case "3":
                par = KeyboardReader.readDoubleInfo(d,"Enter low price boundary",false).toString();
                break;
            case "4":
                par = KeyboardReader.readStrInfo(d,"Enter city", FlatInputFactory.getNamePattern());
                break;
        }
        return par;
    }

    public static void main(String[] args) throws SQLException {
       //models.Flat f = new models.Flat(75,(byte)3,56575.5,"Киев","Вернандского","45","2");

        String mi;
        String par="";
        while (!(mi = getMenuChoice()).equals("0")) {
            switch (mi){
                case "1":
                    if (FlatInputFactory.isFlatEntered(d))
                        FlatModel.addFlat(FlatInputFactory.getEnteredFlat());
                    break;
                case "2":
                    Integer fid= KeyboardReader.readIntInfo(d,"Enter flat ID#",true);
                    if (fid != null)
                        FlatModel.deleteFlat(fid);
                    break;
                case "3":
                    FlatModel.viewFlats("0","");
                    break;
                case "4":
                    String ch = getFilterChoice();
                    if (ch != "")
                        par = getFilterPar(ch);
                    FlatModel.viewFlats(ch,par);
                    break;
            }
        }
        FlatModel.closeStatements();
    }
}
