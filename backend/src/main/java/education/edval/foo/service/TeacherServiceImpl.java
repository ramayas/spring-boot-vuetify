package education.edval.foo.service;

import education.edval.foo.domain.Faculty;
import education.edval.foo.domain.Teacher;
import education.edval.foo.dto.FacultyDto;
import education.edval.foo.dto.TeacherDto;
import education.edval.foo.repository.FacultyRepository;
import education.edval.foo.repository.TeacherRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service("TeacherService")
@Component
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    EntityManager em;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> findByNameOrEmail(String name, String email, Integer page, Integer size, Sort sort) {

        List<TeacherDto> teacherDtoList = new ArrayList<>();

        try {

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);

            Root<Teacher> teacher = cq.from(Teacher.class);
            cq.select(teacher);

            if (!sort.isEmpty()) {
                if (StringUtils.equals(sort.toString(), "name: ASC")) {
                    cq.orderBy(cb.asc(teacher.get("name")));
                } else if (StringUtils.equals(sort.toString(), "name: Desc")) {
                    cq.orderBy(cb.desc(teacher.get("name")));
                } else if (StringUtils.equals(sort.toString(), "email: ASC")) {
                    cq.orderBy(cb.asc(teacher.get("email")));
                } else if (StringUtils.equals(sort.toString(), "email: Desc")) {
                    cq.orderBy(cb.desc(teacher.get("email")));
                }
            }

            List<Predicate> criteria = new ArrayList<Predicate>();

            if (!StringUtils.isBlank(name) || !StringUtils.isEmpty(name)) {
                criteria.add(cb.like(cb.lower(teacher.get("name")), name.toLowerCase() + "%"));
            }

            if (!StringUtils.isBlank(email) || !StringUtils.isEmpty(email)) {
                criteria.add(cb.like(cb.lower(teacher.get("email")), email.toLowerCase() + "%"));
            }

            if (criteria.size() == 1) {
                cq.where(criteria.get(0));
            } else {
                cq.where(cb.and(criteria.toArray(new Predicate[0])));

            }

            // get the list of teachers by page index and page size now
            TypedQuery<Teacher> typedTeacher = em.createQuery(cq);

            if (page >= 1) {

                typedTeacher.setFirstResult((page - 1) * size);
            }
            typedTeacher.setMaxResults(size);

            List<Teacher> teacherList = typedTeacher.getResultList();

            for (Teacher t : teacherList) {
                TeacherDto teacherDto = new TeacherDto();
                teacherDto.setEmail(t.getEmail());
                teacherDto.setName(t.getName());
                teacherDto.setId(t.getId());

                //get faculty
                Faculty faculty = this.facultyRepository.findById(t.getFacultyId()).orElse(null);

                FacultyDto facultyDto = new FacultyDto(faculty.getId(), faculty.getName());

                teacherDto.setFaculty(facultyDto);

                teacherDtoList.add(teacherDto);

            }

        } catch (Exception e) {
            logger.error("error loading Teacher DTO details." + e.getMessage());
        }

        return teacherDtoList;


    }

    @Override
    public List<TeacherDto> findByName(String name) {
        List<Teacher> teacherList = this.teacherRepository.findByName(name);

        List<TeacherDto> teacherDtoList = new ArrayList<>();

        for (Teacher t : teacherList) {
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.setName(t.getName());
            teacherDto.setEmail(t.getEmail());

            teacherDtoList.add(teacherDto);

        }
        return teacherDtoList;
    }

    @Override
    public TeacherDto newTeacherDto(TeacherDto teacherDto) {

        try {

            Teacher teacherFound = this.teacherRepository.findByEmail(teacherDto.getEmail());

            if (teacherFound == null) {

                //ok to save
                Teacher teacher = new Teacher();
                teacher.setEmail(teacherDto.getEmail());
                teacher.setName(teacherDto.getName());
                teacher.setFacultyId(teacherDto.getFaculty().getId());
                teacher = this.teacherRepository.save(teacher);

                teacherDto.setId(teacher.getId());

            }

        } catch (Exception e) {
            logger.error("error saving Teacher DTO details." + e.getMessage());
        }

        return teacherDto;
    }
}
