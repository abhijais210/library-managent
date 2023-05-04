package com.example.Librarymanagementsystem.entity;

import com.example.Librarymanagementsystem.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int numberOfPages;
    private int price;

    private String isIssued;
    @ManyToOne
    @JoinColumn
    private Author author;
    @ManyToOne
    @JoinColumn
    private Card card;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
