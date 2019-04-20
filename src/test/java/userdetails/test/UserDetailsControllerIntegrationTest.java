package userdetails.test;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import userdetails.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ComponentScan
@WebMvcTest(UserDetailsController.class)
public class UserDetailsControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository er;

    @MockBean
    private PasswordsRepository pr;


    @Test
    public void returnsEmployeeByEmailAsJsonGivenEmployeeIsPresent() throws Exception{
        EmployeeEntry ee = new EmployeeEntry("Alex", "Gordon", "19/05/2018", "ag@gmail.com");
        List<EmployeeEntry> el = Arrays.asList(ee);
        given(er.findByEmail("ag@gmail.com")).willReturn(el);

        mvc.perform(get("/get")
        .contentType(MediaType.APPLICATION_JSON).content("ag@gmail.com")).andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.name", is(ee.getName())));
    }

    @Test
    public void addsEmployee() throws Exception {
        EmployeeEntry ee = new EmployeeEntry("Alex", "Gordon", "19/05/2018", "ag@gmail.com");
        String jsonString = "{\n" +
                "\t\"name\": \"Alex\",\n" +
                "\t\"surname\": \"Gordon\",\n" +
                "\t\"dob\": \"19/05/2018\",\n" +
                "\t\"email\": \"ag@gmail.com\",\n" +
                "\t\"password\": 123123\n" +
                "}";
        mvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk()).andExpect(content().string(""));
        verify(er, times(1)).save(ee);
        verify(pr, times(1)).save(any(PasswordEntry.class));
    }


    @Test
    public void deletesEmployeeByEmail() throws Exception {
        EmployeeEntry ee = new EmployeeEntry("Alex", "Gordon", "19/05/2018", "ag@gmail.com");
        given(er.deleteByEmail(ee.getEmail())).willReturn(Long.valueOf(1));
        given(pr.deleteByEmail(ee.getEmail())).willReturn(Long.valueOf(1));
        mvc.perform(delete("/delete").contentType(MediaType.APPLICATION_JSON).content(ee.getEmail()))
                .andExpect(status().isOk()).andExpect(content().string("1"));

    }
}
