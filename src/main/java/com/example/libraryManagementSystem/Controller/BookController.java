package com.example.libraryManagementSystem.Controller;

import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Response.UpdateResponse;
import com.example.libraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")

public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updateBookById(@PathVariable("id") Integer id, @RequestBody Book book) {
        UpdateResponse response = bookService.updateBookById(id, book);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable("id") Integer id) {
        bookService.deleteBookById(id);
        return "Book deleted Successfully";
    }
}
