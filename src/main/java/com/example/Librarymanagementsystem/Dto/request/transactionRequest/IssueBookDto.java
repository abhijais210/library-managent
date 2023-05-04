package com.example.Librarymanagementsystem.Dto.request.transactionRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookDto {
    private int bookId;
    private int cardId;
}
