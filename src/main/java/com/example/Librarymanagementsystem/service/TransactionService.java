package com.example.Librarymanagementsystem.service;

import com.example.Librarymanagementsystem.Dto.request.transactionRequest.IssueBookDto;
import com.example.Librarymanagementsystem.Dto.request.transactionRequest.ReturnBookDto;
import com.example.Librarymanagementsystem.Dto.response.TransactionRespond.IssueBookResponse;
import com.example.Librarymanagementsystem.Dto.response.TransactionRespond.ReturnBookResponse;
import com.example.Librarymanagementsystem.exceptions.BookNotFoundException;
import com.example.Librarymanagementsystem.exceptions.CardExpiredException;
import com.example.Librarymanagementsystem.exceptions.CardNotFoundException;

public interface TransactionService {
    public IssueBookResponse issueBook(IssueBookDto issueBookDto) throws Exception;
    public ReturnBookResponse returnBook(ReturnBookDto returnBookDto) throws Exception;
}
