package com.example.Librarymanagementsystem.repository;

import com.example.Librarymanagementsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
