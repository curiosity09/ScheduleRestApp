CREATE SCHEMA IF NOT EXISTS schedule;

CREATE TABLE IF NOT EXISTS schedule.student
(
    id        SERIAL PRIMARY KEY,
    full_name CHARACTER VARYING
);

CREATE TABLE IF NOT EXISTS schedule.lesson
(
    id   SERIAL PRIMARY KEY,
    name CHARACTER VARYING
);

CREATE TABLE IF NOT EXISTS schedule.schedule
(
    id         SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES schedule.student (id)
);

CREATE TABLE IF NOT EXISTS schedule.lesson_schedule
(
    schedule_id INTEGER REFERENCES schedule.schedule (id),
    lesson_id   INTEGER REFERENCES schedule.lesson (id)
);
