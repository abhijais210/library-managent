package com.example.Librarymanagementsystem.service;

import com.example.Librarymanagementsystem.Dto.request.authorRequest.AddAuthorReqDto;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.DeleteAuthorResponseDto;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.GetAuthorByIdResponse;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.IssuedBooksByAuthorId;
import com.example.Librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.exceptions.BookAlreadyIssuedException;

public interface AuthorService {
    public String addAuthor(AddAuthorReqDto addAuthorReqDto);
    public GetAuthorByIdResponse getAuthorById(Integer authorId) throws AuthorNotFoundException;
    public IssuedBooksByAuthorId issueBookCount(Integer authorId) throws AuthorNotFoundException;
    public DeleteAuthorResponseDto deleteAuthor(Integer authorId) throws AuthorNotFoundException, BookAlreadyIssuedException;

}
