package education.edval.foo.dto;

public class FacultyDto {
    private Long id;
    private String name;

    public FacultyDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
