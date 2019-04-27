package education.edval.foo.controller;


import education.edval.foo.dto.TeacherDto;
import education.edval.foo.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;


    // TODO:
    //  1. Implement a call to the service layer that creates a new teacher.
    //  2. This endpoint (POST) can only be accessed by a user that has ROLE_ADMIN.

    // TODO:
    //  1. Implement a call to service layer that returns a list of teachers, which uses "filter" and "pageable".
    //  2. filter: Query param for multiple filters for searching. e.g. filter={field}:{value}>,{field-nth}:{value-nth}
    //  3. pageable: Query param for size (default to 50), page and sort. E.g. 'size={number}', 'page={number}', 'sort={field},{direction}'
    //  4. This endpoint (GET) should return a list of teacher

    @RequestMapping(value = "/api/teachers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeacherDto>> getTeacher(@RequestParam(value = "filter") String filter, Pageable p) throws ServletException {
        try {

            final String[] fields = filter.split(",");
            final Map<String, String> m = new HashMap<String, String>();

            for (int i = 0; i < fields.length; i++)
                m.put(fields[i].split(":")[0], fields[i].split(":")[1]);

            List<TeacherDto> teacherDtoList = this.teacherService.findByNameOrEmail(m.get("name"), m.get("email"), p.getPageNumber(), p.getPageSize(), p.getSort());
            // Page<TeacherDto> pages = new PageImpl<TeacherDto>(teacherDtoList, p, teacherDtoList.size());

            return ResponseEntity.ok(teacherDtoList);

        } catch (Exception e) {

            logger.error("Unable to perform teacher search - " + e.getMessage());
            System.out.println("Unable to perform teacher search - " + e.getMessage());
            throw new ServletException(e.getMessage(), e);
        }

    }

    @PostMapping(value = "api/teachers")
    public ResponseEntity<TeacherDto> insertTeacher(@RequestBody TeacherDto teacherDTO) throws ServletException {

        try {

            TeacherDto teacherDto = this.teacherService.newTeacherDto(teacherDTO);
            return ResponseEntity.ok(teacherDTO);

        } catch (Exception e) {

            logger.error("Unable to update student assigment despatch details - " + e.getMessage());
            throw new ServletException(e.getMessage(), e);
        }

    }

}
