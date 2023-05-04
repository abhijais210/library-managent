package com.example.Librarymanagementsystem.exceptions;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(){
        super("Author not exists in Database");
    }
    public AuthorNotFoundException(String str){
        super(str);
    }
}
