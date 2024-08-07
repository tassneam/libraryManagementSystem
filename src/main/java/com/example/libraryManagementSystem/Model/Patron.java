package com.example.libraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Patrons")
@Data
// name, contact information
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String contactInformation;

}
