package com.example.Librarymanagementsystem.service.impl;

import com.example.Librarymanagementsystem.Dto.request.authorRequest.AddAuthorReqDto;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.DeleteAuthorResponseDto;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.GetAuthorByIdResponse;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.IssuedBooksByAuthorId;
import com.example.Librarymanagementsystem.entity.Author;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.exceptions.BookAlreadyIssuedException;
import com.example.Librarymanagementsystem.repository.AuthorRepository;
import com.example.Librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addAuthor(AddAuthorReqDto addAuthorReqDto) {
        Author author = new Author();
        author.setName(addAuthorReqDto.getName());
        author.setEmail(addAuthorReqDto.getEmail());
        author.setAge(addAuthorReqDto.getAge());

        authorRepository.save(author);
        return "author added successfully";
    }

    @Override
    public GetAuthorByIdResponse getAuthorById(Integer authorId) throws AuthorNotFoundException {
        try{
            Author author = authorRepository.findById(authorId).get();

            //now generate DTO response
            GetAuthorByIdResponse getAuthorByIdResponse = new GetAuthorByIdResponse();

            getAuthorByIdResponse.setId(authorId);
            getAuthorByIdResponse.setName(author.getName());
            getAuthorByIdResponse.setAge(author.getAge());
            getAuthorByIdResponse.setEmail(author.getEmail());
            getAuthorByIdResponse.setNumberOfBooks(author.getBooks().size());

            return getAuthorByIdResponse;

        }catch(Exception e){
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public IssuedBooksByAuthorId issueBookCount(Integer authorId) throws AuthorNotFoundException {
        //check whether author exists in database
        Author author;
        try{
            author = authorRepository.findById(authorId).get();
        }catch (Exception e){
            throw new AuthorNotFoundException();
        }
        //now get the list of books for this author and after that check which book issued and add to
        //a new list which we will be return as our response DTO
        IssuedBooksByAuthorId issuedBookByAuthorId = new IssuedBooksByAuthorId();
        List<String> responseBookList = issuedBookByAuthorId.getBooktitleList();
        List<Book> bookList = author.getBooks();

        for(Book book : bookList){
            if(book.getIsIssued().equals("true")){
                responseBookList.add(book.getTitle());
            }
        }
        issuedBookByAuthorId.setBooktitleList(responseBookList);
        issuedBookByAuthorId.setTotalIssuedCount(responseBookList.size());
        return issuedBookByAuthorId;
    }

    @Override
    public DeleteAuthorResponseDto deleteAuthor(Integer authorId) throws AuthorNotFoundException, BookAlreadyIssuedException {
        //first check if it is a valid author or not
        Author author;
        try{
            author = authorRepository.findById(authorId).get();
        }catch (Exception e){
            throw new AuthorNotFoundException();
        }
        //get the list of books written by author
        List<Book> bookList = author.getBooks();
        //in this list store the name of all the books that are deleted
        List<String> bookListName = new ArrayList<>();

        for(Book book : bookList){
            if(book.getIsIssued().equals("true")){
                throw new BookAlreadyIssuedException();
            }
            bookListName.add(book.getTitle());
        }

        //generate response DTO
        DeleteAuthorResponseDto deleteAuthorResponseDto = new DeleteAuthorResponseDto();
        deleteAuthorResponseDto.setBookList(bookListName);
        deleteAuthorResponseDto.setName(author.getName());

        authorRepository.deleteById(authorId);
        return deleteAuthorResponseDto;
    }
}
