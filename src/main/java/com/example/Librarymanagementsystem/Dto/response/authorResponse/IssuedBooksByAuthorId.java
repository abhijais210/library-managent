package com.example.Librarymanagementsystem.Dto.response.authorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssuedBooksByAuthorId {
    private List<String> booktitleList = new ArrayList<>();
    private int totalIssuedCount;
}
