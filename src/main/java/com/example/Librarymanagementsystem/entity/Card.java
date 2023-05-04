package com.example.Librarymanagementsystem.entity;

import com.example.Librarymanagementsystem.enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date issueDate;
    @UpdateTimestamp
    private Date updatedOn;
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;
    private String validTill;
    @OneToOne
    @JoinColumn
    private Student student;
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();
}
