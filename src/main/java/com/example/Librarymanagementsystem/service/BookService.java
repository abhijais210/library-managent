package com.example.Librarymanagementsystem.service;

import com.example.Librarymanagementsystem.Dto.request.bookRequest.AddBook;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.GetBookByAuthorResponse;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.exceptions.AuthorNotFoundException;

import java.util.List;

public interface BookService {
    public String addBookForAuthor(AddBook addBook) throws Exception;
    public List<GetBookByAuthorResponse> getBookByAuthorId(Integer authorId) throws AuthorNotFoundException;
}
