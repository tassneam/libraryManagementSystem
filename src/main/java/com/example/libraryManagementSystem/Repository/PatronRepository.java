package com.example.libraryManagementSystem.Repository;

import com.example.libraryManagementSystem.Model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
}
