package com.example.Librarymanagementsystem.exceptions;

public class BookAlreadyIssuedException extends Exception{
    public BookAlreadyIssuedException(){
        super("Book Already issue to a student");
    }
    public BookAlreadyIssuedException(String str){
        super(str);
    }
}
