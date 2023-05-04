package com.example.Librarymanagementsystem.entity;

import com.example.Librarymanagementsystem.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String transactionNumber;
    private boolean isIssueOperation;
    @CreationTimestamp
    private Date tarnsactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @ManyToOne
    @JoinColumn
    private Card card;
    @ManyToOne
    @JoinColumn
    private Book book;
}
