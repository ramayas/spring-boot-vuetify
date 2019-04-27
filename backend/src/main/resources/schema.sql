CREATE TABLE faculties (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
);

CREATE TABLE teachers (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	email VARCHAR(100),
	faculty_id bigint NOT NULL
);

ALTER TABLE teachers ADD CONSTRAINT fk_teachers_faculty_id FOREIGN KEY (faculty_id) REFERENCES faculties(id);
