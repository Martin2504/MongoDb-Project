package com.sparta.spartamongodbfinalproject.logicalOperator;

import java.util.ArrayList;

public class StringToArrayString {
    public static ArrayList<String> convert(String phrase){
        String[] arrString=phrase.split(",");
        ArrayList<String> arrlistString=new ArrayList<>();
        for (String s:arrString){
            arrlistString.add(s);
        }
        return arrlistString;
    }
}
