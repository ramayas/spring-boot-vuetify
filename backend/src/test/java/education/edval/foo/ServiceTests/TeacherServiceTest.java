package education.edval.foo.ServiceTests;


import education.edval.foo.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;


    @Test
    public void filterByNameOrEmail() {

        assertThat(teacherService.findByNameOrEmail("John", "", 1, 5, Sort.by("name").ascending()).get(0).getName(), equalTo("John"));

    }

    @Test
    public void filterByNullOrEmptyReturnAll(){

        assertThat(teacherService.findByNameOrEmail(null, null, 1, 5, Sort.by("name").ascending()).get(0).getName(), equalTo("Arvin Jastek"));

        assertThat(teacherService.findByNameOrEmail("", "", 1, 5, Sort.by("name").ascending()).get(0).getName(), equalTo("Arvin Jastek"));

    }

}
