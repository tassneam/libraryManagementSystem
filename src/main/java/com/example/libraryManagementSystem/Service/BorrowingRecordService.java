package com.example.libraryManagementSystem.Service;

import com.example.libraryManagementSystem.Model.BorrowingRecord;
import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Model.Patron;
import com.example.libraryManagementSystem.Repository.BorrowingRecordRepository;
import com.example.libraryManagementSystem.Repository.BookRepository;
import com.example.libraryManagementSystem.Repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord borrowBook(Integer bookId, Integer patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));

        // Check if the book is already borrowed
        if (borrowingRecordRepository.findByBookAndIsReturned(book)) {
            throw new RuntimeException("Book is already borrowed");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setReturnDate(null);
        borrowingRecord.setReturned(false);

        return borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord returnBook(Integer bookId, Integer patronId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<Patron> patronOpt = patronRepository.findById(patronId);

        if (bookOpt.isEmpty()) {
            throw new RuntimeException("Book not found with id: " + bookId);
        }

        if (patronOpt.isEmpty()) {
            throw new RuntimeException("Patron not found with id: " + patronId);
        }

        Book book = bookOpt.get();
        Patron patron = patronOpt.get();

        Optional<BorrowingRecord> borrowingRecordOpt = borrowingRecordRepository.findByBookAndPatronAndIsReturnedFalse(book, patron);
        if (borrowingRecordOpt.isEmpty()) {
            throw new RuntimeException("No borrowing record found for book id: " + bookId + " and patron id: " + patronId);
        }

        BorrowingRecord borrowingRecord = borrowingRecordOpt.get();
        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecord.setReturned(true);

        return borrowingRecordRepository.save(borrowingRecord);
    }
}

