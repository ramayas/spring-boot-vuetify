package education.edval.foo.service;

import education.edval.foo.dto.TeacherDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TeacherService {
    // TODO:
    //  1. Implement a service method that creates a new teacher and returns the new teacher id.



    // TODO:
    //  1. Implement a service method that retrieves all teachers based on the filter and pageable object.

    /**
     *
     * @param name
     * @param email
     * @param page
     * @param size
     * @param sort
     * @return TeacherDTO object
     */
    List<TeacherDto> findByNameOrEmail(String name, String email, Integer page,Integer size, Sort sort);

    List<TeacherDto> findByName(String name);

    /**
     * insert a new teacher
     * @param teacherDto
     * @return inserted teacher dto object
     */
    TeacherDto newTeacherDto(TeacherDto teacherDto);
}
