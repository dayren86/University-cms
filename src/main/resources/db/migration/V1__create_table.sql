DROP TABLE IF EXISTS university CASCADE;

CREATE TABLE university(
	id SERIAL PRIMARY KEY,
	name_university VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE student(
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	university_id BIGINT,
	FOREIGN KEY (university_id) REFERENCES university(id) ON DELETE CASCADE
);