package com.example.libraryManagementSystem.Service;

import com.example.libraryManagementSystem.ExceptionHandling.NotFoundException;
import com.example.libraryManagementSystem.Model.Patron;
import com.example.libraryManagementSystem.Repository.PatronRepository;
import com.example.libraryManagementSystem.Response.UpdateResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getById(Integer id) {
        Optional<Patron> oldPatron = patronRepository.findById(id);
        if (oldPatron.isPresent()) {
            Patron patron = oldPatron.get();
            return oldPatron;
        } else {
            throw new NotFoundException("Patron with ID " + id + " not found");

        }
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    public UpdateResponse updatePatronById(Integer id, Patron updatedPatron) {
        Optional<Patron> oldPatron = patronRepository.findById(id);
        if (oldPatron.isPresent()) {
            Patron existingPatron = oldPatron.get();

            boolean isChanged = false;
            if (!existingPatron.getName().equals(updatedPatron.getName())) {
                existingPatron.setName(updatedPatron.getName());
                isChanged = true;
            }
            if (!existingPatron.getContactInformation().equals(updatedPatron.getContactInformation())) {
                existingPatron.setContactInformation(updatedPatron.getContactInformation());
                isChanged = true;
            }

            if (isChanged) {
                patronRepository.save(existingPatron);
                return new UpdateResponse("Patron updated successfully.", true);
            } else {
                return new UpdateResponse("No changes detected.", false);
            }
        } else {
            // Throw an exception if the book does not exist
            throw new NotFoundException("Patron with ID " + id + " not found");
        }
    }

    public void deletePatronById(Integer id) {
        Optional<Patron> oldPatron = patronRepository.findById(id);
        if (oldPatron.isPresent()) {
            Patron patron = oldPatron.get();
            patronRepository.deleteById(id);
        } else {
            throw new NotFoundException("Patron with ID " + id + " not found");
        }
    }
}
