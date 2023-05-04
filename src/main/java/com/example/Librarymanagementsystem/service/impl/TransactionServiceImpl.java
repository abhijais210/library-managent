package com.example.Librarymanagementsystem.service.impl;

import com.example.Librarymanagementsystem.Dto.request.transactionRequest.IssueBookDto;
import com.example.Librarymanagementsystem.Dto.request.transactionRequest.ReturnBookDto;
import com.example.Librarymanagementsystem.Dto.response.TransactionRespond.IssueBookResponse;
import com.example.Librarymanagementsystem.Dto.response.TransactionRespond.ReturnBookResponse;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.entity.Card;
import com.example.Librarymanagementsystem.entity.Transaction;
import com.example.Librarymanagementsystem.enums.CardStatus;
import com.example.Librarymanagementsystem.enums.TransactionStatus;
import com.example.Librarymanagementsystem.exceptions.BookNotFoundException;
import com.example.Librarymanagementsystem.exceptions.CardExpiredException;
import com.example.Librarymanagementsystem.exceptions.CardNotFoundException;
import com.example.Librarymanagementsystem.repository.BookRepository;
import com.example.Librarymanagementsystem.repository.CardRepository;
import com.example.Librarymanagementsystem.repository.TransactionRepository;
import com.example.Librarymanagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public IssueBookResponse issueBook(IssueBookDto issueBookDto) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        Book book;
        Card card;
        try{
            card = cardRepository.findById(issueBookDto.getCardId()).get();
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException();
        }
        transaction.setCard(card);

        try{
            book = bookRepository.findById(issueBookDto.getBookId()).get();
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException();
        }
        transaction.setBook(book);

        if(card.getCardStatus() != CardStatus.ACTIVE){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardExpiredException();
        }

        if(book.getIsIssued().compareTo("true") == 0){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book Not available");
        }
        book.setIsIssued(String.valueOf(Boolean.TRUE));

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setBook(book);

        card.getBookList().add(book);
        card.getTransactionList().add(transaction);
        //card.getBookList().add(book);

        book.getTransactionList().add(transaction);
        book.setCard(card);

        IssueBookResponse issueBookResponse = new IssueBookResponse();
        issueBookResponse.setBookName(book.getTitle());
        issueBookResponse.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponse.setTransactionStatus(transaction.getTransactionStatus());

        cardRepository.save(card);

        //now we will  send an email regarding if transaction completed
        String text = "Congrats! " + card.getStudent().getName() +  " You have been issued the book " + book.getTitle();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("LibraryManage210698@gmail.com");
        simpleMailMessage.setTo(card.getStudent().getMobNo());//assumed this as mail
        simpleMailMessage.setSubject("Issue Book");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        return issueBookResponse;
    }

    @Override
    public ReturnBookResponse returnBook(ReturnBookDto returnBookDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        Book book;
        Card card;
        try{
            card = cardRepository.findById(returnBookDto.getCardId()).get();
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException();
        }
        transaction.setCard(card);

        try{
            book = bookRepository.findById(returnBookDto.getBookId()).get();
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException();
        }
        transaction.setBook(book);

        //if card has been already expired
        if(card.getCardStatus() != CardStatus.ACTIVE){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardExpiredException();
        }
        //book not issued earlier
        if(Objects.equals(book.getIsIssued(), "false")){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("This Book have not issued earlier");
        }
        //very important check if the book is associated with the card or not
        if(book.getCard().getId() != card.getId()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception(" book has not issued with this card");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIsIssued(String.valueOf(Boolean.FALSE));
        book.getTransactionList().add(transaction);
        book.setCard(null);

        card.getBookList().remove(book);
        card.getTransactionList().add(transaction);
        cardRepository.save(card);


        ReturnBookResponse returnBookResponse = new ReturnBookResponse();
        returnBookResponse.setBookName(book.getTitle());
        returnBookResponse.setTransactionNumber(transaction.getTransactionNumber());
        returnBookResponse.setTransactionStatus(transaction.getTransactionStatus());

        //now we will be send mail to student regarding book return transactions
        String text = "Congrats! " + card.getStudent().getName() +  " You have been returned the book " + book.getTitle();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("LibraryManage210698@gmail.com");
        simpleMailMessage.setTo(card.getStudent().getMobNo());//assumed this as mail
        simpleMailMessage.setSubject("Return Book");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        return returnBookResponse;
    }
}
