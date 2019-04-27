package education.edval.foo.ServiceTests;


import education.edval.foo.dto.FacultyDto;
import education.edval.foo.dto.TeacherDto;
import education.edval.foo.repository.TeacherRepository;
import education.edval.foo.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    public void filterByNameOrEmail() {

        assertThat(teacherService.findByNameOrEmail("John", "", 1, 5, Sort.by("name").ascending()).get(0).getName(), equalTo("John"));

    }

    @Test
    public void SaveTeacherReturnTeacherDTO(){

        TeacherDto teacherDto=new TeacherDto();
        teacherDto.setEmail("suresh@whatamIdoingwithmylife.com");
        teacherDto.setName("Suresh");
        FacultyDto facultyDto=new FacultyDto((long)7,"History");
        teacherDto.setFaculty(facultyDto);

        teacherDto=this.teacherService.newTeacherDto(teacherDto);

        assertThat(teacherDto.getId(),notNullValue());

    }

}
