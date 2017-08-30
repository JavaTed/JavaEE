package ua.kiev.prog.jpa.controllers;


import ua.kiev.prog.jpa.models.RestaurantMenuItem;

import java.io.BufferedReader;


class ValueOutOfRange extends RuntimeException{
    public ValueOutOfRange(String msg){
        super(msg);
    }
}

public class RestaurantMenuItemInputFactory{
    private static final String namePattern = "^[A-ZА-Я]{1,}[a-zA-Zа-я-]{1,}$";
    private static RestaurantMenuItem buffer;

    public static String getNamePattern() {
        return namePattern;
    }

    static public RestaurantMenuItem getEnteredMenuItem() {
        return buffer;
    }

    static public boolean isMenuItemEntered(BufferedReader d){
       buffer = null;

        String dishName;
        double price;
        double weight;
        double discountPercent;

        dishName = KeyboardReader.readStrInfo(d,"Enter Item Name (Just press ENTER if you don't want enter new menu item)","",true);
        if (dishName != null) {
            price = KeyboardReader.readDoubleInfo(d,"Price",false);
            weight = KeyboardReader.readDoubleInfo(d,"Weight",false);
            discountPercent = KeyboardReader.readDoubleInfo(d,"Discount Percent",false);

            buffer = new RestaurantMenuItem(dishName,price,weight,discountPercent);
            return true;
        }
        buffer = null;
        return false;
    }
}
