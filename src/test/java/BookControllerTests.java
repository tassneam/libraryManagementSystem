import com.example.libraryManagementSystem.Controller.BookController;
import com.example.libraryManagementSystem.Model.Book;
import com.example.libraryManagementSystem.Response.UpdateResponse;
import com.example.libraryManagementSystem.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Title1");
        book1.setAuthor("Author1");
        book1.setPublicationYear(2023);
        book1.setIsbn("ISBN1");

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Title2");
        book2.setAuthor("Author2");
        book2.setPublicationYear(2022);
        book2.setIsbn("ISBN2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Title1")))
                .andExpect(jsonPath("$[1].title", is("Title2")));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Title1");
        book.setAuthor("Author1");
        book.setPublicationYear(2023);
        book.setIsbn("ISBN1");

        when(bookService.getById(anyInt())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Title1")));

        verify(bookService, times(1)).getById(anyInt());
    }

    @Test
    void addBook() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Title1");
        book.setAuthor("Author1");
        book.setPublicationYear(2023);
        book.setIsbn("ISBN1");

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Title1\", \"author\": \"Author1\", \"publicationYear\": 2023, \"isbn\": \"ISBN1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Title1")));

        verify(bookService, times(1)).addBook(any(Book.class));
    }

    @Test
    void updateBookById() throws Exception {
        UpdateResponse response = new UpdateResponse("Book updated successfully", true);
        when(bookService.updateBookById(anyInt(), any(Book.class))).thenReturn(response);

        mockMvc.perform(put("/api/books/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Title\", \"author\": \"Updated Author\", \"publicationYear\": 2024, \"isbn\": \"Updated ISBN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Book updated successfully")));

        verify(bookService, times(1)).updateBookById(anyInt(), any(Book.class));
    }

    @Test
    void deleteBookById() throws Exception {
        doNothing().when(bookService).deleteBookById(anyInt());

        mockMvc.perform(delete("/api/books/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted Successfully"));

        verify(bookService, times(1)).deleteBookById(anyInt());
    }
}
