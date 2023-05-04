package com.example.Librarymanagementsystem.service;

import com.example.Librarymanagementsystem.Dto.response.cardResponse.IssuedBooksForCard;
import com.example.Librarymanagementsystem.exceptions.CardNotFoundException;

public interface CardService {
    public IssuedBooksForCard issuedBook(Integer cardId) throws CardNotFoundException;
}
