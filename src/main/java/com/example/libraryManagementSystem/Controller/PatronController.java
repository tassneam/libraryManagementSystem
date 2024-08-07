package com.example.libraryManagementSystem.Controller;


import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Model.Patron;
import com.example.libraryManagementSystem.Response.UpdateResponse;
import com.example.libraryManagementSystem.Service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")

public class PatronController {
    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patron>> getPatronById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(patronService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.addPatron(patron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updatePatronById(@PathVariable("id") Integer id, @RequestBody Patron patron) {
        UpdateResponse response = patronService.updatePatronById(id, patron);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public String deletePatronById(@PathVariable("id") Integer id) {
        patronService.deletePatronById(id);
        return "Patron deleted Successfully";
    }
}

