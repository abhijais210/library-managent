package com.example.Librarymanagementsystem.service.impl;

import com.example.Librarymanagementsystem.Dto.response.cardResponse.IssuedBooksForCard;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.entity.Card;
import com.example.Librarymanagementsystem.exceptions.CardNotFoundException;
import com.example.Librarymanagementsystem.repository.CardRepository;
import com.example.Librarymanagementsystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Override
    public IssuedBooksForCard issuedBook(Integer cardId) throws CardNotFoundException {
        Card card;
        try {
            card = cardRepository.findById(cardId).get();
        }catch (Exception e){
            throw new CardNotFoundException();
        }
        //get all the books issued with this card
        List<Book> bookList = card.getBookList();
        //in this list only add name of books and create response
        List<String> bookNameList = new ArrayList<>();
        for(Book book : bookList){
            bookNameList.add(book.getTitle());
        }
        //now create response
        return new IssuedBooksForCard(bookNameList);
    }
}
