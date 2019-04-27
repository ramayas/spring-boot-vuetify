package education.edval.foo.WebMVCTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import education.edval.foo.controller.TeacherController;
import education.edval.foo.domain.Teacher;
import education.edval.foo.service.TeacherService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@WebMvcTest(TeacherController.class)
@WithMockUser
public class TeacherWebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void returnTeacherListFromNameAndEmailFullUrl() throws Exception {

        this.mockMvc.perform(get("/api/teachers")
                .param("filter", "name:john,email:user5@example.org")
                .param("page","1")
                .param("size","10")
                .param("sort","name,desc"))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void SaveTeacherReturnTeacherDTOId() throws Exception {
        Teacher teacher=new Teacher();
        teacher.setFacultyId((long)5);
        teacher.setName("Suresh");
        teacher.setEmail("suresh@whatamIdoing.com");

        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(teacher);

        this.mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(print()).andExpect(status().isOk());
    }

}
