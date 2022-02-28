INSERT INTO schedule.student(full_name) VALUES ('Misha Ryzgunsky'),('Vasya Pupkin');

INSERT INTO schedule.lesson (name) VALUES ('Math'),('Geometry'),('Physics'),('Geography'),('Informatics');

INSERT INTO schedule.schedule(student_id) VALUES (1),(2);

INSERT INTO schedule.lesson_schedule(schedule_id, lesson_id) VALUES (1,1),(1,4),(1,2),(2,3),(2,1);

SELECT s.id AS id,st.id AS student_id, st.full_name AS full_name FROM schedule.schedule s JOIN schedule.student st ON s.student_id = st.id WHERE st.id = 2;