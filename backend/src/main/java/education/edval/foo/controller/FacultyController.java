package education.edval.foo.controller;

import education.edval.foo.dto.FacultyDto;
import education.edval.foo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/api/faculties")
    public ResponseEntity<List<FacultyDto>> getFaculties() {
        return new ResponseEntity<>(facultyService.findAll(), HttpStatus.OK);
    }
}
