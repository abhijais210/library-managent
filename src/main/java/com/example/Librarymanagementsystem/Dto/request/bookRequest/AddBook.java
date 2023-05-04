package com.example.Librarymanagementsystem.Dto.request.bookRequest;

import com.example.Librarymanagementsystem.enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBook {
    private int authorId;
    private String title;
    private Genre genre;
    private int numberOfPages;
    private int price;
}