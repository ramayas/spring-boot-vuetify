package education.edval.foo.repository;

import education.edval.foo.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // List<Teacher> findByNameOrEmail(String name, String email, Pageable page);

    List<Teacher> findByName(String name);

    Teacher findByEmail(String email);

}
