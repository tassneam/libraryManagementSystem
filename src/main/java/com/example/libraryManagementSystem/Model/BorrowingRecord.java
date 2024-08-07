package com.example.libraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "borrowingRecords")
@Data
//Tracks the association between books and patrons, including borrowing and return dates
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    private Book book;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    private Patron patron;

    @Column(nullable = false)
    private LocalDate borrowDate;

    private LocalDate returnDate = null;

    @Column(nullable = false)
    private boolean isReturned = false;

}
