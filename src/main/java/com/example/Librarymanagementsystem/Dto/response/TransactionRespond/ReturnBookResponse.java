package com.example.Librarymanagementsystem.Dto.response.TransactionRespond;

import com.example.Librarymanagementsystem.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnBookResponse extends IssueBookResponse{
    private String transactionNumber;
    private TransactionStatus transactionStatus;
    private String bookName;
}
