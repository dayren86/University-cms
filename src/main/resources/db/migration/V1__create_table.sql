CREATE TABLE university(
	id BIGSERIAL PRIMARY KEY,
	name_university VARCHAR(255) NOT NULL
);

CREATE TABLE groups(
    id BIGSERIAL PRIMARY KEY,
    name_group VARCHAR(255),
    university_id BIGINT,
    FOREIGN KEY (university_id) REFERENCES university(id) ON DELETE CASCADE
);

CREATE TABLE student(
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	groups_id BIGINT,
	university_id BIGINT,
	FOREIGN KEY (university_id) REFERENCES university(id) ON DELETE CASCADE,
	FOREIGN KEY (groups_id) REFERENCES groups(id) ON DELETE CASCADE
);

CREATE TABLE teacher(
    id BIGSERIAL PRIMARY KEY,
    name_teacher VARCHAR(255),
    university_id BIGINT,
    FOREIGN KEY (university_id) REFERENCES university(id) ON DELETE CASCADE
);

CREATE TABLE timetable(
    id BIGSERIAL PRIMARY KEY,
    local_date DATE,
    student_id BIGINT,
    teacher_id BIGINT,
    university_id BIGINT,
    FOREIGN KEY (university_id) REFERENCES university(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE
);

CREATE TABLE lesson(
    id BIGSERIAL PRIMARY KEY,
    name_lesson VARCHAR(255),
    time TIME,
    audience INTEGER,
    group_id BIGINT,
    teacher_id BIGINT,
    timetable_id BIGINT,
    university_id BIGINT,
    FOREIGN KEY (university_id) REFERENCES university(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE,
    FOREIGN KEY (timetable_id) REFERENCES timetable(id) ON DELETE CASCADE
);

