package com.example.Librarymanagementsystem.repository;

import com.example.Librarymanagementsystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
