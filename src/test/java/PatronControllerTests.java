
import com.example.libraryManagementSystem.LibraryManagementSystemApplication;
import com.example.libraryManagementSystem.Model.Patron;
import com.example.libraryManagementSystem.Response.UpdateResponse;
import com.example.libraryManagementSystem.Service.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
@AutoConfigureMockMvc
public class PatronControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() throws Exception {
        Patron patron1 = new Patron();
        patron1.setId(1);
        patron1.setName("John Doe");
        patron1.setContactInformation("john.doe@example.com");

        Patron patron2 = new Patron();
        patron2.setId(2);
        patron2.setName("Jane Smith");
        patron2.setContactInformation("jane.smith@example.com");

        Mockito.when(patronService.getAllPatrons()).thenReturn(Arrays.asList(patron1, patron2));

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    public void testGetPatronById() throws Exception {
        Patron patron = new Patron();
        patron.setId(1);
        patron.setName("John Doe");
        patron.setContactInformation("john.doe@example.com");

        Mockito.when(patronService.getById(1)).thenReturn(Optional.of(patron));

        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testAddPatron() throws Exception {
        Patron patron = new Patron();
        patron.setId(1);
        patron.setName("John Doe");
        patron.setContactInformation("john.doe@example.com");

        Mockito.when(patronService.addPatron(Mockito.any(Patron.class))).thenReturn(patron);

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"contactInformation\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdatePatronById() throws Exception {
        UpdateResponse updateResponse = new UpdateResponse("Success", true);

        Mockito.when(patronService.updatePatronById(Mockito.eq(1), Mockito.any(Patron.class))).thenReturn(updateResponse);

        mockMvc.perform(put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"contactInformation\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    public void testDeletePatronById() throws Exception {
        Mockito.doNothing().when(patronService).deletePatronById(1);

        mockMvc.perform(delete("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Patron deleted Successfully"));
    }
}
