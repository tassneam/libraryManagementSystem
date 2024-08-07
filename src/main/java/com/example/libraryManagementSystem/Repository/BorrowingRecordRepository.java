package com.example.libraryManagementSystem.Repository;

import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Model.BorrowingRecord;
import com.example.libraryManagementSystem.Model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    @Query("SELECT CASE WHEN COUNT(br) > 0 THEN true ELSE false END FROM BorrowingRecord br WHERE br.book = :book AND br.isReturned = false")
    boolean findByBookAndIsReturned(@Param("book") Book book);

    Optional<BorrowingRecord> findByBookAndPatronAndIsReturnedFalse(@Param("book") Book book, @Param("patron") Patron patron);

}
