package com.example.Librarymanagementsystem.controller;

import com.example.Librarymanagementsystem.Dto.request.authorRequest.AddAuthorReqDto;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.DeleteAuthorResponseDto;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.GetAuthorByIdResponse;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.IssuedBooksByAuthorId;
import com.example.Librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.exceptions.BookAlreadyIssuedException;
import com.example.Librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AddAuthorReqDto addAuthorReqDto){
        return authorService.addAuthor(addAuthorReqDto);
    }
    @GetMapping("/get_author_by_id")
    public GetAuthorByIdResponse getAuthorById(@RequestParam Integer authorId) throws AuthorNotFoundException {
        return authorService.getAuthorById(authorId);
    }
    @GetMapping("/count_issue_book/{authorId}")
    public IssuedBooksByAuthorId issueBookCount(@PathVariable Integer authorId) throws AuthorNotFoundException {
        return authorService.issueBookCount(authorId);
    }
    @DeleteMapping("/delete")
    public DeleteAuthorResponseDto deleteAuthor(@RequestParam Integer authorId) throws AuthorNotFoundException, BookAlreadyIssuedException {
        return authorService.deleteAuthor(authorId);
    }
}
