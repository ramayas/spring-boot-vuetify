package education.edval.foo.service;

import education.edval.foo.dto.FacultyDto;
import education.edval.foo.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<FacultyDto> findAll() {
        List<FacultyDto> result = new ArrayList<>();
        facultyRepository.findAll().forEach(f -> result.add(new FacultyDto(f.getId(), f.getName())));

        return Collections.unmodifiableList(result);
    }
}
