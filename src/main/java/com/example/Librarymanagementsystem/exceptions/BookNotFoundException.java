package com.example.Librarymanagementsystem.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(){
        super("Book not Exists in Database");
    }
    public BookNotFoundException(String str){
        super(str);
    }
}
