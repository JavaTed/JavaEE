package controllers;

import models.Flat;

import java.io.BufferedReader;


class ValueOutOfRange extends RuntimeException{
    public ValueOutOfRange(String msg){
        super(msg);
    }
}

public class FlatInputFactory{
    private static final String namePattern = "^[A-ZА-Я]{1,}[a-zA-Zа-я-]{1,}$";
    private static Flat flatBuffer;

    public static String getNamePattern() {
        return namePattern;
    }

    static public Flat getEnteredFlat() {
        return flatBuffer;
    }

    static public boolean isFlatEntered(BufferedReader d){
       flatBuffer = null;

         Integer area;
         byte roomNumber;
         double price;
         String city;
         String street;
         String building;
         String flatNumber;

        area = KeyboardReader.readIntInfo(d,"Area of flat (Just press ENTER if you don't want enter new flat)",true);
        if (area != null) {
            roomNumber = KeyboardReader.readIntInfo(d,"Number of rooms",false).byteValue();
            price = KeyboardReader.readDoubleInfo(d,"Price",false);
            city = KeyboardReader.readStrInfo(d,"City",getNamePattern());
            street = KeyboardReader.readStrInfo(d,"Street","");
            building = KeyboardReader.readStrInfo(d,"Building","");
            flatNumber="0";

            flatBuffer = new Flat(area,roomNumber,price,city, street,building,flatNumber);
            return true;
        }
        flatBuffer = null;
        return false;
    }
}
