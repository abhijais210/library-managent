package com.example.Librarymanagementsystem.Dto.response.authorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAuthorByIdResponse {
    private int id;
    private String name;
    private int age;
    private String email;
    private int numberOfBooks;
}
