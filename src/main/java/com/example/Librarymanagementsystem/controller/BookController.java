package com.example.Librarymanagementsystem.controller;

import com.example.Librarymanagementsystem.Dto.request.bookRequest.AddBook;
import com.example.Librarymanagementsystem.Dto.response.authorResponse.GetBookByAuthorResponse;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add-book-for-author")
    public String addBookForAuthor(@RequestBody AddBook addBook) throws Exception {
        return bookService.addBookForAuthor(addBook);
    }
    @GetMapping("/get_bookList_by_authorId")
    public List<GetBookByAuthorResponse> getBookByAuthorId(@RequestParam Integer authorId) throws AuthorNotFoundException {
        return bookService.getBookByAuthorId(authorId);
    }
}
