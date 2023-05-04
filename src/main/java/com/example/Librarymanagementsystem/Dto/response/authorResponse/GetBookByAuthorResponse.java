package com.example.Librarymanagementsystem.Dto.response.authorResponse;

import com.example.Librarymanagementsystem.enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetBookByAuthorResponse {
    private String authorName;
    private int id;
    private String title;
    private Genre genre;
    private int numberOfPages;
    private int price;
}
