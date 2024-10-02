CREATE SCHEMA IF NOT EXISTS jssi_education DEFAULT CHARACTER SET utf8;

Use jssi_education;

INSERT INTO `jssi_education`.`semester` (`semester`)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

INSERT INTO `jssi_education`.`day_week` (`day`)
VALUES ('Monday'), ('Tuesday'), ('Wednesday'), ('Thursday'), ('Friday'), ('Saturday'), ('Sunday');

INSERT INTO `user` (`id_number`, `name`, `lastname`, `user_name`, `email`, `password`, `role`)
VALUES
(123456, 'John', 'Doe', 'johndoe', 'johndoe@example.com', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'administrator'), -- admin
(789012, 'Jane', 'Smith', 'janesmith', 'janesmith@example.com', 'c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91', 'administrator'), -- admin
(345678, 'Alice', 'Johnson', 'alicejohnson', 'alicejohnson@example.com', '5efc2b017da4f7736d192a74dde5891369e0685d4d38f2a455b6fcdab282df9c', 'student'),-- student
(901234, 'Bob', 'Brown', 'bobbrown', 'bobbrown@example.com', 'a20aff106fe011d5dd696e3b7105200ff74331eeb8e865bb80ebd82b12665a07', 'student'), -- student
(567890, 'Charlie', 'Davis', 'charliedavis', 'charliedavis@example.com', '28e91b84bd4ac1d95d81b4510777d2b12f3dffa848bb6e219a42f98cdfa06d7d', 'student'), -- student
(234567, 'Emily', 'Clark', 'emilyclark', 'emilyclark@example.com', 'f6537a5a2f097921d1d1ab410facd30c4356da7326783c2f9ed29f093852cfe2', 'student'), -- student
(890123, 'David', 'Martinez', 'davidmartinez', 'davidmartinez@example.com', '19cb0711df0a5c9915c57bc8209b23da7b9ecae22627bc957eb25bcf53182a81', 'teacher'), -- teacher
(456789, 'Sophia', 'Lopez', 'sophialopez', 'sophialopez@example.com', 'facbd7953fa9ee64e1c57738eae96c5f32c415b370111de2b3cfa6b59b23ad00', 'teacher'), -- teacher
(123789, 'Liam', 'Wilson', 'liamwilson', 'liamwilson@example.com', 'b251019da9d473a1fca39713db51d7f612c93faa88bfc2ebe4f07dcfd47fd266', 'teacher'), -- teacher
(567123, 'Olivia', 'Garcia', 'oliviagarcia', 'oliviagarcia@example.com', '2ce45fb0bf5e07f9a599b713bf3981dfe94a0be138b18e74ceaed1f894cb0202', 'teacher'), -- teacher
(678901, 'Michael', 'Taylor', 'michaeltaylor', 'michaeltaylor@example.com', 'a34e1cc0c8b2b42bcbec122223f654b23e0de095bbe0b499816cf0d23746aaac', 'manager'), -- manager
(234890, 'Isabella', 'Moore', 'isabellamoore', 'isabellamoore@example.com', 'b84c4783ab67bf8f841be5b67907d753f268b87a19723338dbcd66de5f6bf01e', 'manager'), -- manager
(789456, 'Ethan', 'Anderson', 'ethananderson', 'ethananderson@example.com', 'a7c5bbb9aee1c49beb8819da6b5855aea43d0a6cf58b1b8bcf703ec74a4b359d', 'manager'), -- manager
(345678, 'Mia', 'Thomas', 'miathomas', 'miathomas@example.com', '55ab15f68e5fec2f15debbbda30d0ba0e5af8387a866359a8bb7a401e7b1f589', 'manager'); -- manager

INSERT INTO `administrator` (`user_id`)
VALUES
(1),
(2);

INSERT INTO `degree` (`name`)
VALUES
('Degree 1'),
('Degree 2');

INSERT INTO `course` (`course_name`, `semester_id`)
VALUES
('Course 1', 1),
('Course 2', 2),
('Course 3', 1),
('Course 4', 2);


INSERT INTO `degree_course` (`degree_id`, `course_id`)
VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4);

INSERT INTO `student` (`user_id`, `degree_id`)
VALUES
(3, 1),
(4, 1),
(5, 2),
(6, 2);


INSERT INTO `offer` (`start_time`, `end_time`)
VALUES
('08:00:00', '10:00:00'),
('09:00:00', '11:00:00'),
('10:00:00', '12:00:00'),
('08:30:00', '10:30:00'),
('09:30:00', '11:30:00'),
('11:00:00', '13:00:00'),
('12:00:00', '14:00:00'),
('13:00:00', '15:00:00'),
('14:00:00', '16:00:00'),
('15:00:00', '17:00:00');

