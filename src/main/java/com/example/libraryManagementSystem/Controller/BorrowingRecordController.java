package com.example.libraryManagementSystem.Controller;

import com.example.libraryManagementSystem.Model.BorrowingRecord;
import com.example.libraryManagementSystem.Service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(
            @PathVariable Integer bookId,
            @PathVariable Integer patronId) {

        try {
            BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(
            @PathVariable Integer bookId,
            @PathVariable Integer patronId) {

        try {
            BorrowingRecord borrowingRecord = borrowingRecordService.returnBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}