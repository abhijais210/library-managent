package com.example.Librarymanagementsystem.exceptions;
public class CardNotFoundException extends Exception{
    public CardNotFoundException(){
        super("Card not Exists in Database");
    }
    public CardNotFoundException(String str){
        super(str);
    }
}