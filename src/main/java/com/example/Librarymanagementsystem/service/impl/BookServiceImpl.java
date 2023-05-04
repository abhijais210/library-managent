package com.example.Librarymanagementsystem.service.impl;

import com.example.Librarymanagementsystem.Dto.request.bookRequest.AddBook;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.GetBookByAuthorResponse;
import com.example.Librarymanagementsystem.entity.Author;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.repository.AuthorRepository;
import com.example.Librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addBookForAuthor(AddBook addBook) throws Exception {
        Author author;
        try{
            author = authorRepository.findById(addBook.getAuthorId()).get();
            Book book = new Book();

            book.setAuthor(author);
            book.setGenre(addBook.getGenre());
            book.setTitle(addBook.getTitle());
            book.setPrice(addBook.getPrice());
            book.setIsIssued(String.valueOf(Boolean.FALSE));
            book.setNumberOfPages(addBook.getNumberOfPages());

            author.getBooks().add(book);
            authorRepository.save(author);

        }catch (Exception e){
            throw new Exception("Author not Exists");
        }
        return "book added successfully";
    }
    @Override
    public List<GetBookByAuthorResponse> getBookByAuthorId(Integer authorId) throws AuthorNotFoundException {
        Author author;
        try{
            author = authorRepository.findById(authorId).get();
        }catch (Exception e){
            throw new AuthorNotFoundException();
        }
        List<Book> bookList = author.getBooks();
        List<GetBookByAuthorResponse> responseList = new ArrayList<>();

        //now generate response of list of books for a author
        for(Book book : bookList){
            GetBookByAuthorResponse getBookByAuthorResponse = new GetBookByAuthorResponse();

            getBookByAuthorResponse.setAuthorName(author.getName());
            getBookByAuthorResponse.setId(book.getId());
            getBookByAuthorResponse.setGenre(book.getGenre());
            getBookByAuthorResponse.setTitle(book.getTitle());
            getBookByAuthorResponse.setPrice(book.getPrice());
            getBookByAuthorResponse.setNumberOfPages(book.getNumberOfPages());

            responseList.add(getBookByAuthorResponse);
        }

        return responseList;
    }
}
