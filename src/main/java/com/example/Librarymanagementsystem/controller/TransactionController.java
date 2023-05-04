package com.example.Librarymanagementsystem.controller;

import com.example.Librarymanagementsystem.Dto.request.transactionRequest.IssueBookDto;
import com.example.Librarymanagementsystem.Dto.request.transactionRequest.ReturnBookDto;
import com.example.Librarymanagementsystem.Dto.response.TransactionRespond.IssueBookResponse;
import com.example.Librarymanagementsystem.Dto.response.TransactionRespond.ReturnBookResponse;
import com.example.Librarymanagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue_book")
    public IssueBookResponse issueBook(@RequestBody IssueBookDto issueBookDto) throws Exception {
        return transactionService.issueBook(issueBookDto);
    }
    @PostMapping("/return_book")
    public ReturnBookResponse returnBook(@RequestBody ReturnBookDto returnBookDto) throws Exception {
        return transactionService.returnBook(returnBookDto);
    }
}