INSERT INTO offer_day_week (day_week_id, offer_id)
VALUES
    ((SELECT id FROM day_week WHERE day = 'Monday'), 1),
    ((SELECT id FROM day_week WHERE day = 'Tuesday'), 1),
    ((SELECT id FROM day_week WHERE day = 'Wednesday'), 1),
    ((SELECT id FROM day_week WHERE day = 'Thursday'), 2),
    ((SELECT id FROM day_week WHERE day = 'Friday'), 2),
    ((SELECT id FROM day_week WHERE day = 'Monday'), 2),
    ((SELECT id FROM day_week WHERE day = 'Tuesday'), 3),
    ((SELECT id FROM day_week WHERE day = 'Wednesday'), 3),
    ((SELECT id FROM day_week WHERE day = 'Thursday'), 3),
    ((SELECT id FROM day_week WHERE day = 'Friday'), 4),
    ((SELECT id FROM day_week WHERE day = 'Monday'), 4),
    ((SELECT id FROM day_week WHERE day = 'Tuesday'), 5),
    ((SELECT id FROM day_week WHERE day = 'Wednesday'), 5),
    ((SELECT id FROM day_week WHERE day = 'Thursday'), 6),
    ((SELECT id FROM day_week WHERE day = 'Friday'), 6);

INSERT INTO teacher (specialization, user_id)
VALUES
    ('Mathematics', 7),
    ('Physics', 8),
    ('Chemistry', 9),
    ('Biology', 10);

INSERT INTO user_group (classroom, number_students, offer_day_week_id, teacher_id)
VALUES
    ('room2', 25, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Tuesday') AND offer_id = 1), 1),  -- David Martinez, martes en room2
    ('room3', 20, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Wednesday') AND offer_id = 1), 2),  --  Sophia Lopez, miercoles en room3
    ('room1', 35, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Thursday') AND offer_id = 2), 2),  --  Sophia Lopez, jueves en room1
    ('room2', 28, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Friday') AND offer_id = 2), 3),  --  Liam Wilson, viernes en room2
    ('room3', 22, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Monday') AND offer_id = 2), 3),  --  Liam Wilson, lunes en room3
    ('room1', 30, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Tuesday') AND offer_id = 3), 4),  --  Olivia Garcia, martes en room1
    ('room2', 26, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Wednesday') AND offer_id = 3), 4),  --  Olivia Garcia, miercoles en room2
    ('room3', 23, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Thursday') AND offer_id = 3), 1),  --  David Martinez, jueves en room3
    ('room1', 30, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Friday') AND offer_id = 4), 2),  --  Sophia Lopez, viernes en room1
    ('room2', 25, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Monday') AND offer_id = 4), 3),  --  Liam Wilson, lunes en room2
    ('room3', 28, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Tuesday') AND offer_id = 5), 4),  -- r Olivia Garcia, martes en room3
    ('room1', 20, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Wednesday') AND offer_id = 5), 1),  --  David Martinez, miércoles en room1
    ('room2', 32, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Thursday') AND offer_id = 6), 2),  --  Sophia Lopez, jueves en room2
    ('room3', 27, (SELECT id FROM offer_day_week WHERE day_week_id = (SELECT id FROM day_week WHERE day = 'Friday') AND offer_id = 6), 3);   --  Liam Wilson, viernes en room3


INSERT INTO group_course (course_id, group_id) VALUES
    (1, 1),
    (1, 2),
    (3, 7),
    (3, 8),
	(2, 3),
    (2, 4),
    (4, 5),
    (4, 6),
    (2, 9),
    (2, 10);


INSERT INTO didactic_material (description, group_has_course_id) VALUES
('Material 1', 1),
('Material 2', 2),
('Material 3', 3),
('Material 4', 4),
('Material 5', 1),
('Material 6', 3);

INSERT INTO attendance (date, status, group_has_course_id, student_id) VALUES
('2024-09-01', true, 1, 1),
('2024-09-01', false, 1, 2),
('2024-09-01', true, 1, 3),
('2024-09-01', false, 1, 4),
('2024-09-01', true, 2, 1),
('2024-09-01', false, 2, 2),
('2024-09-01', true, 2, 3),
('2024-09-01', false, 2, 4),
('2024-09-01', true, 3, 1),
('2024-09-01', false, 3, 2),
('2024-09-01', true, 3, 3),
('2024-09-01', false, 3, 4),
('2024-09-01', true, 4, 1),
('2024-09-01', false, 4, 2),
('2024-09-01', true, 4, 3),
('2024-09-01', false, 4, 4);

INSERT INTO grade (follow_up, status, test_one, test_two, group_has_course_id, student_id) VALUES
(2.5, false, 3.0, 2.0, 1, 1),
(4.5, true, 4.0, 5.0, 1, 2),
(3.5, false, 3.0, 3.0, 3, 3),
(4.5, true, 4.0, 4.5, 3, 4),
(3.0, false, 3.5, 2.5, 5, 1),
(4.0, true, 4.5, 4.5, 5, 2),
(1.5, false, 1.0, 2.0, 7, 3),
(4.0, true, 4.5, 4.0, 7, 4);


INSERT INTO teacher_evaluation (evaluation, group_has_course_id, student_id) VALUES
(4.5, 1, 1),
(3.0, 1, 2),
(2.5, 1, 3),
(5.0, 1, 4),
(4.0, 2, 1),
(3.5, 2, 2),
(1.0, 2, 3),
(4.5, 2, 4),
(3.0, 3, 1),
(4.0, 3, 2),
(2.0, 3, 3),
(4.5, 3, 4),
(5.0, 4, 1),
(2.5, 4, 2),
(3.5, 4, 3),
(4.0, 4, 4);

