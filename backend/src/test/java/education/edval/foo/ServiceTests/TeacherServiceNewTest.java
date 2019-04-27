package education.edval.foo.ServiceTests;

import education.edval.foo.domain.Teacher;
import education.edval.foo.repository.TeacherRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Normally I wont write unit tests for jpa crud methods but making an exception here...
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql= true)
@AutoConfigureTestDatabase
public class TeacherServiceNewTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void SaveTeacherReturnTeacherDTO(){

/*        TeacherDto teacherDto=new TeacherDto();
        teacherDto.setId((long)18);
        teacherDto.setEmail("suresh@whatamIdoingwithmylife.com");
        teacherDto.setName("Suresh");
        FacultyDto facultyDto=new FacultyDto((long)7,"History");
        teacherDto.setFaculty(facultyDto);*/

        Teacher teacher=new Teacher();
        teacher.setEmail("suresh@whatamIdoingwithmylife.com");
        teacher.setName("suresh");
        teacher.setFacultyId((long)7);

        teacher= teacherRepository.save(teacher);

        assertNotNull(teacher.getId());

    }

}
