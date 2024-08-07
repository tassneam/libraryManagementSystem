package com.example.libraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
// title, author, publication year, ISBN (International Standard Book Number)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer publicationYear;

    @Column(unique = true, nullable = false)
    private String isbn;


}
