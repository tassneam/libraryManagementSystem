package com.example.libraryManagementSystem.Service;

import com.example.libraryManagementSystem.ExceptionHandling.NotFoundException;
import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Repository.BookRepository;
import com.example.libraryManagementSystem.Response.UpdateResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Integer id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            Book book = oldBook.get();
            return oldBook;
        } else
            throw new NotFoundException("Book with ID " + id + " not found");

    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public UpdateResponse updateBookById(Integer id, Book updatedBook) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            Book existingBook = oldBook.get();

            boolean isChanged = false;
            if (!existingBook.getTitle().equals(updatedBook.getTitle())) {
                existingBook.setTitle(updatedBook.getTitle());
                isChanged = true;
            }
            if (!existingBook.getAuthor().equals(updatedBook.getAuthor())) {
                existingBook.setAuthor(updatedBook.getAuthor());
                isChanged = true;
            }
            if (!existingBook.getIsbn().equals(updatedBook.getIsbn())) {
                existingBook.setIsbn(updatedBook.getIsbn());
                isChanged = true;
            }
            if (!existingBook.getPublicationYear().equals(updatedBook.getPublicationYear())) {
                existingBook.setPublicationYear(updatedBook.getPublicationYear());
                isChanged = true;
            }

            if (isChanged) {
                bookRepository.save(existingBook);
                return new UpdateResponse("Book updated successfully.", true);
            } else {
                return new UpdateResponse("No changes detected.", false);
            }
        } else {
            // Throw an exception if the book does not exist
            throw new NotFoundException("Book with ID " + id + " not found");
        }
    }

    public void deleteBookById(Integer id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            Book book = oldBook.get();
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException("Book with ID " + id + " not found");
        }
    }
}