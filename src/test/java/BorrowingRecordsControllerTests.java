
import com.example.libraryManagementSystem.LibraryManagementSystemApplication;
import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Model.Patron;
import com.example.libraryManagementSystem.Model.BorrowingRecord;
import com.example.libraryManagementSystem.Service.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)

@AutoConfigureMockMvc
public class BorrowingRecordsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testBorrowBook() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book One");
        book.setAuthor("Author One");
        book.setPublicationYear(2021);
        book.setIsbn("123-4567890123");

        Patron patron = new Patron();
        patron.setId(1);
        patron.setName("John Doe");
        patron.setContactInformation("john.doe@example.com");

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1);
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        Mockito.when(borrowingRecordService.borrowBook(1, 1)).thenReturn(borrowingRecord);

        mockMvc.perform(post("/api/borrow/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.book.id").value(1))
                .andExpect(jsonPath("$.patron.id").value(1));
    }

    @Test
    public void testReturnBook() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book One");
        book.setAuthor("Author One");
        book.setPublicationYear(2021);
        book.setIsbn("123-4567890123");

        Patron patron = new Patron();
        patron.setId(1);
        patron.setName("John Doe");
        patron.setContactInformation("john.doe@example.com");

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1);
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecord.setReturned(true);

        Mockito.when(borrowingRecordService.returnBook(1, 1)).thenReturn(borrowingRecord);

        mockMvc.perform(put("/api/return/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.returnDate").exists());
    }
}
