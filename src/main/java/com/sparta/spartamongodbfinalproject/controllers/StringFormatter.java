package com.sparta.spartamongodbfinalproject.controllers;

public class StringFormatter {
    public static String capitalizeWord(String str){
        String[] words =str.split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for(String word : words){
            String first = word.substring(0,1);
            String afterFirst = word.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterFirst).append(" ");
        }
        return capitalizeWord.toString().trim();
    }
}