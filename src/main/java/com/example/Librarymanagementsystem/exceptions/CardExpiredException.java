package com.example.Librarymanagementsystem.exceptions;

public class CardExpiredException extends Exception{
    public CardExpiredException(){
        super("Card has expired");
    }
    public CardExpiredException(String str){
        super(str);
    }
}
