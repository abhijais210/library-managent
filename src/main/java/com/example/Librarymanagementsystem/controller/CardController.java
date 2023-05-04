package com.example.Librarymanagementsystem.controller;

import com.example.Librarymanagementsystem.Dto.response.cardResponse.IssuedBooksForCard;
import com.example.Librarymanagementsystem.exceptions.CardNotFoundException;
import com.example.Librarymanagementsystem.service.CardService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("card")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CardController {
    @Autowired
    CardService cardService;
    @GetMapping("issued_books")
    IssuedBooksForCard issuedBook(@RequestParam Integer cardId) throws CardNotFoundException {
        return cardService.issuedBook(cardId);
    }
}
