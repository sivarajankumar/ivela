INSERT INTO "IVELA"."SYSTEM_USER" (id, email, social_number, username, "PASSWORD", enabled, profile, last_unit_content, created_at, authentication, recovery_question, recovery_answer) VALUES (1, '', '', '', 'English4Smart', 0, 1, 1, NULL, 0, '', '');

INSERT INTO "IVELA".course (id, name, description, image, target_audience, contents, repository_structure, active, upload_package_enabled, custom_toc, challenge_retries, challenge_count, challenge_weight) VALUES (1, 'Professional English e-Course', 'Curso de Inglês para negócios, para os níveis básico e intermediário na metodologia de ensino à distância.', '/opt/ivela/upload/1/RenderServletPartner.png', 'Este curso está disponível no momento para alunos do CETEEPS (Fatecs, Etecs), Fundação Bradesco e SENAIs.', 'O conteúdo é dividido nos seguintes temas: Apresentações, Identificações, Convivência no Escritório, Entrevistas, Descrição de Produtos, Falar ao Telefone, Planos, Localizações, Eventos, Treinamentos para sucesso na Carreira, Descrição de Problemas no Trabalho, Introdução de um novo colega no Trabalho, Decisões, Situações de Mudanças, Etiqueta Cooporativa e Vídeo conferência. Cada tema ensinará práticas de vocabulário e gramaticais, exercitando as habilidades de ouvir, escrever, ler e falar.', '<repository>
  <id>1</id>
  <next__value>2</next__value>
  <created__at>Mon Nov 10 05:15:55 EST 2008</created__at>
  <directory>
    <id>1</id>
    <name></name>
    <created__at>Tue May 05 06:07:02 BRT 2009</created__at>
  </directory>
</repository>', 1, 1, 1, 2, 773, 773);

INSERT INTO "IVELA".discipline (id, course, name, tag) VALUES (1, 1, 'Module 1', 'module1');
INSERT INTO "IVELA".discipline (id, course, name, tag) VALUES (2, 1, 'Module 2', 'module2');
INSERT INTO "IVELA".discipline (id, course, name, tag) VALUES (3, 1, 'Module 3', 'module3');

INSERT INTO "IVELA".unit (id, discipline, name, active) VALUES (1, 1, 'Unit One', 1);
INSERT INTO "IVELA".unit (id, discipline, name, active) VALUES (2, 2, 'Unit One', 1);
INSERT INTO "IVELA".unit (id, discipline, name, active) VALUES (3, 3, 'Unit One', 1);

INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (1, 1, 1, 'Lesson 1', 'none', 2, 820, 1500, '01:34:30', 'lesson1');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (2, 2, 1, 'Lesson 2', 'none', 2, 820, 1500, '00:42:00', 'lesson2');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (3, 3, 1, 'Lesson 3', 'none', 2, 820, 1500, '00:35:00', 'lesson3');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (4, 4, 1, 'Lesson 4', 'none', 2, 820, 1500, '01:27:30', 'lesson4');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (5, 5, 1, 'Lesson 5', 'none', 2, 820, 1500, '00:42:00', 'lesson5');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (6, 6, 1, 'Lesson 6', 'none', 2, 820, 1500, '00:59:30', 'lesson6');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (7, 7, 1, 'Lesson 7', 'none', 2, 820, 1500, '02:06:00', 'lesson7');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (8, 8, 1, 'Lesson 8', 'none', 2, 820, 1500, '01:17:00', 'lesson8');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (9, 9, 1, 'Lesson 9', 'none', 2, 820, 1500, '00:35:00', 'lesson9');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (10, 10, 1, 'Lesson 10', 'none', 2, 820, 1500, '00:42:00', 'lesson10');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (11, 11, 1, 'Lesson 11', 'none', 2, 820, 1500, '00:52:30', 'lesson11');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (12, 12, 1, 'Lesson 12', 'none', 2, 820, 1500, '01:06:30', 'lesson12');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (13, 13, 1, 'Lesson 13', 'none', 2, 820, 1500, '02:13:00', 'lesson13');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (14, 14, 1, 'Lesson 14', 'none', 2, 820, 1500, '01:03:00', 'lesson14');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (15, 15, 1, 'Lesson 15', 'none', 2, 820, 1500, '01:13:30', 'lesson15');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (16, 16, 1, 'Lesson 16', 'none', 2, 820, 1500, '01:20:30', 'lesson16');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (17, 17, 1, 'Lesson 17', 'none', 2, 820, 1500, '01:10:00', 'lesson17');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (18, 18, 1, 'Lesson 18', 'none', 2, 820, 1500, '00:52:30', 'lesson18');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (19, 19, 1, 'Lesson 19', 'none', 2, 820, 1500, '01:24:00', 'lesson19');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (20, 20, 1, 'Lesson 20', 'none', 2, 820, 1500, '00:52:30', 'lesson20');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (21, 21, 1, 'Lesson 21', 'none', 2, 820, 1500, '01:31:00', 'lesson21');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (22, 22, 2, 'Lesson 1', 'none', 2, 820, 1500, '00:49:00', 'lesson1');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (23, 23, 2, 'Lesson 2', 'none', 2, 820, 1500, '00:59:30', 'lesson2');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (24, 24, 2, 'Lesson 3', 'none', 2, 820, 1500, '01:34:30', 'lesson3');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (25, 25, 2, 'Lesson 4', 'none', 2, 820, 1500, '01:10:00', 'lesson4');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (26, 26, 2, 'Lesson 5', 'none', 2, 820, 1500, '01:41:30', 'lesson5');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (27, 27, 2, 'Lesson 6', 'none', 2, 820, 1500, '01:17:00', 'lesson6');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (28, 28, 2, 'Lesson 7', 'none', 2, 820, 1500, '01:06:30', 'lesson7');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (29, 29, 2, 'Lesson 8', 'none', 2, 820, 1500, '01:52:00', 'lesson8');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (30, 30, 2, 'Lesson 9', 'none', 2, 820, 1500, '00:56:00', 'lesson9');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (31, 31, 2, 'Lesson 10', 'none', 2, 820, 1500, '01:17:00', 'lesson10');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (32, 32, 2, 'Lesson 11', 'none', 2, 820, 1500, '01:27:30', 'lesson11');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (33, 33, 2, 'Lesson 12', 'none', 2, 820, 1500, '01:27:30', 'lesson12');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (34, 34, 2, 'Lesson 13', 'none', 2, 820, 1500, '00:59:30', 'lesson13');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (35, 35, 2, 'Lesson 14', 'none', 2, 820, 1500, '01:10:00', 'lesson14');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (36, 36, 2, 'Lesson 15', 'none', 2, 820, 1500, '01:17:00', 'lesson15');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (37, 37, 2, 'Lesson 16', 'none', 2, 820, 1500, '01:17:00', 'lesson16');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (38, 38, 2, 'Lesson 17', 'none', 2, 820, 1500, '01:38:00', 'lesson17');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (39, 39, 2, 'Lesson 18', 'none', 2, 820, 1500, '00:49:00', 'lesson18');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (40, 40, 2, 'Lesson 19', 'none', 2, 820, 1500, '00:59:30', 'lesson19');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (41, 41, 2, 'Lesson 20', 'none', 2, 820, 1500, '01:13:30', 'lesson20');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (42, 42, 2, 'Lesson 21', 'none', 2, 820, 1500, '01:38:00', 'lesson21');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (43, 43, 3, 'Lesson 1', 'none', 2, 820, 1500, '02:13:00', 'lesson1');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (44, 44, 3, 'Lesson 2', 'none', 2, 820, 1500, '01:13:30', 'lesson2');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (45, 45, 3, 'Lesson 3', 'none', 2, 820, 1500, '01:31:00', 'lesson3');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (46, 46, 3, 'Lesson 4', 'none', 2, 820, 1500, '01:48:30', 'lesson4');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (47, 47, 3, 'Lesson 5', 'none', 2, 820, 1500, '02:06:00', 'lesson5');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (48, 48, 3, 'Lesson 6', 'none', 2, 820, 1500, '01:31:00', 'lesson6');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (49, 49, 3, 'Lesson 7', 'none', 2, 820, 1500, '01:24:00', 'lesson7');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (50, 50, 3, 'Lesson 8', 'none', 2, 820, 1500, '01:27:30', 'lesson8');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (51, 51, 3, 'Lesson 9', 'none', 2, 820, 1500, '01:31:00', 'lesson9');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (52, 52, 3, 'Lesson 10', 'none', 2, 820, 1500, '01:34:30', 'lesson10');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (53, 53, 3, 'Lesson 11', 'none', 2, 820, 1500, '01:17:00', 'lesson11');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (54, 54, 3, 'Lesson 12', 'none', 2, 820, 1500, '01:17:00', 'lesson12');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (55, 55, 3, 'Lesson 13', 'none', 2, 820, 1500, '01:06:30', 'lesson13');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (56, 56, 3, 'Lesson 14', 'none', 2, 820, 1500, '01:38:00', 'lesson14');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (57, 57, 3, 'Lesson 15', 'none', 2, 820, 1500, '00:56:00', 'lesson15');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (58, 58, 3, 'Lesson 16', 'none', 2, 820, 1500, '01:41:30', 'lesson16');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (59, 59, 3, 'Lesson 17', 'none', 2, 820, 1500, '00:59:30', 'lesson17');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (60, 60, 3, 'Lesson 18', 'none', 2, 820, 1500, '01:34:30', 'lesson18');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (61, 61, 3, 'Lesson 19', 'none', 2, 820, 1500, '01:13:30', 'lesson19');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (62, 62, 3, 'Lesson 20', 'none', 2, 820, 1500, '01:48:30', 'lesson20');
INSERT INTO "IVELA".unit_content (id, order_n, unit, title, description, type, width, height, duration, tag) VALUES (63, 63, 3, 'Lesson 21', 'none', 2, 820, 1500, '01:31:00', 'lesson21');

INSERT INTO "IVELA".transcript(
            id, grade, "SYSTEM_USER", status, score, average_exercise, average_exam, 
            manual_score, average_challenge, challenges_done, challenges_weight, 
            challenges_total)
    VALUES (1, 1, 1, 3, 0.0, 0.0, 0.0, 
            0, 0.0, 0, 0, 0.0);

INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (445, 'mod1uni1les1p2c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p2c">
<field name = "a" value="morning"/>
<field name = "b" value="Good"/>
<field name = "c" value="hello[or]hi"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 450, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (448, 'mod1uni1les1p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p8">
<field name = "a" value="morning "/>
<field name = "b" value="Hello"/>
<field name = "b|1" value="meet"/>
<field name = "c" value="Nice "/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 447, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (450, 'mod1uni1les1p10c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p10c">
<field name = "a" value="am[or]''m"/>
<field name = "b" value="is[or]''s"/>
<field name = "c" value="are[or]''re"/>
<field name = "d" value="are"/>
<field name = "e" value="is"/>
<field name = "f" value="is"/>
<field name = "g" value="is"/>
<field name = "h" value="is"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 449, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (657, 'mod1uni1les19p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p2">
<field name = "a" value="Juliet Thomas"/>
<field name = "b" value="No, she doesn''t"/>
<field name = "c" value="No, he isn''t"/>
<field name = "d" value="Yes, she does"/>
<field name = "e" value="Yes, he does"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (769, 'mod2uni1les9p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p21">
<field name = "a" value="Sunny[or]sunny"/>
<field name = "b" value="Rainy[or]rainy"/>
<field name = "c" value="Humid[or]humid"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (773, 'mod2uni1les9p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p25">
<field name = "n" value="Snowing[or]snowing"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (777, 'mod2uni1les9p31', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p31">
<field name = "a" value="finds"/>
<field name = "a|1" value="training"/>
<field name = "a|2" value="different"/>
<field name = "a|3" value="late"/>
<field name = "a|4" value="sleep"/>
<field name = "b" value="excellent"/>
<field name = "b|1" value="important"/>
<field name = "b|2" value="problem"/>
<field name = "b|3" value="restaurant"/>
<field name = "c" value="intense"/>
<field name = "c|1" value="American"/>
<field name = "c|2" value="important"/>
<field name = "c|3" value="invests"/>
<field name = "d" value="start"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (765, 'mod2uni1les10p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p5">
<field name = "a" value="was"/>
<field name = "b" value="were"/>
<field name = "c" value="were"/>
<field name = "d" value="was"/>
<field name = "e" value="were"/>
<field name = "f" value="was"/>
<field name = "f|1" value="were"/>
<field name = "g" value="was"/>
<field name = "h" value="were"/>
</challenge>
</payload>
</ivela>
', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (793, 'mod2uni1les10p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p11">
<field name = "a" value="True"/>
<field name = "b" value="True"/>
<field name = "c" value="False"/>
<field name = "d" value="False"/>
<field name = "e" value="I don''t know"/>
<field name = "f" value="I don''t know"/>
<field name = "g" value="False"/>
<field name = "h" value="True"/>
<field name = "i" value="False"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (803, 'mod2uni1les11p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p14">
<field name="a" value="negotiate"/>
<field name="b" value="increase"/>
<field name="c" value="decrease"/>
<field name="c|1" value="complain"/>
<field name="d" value="reduce"/>
<field name="d|1" value="produce"/>
<field name="e" value="request"/>
<field name="e|1" value="adjust"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (807, 'mod2uni1les11p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p19">
<field name = "a" value="We evaluated our career goals and achievements"/>
<field name = "b" value="We also analyzed how important it is to embrace challenges and to learn on a daily basis"/>
<field name = "c" value="We worked in groups and discussed some topical issues"/>
<field name = "d" value="We also requested this training in our department"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (902, 'mod2uni1les20p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p5">
<field name = "g" value="Language proficiency"/>
<field name = "h" value="Additional information"/>
<field name = "i" value="References"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (906, 'mod2uni1les20p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p12">
<field name = "e" value="Peter"/>
<field name = "f" value="Peter"/>
<field name = "g" value="John"/>
<field name = "h" value="Peter"/>
<field name = "i" value="Peter"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (446, 'mod1uni1les1p3b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p3b">
<field name = "a" value="bye"/>
<field name = "b" value="you"/>
<field name = "c" value="See"/>
<field name = "d" value="Good"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 445, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (451, 'mod1uni1les1p11c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p11c">
<field name = "a" value="isn''t[or]is not"/>
<field name = "b" value="aren''t[or]are not"/>
<field name = "c" value="isn''t[or]is not"/>
<field name = "d" value="isn''t[or]is not"/>
<field name = "e" value="am not"/>
<field name = "f" value="isn''t[or]is not"/>
<field name = "g" value="aren''t[or]are not"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 450, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (454, 'mod1uni1les2p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les2p3">
<field name = "a" value="French"/>
<field name = "b" value="Polish"/>
<field name = "c" value="Japanese"/>
<field name = "d" value="Russian"/>
<field name = "e" value="Chinese"/>
<field name = "f" value="Brazilian"/>
<field name = "g" value="Turkish"/>
<field name = "h" value="Spanish"/>
<field name = "i" value="German"/>
<field name = "j" value="British"/>
<field name = "k" value="Dutch"/>
<field name = "l" value="Italian"/>
<field name = "m" value="American"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 452, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (457, 'mod1uni1les2p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les2p9">
<field name = "a" value="American"/>
<field name = "b" value="Canadian"/>
<field name = "c" value="French"/>
<field name = "d" value="Brazil"/>
<field name = "e" value="Portugal"/>
<field name = "f" value="Argentina"/>
<field name = "g" value="Japan"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 456, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (658, 'mod1uni1les21p1', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p1">
<field name = "a" value="call"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (661, 'mod1uni1les21p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p4">
<field name = "d" value="the most efficient"/>
</challenge>
</payload>
</ivela>
', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (665, 'mod1uni1les21p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p8">
<field name = "h" value="receipt"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (669, 'mod1uni1les21p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p12">
<field name = "m" value="can"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (673, 'mod1uni1les21p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p16">
<field name = "q" value="Sometimes."/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (677, 'mod1uni1les21p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p20">
<field name = "u" value="book"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (681, 'mod1uni1les21p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p24">
<field name = "a" value="Does"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (748, 'mod2uni1les5p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p21">
<field name = "f" value="cheap"/>
<field name = "g" value="sophisticated"/>
<field name = "h" value="plain"/>
<field name = "i" value="useful"/>
<field name = "j" value="useless"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (774, 'mod2uni1les9p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p26">
<field name = "b" value="It was mild and sunny in Baltimore"/>
<field name = "c" value="It was hot and humid in Hawaii"/>
<field name = "d" value="It was cold and changeable in Hollywood"/>
<field name = "e" value="It was cold and windy in France"/>
<field name = "f" value="It was cold and overcast in China"/>
<field name = "g" value="It was hot and rainy in Barcelona"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (770, 'mod2uni1les9p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p22">
<field name = "d" value="minus 5C"/>
<field name = "e" value="Changeable[or]changeable"/>
<field name = "f" value="Windy[or]windy"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (794, 'mod2uni1les10p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p12">
<field name = "a" value="E[or]e"/>
<field name = "b" value="C[or]c"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="D[or]d"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (797, 'mod2uni1les9p03', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p03">
<field name = "a" value="Positive"/>
<field name = "b" value="Negative"/>
<field name = "c" value="Negative"/>
<field name = "d" value="Negative"/>
<field name = "e" value="Positive"/>
<field name = "f" value="Negative"/>
<field name = "g" value="Positive"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (447, 'mod1uni1les1p6b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p6b">
<field name = "a" value="Good"/>
<field name = "b" value="Mr"/>
<field name = "c" value="Mr"/>
<field name = "d" value="Mrs"/>
<field name = "e" value="Mr"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 446, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (453, 'mod1uni1les1p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p14">
<field name = "a" value="Hi"/>
<field name = "b" value="from"/>
<field name = "c" value="nice"/>
<field name = "c|1" value="name''s"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 452, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (456, 'mod1uni1les2p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les2p7">
<field name = "a" value="I''m Chico. I''m from Italy."/>
<field name = "b" value="I''m Diego. I''m from France. "/>
<field name = "c" value="Where is Carolina from? "/>
<field name = "d" value="Are you from Brazil? "/>
<field name = "e" value="My name is Phil and I''m Austrian."/>
<field name = "f" value="She is not Argentinian. She''s from Madrid"/>
<field name = "g" value="Are they from KLM? "/>
<field name = "h" value="I am American. I''m from Chicago. "/>
<field name = "i" value="Is Mr Stuart Spanish? "/>
<field name = "j" value="Is McDonald''s American? "/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 455, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (659, 'mod1uni1les21p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p2">
<field name = "b" value="hold"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (459, 'mod1uni1les2p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les2p11">
<field name = "a" value="afternoon"/>
<field name = "b" value="name''s"/>
<field name = "c" value="Hungarian"/>
<field name = "d" value="with"/>
<field name = "e" value="are"/>
<field name = "f" value="Canadian"/>
<field name = "g" value="is"/>
<field name = "g|1" value="Mexican"/>
<field name = "h" value="He''s"/>
<field name = "i" value="aren''t"/>
<field name = "i|1" value="We''re"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 458, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (663, 'mod1uni1les21p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p6">
<field name = "f" value="low"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (667, 'mod1uni1les21p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p10">
<field name = "j" value="broken"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (671, 'mod1uni1les21p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p14">
<field name = "o" value="How often"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (675, 'mod1uni1les21p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p18">
<field name = "s" value="on"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (679, 'mod1uni1les21p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p22">
<field name = "x" value="do"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (683, 'mod1uni1les21p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p26">
<field name = "c" value="When do"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (771, 'mod2uni1les9p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p23">
<field name = "g" value="Cold[or]cold"/>
<field name = "h" value="Overcast[or]overcast"/>
<field name = "i" value="Hot[or]hot"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (775, 'mod2uni1les9p28', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p28">
<field name = "b" value="yesterday [or] YESTERDAY"/>
<field name = "c" value="two weeks ago"/>
<field name = "d" value="three days ago"/>
<field name = "e" value="last week"/>
<field name = "f" value="last night"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (767, 'mod2uni1les9p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p5">
<field name = "o" value="Negative"/>
<field name = "p" value="Negative"/>
<field name = "q" value="Positive"/>
<field name = "r" value="Positive"/>
<field name = "s" value="Negative"/>
<field name = "t" value="Negative"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (798, 'mod2uni1les9p04', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p04">
<field name = "h" value="Positive"/>
<field name = "i" value="Positive"/>
<field name = "j" value="Positive"/>
<field name = "k" value="Negative"/>
<field name = "l" value="Negative"/>
<field name = "m" value="Negative"/>
<field name = "n" value="Negative"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (749, 'mod2uni1les7p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p2">
<field name="a" value="Accurate[or]accurate"/>
<field name="b" value="Advisable[or]advisable"/>
<field name="c" value="Appropriate[or]appropriate"/>
<field name="d" value="Articulate[or]articulate"/>
<field name="e" value="Audible[or]audible"/>
<field name="f" value="Believable[or]believable"/>
<field name="g" value="Coherent[or]coherent"/>
<field name="h" value="Cover[or]cover"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (660, 'mod1uni1les21p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p3">
<field name = "c" value="He can talk to the person in a minute."/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (664, 'mod1uni1les21p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p7">
<field name = "g" value="better"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (449, 'mod1uni1les1p9b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p9b">
<field name = "a" value="Carlo"/>
<field name = "b" value="John"/>
<field name = "c" value="Your teacher"/>
<field name = "d" value="Your manager"/>
<field name = "e" value="Your boss"/>
<field name = "f" value="Your supervisor"/>
<field name = "g" value="Diana"/>
<field name = "h" value="Claudia"/>
<field name = "i" value="Carolina"/>
<field name = "j" value="Your teacher"/>
<field name = "k" value="Your manager"/>
<field name = "l" value="Your boss"/>
<field name = "m" value="Your supervisor"/>
<field name = "n" value="Your city"/>
<field name = "o" value="Your department"/>
<field name = "p" value="Your office"/>
<field name = "q" value="Ronald and you"/>
<field name = "r" value="Your colleagues and you"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (668, 'mod1uni1les21p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p11">
<field name = "l" value="invite"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (672, 'mod1uni1les21p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p15">
<field name = "p" value="Who"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (676, 'mod1uni1les21p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p19">
<field name = "t" value="single"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (680, 'mod1uni1les21p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p23">
<field name = "z" value="there is"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (776, 'mod2uni1les9p30', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p30">
<field name = "a" value="Last month"/>
<field name = "b" value="Late and turbulent"/>
<field name = "c" value="Very good"/>
<field name = "d" value="Friendly"/>
<field name = "e" value="Efficient"/>
<field name = "f" value="Productive"/>
<field name = "g" value="Yes, she was"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (768, 'mod2uni1les9p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p6">
<field name = "a" value="H[or]h"/>
<field name = "b" value="F[or]f"/>
<field name = "c" value="I[or]i"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="A[or]a"/>
<field name = "f" value="C[or]c"/>
<field name = "g" value="D[or]d"/>
<field name = "h" value="G[or]g"/>
<field name = "i" value="E[or]e"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (750, 'mod2uni1les7p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p3">
<field name="a" value="Do[or]do"/>
<field name="b" value="Efficient[or]efficient"/>
<field name="c" value="Employed[or]employed"/>
<field name="d" value="Employment[or]employment"/>
<field name="e" value="Expected[or]expected"/>
<field name="f" value="Expensive[or]expensive"/>
<field name="g" value="Fortunately[or]fortunately"/>
<field name="h" value="Healthy[or]healthy"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (806, 'mod2uni1les11p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p18">
<field name = "a" value="4"/>
<field name = "b" value="1"/>
<field name = "c" value="10"/>
<field name = "d" value="3"/>
<field name = "e" value="6"/>
<field name = "f" value="9"/>
<field name = "g" value="2"/>
<field name = "h" value="7"/>
<field name = "i" value="5"/>
<field name = "j" value="8"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (772, 'mod2uni1les9p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p24">
<field name = "j" value="Mild[or]mild"/>
<field name = "l" value="Cloudy[or]cloudy"/>
<field name = "m" value="18C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (796, 'mod2uni1les10p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p14">
<field name = "a" value="helpful"/>
<field name = "b" value="friendly"/>
<field name = "c" value="good[and]short"/>
<field name = "d" value="staff[and]products"/>
<field name = "e" value="survey"/>
<field name = "f" value="weather"/>
<field name = "g" value="crowded"/>
<field name = "h" value="fantastic"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (799, 'mod2uni1les9p05', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les9p05">
<field name = "o" value="Negative"/>
<field name = "p" value="Negative"/>
<field name = "q" value="Positive"/>
<field name = "r" value="Positive"/>
<field name = "s" value="Negative"/>
<field name = "t" value="Negative"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (802, 'mod2uni1les11p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p9">
<field name="a" value="major markets"/>
<field name="b" value="prospective clients"/>
<field name="c" value="complementary course"/>
<field name="d" value="benchmarking"/>
<field name="e" value="stockholders"/>
<field name="f" value="competitor"/>
<field name="g" value="long-term relationships"/>
<field name="h" value="Sales figures"/>
<field name="h|1" value="quarter"/>
<field name="i" value="market share"/>
<field name="i|1" value="volume of sales"/>
<field name="j" value="target clients"/>
<field name="j|1" value="network"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (452, 'mod1uni1les1p12c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p12c">
<field name = "a" value="Is she Janet"/>
<field name = "b" value="Are you here for the conference"/>
<field name = "c" value="Are your colleagues in the office"/>
<field name = "d" value="Is the boss in the office"/>
<field name = "e" value="Is the department over there"/>
<field name = "f" value="Is your office small"/>
<field name = "g" value="Is your company big"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 451, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (662, 'mod1uni1les21p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p5">
<field name = "e" value="the latest"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (666, 'mod1uni1les21p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p9">
<field name = "i" value ="is finishing"/>  
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (670, 'mod1uni1les21p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p13">
<field name = "n" value="reliable"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (674, 'mod1uni1les21p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p17">
<field name = "r" value="has"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (678, 'mod1uni1les21p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p21">
<field name = "v" value="at"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (910, 'mod2uni1les20p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p19">
<field name = "a" value="bought"/>
<field name = "a|1" value="was"/>
<field name = "a|2" value="asked"/>
<field name = "b" value="arrived"/>
<field name = "b|1" value="was"/>
<field name = "c" value="lost"/>
<field name = "d" value="tried"/>
<field name = "d|1" value="was"/>
<field name = "e" value="did you apply"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (682, 'mod1uni1les21p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les21p25">
<field name = "b" value="hasn''t"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (751, 'mod2uni1les7p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p4">
<field name="a" value="Legal[or]legal"/>
<field name="b" value="Legible[or]legible"/>
<field name="c" value="Literate[or]literate"/>
<field name="d" value="Lock[or]lock"/>
<field name="e" value="Modest[or]modest"/>
<field name="f" value="Pack[or]pack"/>
<field name="g" value="Polite[or]polite"/>
<field name="h" value="Possible[or]possible"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (766, 'mod2uni1les10p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p6">
<field name = "a" value="We were very late for the meeting"/>
<field name = "b" value="They were the best professionals in their area"/>
<field name = "c" value="Barbara was thirty five years old"/>
<field name = "d" value="This company was seen as reliable and well-established"/>
<field name = "e" value="The shareholders and investors of Petrobras were worried"/>
<field name = "f" value="We were always doing the same things to address this problem"/>
<field name = "g" value="I was convinced of the best way forward for the company"/>
<field name = "h" value="She was making decisions without consulting her boss"/>
</challenge>
</payload>
</ivela>		
', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (804, 'mod2uni1les11p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p15">
<field name="f" value="provide"/>
<field name="g" value="improve"/>
<field name="h" value="outcomes"/>
<field name="i" value="reach"/>
<field name="j" value="launch"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (808, 'mod2uni1les11p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p20">
<field name = "e" value="We received some handouts with good reminders for practical use"/>
<field name = "f" value="Whatever the problem or the situation, we will always try to find creative solutions"/>
<field name = "g" value="I see the training was really effective!"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (455, 'mod1uni1les2p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les2p5">
<field name = "a" value="are"/>
<field name = "b" value="m"/>
<field name = "c" value="s"/>
<field name = "d" value="s"/>
<field name = "e" value="are"/>
<field name = "f" value="are"/>
<field name = "g" value="aren''t"/>
<field name = "h" value="is"/>
<field name = "i" value="isn''t"/>
<field name = "j" value="is"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 454, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (458, 'mod1uni1les2p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les2p10">
<field name = "a" value="FALSE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="TRUE"/>
<field name = "e" value="TRUE"/>
<field name = "f" value="FALSE"/>
<field name = "g" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 457, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (752, 'mod2uni1les7p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p5">
<field name = "a" value="Profitable[or]profitable"/>
<field name = "b" value="Regular[or]regular"/>
<field name = "c" value="Relevant[or]relevant"/>
<field name = "d" value="Significant[or]significant"/>
<field name = "e" value="Sustainable[or]sustainable"/>
<field name = "f" value="Usual[or]usual"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (805, 'mod2uni1les11p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p17">
<field name="a" value="True"/>
<field name="b" value="False"/>
<field name="c" value="True"/>
<field name="d" value="True"/>
<field name="e" value="False"/>
<field name="f" value="I dont''t know"/>
<field name="g" value="False"/>
<field name="h" value="True"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (911, 'mod2uni1les20p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p20">
<field name = "f" value="did you interview"/>
<field name = "g" value="Did you send"/>
<field name = "h" value="didn''t remember"/>
<field name = "j" value="Were"/>
<field name = "j" value="Did you schedule"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (778, 'mod2uni1les10p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p7">
<field name = "b" value="wasn''t"/>
<field name = "c" value="wasn''t"/>
<field name = "d" value="weren''t"/>
<field name = "e" value="wasn''t"/>
<field name = "f" value="wasn''t"/>
<field name = "g" value="weren''t"/>
<field name = "h" value="wasn''t"/>
<field name = "i" value="weren''t"/>
</challenge>
</payload>
</ivela>	
', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (460, 'mod1uni1les3p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les3p9">
<field name = "a" value="Sorry"/>
<field name = "b" value="I''m"/>
<field name = "c" value="first"/>
<field name = "d" value="M-A-R-I-A"/>
<field name = "d|1" value="family"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 459, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (685, 'mod2uni1les1p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p5">
<field name = "a" value="G[or]g"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="E[or]e"/>
<field name = "f" value="C[or]c"/>
<field name = "g" value="D[or]d"/>
<field name = "h" value="H[or]h"/>
<field name = "i" value="I[or]i"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 688, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (689, 'mod2uni1les1p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p13">
<field name = "a" value="5"/>
<field name = "b" value="2"/>
<field name = "c" value="4"/>
<field name = "d" value="1"/>
<field name = "e" value="3"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 688, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (753, 'mod2uni1les7p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p9">
<field name = "a" value="Inexpensive[or]inexpensive"/>
<field name = "b" value="Irresponsible[or]irresponsible"/>
<field name = "c" value="Insignificant[or]insignificant"/>
<field name = "d" value="Unusual[or]unusual"/>
<field name = "e" value="Inadvisable[or]inadvisable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (779, 'mod2uni1les10p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p8">
<field name = "b" value="Was my coffee machine broken?"/>
<field name = "c" value="Were we targeted as their main public?"/>
<field name = "d" value="Was it an operational problem?"/>
<field name = "e" value="Was the invoice for my new printer correct?"/>
<field name = "f" value="Were they starting to expand to Asia?"/>
<field name = "g" value="Was she difficult to work with?"/>
</challenge>
</payload>
</ivela>		
', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (809, 'mod2uni1les6p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p7">
<field name = "a" value="on"/>
<field name = "b" value="on"/>
<field name = "c" value="in"/>
<field name = "d" value="in"/>
<field name = "e" value="in"/>
<field name = "f" value="at"/>
<field name = "g" value="at"/>
<field name = "h" value="in"/>
<field name = "i" value="on"/>
<field name = "j" value="on"/>
<field name = "l" value="on"/>
<field name = "l|1" value="at"/>
<field name = "l|2" value="in"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (819, 'mod2uni1es12p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1es12p6">
<field name = "a" value="affected"/>
<field name = "a|1" value="started"/>
<field name = "a|2" value="gained"/>
<field name = "b" value="wanted"/>
<field name = "b|1" value="realized"/>
<field name = "b|2" value="increased"/>
<field name = "b|3" value="reached"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (823, 'mod2uni1les12p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p17">
<field name = "a" value="You didn''t play tennis with your boss yesterday"/>
<field name = "b" value="Did you play tennis with your boss yesterday"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (827, 'mod2uni1les12p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p21">
<field name = "a" value="The new employee didn''t need help"/>
<field name = "b" value="Did the new employee need help"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (830, 'mod2uni1les13p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p5">
<field name = "a" value="G[or]g" />
<field name = "b" value="E[or]e"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="H[or]h"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="C[or]c"/>
<field name = "g" value="F[or]f"/>
<field name = "h" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (838, 'mod2uni1les13p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p24">
<field name = "e" value="altogether" />
<field name = "e|1" value="gift"/>
<field name = "f" value="differentials"/>
<field name = "g" value="costs"/>
<field name = "h" value="mind"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (912, 'mod2uni1les20p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p21">
<field name = "a" value="C[or]c"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (466, 'mod1uni1les4p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p7">
<field name = "a" value="engineer"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 465, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (467, 'mod1uni1les4p7b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p7b">
<field name = "a" value="telephone operator"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 466, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (471, 'mod1uni1les4p7f', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p7f">
<field name = "a" value="porters"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 470, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (474, 'mod1uni1les4p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p11">
<field name = "a" value="Japan"/>
<field name = "b" value="Nakata"/>
<field name = "c" value="marketing director"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 473, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (478, 'mod1uni1les4p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p16">
<field name = "a" value="Welcome to Miami"/>
<field name = "b" value="This is my colleague "/>
<field name = "c" value="This is Ronald''s office "/>
<field name = "d" value="His computer is here "/>
<field name = "e" value="Her office is over there "/>
<field name = "f" value="That''s a photo of her friend "/>
<field name = "g" value="This is your office "/>
<field name = "h" value="She''s an assistant "/>
<field name = "i" value="He''s an engineer  "/>
<field name = "j" value="I''m a receptionist   "/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 477, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (461, 'mod1uni1les3p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les3p3">
<field name = "b" value="A "/>
<field name = "c" value="J"/>
<field name = "d" value="K"/>
<field name = "f" value="B"/>
<field name = "g" value="D"/>
<field name = "h" value="E"/>
<field name = "i" value="G"/>
<field name = "j" value="P"/>
<field name = "k" value="T"/>
<field name = "l" value="V"/>
<field name = "m" value="Z"/>
<field name = "n" value="F"/>
<field name = "o" value="L"/>
<field name = "p" value="M"/>
<field name = "q" value="N"/>
<field name = "r" value="S"/>
<field name = "s" value="X"/>
<field name = "t" value="I"/>
<field name = "u" value="Y"/>
<field name = "v" value="O"/>
<field name = "x" value="Q"/>
<field name = "w" value="U"/>
<field name = "y" value="W"/>
<field name = "z" value="R"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (758, 'mod2uni1les7p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p14">
<field name="f" value="Undo"/>
<field name="g" value="inadvisable"/>
<field name="h" value="inefficient"/>
<field name="i" value="impersonal"/>
<field name="j" value="illogical"/>
<field name="j|1" value="irrelevant"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (686, 'mod2uni1les1p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p8">
<field name = "a" value="Delay[or]delay"/>
<field name = "b" value="Supply[or]supply"/>
<field name = "c" value="Mistake[or]mistake"/>
<field name = "d" value="Invoice[or]invoice"/>
<field name = "e" value="Load[or]load"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (762, 'mod2uni1les7p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p19">
<field name="a" value="I notice you''re currently buying less than usual"/>
<field name="b" value="There''s a significant decrease in orders over the past three months"/>
<field name="c" value="You''ll see a big poster on how much printing was processed"/>
<field name="d" value="It''s unbelievable when you see some numbers"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (754, 'mod2uni1les7p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p10">
<field name = "f" value="Impossible[or]impossible"/>
<field name = "g" value="Unsustainable[or]unsustainable"/>
<field name = "h" value="Inaccurate[or]inaccurate"/>
<field name = "i" value="Impolite[or]impolite"/>
<field name = "j" value="Inefficient[or]inefficient"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (810, 'mod2uni1les6p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p14">
<field name = "a" value="at the reception"/>
<field name = "b" value="at the end"/>
<field name = "c" value="on the envelope"/>
<field name = "d" value="in the"/>
<field name = "e" value="in a bank"/>
<field name = "f" value="at that table"/>
<field name = "g" value="on the left"/>
<field name = "h" value="at the door"/>
<field name = "i" value="in a row"/>
<field name = "j" value="on the ground floor"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (816, 'mod2uni1les12p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p3">
<field name = "a" value="D[or]d"/>
<field name = "b" value="F[or]f"/>
<field name = "c" value="B[or]b"/>
<field name = "d" value="E[or]e"/>
<field name = "e" value="C[or]c"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (820, 'mod2uni1les12p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p14">
<field name = "a" value="worked"/>
<field name = "b" value="delivered"/>
<field name = "c" value="offered"/>
<field name = "d" value="placed"/>
<field name = "e" value="asked"/>
<field name = "f" value="delayed"/>
<field name = "g" value="returned"/>
<field name = "h" value="issued"/>
<field name = "i" value="suggested"/>
<field name = "j" value="complained"/>
<field name = "k" value="received"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (780, 'mod2uni1les10p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p9">
<field name = "a" value="E[or]e"/>
<field name = "b" value="G[or]g"/>
<field name = "c" value="B[or]b"/>
<field name = "d" value="D[or]d"/>
<field name = "e" value="C[or]c"/>
<field name = "f" value="F[or]f"/>
<field name = "g" value="A[or]a"/>
</challenge>
</payload>
</ivela>
', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (469, 'mod1uni1les4p7d', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p7d">
<field name = "a" value="executives"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 468, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (475, 'mod1uni1les4p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p12">
<field name = "a" value="Sorry. What''s your name again?"/>
<field name = "b" value="How do you spell your first name, please?"/>
<field name = "c" value="What''s your job?"/>
<field name = "d" value="I''m a sales manager at IBM."/>
<field name = "e" value="Here''s your badge."/>
<field name = "f" value="Thank you very much."/>
<field name = "g" value="What''s his company?"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 474, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (462, 'mod1uni1les3p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les3p4">
<field name = "a" value="A for Alpha"/>
<field name = "b" value="K for Kilo"/>
<field name = "f" value="U for Uniform"/>
<field name = "g" value="E for Echo"/>
<field name = "k" value="O for Oscar"/>
<field name = "l" value="W for Whisky"/>
<field name = "m" value="G for Gold"/>
<field name = "o" value="X for X-ray"/>
<field name = "p" value="H for Hotel"/>
<field name = "q" value="Q for Quebec"/>
<field name = "r" value="Y for Yankee"/>
<field name = "s" value="I for India"/>
<field name = "t" value="R for Romeo"/>
<field name = "v" value="J for Juliet"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (687, 'mod2uni1les1p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p11">
<field name = "a" value="No"/>
<field name = "b" value="Yes"/>
<field name = "c" value="Yes"/>
<field name = "d" value="No"/>
<field name = "e" value="No"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 688, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (759, 'mod2uni1les7p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p15">
<field name="a" value="inadvisable"/>
<field name="a|1" value="inarticulate"/>
<field name="a|2" value="inaudible"/>
<field name="a|3" value="incoherent"/>
<field name="a|4" value="incomparable"/>
<field name="a|5" value="incomplete"/>
<field name="a|6" value="independent"/>
<field name="a|7" value="imperfect"/>
<field name="a|8" value="immoderate"/>
<field name="a|9" value="immodest"/>
<field name="a|10" value="immoral"/>
<field name="a|11" value="illegal"/>
<field name="a|12" value="illogical"/>
<field name="a|13" value="illegible"/>
<field name="a|14" value="illiterate"/>
<field name="a|15" value="irrelevant"/>
<field name="a|16" value="irresponsible"/>
<field name="a|17" value="irrational"/>
<field name="a|18" value="irreversible"/>
<field name="a|19" value="unsustainable"/>
<field name="a|20" value="unemployed"/>
<field name="a|21" value="unfortunately"/>
<field name="a|22" value="unpack"/>
<field name="a|23" value="untie"/>
<field name="a|24" value="unusual"/>
<field name="a|25" value="undo"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (763, 'mod2uni1les7p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p20">
<field name="e" value="In fact, IBM is widely known for being environmentally responsible for a long time"/>
<field name="f" value="Besides, all of our suppliers have to be environmentally responsible, too"/>
<field name="g" value="Unfortunately, not everyone is so conscious"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (755, 'mod2uni1les7p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p11">
<field name="k" value="Impatience[or]impatience"/>
<field name="l" value="Unbelievable[or]unbelievable"/>
<field name="m" value="Impersonal[or]impersonal"/>
<field name="n" value="Illegal[or]illegal"/>
<field name="o" value="Illogical[or]illogical"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (817, 'mod2uni1les12p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p4">
<field name = "a" value="figures"/>
<field name = "b" value="increased"/>
<field name = "c" value="reached"/>
<field name = "d" value="performance[and]affected"/>
<field name = "e" value="popularity"/>
<field name = "f" value="grow"/>
<field name = "g" value="realized"/>
<field name = "h" value="volume"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (821, 'mod2uni1les12p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p15">
<field name = "a" value="asked"/>
<field name = "b" value="offered"/>
<field name = "c" value="delayed"/>
<field name = "d" value="placed"/>
<field name = "e" value="delivered"/>
<field name = "f" value="returned"/>
<field name = "g" value="worked"/>
<field name = "h" value="requested"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (815, 'mod2uni1les6p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p27">
<field name = "a" value="Is there a taxi stand near here"/>
<field name = "b" value="Are there wheelchair facilities in this building"/>
<field name = "c" value="Is there a research center on this floor"/>
<field name = "d" value="The bus stop is at the corner"/>
<field name = "e" value="The toilets are next to the stairs[or]The stairs are next to the toilets"/>
<field name = "f" value="The stapler is on top of the cabinet"/>
<field name = "g" value="Where are the business cards"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (781, 'mod2uni1les10p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les10p13">
<field name = "a" value="satisfaction"/>
<field name = "b" value="problem"/>
<field name = "c" value="PC"/>
<field name = "d" value="correct"/>
<field name = "e" value="service"/>
<field name = "f" value="slow"/>
<field name = "f|1" value="reports"/>
<field name = "g" value="Really"/>
<field name = "h" value="professional"/>
<field name = "i" value="supervisor"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (463, 'mod1uni1les3p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les3p7">
<field name = "a" value="her"/>
<field name = "b" value="their"/>
<field name = "c" value="his"/>
<field name = "d" value="her"/>
<field name = "e" value="His"/>
<field name = "f" value="Their"/>
<field name = "g" value="her"/>
<field name = "h" value="your"/>
<field name = "i" value="Susan''s"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 462, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (465, 'mod1uni1les4p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p5">
<field name = "a" value="an"/>
<field name = "b" value="a"/>
<field name = "c" value="an"/>
<field name = "d" value="a"/>
<field name = "e" value="a"/>
<field name = "e|1" value="an"/>
<field name = "f" value="a"/>
<field name = "f|1" value="an"/>
<field name = "g" value="a"/>
<field name = "h" value="an"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 464, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (468, 'mod1uni1les4p7c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p7c">
<field name = "a" value="cashier"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 467, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (473, 'mod1uni1les4p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p10">
<field name = "a" value="job"/>
<field name = "b" value="family name"/>
<field name = "c" value="company"/>
<field name = "d" value="marketing"/>
<field name = "e" value="ticket"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 472, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (477, 'mod1uni1les4p15b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p15b">
<field name = "a" value="FALSE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="FALSE"/>
<field name = "e" value="TRUE"/>
<field name = "f" value="I DON''T KNOW"/>
<field name = "g" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 476, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (818, 'mod2uni1es12p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1es12p5">
<field name = "a" value="introduced"/>
<field name = "a|1" value="increased"/>
<field name = "b" value="reached"/>
<field name = "b|1" value="continued"/>
<field name = "b|2" value="started"/>
<field name = "b|3" value="reached"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (688, 'mod2uni1les1p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p12">
<field name = "a" value="Shipping"/>
<field name = "b" value="This"/>
<field name = "c" value="How"/>
<field name = "d" value="calling"/>
<field name = "e" value="check"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 688, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (760, 'mod2uni1les7p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p17">
<field name="a" value="Environmental responsibility"/>
<field name="b" value="They have a policy of environmental responsibility"/>
<field name="c" value="Conscious-raising posters in the printing room"/>
<field name="d" value="Green Recycling and Asset Disposal for the Enterprise"/>
<field name="e" value="That''s about the IT equipment renewal and recycling arm of IBM"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (756, 'mod2uni1les7p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p12">
<field name="p" value="Inactive[or]inactive"/>
<field name="q" value="Unexpected[or]unexpected"/>
<field name="r" value="Irrelevant[or]irrelevant"/>
<field name="s" value="Unable[or]unable"/>
<field name="t" value="Unemployed[or]unemployed"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (822, 'mod2uni1les12p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p16">
<field name = "a" value="started"/>
<field name = "b" value="decided"/>
<field name = "c" value="celebrated"/>
<field name = "d" value="introduced"/>
<field name = "d|1" value="increased"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (782, 'mod2uni1les11p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les11p8">
<field name="a" value="e[or]E"/>
<field name="b" value="f[or]F"/>
<field name="c" value="i[or]I"/>
<field name="d" value="h[or]H"/>
<field name="e" value="c[or]C"/>
<field name="f" value="a[or]A"/>
<field name="g" value="j[or]J"/>
<field name="h" value="d[or]D"/>
<field name="i" value="g[or]G"/>
<field name="j" value="b[or]B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (826, 'mod2uni1les12p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p20">
<field name = "a" value="Thomas didn''t create a brand new program"/>
<field name = "b" value="Did Thomas create a brand new program"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (913, 'mod3uni1les1p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p3">
<field name = "a" value="NO"/>
<field name = "b" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (917, 'mod3uni1les1p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p8">
<field name = "a" value="TRUE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="FALSE"/>
<field name = "d" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 913, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (464, 'mod1uni1les4p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p3">
<field name = "a" value="Accountant"/>
<field name = "a|1" value="Finance"/>
<field name = "a|2" value="Receptionist"/>
<field name = "a|3" value="HR"/>
<field name = "a|4" value="Engineer"/>
<field name = "a|5" value="Marketing"/>
<field name = "a|6" value="Designer"/>
<field name = "a|7" value="Shipping"/>
<field name = "a|8" value="PA"/>
<field name = "a|9" value="Public Relations"/>
<field name = "a|10" value="Director"/>
<field name = "a|11" value="Administration"/>
<field name = "a|12" value="Assistant"/>
<field name = "a|13" value="Research and Development"/>
<field name = "a|14" value="Sales Manager"/>
<field name = "a|15" value="Customer Service"/>
<field name = "a|16" value="Technician"/>
<field name = "a|17" value=""/>
<field name = "a|18" value="Cashier"/>
<field name = "a|19" value=""/>
<field name = "a|20" value="Telephone Operator"/>
<field name = "a|21" value=""/>
<field name = "a|22" value="CEO"/>
<field name = "a|23" value=""/>
<field name = "a|24" value="Porter"/>
<field name = "a|25" value=""/>
<field name = "a|26" value="Buyer"/>
<field name = "a|27" value=""/>
<field name = "a|28" value="Salesperson"/>
<field name = "a|29" value=""/>
<field name = "a|30" value="Trainee"/>
<field name = "a|31" value=""/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 460, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (472, 'mod1uni1les4p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p9">
<field name = "a" value="job"/>
<field name = "b" value="sales manager"/>
<field name = "c" value="your"/>
<field name = "d" value="Have a good day"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 471, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (476, 'mod1uni1les4p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p13">
<field name = "a" value="G"/>
<field name = "a|1" value="D"/>
<field name = "a|2" value="E"/>
<field name = "a|3" value="B"/>
<field name = "a|4" value="C"/>
<field name = "a|5" value="F"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 475, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (690, 'mod2uni1les2p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p7">
<field name = "a" value="eats"/>
<field name = "b" value="is eating"/>
<field name = "c" value="is Peter doing"/>
<field name = "d" value="is going"/>
<field name = "e" value="asks"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (757, 'mod2uni1les7p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p13">
<field name="a" value="Unfortunately"/>
<field name="b" value="unemployment"/>
<field name="c" value="unable"/>
<field name="d" value="irrational"/>
<field name="e" value="unbelievable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (761, 'mod2uni1les7p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p18">
<field name="a" value="C[or]c"/>
<field name="b" value="E[or]e"/>
<field name="c" value="B[or]b"/>
<field name="d" value="F[or]f"/>
<field name="e" value="A[or]a"/>
<field name="f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (783, 'mod2uni1les8p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p7">
<field name="a" value="any"/>
<field name="b" value="any"/>
<field name="c" value="some"/>
<field name="c|1" value="any"/>
<field name="d" value="some"/>
<field name="e" value="some"/>
<field name="f" value="any"/>
<field name="g" value="some"/>
<field name="h" value="some"/>
<field name="i" value="any"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (787, 'mod2uni1les8p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p11">
<field name="a" value="few"/>
<field name="b" value="little"/>
<field name="c" value="few"/>
<field name="d" value="Few"/>
<field name="e" value="little"/>
<field name="f" value="few"/>
<field name="g" value="few"/>
<field name="h" value="little"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (791, 'mod2uni1les8p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p17">
<field name = "a" value="1"/>
<field name = "b" value="7"/>
<field name = "c" value="3"/>
<field name = "d" value="2"/>
<field name = "e" value="5"/>
<field name = "f" value="4"/>
<field name = "g" value="6"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (824, 'mod2uni1les12p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p18">
<field name = "a" value="She didn''t want to buy a better computer"/>
<field name = "b" value="Did she want to buy a better computer"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (828, 'mod2uni1les12p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p22">
<field name = "a" value="They didn''t issue your invoice yesterday"/>
<field name = "b" value="Did they issue your invoice yesterday"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (831, 'mod2uni1les13p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p15">
<field name = "a" value="flexible"/>
<field name = "b" value="sound"/>
<field name = "c" value="best"/>
<field name = "d" value="give"/>
<field name = "e" value="rough"/>
<field name = "f" value="time"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (835, 'mod2uni1les3p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p21">
<field name = "a" value="Yes, he did"/>
<field name = "b" value="His manager"/>
<field name = "c" value="Yes, she was"/>
<field name = "d" value="Yes, he did"/>
<field name = "e" value="Yes, he did"/>
<field name = "f" value="No, she didn''t"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (839, 'mod2uni1les13p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p25">
<field name = "i" value="remarks" />
<field name = "j" value="payment"/>
<field name = "k" value="installments"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (914, 'mod3uni1les1p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p4">
<field name = "c" value="YES"/>
<field name = "d" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 913, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (470, 'mod1uni1les4p7e', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les4p7e">
<field name = "a" value="taxi[or]cab driver"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 469, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (840, 'mod2uni1les3p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p26">
<field name = "a" value="client"/>
<field name = "b" value="alternative"/>
<field name = "c" value="focal"/>
<field name = "d" value="pricing"/>
<field name = "e" value="value proposition"/>
<field name = "f" value="mind"/>
<field name = "g" value="conditions"/>
<field name = "h" value="wrote back"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (788, 'mod2uni1les8p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p12">
<field name = "a" value="many"/>
<field name = "b" value="few"/>
<field name = "c" value="much"/>
<field name = "c|1" value="few"/>
<field name = "d" value="some"/>
<field name = "e" value="many"/>
<field name = "f" value="much"/>
<field name = "g" value="little"/>
<field name = "h" value="few"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (784, 'mod2uni1les8p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p8">
<field name="a" value="They don''t give us any information."/>
<field name="b" value="CORRECT[or]Correct[or]correct"/>
<field name="c" value="We have some problems."/>
<field name="d" value="Their company receives some very good grants."/>
<field name="e" value="CORRECT[or]Correct[or]correct"/>
<field name="f" value="Kenneth is always trying to take some pictures of that actress."/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (915, 'mod3uni1les1p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p5">
<field name = "e" value="NO"/>
<field name = "f" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 913, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (691, 'mod2uni1les2p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p9">
<field name = "a" value="G[or]g"/>
<field name = "b" value="I[or]i"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="E[or]e"/>
<field name = "f" value="C[or]c"/>
<field name = "g" value="D[or]d"/>
<field name = "h" value="H[or]h"/>
<field name = "i" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (825, 'mod2uni1les12p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p19">
<field name = "a" value="Richard didn''t finish his report"/>
<field name = "b" value="Did Richard finish his report"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (829, 'mod2uni1les12p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les12p23">
<field name = "a" value="wanted"/>
<field name = "b" value="tried"/>
<field name = "b|1" value="didn''t answer[or]did not answer"/>
<field name = "c" value="talked"/>
<field name = "c|1" value="solved"/>
<field name = "d" value="finished"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (832, 'mod2uni1les13p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p16">
<field name = "g" value="charge"/>
<field name = "h" value="stock"/>
<field name = "i" value="charge"/>
<field name = "j" value="subscribe"/>
<field name = "k" value="provide"/>
<field name = "l" value="much"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (836, 'mod2uni1les13p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p22">
<field name = "a" value="B[or]b" />
<field name = "b" value="D[or]d"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="E[or]e"/>
<field name = "f" value="C[or]c"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (919, 'mod3uni1les1p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p10">
<field name = "a" value="needs"/>
<field name = "b" value="suits"/>
<field name = "c" value="sounds"/>
<field name = "d" value="urgent"/>
<field name = "e" value="accommodate"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 913, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (923, 'mod3uni1les1p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p26">
<field name = "a" value="N[or]n"/>
<field name = "b" value="V[or]v"/>
<field name = "c" value="N[or]n"/>
<field name = "d" value="N[or]n"/>
<field name = "e" value="N[or]n"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (927, 'mod3uni1les1p32', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p32">
<field name = "a" value="change"/>
<field name = "b" value="come up with"/>
<field name = "c" value="refused"/>
<field name = "d" value="credit history"/>
<field name = "e" value="world crisis"/>
<field name = "f" value="stock market crisis"/>
<field name = "g" value="management"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (935, 'mod3uni1les2p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p4">
<field name = "a" value="8"/>
<field name = "a|1" value="2"/>
<field name = "a|2" value="6"/>
<field name = "a|3" value="3"/>
<field name = "a|4" value="5"/>
<field name = "a|5" value="1"/>
<field name = "a|6" value="4"/>
<field name = "a|7" value="7"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (939, 'mod3uni1les2p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p11">
<field name = "f" value="Will we increase exports because of shipping facilities"/>
<field name = "g" value="We will have to keep prices low"/>
<field name = "h" value="The main competition will be on price"/>
<field name = "i" value="Will you team up with me in this ambitious project"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (943, 'mod3uni1les2p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p18">
<field name = "a" value="will be"/>
<field name = "b" value="will remain"/>
<field name = "c" value="will guarantee"/>
<field name = "d" value="won''t do"/>
<field name = "e" value="will you choose"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (479, 'mod1uni1les5p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les5p3">
<field name = "a" value="How many workstations?"/>
<field name = "b" value="How many meetings?"/>
<field name = "c" value="How many employees are here?"/>
<field name = "d" value="How many departments?"/>
<field name = "e" value="How many conference rooms?"/>
<field name = "f" value="How many English classes?"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 478, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (482, 'mod1uni1les5p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les5p7">
<field name = "a" value="There''s a meeting on Friday"/>
<field name = "b" value="There are four calls this week"/>
<field name = "c" value="How many calls are there today?"/>
<field name = "d" value="How many products are there?"/>
<field name = "e" value="There isn''t a laptop computer here"/>
<field name = "f" value="Are there any supervisors here?"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 481, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (484, 'mod1uni1les6p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p4">
<field name = "a" value="5"/>
<field name = "a|1" value=""/>
<field name = "a|2" value=""/>
<field name = "a|3" value="19"/>
<field name = "a|4" value="12"/>
<field name = "a|5" value="20"/>
<field name = "a|6" value="13"/>
<field name = "a|7" value=""/>
<field name = "a|8" value=""/>
<field name = "a|9" value="17"/>
<field name = "a|10" value=""/>
<field name = "a|11" value="18"/>
<field name = "a|12" value=""/>
<field name = "a|13" value="14"/>
<field name = "a|14" value="06"/>
<field name = "a|15" value=""/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 483, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (692, 'mod2uni1les2p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p10">
<field name = "a" value="I''m checking your order now"/>
<field name = "b" value="We store the products in our warehouse"/>
<field name = "c" value="We sometimes offer big discounts"/>
<field name = "d" value="We are a company that sells services"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (837, 'mod2uni1les13p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p23">
<field name = "a" value="prospective" />
<field name = "a|1" value="proposal"/>
<field name = "b" value="slot"/>
<field name = "c" value="actually"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (789, 'mod2uni1les8p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p13">
<field name = "a" value="uncountable"/>
<field name = "b" value="countable"/>
<field name = "c" value="uncountable"/>
<field name = "d" value="uncountable"/>
<field name = "e" value="uncountable"/>
<field name = "f" value="countable"/>
<field name = "g" value="countable"/>
<field name = "h" value="countable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (785, 'mod2uni1les8p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p9">
<field name="g" value="Do you have any other questions?"/>
<field name="h" value="CORRECT[or]Correct[or]correct"/>
<field name="i" value="CORRECT[or]Correct[or]correct"/>
<field name="j" value="CORRECT[or]Correct[or]correct"/>
<field name="k" value="Do you have any information about the merger?"/>
<field name="l" value="We never have any problems like this one."/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (916, 'mod3uni1les1p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p6">
<field name = "a" value="May I"/>
<field name = "b" value="choose"/>
<field name = "c" value="kinds"/>
<field name = "d" value="minute"/>
<field name = "e" value="recommend"/>
<field name = "f" value="month"/>
<field name = "g" value="brochure"/>
<field name = "g1" value="available "/>
<field name = "h" value="appreciate"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 913, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (920, 'mod3uni1les1p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p11">
<field name = "f" value="reach"/>
<field name = "g" value="contact"/>
<field name = "h" value="confident"/>
<field name = "i" value="satisfaction"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (924, 'mod3uni1les1p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p27">
<field name = "g" value="N [or] n"/>
<field name = "h" value="V [or] v"/>
<field name = "i" value="V [or] v"/>
<field name = "j" value="N [or] n"/>
<field name = "k" value="N [or] n"/>
<field name = "l" value="N [or] n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (936, 'mod3uni1les2p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p5">
<field name = "a" value="We have some savings from last year''s revenues"/>
<field name = "b" value="I''ll lend money to companies to help them develop products"/>
<field name = "c" value="I''ll look into some IT companies first"/>
<field name = "d" value="It may be too risky to invest in start-ups"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (940, 'mod3uni1les2p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p12">
<field name = "a" value="Will we make a loss this year"/>
<field name = "b" value="We will be in serious financial difficulty"/>
<field name = "c" value="Profits will reach a peak of 7 million dollars this year"/>
<field name = "d" value="Sales will fluctuate this quarter"/>
<field name = "e" value="Most of our customers will order their products at their own computers"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (480, 'mod1uni1les5p4b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les5p4b">
<field name = "a" value="a"/>
<field name = "b" value="an"/>
<field name = "c" value="an"/>
<field name = "d" value="a lot of"/>
<field name = "e" value="a lot of"/>
<field name = "f" value="an"/>
<field name = "g" value="a lot of"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 479, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (489, 'mod1uni1les6p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p12">
<field name = "a" value="There are"/>
<field name = "b" value="workstations"/>
<field name = "c" value="Is there"/>
<field name = "d" value="telephone"/>
<field name = "e" value="What''s"/>
<field name = "f" value="when"/>
<field name = "g" value="Monday"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 488, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (491, 'mod1uni1les7p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p5">
<field name = "a" value="Can I smoke in the building?"/>
<field name = "b" value="Can I use a computer?"/>
<field name = "c" value="Can I get secretarial help?"/>
<field name = "d" value="Can I make copies?"/>
<field name = "e" value="Can I arrive at 6 a.m.?"/>
<field name = "f" value="Can I have breakfast in the cafeteria?"/>
<field name = "g" value="Can I meet you in the afternoon?"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 490, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (495, 'mod1uni1les7p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p17">
<field name = "a" value="o''clock"/>
<field name = "b" value="It''s"/>
<field name = "c" value="at"/>
<field name = "d" value="It''s"/>
<field name = "d|1" value="at"/>
<field name = "e" value="It''s"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 494, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (693, 'mod2uni1les2p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p14">
<field name = "a" value="arriving"/>
<field name = "a|1" value="sorry"/>
<field name = "a|2" value="days"/>
<field name = "a|3" value="delay"/>
<field name = "a|4" value="right"/>
<field name = "a|5" value="care"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (786, 'mod2uni1les8p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p10">
<field name="a" value="much"/>
<field name="b" value="many"/>
<field name="c" value="many"/>
<field name="d" value="much"/>
<field name="e" value="many"/>
<field name="f" value="Many"/>
<field name="g" value="much"/>
<field name="h" value="many"/>
<field name="i" value="much"/>
<field name="j" value="many"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (790, 'mod2uni1les8p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p16">
<field name = "a" value="False"/>
<field name = "b" value="I don''t know"/>
<field name = "c" value="True"/>
<field name = "d" value="False"/>
<field name = "e" value="True"/>
<field name = "f" value="True"/>
<field name = "g" value="True"/>
<field name = "h" value="False"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (841, 'mod2uni1les15p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p3">
<field name = "a" value="Yes"/>
<field name = "b" value="Yes"/>
<field name = "c" value="No"/>
<field name = "d" value="No"/>
<field name = "e" value="Yes"/>
<field name = "f" value="Yes"/>
<field name = "g" value="Yes"/>
<field name = "h" value="No"/>
<field name = "i" value="Yes"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (845, 'mod2uni1les15p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p8">
<field name = "g" value="Uncountable"/>
<field name = "h" value="Uncountable"/>
<field name = "i" value="Uncountable"/>
<field name = "j" value="Countable"/>
<field name = "k" value="Uncountable"/>
<field name = "l" value="Countable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (849, 'mod2uni1les15p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p13">
<field name = "a" value="advice"/>
<field name = "b" value="collaboration"/>
<field name = "c" value="dollar"/>
<field name = "d" value="e-commerce"/>
<field name = "e" value="employment"/>
<field name = "f" value="equipment"/>
<field name = "g" value="homework"/>
<field name = "h" value="information"/>
<field name = "i" value="knowledge"/>
<field name = "j" value="progress"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (853, 'mod2uni1les15p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p18">
<field name = "a" value="1"/>
<field name = "b" value="3"/>
<field name = "c" value="4"/>
<field name = "d" value="2"/>
<field name = "e" value="5"/>
<field name = "f" value="7"/>
<field name = "g" value="8"/>
<field name = "h" value="6"/>
<field name = "i" value="9"/>
<field name = "j" value="11"/>
<field name = "k" value="13"/>
<field name = "l" value="10"/>
<field name = "m" value="12"/>
<field name = "n" value="14"/>
<field name = "o" value="15"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (918, 'mod3uni1les1p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p9">
<field name = "e" value="FALSE"/>
<field name = "f" value="FALSE"/>
<field name = "g" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 913, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (922, 'mod3uni1les1p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p13">
<field name = "g" value="P[or]p"/>
<field name = "h" value="P[or]p"/>
<field name = "i" value=""/>
<field name = "j" value=""/>
<field name = "k" value=""/>
<field name = "l" value=""/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (926, 'mod3uni1les1p29', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p29">
<field name = "t" value="N [or] n"/>
<field name = "u" value="V [or] N [or] v [or] n"/>
<field name = "v" value="N [or] n"/>
<field name = "x" value="N [or] n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (934, 'mod3uni1les2p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p3">
<field name = "a" value="FALSE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="I DON''T KNOW"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (481, 'mod1uni1les5p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les5p6">
<field name = "a" value="there''s"/>
<field name = "b" value="how many"/>
<field name = "c" value="there are"/>
<field name = "d" value="there aren''t"/>
<field name = "e" value="is there"/>
<field name = "f" value="are there"/>
<field name = "g" value="there isn''t"/>
<field name = "h" value="is there"/>
<field name = "i" value="there aren''t"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 480, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (494, 'mod1uni1les7p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p16">
<field name = "a" value="flight"/>
<field name = "b" value="5"/>
<field name = "c" value="afternoon"/>
<field name = "d" value="morning"/>
<field name = "e" value="time"/>
<field name = "f" value="3"/>
<field name = "g" value="you"/>
<field name = "h" value="ticket"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 493, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (486, 'mod1uni1les6p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p6">
<field name = "a" value="four-zero-nine-seven  eight-one-two-two"/>
<field name = "b" value="Five-five  two-one  nine-nine-two-zero  seven-six-eight-nine"/>
<field name = "c" value="Three-eight-one-four  zero-zero-two-one"/>
<field name = "d" value="Three-nine-four-eight"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (694, 'mod2uni1les2p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p15">
<field name = "a" value="here"/>
<field name = "b" value="days"/>
<field name = "c" value="sorry[and]delivery"/>
<field name = "d" value="tomorrow[and]situation"/>
<field name = "e" value="talk"/>
<field name = "f" value="apologies[and]delay"/>
<field name = "g" value="all right[and]now"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (921, 'mod3uni1les1p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p12">
<field name = "a" value=""/>
<field name = "b" value=""/>
<field name = "c" value="P[or]p"/>
<field name = "d" value="P[or]p"/>
<field name = "e" value=""/>
<field name = "f" value=""/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (792, 'mod2uni1les8p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les8p14">
<field name = "i" value="uncountable"/>
<field name = "j" value="countable"/>
<field name = "k" value="countable"/>
<field name = "l" value="uncountable"/>
<field name = "m" value="uncountable"/>
<field name = "n" value="countable"/>
<field name = "o" value="countable"/>
<field name = "p" value="uncountable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (842, 'mod2uni1les15p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p4">
<field name = "a" value="C[or]c"/>
<field name = "b" value="D[or]d"/>
<field name = "c" value="G[or]g"/>
<field name = "d" value="E[or]e"/>
<field name = "e" value="H[or]h"/>
<field name = "f" value="B[or]b"/>
<field name = "g" value="F[or]f"/>
<field name = "h" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (846, 'mod2uni1les15p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p9">
<field name = "m" value="Uncountable"/>
<field name = "n" value="Countable"/>
<field name = "o" value="Countable"/>
<field name = "p" value="Countable"/>
<field name = "q" value="Countable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (850, 'mod2uni1les15p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p14">
<field name = "a" value="picks up"/>
<field name = "b" value="find out"/>
<field name = "c" value="looking for"/>
<field name = "d" value="hire"/>
<field name = "e" value="team up"/>
<field name = "f" value="loof after"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (925, 'mod3uni1les1p28', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p28">
<field name = "m" value="N [or] n"/>
<field name = "n" value="N [or] n"/>
<field name = "o" value="V [or] N [or] v [or] n"/>
<field name = "p" value="N [or] n"/>
<field name = "q" value="A [or] a"/>
<field name = "r" value="V [or] v"/>
<field name = "s" value="V [or] v"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (933, 'mod3uni1les2p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p2">
<field name = "a" value="FALSE"/>
<field name = "b" value="TRUE"/>
<field name = "c" value="FALSE"/>
<field name = "d" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (937, 'mod3uni1les2p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p6">
<field name = "e" value="Besides, at this point I cannot jeopardize capital"/>
<field name = "f" value="I''ll put all my efforts into making the right decision"/>
<field name = "g" value="I''ll bring you a lot of food for thought"/>
<field name = "h" value="I know I can count on your entrepreneurial nature and vision"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (941, 'mod3uni1les2p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p13">
<field name = "f" value="We will take risks and invest in start-ups[or]We will invest in start-ups and take risks"/>
<field name = "g" value="They won''t make the same mistake again"/>
<field name = "h" value="Will you design a plan to fight competition"/>
<field name = "i" value="Will you organize the workshop about stress management"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (945, 'mod3uni1les2p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p20">
<field name = "a" value="bail out"/>
<field name = "b" value="market share"/>
<field name = "c" value="stock market"/>
<field name = "d" value="Forecast"/>
<field name = "e" value="policy"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (483, 'mod1uni1les5p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les5p10">
<field name = "a" value="office"/>
<field name = "a|1" value="20"/>
<field name = "b" value="How many"/>
<field name = "c" value="there''s"/>
<field name = "d" value="computer"/>
<field name = "e" value="locker"/>
<field name = "f" value="your"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 482, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (696, 'mod2uni1les2p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p8">
<field name = "f" value="wants"/>
<field name = "g" value="don''t like"/>
<field name = "h" value="are you going"/>
<field name = "i" value="don''t understand"/>
<field name = "j" value="doesn''t dispatch"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 688, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (843, 'mod2uni1les15p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p5">
<field name = "a" value="to recruit"/>
<field name = "b" value="to hire"/>
<field name = "c" value="to look for"/>
<field name = "d" value="to give up"/>
<field name = "e" value="to find out"/>
<field name = "f" value="to look after"/>
<field name = "g" value="to team up"/>
<field name = "h" value="to pick up"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (847, 'mod2uni1les15p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p10">
<field name = "a" value="g"/>
<field name = "b" value="c"/>
<field name = "c" value="m"/>
<field name = "d" value="i"/>
<field name = "e" value="d"/>
<field name = "f" value="k"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (851, 'mod2uni1les15p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p15">
<field name = "g" value="advice"/>
<field name = "h" value="budget"/>
<field name = "i" value="knowledge"/>
<field name = "j" value="targets"/>
<field name = "k" value="equipment"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (929, 'mod3uni1les1p33', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p33">
<field name = "a" value="H [or] h"/>
<field name = "b" value="I [or] i"/>
<field name = "c" value="M [or] m"/>
<field name = "d" value="A [or] a"/>
<field name = "e" value="K [or] k"/>
<field name = "f" value="C [or] c"/>
<field name = "g" value="J [or] j"/>
<field name = "h" value="L [or] l"/>
<field name = "i" value="D [or] d"/>
<field name = "j" value="E [or] e"/>
<field name = "k" value="F [or] f"/>
<field name = "l" value="G [or] g"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (485, 'mod1uni1les6p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p5">
<field name = "a" value="zero [or] oh"/>
<field name = "a|1" value="ten"/>
<field name = "b" value="two"/>
<field name = "b|1" value="twelve"/>
<field name = "b|2" value="twenty"/>
<field name = "c" value="three"/>
<field name = "c|1" value="thirteen"/>
<field name = "d" value="four"/>
<field name = "d|1" value="fourteen"/>
<field name = "e" value="eight"/>
<field name = "e|1" value="eighteen"/>
<field name = "f" value="nine"/>
<field name = "f|1" value="nineteen"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (490, 'mod1uni1les6p14b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p14b">
<field name = "a" value="FALSE"/>
<field name = "b" value="I DON''T KNOW"/>
<field name = "c" value="FALSE"/>
<field name = "d" value="TRUE"/>
<field name = "e" value="FALSE"/>
<field name = "f" value="I DON''T KNOW"/>
<field name = "g" value="FALSE"/>
<field name = "h" value="TRUE"/>
<field name = "i" value="TRUE"/>
<field name = "j" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (697, 'mod1uni1les15p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p16">
<field name = "e" value="problem"/>
<field name = "f" value="very[and]easy"/>
<field name = "g" value="telling"/>
<field name = "h" value="him"/>
<field name = "i" value="team[and]player"/>
<field name = "j" value="always[and]thinking"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (844, 'mod2uni1les15p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p7">
<field name = "a" value="Countable"/>
<field name = "b" value="Uncountable"/>
<field name = "c" value="Countable"/>
<field name = "d" value="Uncountable"/>
<field name = "e" value="Uncountable"/>
<field name = "f" value="Uncountable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (848, 'mod2uni1les15p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p12">
<field name = "a" value="achievement"/>
<field name = "b" value="budget"/>
<field name = "c" value="mailing list"/>
<field name = "d" value="money"/>
<field name = "e" value="overachievement"/>
<field name = "f" value="subordinate"/>
<field name = "g" value="target"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (852, 'mod2uni1les15p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les15p17">
<field name = "a" value="to set targets"/>
<field name = "b" value="cost management department"/>
<field name = "c" value="the system"/>
<field name = "d" value="useful in this department"/>
<field name = "e" value="a sales campaign"/>
<field name = "f" value="increasing sales"/>
<field name = "g" value="reduce costs"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (930, 'mod3uni1les1p35', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p35">
<field name = "a" value="P"/>
<field name = "b" value="A"/>
<field name = "c" value="P"/>
<field name = "d" value="A"/>
<field name = "e" value="P"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (487, 'mod1uni1les6p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p8">
<field name = "a" value="It''s on Sunday"/>
<field name = "b" value="It''s on Friday"/>
<field name = "c" value="It''s on Wednesday"/>
<field name = "d" value="It''s on Tuesday"/>
<field name = "e" value="It''s on Saturday"/>
<field name = "f" value="It''s on Thursday"/>
<field name = "g" value="It''s on Monday"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 486, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (492, 'mod1uni1les7p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p6">
<field name = "a" value="b"/>
<field name = "b" value="d"/>
<field name = "c" value="a"/>
<field name = "d" value="c"/>
<field name = "e" value="f"/>
<field name = "f" value="g"/>
<field name = "g" value="e"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 491, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (493, 'mod1uni1les7p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p9">
<field name = "a" value="b"/>
<field name = "b" value="f"/>
<field name = "c" value="g"/>
<field name = "d" value="a"/>
<field name = "e" value="e"/>
<field name = "f" value="c"/>
<field name = "g" value="f"/>
<field name = "h" value="i"/>
<field name = "i" value="f"/>
<field name = "j" value="g"/>
<field name = "k" value="c"/>
<field name = "l" value="d"/>
<field name = "m" value="h"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 492, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (698, 'mod1uni1les15p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p18">
<field name = "a" value="doesn''t work"/>
<field name = "a|1" value="it''s broken"/>
<field name = "a|2" value="missing"/>
<field name = "a|3" value="engaged"/>
<field name = "a|4" value="is incorrect"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (854, 'mod2uni1les16p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p7">
<field name = "a" value="was finishing"/>
<field name = "b" value="was expanding"/>
<field name = "c" value="were having"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (858, 'mod2uni1les16p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p11">
<field name = "a" value="wasn''t sleeping"/>
<field name = "b" value="wasn''t giving"/>
<field name = "c" value="weren''t writing"/>
<field name = "d" value="wasn''t talking"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (862, 'mod2uni1les16p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p15">
<field name = "a" value="They were preparing a presentation about the marketing strategies"/>
<field name = "b" value="She was listening to the entire conversation"/>
<field name = "c" value="Your company was making substantial progress last year"/>
<field name = "d" value="The staff was developing a new product last semester"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (866, 'mod2uni1les16p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p20">
<field name = "a" value="JOHN"/>
<field name = "b" value="WILLIAM"/>
<field name = "c" value="JOHN"/>
<field name = "d" value="WILLIAM"/>
<field name = "e" value="JOHN"/>
<field name = "f" value="WILLIAM"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (931, 'mod3uni1les1p36', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p36">
<field name = "f" value="P"/>
<field name = "g" value="P"/>
<field name = "h" value="A"/>
<field name = "i" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (488, 'mod1uni1les6p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les6p11">
<field name = "a" value="phone"/>
<field name = "b" value="4560"/>
<field name = "c" value="department"/>
<field name = "c|1" value="Friday"/>
<field name = "d" value="Monday"/>
<field name = "d|1" value="Tuesday"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 487, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (496, 'mod1uni1les7p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p19">
<field name = "a" value="ticket"/>
<field name = "a|1" value="passport"/>
<field name = "b" value="baggage"/>
<field name = "c" value="luggage"/>
<field name = "d" value="moment"/>
<field name = "e" value="departure"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 495, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (497, 'mod1uni1les7p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p21">
<field name = "a" value="are"/>
<field name = "b" value="you"/>
<field name = "c" value="One"/>
<field name = "d" value="welcome"/>
<field name = "e" value="you"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 496, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (855, 'mod2uni1les16p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p8">
<field name = "d" value="was helping"/>
<field name = "e" value="were reading"/>
<field name = "f" value="am saying"/>
<field name = "g" value="were making"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (859, 'mod2uni1les16p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p12">
<field name = "a" value="weren''t explaining"/>
<field name = "b" value="weren''t looking"/>
<field name = "c" value="wasn''t being"/>
<field name = "d" value="weren''t playing"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (863, 'mod2uni1les16p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p16">
<field name = "e" value="I wasn''t listening when you were talking to me"/>
<field name = "f" value="She wasn''t sleeping"/>
<field name = "g" value="Were you going home"/>
<field name = "h" value="Was their company reducing costs"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (867, 'mod2uni1les16p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p21">
<field name = "a" value="meeting"/>
<field name = "a|1" value="targets"/>
<field name = "b" value="discussing"/>
<field name = "c" value="were"/>
<field name = "d" value="budget"/>
<field name = "d|1" value="understands"/>
<field name = "e" value="agree"/>
<field name = "f" value="percentage"/>
<field name = "f|1" value="catering"/>
<field name = "g" value="prizes"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (932, 'mod3uni1les1p37', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les1p37">
<field name = "a" value="1"/>
<field name = "b" value="2"/>
<field name = "c" value="3"/>
<field name = "d" value="4"/>
<field name = "e" value="5"/>
<field name = "f" value="6"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (498, 'mod1uni1les7p2z', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p2z">
<field name = "a" value="a"/>
<field name = "b" value="b"/>
<field name = "c" value="c"/>
<field name = "d" value="d"/>
<field name = "e" value="e"/>
<field name = "f" value="f"/>
<field name = "g" value="g"/>
<field name = "h" value="h"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 490, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (856, 'mod2uni1les16p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p9">
<field name = "a" value="They were saying strange things"/>
<field name = "b" value="Barbara was studying in her room"/>
<field name = "c" value="She was answering another telephone call"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (860, 'mod2uni1les16p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p13">
<field name = "a" value="Were my colleagues planning their holidays"/>
<field name = "b" value="Was our competitor launching new products"/>
<field name = "c" value="Was the seminar going to be on Tuesday"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (864, 'mod2uni1les16p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p18">
<field name = "a" value="FALSE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="I DON''T KNOW"/>
<field name = "d" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (868, 'mod2uni1les16p8c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p8c">
<field name = "d" value="was helping"/>
<field name = "e" value="were reading"/>
<field name = "f" value="am saying"/>
<field name = "g" value="were making"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (869, 'mod2uni1les17p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p3">
<field name = "a" value="Prejudice[or]prejudice"/>
<field name = "b" value="Awareness[or]awareness"/>
<field name = "c" value="Sensitivity[or]sensitivity"/>
<field name = "d" value="Sensibility[or]sensibility"/>
<field name = "e" value="Acceptance[or]acceptance"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (873, 'mod2uni1les17p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p13">
<field name = "a" value="6"/>
<field name = "b" value="4"/>
<field name = "c" value="12"/>
<field name = "d" value="7"/>
<field name = "e" value="9"/>
<field name = "f" value="2"/>
<field name = "g" value="3"/>
<field name = "h" value="5"/>
<field name = "i" value="10"/>
<field name = "j" value="1"/>
<field name = "k" value="11"/>
<field name = "l" value="8"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (877, 'mod2uni1les17p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p20">
<field name = "e" value="respectful[and]setting"/>
<field name = "f" value="prejudice[and]gender[and]disabilities"/>
<field name = "g" value="wrong[and]disrespectful"/>
<field name = "h" value="seriously"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (938, 'mod3uni1les2p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p10">
<field name = "a" value="We will rebuild the website very shortly"/>
<field name = "b" value="In two week''s time we will have our catalogues online"/>
<field name = "c" value="Sales won''t be a problem anymore"/>
<field name = "d" value="Sales of hardware will double by next quarter"/>
<field name = "e" value="Most of our customers will order their products at their own computers"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (942, 'mod3uni1les2p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p17">
<field name = "a" value="C[or]c"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="D[or]d"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="E[or]e"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (946, 'mod3uni1les2p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p21">
<field name = "f" value="budget"/>
<field name = "g" value="economical"/>
<field name = "h" value="deadline"/>
<field name = "i" value="loan"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (499, 'mod1uni1les8p6b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p6b">
      <field name = "a" value="on"/>
      <field name = "a|1" value="at"/>
      <field name = "b" value="in the"/>
      <field name = "c" value="in the"/>
      <field name = "c|1" value="on"/>
      <field name = "d" value="on"/>
      <field name = "e" value="on"/>
      <field name = "e|1" value="at"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 497, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (857, 'mod2uni1les16p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p10">
<field name = "d" value="It was raining a lot outside"/>
<field name = "e" value="We were taking a big risk with this joint venture"/>
<field name = "f" value="They were making progress very quickly"/>
<field name = "g" value="She was making very difficult decisions"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (861, 'mod2uni1les16p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p14">
<field name = "d" value="Were Asian customers spending more money"/>
<field name = "e" value="Was he negotiating with the bank"/>
<field name = "f" value="Was the IT department going through a major change"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (865, 'mod2uni1les16p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les16p19">
<field name = "e" value="TRUE"/>
<field name = "f" value="TRUE"/>
<field name = "g" value="TRUE"/>
<field name = "h" value="I DON''T KNOW"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (944, 'mod3uni1les2p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les2p19">
<field name = "f" value="Will Sue have to"/>
<field name = "g" value="will pay"/>
<field name = "h" value="won''t increase"/>
<field name = "i" value="will lend"/>
<field name = "j" value="will not happen"/>
<field name = "k" value="will look into"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (500, 'mod1uni1les8p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p7">
      <field name = "a" value="July seventh"/>
      <field name = "b" value="September twenty-fifth"/>
      <field name = "c" value="May third"/>
      <field name = "d" value="April twelfth"/>
      <field name = "e" value="December second"/>
      <field name = "f" value="November first"/>
      <field name = "g" value="May twenty-second"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 499, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (870, 'mod2uni1les17p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p4">
<field name = "a" value="Sensitivity"/>
<field name = "b" value="acceptance"/>
<field name = "c" value="awareness"/>
<field name = "d" value="Prejudice"/>
<field name = "e" value="sensibility"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (874, 'mod2uni1les17p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p17">
<field name = "a" value="C[or]c"/>
<field name = "b" value="E[or]e"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="D[or]d"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (878, 'mod2uni1les17p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p21">
<field name = "i" value="acceptance"/>
<field name = "j" value="recommend[and]policy"/>
<field name = "k" value="world"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (501, 'mod1uni1les8p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p10">
      <field name = "a" value="smoking"/>
      <field name = "b" value="Sorry"/>
      <field name = "c" value="window"/>
      <field name = "d" value="please"/>
      <field name = "e" value="Here''s"/>
      <field name = "e|1" value="22"/>
      <field name = "e|2" value="4:30"/>
      <field name = "f" value="Thank you"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 500, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (502, 'mod1uni1les8p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p13">
      <field name = "a" value="Can I smoke here?"/>
      <field name = "b" value="You can take a taxi."/>
      <field name = "c" value="Can I use a computer?"/>
      <field name = "d" value="I can make the copies."/>
      <field name = "e" value="Can I leave at 4p.m. today?"/>
      <field name = "f" value="I can''t open this locker."/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 501, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (871, 'mod2uni1les17p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p7">
<field name = "a" value="C[or]c"/>
<field name = "b" value="E[or]e"/>
<field name = "c" value="D[or]d"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (875, 'mod2uni1les17p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p18">
<field name = "a" value="values"/>
<field name = "b" value="environment"/>
<field name = "c" value="diversity"/>
<field name = "d" value="respectful"/>
<field name = "e" value="unacceptable"/>
<field name = "f" value="traffic"/>
<field name = "g" value="diversity"/>
<field name = "h" value="unacceptable"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (503, 'mod1uni1les8p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p15">
      <field name = "a" value="take"/>
      <field name = "b" value="take"/>
      <field name = "c" value="use"/>
      <field name = "d" value="There''s [or] there is"/>
      <field name = "e" value="make"/>
      <field name = "f" value="There''s [or] there is"/>
      <field name = "g" value="have"/>
      <field name = "h" value="there are"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 502, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (508, 'mod1uni1les9p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les9p4">
      <field name = "a" value="likes"/>
      <field name = "b" value="travel"/>
      <field name = "c" value="need"/>
      <field name = "d" value="uses "/>
      <field name = "e" value="speaks"/>
      <field name = "f" value="finish"/>
      <field name = "g" value="answer"/>
      <field name = "h" value="has"/>
      <field name = "i" value="studies"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 507, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (509, 'mod1uni1les9p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les9p5">
      <field name = "a" value="speaks"/>
      <field name = "b" value="leave"/>
      <field name = "c" value="talks"/>
      <field name = "d" value="drive"/>
      <field name = "e" value="make"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 508, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (505, 'mod1uni1les8p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p18">
      <field name = "a" value="June third; twelve p.m. [or] June third; midday"/>
      <field name = "b" value="January first; seven fifteen"/>
      <field name = "c" value="February second; four-oh-five"/>
      <field name = "d" value="August twenty-third; two forty-five"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (872, 'mod2uni1les17p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p12">
<field name = "a" value="E[or]e"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="C[or]c"/>
<field name = "e" value="D[or]d"/>
<field name = "f" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (876, 'mod2uni1les17p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les17p19">
<field name = "a" value="values"/>
<field name = "b" value="work environment"/>
<field name = "c" value="define[and]rules[and]diversity"/>
<field name = "d" value="specific"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (506, 'mod1uni1les8p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p20">
      <field name = "a" value="in"/>
      <field name = "a|1" value="on"/>
      <field name = "a|2" value="at"/>
      <field name = "a|3" value="on"/>
      <field name = "a|4" value="in"/>
      <field name = "a|5" value="at"/>
      <field name = "a|6" value="can"/>
      <field name = "a|7" value="Could"/>
      <field name = "a|8" value="Could"/>
      <field name = "a|9" value="Can"/>
      <field name = "a|10" value="in the"/>
      <field name = "a|11" value="on"/>
      <field name = "a|12" value="at"/>
      <field name = "a|13" value="Could"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 505, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (515, 'mod1uni1les10p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les10p7">
<field name = "a" value="knows"/>
<field name = "b" value="buys"/>
<field name = "b|1" value="has"/>
<field name = "c" value="has"/>
<field name = "d" value="starts"/>
<field name = "e" value="has"/>
<field name = "f" value="leaves"/>
<field name = "f|1" value="drinks"/>
<field name = "g" value="writes"/>
<field name = "h" value="finishes"/>
<field name = "i" value="goes"/>
<field name = "i|1" value="arrives"/>
<field name = "j" value="goes"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 514, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (504, 'mod1uni1les8p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les8p17">
      <field name = "a" value="Her departure from Vancouver is on Sep 24th"/>
      <field name = "b" value="Her departure from Vancouver is at quarter to seven in the evening"/>
      <field name = "c" value="Arrival in Rio de Janeiro is scheduled for September 25th, at 11:30 a.m."/>
      <field name = "d" value="She needs a single room for three nights"/>
      <field name = "e" value="She needs a list of clients to visit"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (512, 'mod1uni1les10p2c', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les10p2c">
<field name = "a" value="I always fly the same air company."/>
<field name = "b" value="They almost never stay at that hotel."/>
<field name = "c" value="We often speak English in our meetings."/>
<field name = "d" value="I always have lunch here."/>
<field name = "e" value="I sometimes use the internet to book a flight."/>
<field name = "f" value="He rarely sends personal e-mails from work."/>
<field name = "g" value="Eddie hardly ever finishes work early. "/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 511, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (507, 'mod1uni1les9p2b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les9p2b">
      <field name = "a" value="ome"/>
      <field name = "b" value="old"/>
      <field name = "c" value="lose"/>
      <field name = "d" value="rite"/>
      <field name = "e" value="uy"/>
      <field name = "f" value="ead"/>
      <field name = "g" value="all"/>
      <field name = "h" value="pen"/>
      <field name = "i" value="uy"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (711, 'mod1uni1les19p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p14">
<field name = "a" value="sorry"/>
<field name = "a|1" value="available"/>
<field name = "b" value="afraid"/>
<field name = "b|1" value="wrong"/>
<field name = "c" value="putting"/>
<field name = "d" value="again"/>
<field name = "e" value="office"/>
<field name = "f" value="hold"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (708, 'mod1uni1les19p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p8">
<field name = "a" value="May I speak to Mrs Johnson, please"/>
<field name = "b" value="May I leave a message"/>
<field name = "c" value="How can I help you"/>
<field name = "d" value="Can I take a message"/>
<field name = "e" value="Is Bruce Roberts there"/>
<field name = "f" value="Yes, speaking"/>
<field name = "g" value="I''m putting you through"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (510, 'mod1uni1les9p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les9p7">
      <field name = "a" value="morning"/>
      <field name = "b" value="new"/>
      <field name = "b|1" value="help"/>
      <field name = "c" value="need"/>
      <field name = "d" value="lunch "/>
      <field name = "e" value="usually"/>
      <field name = "f" value="where"/>
      <field name = "g" value="restaurant"/>
      <field name = "h" value="come"/>
      <field name = "i" value="start"/>
      <field name = "i|1" value="finish"/>
      <field name = "j" value="leave"/>
      <field name = "k" value="locker"/>
      <field name = "l" value="go"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, 509, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (709, 'mod1uni1les19p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p9">
<field name = "a" value="A[or]a"/>
<field name = "b" value="C[or]c"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="D[or]d"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="B[or]b"/>
<field name = "g" value="I[or]i"/>
<field name = "h" value="G[or]g"/>
<field name = "i" value="H[or]h"/>
<field name = "j" value="J[or]j"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (811, 'mod2uni1les6p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p21">
<field name = "a" value="My sister lives in Hawaii"/>
<field name = "b" value="There are 3 shops at the end of the street"/>
<field name = "c" value="The president''s office is opposite the elevators"/>
<field name = "d" value="The conference room is between the stairs and the toilets [or] The conference room is between the toilets and the stairs"/>
<field name = "e" value="She''s standing at the bus stop"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (511, 'mod1uni1les9p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
  <header doctype="plugin" docsubtype="challenge"/>
  <payload>
    <challenge name="mod1uni1les9p8">
      <field name = "a" value="morning"/>
      <field name = "b" value="hi[and]help"/>
      <field name = "c" value="sure[and]need"/>
      <field name = "d" value="lunch"/>
      <field name = "e" value="usually[and]12:30"/>
      <field name = "f" value="where"/>
      <field name = "g" value="restaurant"/>
      <field name = "h" value="come back"/>
      <field name = "i" value="again[and]finish"/>
      <field name = "j" value="leave[and]office"/>
      <field name = "k" value=""/>
      <field name = "l" value="listen"/>
    </challenge>
  </payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (710, 'mod1uni1les19p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p12">
<field name = "a" value="I''m sorry, but she''s not available right now"/>
<field name = "b" value="I''m afraid you have the wrong number"/>
<field name = "c" value="I''m putting you through"/>
<field name = "d" value="What''s your name again, please?"/>
<field name = "e" value="I''m afraid he''s not in the office today"/>
<field name = "f" value="Please hold on a minute"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (812, 'mod2uni1les6p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p22">
<field name = "f" value="Jerry is studying law at university"/>
<field name = "g" value="I''m going to meet Carlo at the station"/>
<field name = "h" value="Who is the man in this photo"/>
<field name = "i" value="My table is next to Angelo''s"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (513, 'mod1uni1les10p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les10p3">
<field name = "a" value="leaves"/>
<field name = "b" value="eat"/>
<field name = "c" value="have"/>
<field name = "d" value="drink"/>
<field name = "e" value="has"/>
<field name = "f" value="goes"/>
<field name = "g" value="holds"/>
<field name = "h" value="arrives"/>
<field name = "i" value="pass"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (517, 'mod1uni1les10p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les10p9">
<field name = "a" value="Matt has breakfast at work."/>
<field name = "a|1" value="false"/>
<field name = "b" value=""/>
<field name = "b|1" value="true"/>
<field name = "c" value="He always finishes work at 5."/>
<field name = "c|1" value="false"/>
<field name = "d" value="He has a folder on his desk."/>
<field name = "d|1" value="false"/>
<field name = "e" value=""/>
<field name = "e|1" value="true"/>
<field name = "f" value="He buys office supplies."/>
<field name = "f|1" value="false"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 516, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (712, 'mod1uni1les1p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les1p3"/>
<field name = "b" value="G[or]g"/>	
<field name = "c" value="F[or]f"/>
<field name = "d" value="C[or]c"/>
<field name = "e" value="D[or]d"/>
<field name = "f" value="E[or]e"/>
<field name = "g" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (814, 'mod2uni1les6p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p25">
<field name = "a" value="1"/>
<field name = "a|1" value="3"/>
<field name = "a|2" value="5"/>
<field name = "a|3" value="2"/>
<field name = "a|4" value="10"/>
<field name = "a|5" value="7"/>
<field name = "a|6" value="4"/>
<field name = "a|7" value="8"/>
<field name = "a|8" value="6"/>
<field name = "a|9" value="9"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (514, 'mod1uni1les10p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les10p5">
<field name = "a" value="director"/>
<field name = "b" value="clock"/>
<field name = "c" value="radio"/>
<field name = "d" value="work"/>
<field name = "e" value="minutes"/>
<field name = "f" value="office"/>
<field name = "f|1" value="e-mail"/>
<field name = "g" value="meetings"/>
<field name = "h" value="lunch"/>
<field name = "h|1" value="client"/>
<field name = "i" value="around"/>
<field name = "i|1" value="evening"/>
<field name = "i|2" value="job"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 513, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (516, 'mod1uni1les10p8b', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les10p8b">
<field name = "a" value="Matt is very organized"/>
<field name = "b" value="He starts work at 8 in the morning "/>
<field name = "c" value="He has a folder and a paper clip holder on his desk"/>
<field name = "d" value="He usually has breakfast at work"/>
<field name = "e" value="He writes e-mails at work"/>
<field name = "f" value="He always goes to college in the evening"/>
<field name = "g" value="He always goes to college after work"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (713, 'mod1uni1les19p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p3">
<field name = "b" value="G[or]g"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="C[or]c"/>
<field name = "e" value="D[or]d"/>
<field name = "f" value="E[or]e"/>
<field name = "g" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (881, 'mod2uni1les18p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p2">
<field name = "a" value="BOTH"/>
<field name = "b" value="BOTH"/>
<field name = "c" value="CARLO"/>
<field name = "d" value="NELSON"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (518, 'mod1uni1les7p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p12">
<field name = "a" value="12"/>
<field name = "b" value="20"/>
<field name = "c" value="13"/>
<field name = "d" value="30"/>
<field name = "e" value="16"/>
<field name = "f" value="60"/>
<field name = "g" value="19"/>
<field name = "h" value="90"/>
<field name = "i" value="17"/>
<field name = "j" value="70"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 493, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (718, 'mod2uni1les5p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p23">
<field name = "b" value="how heavy"/>
<field name = "c" value="how important"/>
<field name = "d" value="how efficient"/>
<field name = "e" value="how far"/>
<field name = "f" value="how tight"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (714, 'mod2uni1les5p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p17">
<field name = "b" value="difficult[or]complex"/>
<field name = "c" value="full"/>
<field name = "d" value="outdated"/>
<field name = "e" value="plain"/>
<field name = "f" value="useless"/>
<field name = "g" value="dangerous"/>
<field name = "h" value="slow"/>
<field name = "i" value="expensive"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (882, 'mod2uni1les18p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p3">
<field name = "e" value="CARLO"/>
<field name = "f" value="NELSON"/>
<field name = "g" value="NELSON"/>
<field name = "h" value="NELSON"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (717, 'mod2uni1les5p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p22">
<field name = "b" value="How expensive!"/>
<field name = "c" value="How cheap!"/>
<field name = "d" value="How fast!"/>
<field name = "e" value="How big!"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (519, 'mod1uni1les7p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les7p20">
<field name = "a" value="Ticket[and]passport"/>
<field name = "b" value="are"/>
<field name = "c" value="check in"/>
<field name = "d" value="items"/>
<field name = "e" value="hand"/>
<field name = "f" value="piece"/>
<field name = "g" value="moment"/>
<field name = "h" value="departure"/>
<field name = "i" value="11"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 496, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (520, 'mod1uni1les8p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les8p11">
<field name = "a" value="seat"/>
<field name = "b" value="allowed"/>
<field name = "c" value="prefer"/>
<field name = "d" value="have"/>
<field name = "e" value="boarding pass"/>
<field name = "f" value="gate"/>
<field name = "g" value="left"/>
<field name = "h" value="Boarding"/>
<field name = "i" value="flight"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (720, 'mod2uni1les5p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p26">
<field name = "a" value="1"/>
<field name = "b" value="3"/>
<field name = "c" value="6"/>
<field name = "d" value="4"/>
<field name = "e" value="2"/>
<field name = "f" value="8"/>
<field name = "g" value="7"/>
<field name = "h" value="5"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (522, 'mod1uni1les11p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les11p3">
<field name = "a" value="interrupt"/>
<field name = "b" value="thanks[and]interview[and]ask"/>
<field name = "c" value="sure"/>
<field name = "d" value="where"/>
<field name = "e" value="relax"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (529, 'mod1uni1les12p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p5">
<field name = "b" value="Rita goes to the beach with her family in January"/>
<field name = "c" value="Rita spends some days on the beach"/>
<field name = "d" value="Rita usually goes to Europe or South America on her winter holiday"/>
<field name = "e" value="Rita likes to have a long break"/>
<field name = "f" value="Rita likes to go to Miami in the summer"/>
<field name = "g" value="At weekends, she goes to a friend''s house in the country"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (524, 'mod1uni1les11p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les11p14">
<field name = "a" value="Do"/>
<field name = "b" value="do"/>
<field name = "c" value="Do"/>
<field name = "d" value="don''t"/>
<field name = "e" value="do"/>
<field name = "f" value="do"/>
<field name = "f|1" value="Does"/>
<field name = "g" value="doesn''t"/>
<field name = "h" value="Does"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (721, 'mod2uni1les5p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p27">
<field name = "a" value="opinion"/>
<field name = "a|1" value="ground"/>
<field name = "a|2" value="sound"/>
<field name = "b" value="needs"/>
<field name = "b|1" value="language"/>
<field name = "b|2" value="support"/>
<field name = "c" value="sense"/>
<field name = "c|1" value="cabinet"/>
<field name = "c|2" value="projector"/>
<field name = "d" value="functional"/>
<field name = "e" value="big"/>
<field name = "f" value="people"/>
<field name = "g" value="nice"/>
<field name = "g|1" value="room"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (523, 'mod1uni1les11p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les11p4">
<field name = "f" value="play"/>
<field name = "g" value="with"/>
<field name = "h" value="often"/>
<field name = "i" value="all[and]guess"/>
<field name = "j" value="time"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (883, 'mod2uni1les18p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p4">
<field name = "a" value="3"/>
<field name = "b" value="1"/>
<field name = "c" value="4"/>
<field name = "d" value="2"/>
<field name = "e" value="5"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (528, 'mod1uni1les12p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p4">
<field name = "a" value="True"/>
<field name = "b" value="False"/>
<field name = "c" value="False"/>
<field name = "d" value="True"/>
<field name = "e" value="True"/>
<field name = "f" value="False"/>
<field name = "g" value="True"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (526, 'mod1uni1les12p1', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p1">
<field name = "a" value="C [or] c"/>
<field name = "b" value="D [or] d"/>
<field name = "c" value="E [or] e"/>
<field name = "d" value="H [or] h"/>
<field name = "e" value="I [or] i"/>
<field name = "f" value="F [or] f"/>
<field name = "g" value="G [or] g"/>
<field name = "i" value="B [or] b"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (530, 'mod1uni1les12p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p6">
<field name = "a" value="G [or] g"/>
<field name = "b" value="A [or] a"/>
<field name = "c" value="F [or] f"/>
<field name = "d" value="B [or] b"/>
<field name = "e" value="E [or] e"/>
<field name = "f" value="C [or] c"/>
<field name = "g" value="D [or] d"/>
<field name = "h" value="H [or] h"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (527, 'mod1uni1les12p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p2">
<field name = "a" value="you"/>
<field name = "b" value="help"/>
<field name = "c" value="I have"/>
<field name = "d" value="about"/>
<field name = "e" value="nice"/>
<field name = "f" value="waiting"/>
<field name = "g" value="reply"/>
<field name = "h" value="that"/>
<field name = "i" value="your"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (535, 'mod1uni1les15p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p12">
<field name = "a" value="team"/>
<field name = "b" value="colleagues"/>
<field name = "b|1" value="rude"/>
<field name = "c" value="definitely"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (719, 'mod2uni1les5p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p25">
<field name = "a" value="False"/>
<field name = "b" value="True"/>
<field name = "c" value="False"/>
<field name = "d" value="I don''t know"/>
<field name = "e" value="False"/>
<field name = "f" value="I don''t know"/>
</challenge>
</payload>
</ivela>
', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (884, 'mod2uni1les18p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p5">
<field name = "a" value="cheking"/>
<field name = "a|1" value="found"/>
<field name = "b" value="encourages"/>
<field name = "b|1" value="created"/>
<field name = "b|2" value="values"/>
<field name = "c" value="worked"/>
<field name = "c|1" value="trying"/>
<field name = "c|2" value="having"/>
<field name = "c|3" value="working"/>
<field name = "c|4" value="deal"/>
<field name = "d" value="hired"/>
<field name = "d|1" value="shadowing"/>
<field name = "d|2" value="got"/>
<field name = "d|3" value="doing"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (885, 'mod2uni1les18p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p9">
<field name = "a" value="called"/>
<field name = "a|1" value="weren''t"/>
<field name = "a|2" value="were"/>
<field name = "a|3" value="was working"/>
<field name = "b" value="walked"/>
<field name = "b|1" value="was talking"/>
<field name = "b|2" value="were working"/>
<field name = "b|3" value="were discussing"/>
<field name = "c" value="was watching"/>
<field name = "c|1" value="went"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (889, 'mod2uni1les18p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p13">
<field name = "a" value="They weren''t very successful."/>
<field name = "b" value="They didn''t need financial help."/>
<field name = "c" value="Did this firm have any financial difficulties?"/>
<field name = "d" value="We put efforts into the diversity project last year."/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (539, 'mod1uni1les12p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p16">
<field name = "a" value="g [or] G"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (531, 'mod1uni1les12p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p7">
<field name = "a" value="D [or] d"/>
<field name = "b" value="E [or] e"/>
<field name = "c" value="C [or] c"/>
<field name = "d" value="F [or] f"/>
<field name = "e" value="B [or] b"/>
<field name = "f" value="G [or] g"/>
<field name = "g" value="A [or] a"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (723, 'mod2uni1les1p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p9">
<field name = "f" value="Deliver[or]deliver"/>
<field name = "g" value="Refund[or]refund"/>
<field name = "h" value="Inconvenience[or]inconvenience"/>
<field name = "i" value="Apologize[or]apologize"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (886, 'mod2uni1les18p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p10">
<field name = "e" value="was"/>
<field name = "e|1" value="told"/>
<field name = "e|2" value="didn''t hear"/>
<field name = "e|3" value="wasn''t listening"/>
<field name = "f" value="was checking"/>
<field name = "f|1" value="found"/>
<field name = "g" value="was having"/>
<field name = "g|1" value="got"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (890, 'mod2uni1les18p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p14">
<field name = "e" value="She was training in the finance department."/>
<field name = "f" value="Were they investing in facilities for the disabled?"/>
<field name = "g" value="What were you doing when the phone rang?"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (536, 'mod1uni1les12p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p13">
<field name = "a" value="a [or] A"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (540, 'mod1uni1les12p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p17">
<field name = "a" value="b [or] B"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (724, 'mod2uni1les1p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les1p6">
<field name = "a" value="discount"/>
<field name = "b" value="complain"/>
<field name = "c" value="inconvenience"/>
<field name = "d" value="dispatch"/>
<field name = "e" value="order"/>
<field name = "f" value="leave"/>
<field name = "g" value="refund"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (887, 'mod2uni1les18p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p11">
<field name = "a" value="began"/>
<field name = "b" value="were trying"/>
<field name = "c" value="saw"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (533, 'mod1uni1les12p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p10">
<field name = "a" value="i [or] I"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (537, 'mod1uni1les12p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p14">
<field name = "a" value="d [or] D"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (541, 'mod1uni1les12p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p18">
<field name = "a" value="e [or] E"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (525, 'mod1uni1les11p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les11p15">
<field name = "a" value="Do you always clear your desk?"/>
<field name = "b" value="How often do you check your e-mail box?"/>
<field name = "c" value="How often do you travel abroad?"/>
<field name = "d" value="Do you play solitaire at lunchtime?"/>
<field name = "e" value="Does he prefer soccer or baseball?"/>
<field name = "f" value="When does she go to the gym?"/>
<field name = "g" value="Where do you play tennis?"/>
<field name = "h" value="When do you go on holiday?"/>
<field name = "i" value="Where does she go at weekends?"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (725, 'mod2uni1les2p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p11">
<field name = "e" value="We don''t use distributors"/>
<field name = "f" value="We ship our products directly to customers"/>
<field name = "g" value="We are waiting for their payment"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (538, 'mod1uni1les12p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p15">
<field name = "a" value="j [or] J"/>
</challenge>
</payload>
</ivela>
', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (888, 'mod2uni1les18p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les18p12">
<field name = "d" value="stopped"/>
<field name = "e" value="were crossing"/>
<field name = "f" value="looked"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (552, 'mod1uni1les14p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les14p9">
<field name = "a" value="smoke"/>
<field name = "b" value="give"/>
<field name = "c" value="wear"/>
<field name = "d" value="be"/>
<field name = "e" value="be"/>
<field name = "f" value="dress"/>
<field name = "g" value="sit"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (553, 'mod1uni1les14p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les14p10">
<field name = "a" value="Don''t kiss"/>
<field name = "b" value="Don''t wear too informal clothes"/>
<field name = "c" value="Don''t be impolite"/>
<field name = "d" value="Don''t be late for work"/>
<field name = "e" value="Don''t dress too casually"/>
<field name = "f" value="Don''t sit on your legs"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (534, 'mod1uni1les12p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p11">
<field name = "a" value="f [or] F"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (550, 'mod1uni1les14p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les14p6">
<field name = "a" value="read"/>
<field name = "a|1" value="English"/>
<field name = "b" value="understand"/>
<field name = "c" value="important"/>
<field name = "c|1" value="Spanish"/>
<field name = "d" value="want"/>
<field name = "e" value="start"/>
<field name = "f" value="immediately"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (551, 'mod1uni1les14p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les14p7">
<field name = "a" value="to other countries"/>
<field name = "b" value="live in"/>
<field name = "c" value="understand"/>
<field name = "c|1" value="speak"/>
<field name = "d" value="advanced"/>
<field name = "d|1" value="speak"/>
<field name = "e" value="important"/>
<field name = "f" value="agree"/>
<field name = "f|1" value="learn"/>
<field name = "g" value="be online"/>
<field name = "g|1" value="in two weeks"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (554, 'mod1uni1les14p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les14p11">
<field name = "a" value="Leave your purse in the locker"/>
<field name = "b" value="Open a ticket for this problem"/>
<field name = "c" value="Start the conference"/>
<field name = "d" value="Dial her number"/>
<field name = "e" value="Reply to the e-mails"/>
<field name = "f" value="Invite Susan for the chat"/>
<field name = "g" value="Press the mute button"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (734, 'mod2uni1les3p28', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p28">
<field name = "e" value="doing"/>
<field name = "f" value="busy"/>
<field name = "f|1" value="check"/>
<field name = "f|2" value="seminar"/>
<field name = "f|3" value="doing"/>
<field name = "g" value="having"/>
<field name = "h" value="interesting"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (730, 'mod2uni1les3p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p22">
<field name = "a" value="D[or]d"/>
<field name = "b" value="E[or]e"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="G[or]g"/>
<field name = "e" value="H[or]h"/>
<field name = "f" value="B[or]b"/>
<field name = "h" value="C[or]c"/>
<field name = "i" value="I[or]i"/>
<field name = "j" value="K[or]k"/>
<field name = "k" value="J[or]j"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (728, 'mod2uni1les3p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p19">
<field name = "a" value="change"/>
<field name = "b" value="transfer"/>
<field name = "c" value="arrive"/>
<field name = "d" value="miss"/>
<field name = "e" value="turn on"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (891, 'mod2uni1les19p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p2">
<field name = "a" value="TRUE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="FALSE"/>
<field name = "e" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (895, 'mod2uni1les19p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p12">
<field name = "a" value="applications"/>
<field name = "b" value="apply for"/>
<field name = "c" value="candidates"/>
<field name = "c|1" value="application"/>
<field name = "c|2" value="applicant"/>
<field name = "d" value="career"/>
<field name = "e" value="advisor"/>
<field name = "f" value="change"/>
<field name = "g" value="apply for"/>
<field name = "g|1" value="apprenticeship"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (726, 'mod2uni1les3p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p13">
<field name = "a" value="G[or]g"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="H[or]h"/>
<field name = "e" value="J[or]j"/>
<field name = "f" value="B[or]b"/>
<field name = "g" value="I[or]i"/>
<field name = "h" value="D[or]d"/>
<field name = "i" value="E[or]e"/>
<field name = "j" value="C[or]c"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (732, 'mod2uni1les3p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p25">
<field name = "a" value="C[or]c"/>
<field name = "b" value="E[or]e"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (521, 'mod1uni1les11p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les11p2">
<field name = "a" value="Yes"/>
<field name = "b" value="No"/>
<field name = "c" value="Yes"/>
<field name = "d" value="I don''t know"/>
<field name = "e" value="Wrong"/>
<field name = "f" value="Right"/>
<field name = "g" value="I don''t know"/>
<field name = "h" value="Right"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (555, 'mod1uni1les14p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"></header>
<payload>
<challenge name="mod1uni1les14p14">
<field name = "a" value="True"/>
<field name = "b" value="I don''t know"/>
<field name = "c" value="True"/>
<field name = "d" value="False"/>
<field name = "e" value="True"/>
<field name = "f" value="False"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (731, 'mod2uni1les3p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p24">
<field name = "a" value="No, he isn''t"/>
<field name = "b" value="Yes, she is"/>
<field name = "c" value="No, she isn''t"/>
<field name = "d" value="Yes, he is"/>
<field name = "e" value="She''s working on a video project"/>
<field name = "f" value="On Tuesday evening"/>
<field name = "g" value="She''s going to visit some clients"/>
<field name = "h" value="Yes, they''re going to the movies"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (729, 'mod2uni1les3p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p20">
<field name = "f" value="turn up"/>
<field name = "g" value="delete"/>
<field name = "h" value="validate"/>
<field name = "i" value="pick up"/>
<field name = "j" value="pack "/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (733, 'mod2uni1les3p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p27">
<field name = "a" value="doing"/>
<field name = "b" value="working"/>
<field name = "c" value="Sounds"/>
<field name = "d" value="dinner"/>
<field name = "e" value="doing"/>
<field name = "f" value="busy"/>
<field name = "f|1" value="check"/>
<field name = "f|2" value="seminar"/>
<field name = "f|3" value="doing"/>
<field name = "g" value="having"/>
<field name = "h" value="interesting"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (892, 'mod2uni1les19p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p3">
<field name = "f" value="I DON''T KNOW"/>
<field name = "g" value="FALSE"/>
<field name = "h" value="TRUE"/>
<field name = "i" value="I DON''T KNOW"/>
<field name = "j" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (896, 'mod2uni1les19p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p13">
<field name = "a" value="g[or]G"/>
<field name = "b" value="l[or]L"/>
<field name = "c" value="m[or]M"/>
<field name = "d" value="i[or]I"/>
<field name = "e" value="l[or]L"/>
<field name = "f" value="k[or]K"/>
<field name = "g" value="e[or]E"/>
<field name = "h" value="c[or]C"/>
<field name = "i" value="b[or]B"/>
<field name = "j" value="d[or]D"/>
<field name = "k" value="h[or]H"/>
<field name = "l" value="f[or]F"/>  
<field name = "m" value="a[or]A"/>                     
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (727, 'mod2uni1les3p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les3p18">
<field name = "a" value="C[or]c"/>
<field name = "b" value="D[or]d"/>
<field name = "c" value="J[or]j"/>
<field name = "d" value="G[or]g"/>
<field name = "e" value="I[or]i"/>
<field name = "f" value="H[or]h"/>
<field name = "g" value="F[or]f"/>
<field name = "h" value="A[or]a"/>
<field name = "i" value="E[or]e"/>
<field name = "j" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (603, 'mod1uni1les15p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p13">
<field name = "a" value="cash machine"/>
<field name = "b" value="solve"/>
<field name = "c" value="work"/>
<field name = "d" value="buy"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (604, 'mod1uni1les15p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p14">
<field name = "a" value="communication"/>
<field name = "b" value="nobody"/>
<field name = "c" value="department"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (605, 'mod1uni1les15p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p15">
<field name = "a" value="talk[and]second"/>
<field name = "b" value="come[and]in"/>
<field name = "c" value="seat"/>
<field name = "d" value="about"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (735, 'mod2uni1les4p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p4">
<field name = "b" value="having lunch with Ken"/>
<field name = "c" value="going to Rio for a meeting"/>
<field name = "d" value="meeting Janet"/>
<field name = "e" value="checking project deadlines"/>
<field name = "f" value="studying for promotion exam"/>
<field name = "g" value="going out with his family"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (739, 'mod2uni1les4p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p10">
<field name = "c" value="They''re going to play soccer"/>
<field name = "d" value="She''s going to pack"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (893, 'mod2uni1les19p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p5">
<field name = "a" value="customer"/>
<field name = "b" value="apply"/>
<field name = "c" value="attached"/>
<field name = "d" value="degree"/>
<field name = "e" value="fluently"/>
<field name = "f" value="currently"/>
<field name = "g" value="available"/>
<field name = "h" value="forward"/>
<field name = "i" value="regards"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (593, 'mod1uni1les13p33', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p33">
<field name = "a" value="hard-working"/>
<field name = "b" value="energetic"/>
<field name = "c" value="focused"/>
<field name = "d" value="teamwork"/>
<field name = "e" value="long meetings"/>
<field name = "f" value="reputable"/>
<field name="g" value="subsidiary"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (594, 'mod1uni1les13p35', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p35">
<field name = "a" value="little"/>
<field name = "b" value="focused"/>
<field name = "c" value="teams"/>
<field name = "d" value="productive"/>
<field name = "e" value="job"/>
</challenge>
</payload>
</ivela>
', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (595, 'mod1uni1les13p36', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p36">
<field name = "f" value="necessary"/>
<field name = "g" value="alone"/>
<field name = "h" value="sure"/>
<field name = "i" value="challenges"/>
<field name = "j" value="long"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (598, 'mod1uni1les11p04', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les11p04">
<field name = "a" value="a colleague"/>
<field name = "b" value="a machine that doesn''t work"/>
<field name = "c" value="the communication between departments"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (557, 'mod1uni1les12p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p9">
<field name = "a" value="H [or] h"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (602, 'mod1uni1les12p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les12p12">
<field name = "a" value="c [or] C"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (591, 'mod1uni1les13p29', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p29">
<field name = "a" value="I[or]i"/>
<field name = "b" value="h[or]h"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="C[or]c"/>
<field name = "f" value="d[or]d"/>
<field name = "g" value="F[or]f"/>
<field name = "h" value="J[or]j"/>
<field name = "i" value="B[or]b"/>
<field name = "j" value="G[or]g"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (597, 'mod1uni1les13p38', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p38">
<field name = "a" value="b[or]B"/>
<field name = "c" value="c[or]C"/>
<field name = "d" value="a[or]c"/>
<field name = "e" value="d[or]D"/>
<field name = "f" value="d[or]D"/>
<field name = "g" value="a[or]A"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (592, 'mod1uni1les13p31', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p31">
<field name = "a" value="e[or]E"/>
<field name = "b" value="d[or]D"/>
<field name = "c" value="i[or]I"/>
<field name = "d" value="g[or]G"/>
<field name = "f" value="j[or]J"/>
<field name = "g" value="b[or]B"/>
<field name = "h" value="c[or]C"/>
<field name = "i" value="f[or]F"/>
<field name = "j" value="h[or]H"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (596, 'mod1uni1les13p37', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les13p37">
<field name = "k" value="necessary[and]important"/>
<field name = "l" value="understand"/>
<field name = "m" value="reputation"/>
<field name = "n" value="products"/>
<field name = "o" value="subsidiary"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (556, 'mod1uni1les14p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les14p15">
<field name = "a" value="travel"/>
<field name = "b" value="problem"/>
<field name = "c" value="move"/>
<field name = "d" value="languages"/>
<field name = "d|1" value="speak"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (607, 'mod1uni1les16p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les16p7">
<field name = "a" value="am calling[or]''m calling"/>
<field name = "b" value="are telling[or]''re telling"/>
<field name = "c" value="are working[or]''re working"/>
<field name = "d" value="is offering[or]''s offering"/>
<field name = "e" value="are talking[or]''re talking"/>
<field name = "f" value="is"/>
<field name = "f|1" value="doing"/>
<field name = "g" value="are selling[or]''re selling"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (609, 'mod1uni1les16p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les16p9">
<field name = "a" value="They are solving problems in the system[or]They''re solving problems in the system"/>
<field name = "b" value="She is talking about the future of the company[or]She''s talking about the future of the company"/>
<field name = "c" value="You are offering a good discount[or]You''re offering a good discount"/>
<field name = "d" value="I am having a problem with my product[or]I''m having a problem with my product"/>
<field name = "e" value="She is comparing prices of everything[or]She''s comparing prices of everything"/>
<field name = "f" value="They are writing e-mails to the head office[or]They''re writing e-mails to the head office"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (736, 'mod2uni1les4p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p5">
<field name = "a" value="He isn''t having lunch with Ken on Friday"/>
<field name = "b" value="He isn''t going to Rio for a meeting on Friday"/>
<field name = "c" value="He isn''t checking project dealines on Tuesday"/>
<field name = "d" value="He isn''t studying for promotion exam on Saturday"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (740, 'mod2uni1les4p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p11">
<field name = "e" value="It''s going to rain"/>
<field name = "f" value="He''s going to see the show"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (618, 'mod1uni1les17p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p14">
<field name = "a" value="Cotton [and] Iron [and] Wood"/>
<field name = "b" value="Car [and] Computer [and] Clothes [and] Telephone [and] Camera"/>
<field name = "c" value="Durable [and] Attractive [and] Cheap [and] Stylish [and] Low [and] High [and] Narrow [and] Short [and] Long"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (614, 'mod1uni1les17p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p4">
<field name = "a" value="purse"/>
<field name = "b" value="attractive"/>
<field name = "b|1" value="stylish"/>
<field name = "c" value="leather"/>
<field name = "c|1" value="durable"/>
<field name = "d" value="black"/>
<field name = "d|1" value="brown"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (616, 'mod1uni1les17p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p6">
<field name = "a" value="product[and]purse"/>
<field name = "b" value="see"/>
<field name = "c" value="attractive"/>
<field name = "d" value="What[and]material"/>
<field name = "e" value="leather"/>
<field name = "f" value="durable"/>
<field name = "g" value="features"/>
<field name = "h" value="comes"/>
<field name = "i" value="well[and]designed"/>
<field name = "j" value="very"/>
</challenge>
</payload>
</ivela>

', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (613, 'mod1uni1les17p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p3">
<field name = "f" value="is"/>
<field name = "g" value="easy"/>
<field name = "h" value="price"/>
<field name = "i" value="300"/>
<field name = "j" value="bit"/>
<field name = "l" value="quality"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (612, 'mod1uni1les17p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p2">
<field name = "a" value="product"/>
<field name = "b" value="material"/>
<field name = "d" value="about"/>
<field name = "e" value="black"/>
<field name = "e|1" value="brown"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (617, 'mod1uni1les17p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p7">
<field name = "k" value="What[and]size"/>
<field name = "l" value="easy[and]carry"/>
<field name = "m" value="also[and]light"/>
<field name = "n" value="about"/>
<field name = "o" value="don''t[and]cheapest"/>
<field name = "p" value="selling"/>
<field name = "q" value="a bit[and]expensive"/>
<field name = "r" value="value[and]money"/>
<field name = "s" value="more"/>
<field name = "t" value="higher"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (615, 'mod1uni1les17p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p5">
<field name = "e" value="designed"/>
<field name = "e|1" value="practical"/>
<field name = "f" value="cheapest"/>
<field name = "g" value="competitors"/>
<field name = "h" value="more expensive"/>
<field name = "h|1" value="higher"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (610, 'mod1uni1les16p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les16p10">
<field name = "a" value="They aren''t solving problems in the system[or]They are not solving problems in the system"/>
<field name = "a|1" value="Are they solving problems in the system"/>
<field name = "b" value="She isn''t talking about the future of the company[or]She is not talking about the future of the company"/>
<field name = "b|1" value="Is she talking about the future of the company"/>
<field name = "c" value="You aren''t offering a good discount[or]You are not offering a good discount"/>
<field name = "c|1" value="Are you offering a good discount"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (611, 'mod1uni1les16p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les16p11">
<field name = "d" value="I''m not having a problem with my product"/>
<field name = "d|1" value="Are you having a problem with your product"/>
<field name = "e" value="She isn''t always comparing prices[or]She is not always comparing prices"/>
<field name = "e|1" value="Is she always comparing prices"/>
<field name = "f" value="They aren''t always writing e-mails to the head office[or]They are not always writing e-mails to the head office"/>
<field name = "f|1" value="Are they always writing e-mails to the head office"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (619, 'mod1uni1les17p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p15">
<field name = "b" value="It is a fashionable car"/>
<field name = "c" value="It is an expensive computer"/>
<field name = "d" value="It is a multinational wood industry"/>
<field name = "e" value="It is a stylish cotton shirt"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (737, 'mod2uni1les4p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p6">
<field name = "a" value="is"/>
<field name = "a|1" value="having"/>
<field name = "b" value="Is"/>
<field name = "b|1" value="going"/>
<field name = "c" value="Is"/>
<field name = "c|1" value="checking"/>
<field name = "d" value="is"/>
<field name = "d|1" value="meeting"/>
<field name = "e" value="is"/>
<field name = "e|1" value="having"/>
<field name = "f" value="is"/>
<field name = "f|1" value="going"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (894, 'mod2uni1les19p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p8">
<field name = "a" value="n[or]N"/>
<field name = "b" value="n[or]N"/>
<field name = "c" value="n[or]N"/>
<field name = "d" value="n[or]N"/>
<field name = "e" value="n[or]N"/>
<field name = "f" value="n[or]N"/>
<field name = "g" value="n[or]N"/>
<field name = "h" value="v[or]V"/>
<field name = "i" value="n[or]N"/>
<field name = "j" value="v[or]V"/>
<field name = "k" value="n[or]N"/>
<field name = "l" value="n[or]N"/>
<field name = "m" value="n[or]N"/>
<field name = "n" value="v[or]V"/>
<field name = "o" value="n[or]N"/>
<field name = "p" value="n[or]N"/>
<field name = "q" value="n[or]N"/>
<field name = "r" value="a[or]A"/>
<field name = "s" value="n[or]N"/>
<field name = "t" value="a[or]A"/>
<field name = "u" value="v[or]V"/>
<field name = "v" value="v[or]V"/>
<field name = "w" value="n[or]N"/>
<field name = "x" value="n[or]N"/>
<field name = "y" value="n[or]N[or]v[or]V"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (898, 'mod2uni1les19p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p16">
<field name = "a" value="3"/>
<field name = "a|1" value="1"/>
<field name = "a|2" value="4"/>
<field name = "a|3" value="2"/>
<field name = "a|4" value="6"/>
<field name = "a|5" value="5"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (630, 'mod1uni1les18p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p17">
<field name = "f" value="the most reliable"/>
<field name = "g" value="the most fashionable"/>
<field name = "h" value="the best"/>
<field name = "i" value="the widest"/>
<field name = "j" value="the largest"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (620, 'mod1uni1les17p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p16">
<field name = "a" value="Wood"/>
<field name = "b" value="Cotton"/>
<field name = "c" value="Iron"/>
<field name = "d" value=""/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (738, 'mod2uni1les4p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p9">
<field name = "b" value="I''m going to have a shower"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (897, 'mod2uni1les19p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les19p15">
<field name = "a" value="i[or]I"/>
<field name = "b" value="i[or]I"/>
<field name = "c" value="s[or]S"/>
<field name = "d" value="s[or]S"/>
<field name = "e" value="i[or]I"/>
<field name = "f" value="i[or]I"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (623, 'mod1uni1les18p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p3">
<field name = "a" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "b" value="Customer[or]customer[or]CUSTOMER"/>
<field name = "c" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "d" value="Customer[or]customer[or]CUSTOMER"/>
<field name = "e" value="Customer[or]customer[or]CUSTOMER"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (624, 'mod1uni1les18p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p4">
<field name = "f" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "g" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "h" value="Customer[or]customer[or]CUSTOMER"/>
<field name = "i" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "j" value="Customer[or]customer[or]CUSTOMER"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (625, 'mod1uni1les18p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p5">
<field name = "k" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "l" value="Customer[or]customer[or]CUSTOMER"/>
<field name = "m" value="Customer[or]customer[or]CUSTOMER"/>
<field name = "n" value="Salesman[or]salesman[or]SALESMAN"/>
<field name = "o" value="Salesman[or]salesman[or]SALESMAN"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (629, 'mod1uni1les18p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p16">
<field name = "a" value="most difficult"/>
<field name = "b" value="the greatest"/>
<field name = "c" value="the safest"/>
<field name = "d" value="the cheapest"/>
<field name = "e" value="the fastest"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (626, 'mod1uni1les18p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p6">
<field name = "a" value="Hello. How can I help you?"/>
<field name = "b" value="I''m looking for a new washing machine."/>
<field name = "c" value="Do you prefer any specific brand?"/>
<field name = "d" value="Not really. But I''d rather have a cheap one."/>
<field name = "e" value="Ok, let''s see what we have. Well, I can offer you these two models."/>
<field name = "f" value="What are the differences between them?"/>
<field name = "g" value="This one has got the best price of the market."/>
<field name = "h" value="It sounds good. Can you tell me a little about the other washing machine?"/>
<field name = "i" value="It is a bit more expensive, but he difference is only 230 dollars."/>
<field name = "j" value="Right. I think I need more time to decide. Thank you very much."/>
<field name = "k" value="Anytime. Have a nice day!"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (627, 'mod1uni1les18p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p14">
<field name = "a" value="higher than"/>
<field name = "b" value="more profitable than"/>
<field name = "c" value="lower than"/>
<field name = "d" value="more competitive than"/>
<field name = "e" value="cheaper than"/>
<field name = "f" value="more popular than"/>
<field name = "g" value="faster than"/>
<field name = "h" value="better"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (628, 'mod1uni1les18p15', '<?xml version="1.0" encoding="ISO-8859-1"?><ivela>
<header doctype="plugin" docsubtype="challenge" />
<payload>
<challenge name="mod1uni1les18p15">
<field name = "a" value="more difficult"/>
<field name = "b" value="more expensive"/>
<field name = "c" value="older"/>
<field name = "d" value="younger"/>
<field name = "e" value="later"/>
<field name = "f" value="more intelligent"/>
<field name = "g" value="more stylish"/>
<field name = "h" value="more efficient"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (622, 'mod1uni1les18p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p2">
<field name = "a" value="False"/>
<field name = "b" value="True"/>
<field name = "c" value="False"/>
<field name = "d" value="True"/>
<field name = "e" value="False"/>
<field name = "f" value="True"/>
<field name = "g" value="False"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (741, 'mod2uni1les4p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p12">
<field name = "g" value="They''re going to fly to Curitiba"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (632, 'mod1uni1les18p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p19">
<field name = "f" value="Higher[or]higher"/>
<field name = "f|1" value="The highest[or]the highest"/>
<field name = "f|2" value="Low[or]low"/>
<field name = "g" value="Smaller[or]smaller"/>
<field name = "g|1" value="The smallest[or]the smallest"/>
<field name = "g|2" value="Big[or]big"/>
<field name = "h" value="More expensive[or]more expensive"/>
<field name = "h|1" value="The most expensive[or]the most expensive"/>
<field name = "h|2" value="Cheap[or]cheap"/>
<field name = "i" value="Colder[or]colder"/>
<field name = "i|1" value="The coldest[or]the coldest"/>
<field name = "i|2" value="Hot[or]hot"/>
<field name = "j" value="Shorter[or]shorter"/>
<field name = "j|1" value="The shortest[or]the shortest"/>
<field name = "j|2" value="Long[or]long"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (621, 'mod1uni1les17p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p19">
<field name = "b" value="It is a fashionable car"/>
<field name = "c" value="It is an expensive computer"/>
<field name = "d" value="It is a multinational wood industry"/>
<field name = "e" value="It is a stylish cotton shirt"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (744, 'mod2uni1les4p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p16">
<field name = "a" value="I don''t know"/>
<field name = "b" value="Yes"/>
<field name = "c" value="Yes"/>
<field name = "d" value="I don''t know"/>
<field name = "e" value="No"/>
<field name = "f" value="Yes"/>
<field name = "g" value="No"/>
<field name = "h" value="Yes"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (642, 'mod1uni1les20p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p4">
<field name = "a" value="Stuart has a problem in his system"/>
<field name = "b" value="Nelson plays tennis"/>
<field name = "c" value="The server is not responding[or]The server isn''t responding"/>
<field name = "d" value="Mr Robinson is not in the office[or]Mr Robinson isn''t in the office"/>
<field name = "e" value="He''s placing an order for service"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (637, 'mod1uni1les19p09', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p09">
<field name = "a" value="May I speak to Mrs Johnson, please?"/>
<field name = "b" value="May I leave a message?"/>
<field name = "c" value="This is 3539-4009. How can I help you?"/>
<field name = "d" value="Can I take a message?"/>
<field name = "e" value="Is Bruce Roberts there?"/>
<field name = "f" value="Yes, speaking"/>
<field name = "g" value="I''m putting you through"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (640, 'mod1uni1les19p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p16">
<field name = "a|1" value="sorry"/>
<field name = "a|2" value="available"/>
<field name = "b|1" value="afraid"/>
<field name = "b|2" value="wrong"/>
<field name = "c" value="putting"/>
<field name = "d" value="again"/>
<field name = "e" value="office"/>
<field name = "f" value="hold"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (631, 'mod1uni1les18p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"></header>
<payload>
<challenge name="mod1uni1les18p18">
<field name = "a" value="Hotter[or]hotter"/>
<field name = "a|1" value="The hottest[or]the hottest"/>
<field name = "a|2" value="Cold[or]cold"/>
<field name = "b" value="Longer [or] longer"/>
<field name = "b|1" value="The longest[or]the longest"/>
<field name = "b|2" value="Short[or]short"/>
<field name = "c" value="Slower[or]slower"/>
<field name = "c|1" value="The slowest[or]the slowest"/>
<field name = "c|2" value="Fast[or]fast"/>
<field name = "d" value="Wider[or]wider"/>
<field name = "d|1" value="The widest[or]the widest"/>
<field name = "d|2" value="Narrow[or]narrow"/>
<field name = "e" value="Heavy[or]heavy"/>
</challenge>
<field name = "e|1" value="The lightest[or]the lightest"/>
<field name = "e|2" value="Lighter[or]lighter"/>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (742, 'mod2uni1les4p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p13">
<field name = "a" value="What is she doing next Tuesday"/>
<field name = "b" value="She''s going to visit some clients"/>
<field name = "c" value="Are they doing something together on Friday"/>
<field name = "d" value="In the evening I''m having dinner with my team"/>
<field name = "e" value="I''m going to change the deadline"/>
<field name = "f" value="She isn''t going to validate these lists"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (638, 'mod1uni1les19p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p10">
<field name = "a" value="A[or]a"/>
<field name = "b" value="C[or]c"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="D[or]d"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="B[or]b"/>
<field name = "g" value="I[or]i"/>
<field name = "h" value="G[or]g"/>
<field name = "i" value="H[or]h"/>
<field name = "j" value="J[or]j"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (641, 'mod1uni1les20p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p3">
<field name = "a" value="2"/>
<field name = "b" value="1"/>
<field name = "c" value="1"/>
<field name = "d" value="2"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (648, 'mod1uni1les20p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p19">
<field name = "a" value="How can I help you?[and]I''m calling about the new.[and]Have a good day."/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (643, 'mod1uni1les20p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p5">
<field name = "b" value="How"/>
<field name = "c" value="Great"/>
<field name = "c|1" value="tennis"/>
<field name = "c|2" value="weekend"/>
<field name = "d" value="problem"/>
<field name = "e" value="What''s[or]What is"/>
<field name = "f" value="responding"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (636, 'mod1uni1les19p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p5">
<field name = "a" value="speaking"/>
<field name = "b" value="that"/>
<field name = "c" value="help"/>
<field name = "d" value="speak"/>
<field name = "e" value="calling"/>
<field name = "f" value="This"/>
<field name = "g" value="sorry"/>
<field name = "h" value="leave"/>
<field name = "i" value="Sure"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (746, 'mod2uni1les4p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p18">
<field name = "e" value="double"/>
<field name = "f" value="price"/>
<field name = "g" value="pay"/>
<field name = "h" value="cash"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (644, 'mod1uni1les20p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p9">
<field name = "a" value="C[or]c"/>
<field name = "b" value="F[or]f"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="A[or]a"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (645, 'mod1uni1les20p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p12">
<field name = "a" value="I''m calling about my air conditioning[or]Hello. I''m calling about my air conditioning"/>
<field name = "b" value="Well, the instructions are missing and the invoice is incorrect"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (743, 'mod2uni1les4p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p14">
<field name = "a" value="is walking[or]is going to walk"/>
<field name = "b" value="are going[or]are going to go"/>
<field name = "b|1" value="is having[or]is going to have"/>
<field name = "c" value="are discussing[or]are going to discuss"/>
<field name = "d" value="is going to be"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (747, 'mod2uni1les4p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p19">
<field name = "a" value="staying"/>
<field name = "b" value="arriving"/>
<field name = "c" value="needs"/>
<field name = "d" value="fee"/>
<field name = "e" value="take"/>
<field name = "f" value="paying"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (899, 'mod2uni1les20p1', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p1">
<field name = "a" value="Y[OR]y"/>
<field name = "b" value="Y[OR]y"/>
<field name = "c" value="N[OR]n"/>
<field name = "d" value="Y[OR]y"/>
<field name = "e" value="Y[OR]y"/>
<field name = "f" value="N[OR]n"/>
<field name = "g" value="Y[OR]y"/>
<field name = "h" value="Y[OR]y"/>
<field name = "i" value="Y[OR]y"/>
<field name = "j" value="Y[OR]y"/>
<field name = "k" value="N[OR]n"/>
<field name = "l" value="Y[OR]y"/>
<field name = "m" value="Y[OR]y"/>
<field name = "n" value="N[OR]n"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (903, 'mod2uni1les20p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p6">
<field name = "a" value="A1"/>
<field name = "b" value="B1"/>
<field name = "c" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (907, 'mod2uni1les20p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p14">
<field name = "e" value="3"/>
<field name = "f" value="5"/>
<field name = "g" value="1"/>
<field name = "h" value="6"/>
<field name = "i" value="2"/>
<field name = "g" value="4"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (652, 'mod1uni1les17p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p21">
<field name = "b" value="Heavy"/>
<field name = "c" value="Short"/>
<field name = "d" value="Cold"/>
<field name = "e" value="Big"/>
<field name = "f" value="Fast"/>
<field name = "g" value="Expensive"/>
<field name = "h" value="Narrow"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (650, 'mod1uni1les17p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p17">
<field name = "a" value="Car[and]Telephone"/>
<field name = "b" value="Clothes"/>
<field name = "c" value="Computer"/>
<field name = "d" value="Camera"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (653, 'mod1uni1les17p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p20">
<field name = "g" value="This is an efficient method"/>
<field name = "h" value="That is a very good computer program"/>
<field name = "i" value="It is a durable leather jacket"/>
<field name = "j" value="It is a cheap computer"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (606, 'mod1uni1les15p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p17">
<field name = "a" value="doesn''t work"/>
<field name = "b" value="it''s broken"/>
<field name = "c" value="missing"/>
<field name = "d" value="engaged"/>
<field name = "e" value="is incorrect"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (649, 'mod1uni1les15p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p4">
<field name = "a" value="a colleague"/>
<field name = "b" value="a machine that doesn''t work"/>
<field name = "c" value="the communication between departments"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (608, 'mod1uni1les16p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les16p8">
<field name = "a" value="CUSTOMER[or]customer"/>
<field name = "b" value="CUSTOMER[or]customer"/>
<field name = "c" value="SALESPERSON[or]salesperson"/>
<field name = "d" value="SALESPERSON[or]salesperson"/>
<field name = "e" value="SALESPERSON[or]salesperson"/>
<field name = "f" value="CUSTOMER[or]customer"/>
<field name = "g" value="SALESPERSON[or]salesperson"/>
<field name = "h" value="CUSTOMER[or]customer"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (654, 'mod1uni1les17p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p23">
<field name = "a" value="G[or]g"/>
<field name = "b" value="F[or]f"/>
<field name = "c" value="C[or]c"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="H[or]h"/>
<field name = "f" value="D[or]d"/>
<field name = "h" value="E[or]e"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (745, 'mod2uni1les4p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les4p17">
<field name = "a" value="make"/>
<field name = "b" value="reservation"/>
<field name = "c" value="staying"/>
<field name = "d" value="people"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (647, 'mod1uni1les20p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p17">
<field name = "a" value="D[or]d"/>
<field name = "b" value="F[or]f"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="C[or]c"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="E[or]e"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (599, 'mod1uni1les15p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les15p11">
<field name = "a" value="team"/>
<field name = "b" value="colleagues"/>
<field name = "b|1" value="rude"/>
<field name = "c" value="definitely"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (900, 'mod2uni1les20p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p3">
<field name = "a" value="Goal"/>
<field name = "b" value="Summary of qualifications"/>
<field name = "c" value="Strong points"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (651, 'mod1uni1les17p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les17p18">
<field name = "a" value="Durable[and]High"/>
<field name = "b" value="Low[and]Narrow"/>
<field name = "c" value="Attractive[and]Cheap"/>
<field name = "d" value="Short[and]Stylish[and]Long"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (904, 'mod2uni1les20p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p7">
<field name = "d" value="D2"/>
<field name = "e" value="E"/>
<field name = "f" value="F"/>
<field name = "g" value="G"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (646, 'mod1uni1les20p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les20p15">
<field name = "a" value="The train doesn''t leave at 10:00 [or] The train does not leave at 10:00."/>
<field name = "b" value="Does the office open at 9:00?"/>
<field name = "c" value="I don''t have a meeting on Friday. [or] I do not have a meeting on Friday."/>
<field name = "d" value="Do our colleagues have a call every Wednesday?"/>
<field name = "e" value="Do you finish work early on Fridays?"/>
<field name = "f" value="Yes, I do."/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, 512, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (908, 'mod2uni1les20p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p16">
<field name = "a" value="work"/>
<field name = "b" value="are organizing"/>
<field name = "c" value="need"/>
<field name = "d" value="does the park open"/>
<field name = "e" value="does it close"/>
<field name = "f" value="are"/>
<field name = "g" value="opens"/>
<field name = "h" value="close"/>
<field name = "i" value="does the first parade start"/>
<field name = "j" value="starts"/>
<field name = "k" value="finishes"/>
<field name = "l" value="are you planning"/>
<field name = "m" value="am considering"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (633, 'mod1uni1les18p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les18p20">
<field name = "k" value="Faster[or]faster"/>
<field name = "k|1" value="The fastest[or]the fastest"/>
<field name = "k|2" value="Slow[or]slow"/>
<field name = "l" value="Narrower[or]narrower"/>
<field name = "l|1" value="The narrowest[or]the narrowest"/>
<field name = "l|2" value="Wide[or]wide"/>
<field name = "m" value="Heavier[or]heavier"/>
<field name = "m|1" value="The heaviest[or]the heaviest"/>
<field name = "m|2" value="Light[or]light"/>
<field name = "n" value="Lower[or]lower"/>
<field name = "n|1" value="The lowest[or]the lowest"/>
<field name = "n|2" value="High[or]high"/>
<field name = "o" value="Bigger[or]bigger"/>
<field name = "o|1" value="The biggest[or]the biggest"/>
<field name = "o|2" value="Small[or]small"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (655, 'mod1uni1les19p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod1uni1les19p15">
<field name = "a" value="sorry"/>
<field name = "a|1" value="available"/>
<field name = "b" value="afraid"/>
<field name = "b|1" value="wrong"/>
<field name = "c" value="putting"/>
<field name = "d" value="again"/>
<field name = "e" value="office"/>
<field name = "f" value="hold"/>
</challenge>
</payload>
</ivela>', 1, 1, 1, NULL, NULL, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (715, 'mod2uni1les5p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p18">
<field name = "a" value="C[or]c"/>
<field name = "c" value="D[or]d"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="E[or]e"/>
<field name = "f" value="B[or]b"/>
<field name = "g" value="G[or]g"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (901, 'mod2uni1les20p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p4">
<field name = "d" value="Educational background"/>
<field name = "e" value="Work experience"/>
<field name = "f" value="Results obtained"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (905, 'mod2uni1les20p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p11">
<field name = "a" value="Sarah"/>
<field name = "b" value="John"/>
<field name = "c" value="Sarah"/>
<field name = "d" value="John"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (909, 'mod2uni1les20p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les20p17">
<field name = "a" value="manufactures"/>
<field name = "b" value="sells"/>
<field name = "c" value="requires"/>
<field name = "d" value="starts"/>
<field name = "e" value="puts"/>
<field name = "f" value="is giving"/>
<field name = "g" value="is demonstrating"/>
<field name = "h" value="is explaining"/>
<field name = "i" value="is applying"/>
<field name = "j" value="has"/>
<field name = "k" value="starts"/>
<field name = "l" value="has"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (947, 'mod2uni1les21p1', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p1">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (948, 'mod2uni1les21p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p2">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>
', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (949, 'mod2uni1les21p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p3">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (950, 'mod2uni1les21p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p4">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (951, 'mod2uni1les21p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p5">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (952, 'mod2uni1les21p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p6">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (953, 'mod2uni1les21p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p7">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (954, 'mod2uni1les21p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p8">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (955, 'mod2uni1les21p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p9">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (956, 'mod2uni1les21p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p10">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (957, 'mod2uni1les21p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p11">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (958, 'mod2uni1les21p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p12">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>
', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (959, 'mod2uni1les21p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p13">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (960, 'mod2uni1les21p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p14">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (961, 'mod2uni1les21p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p15">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (965, 'mod2uni1les21p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p19">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (969, 'mod2uni1les21p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p23">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (973, 'mod2uni1les21p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p27">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (962, 'mod2uni1les21p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p16">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (966, 'mod2uni1les21p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p20">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (970, 'mod2uni1les21p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p24">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (974, 'mod2uni1les21p28', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p28">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (963, 'mod2uni1les21p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p17">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (967, 'mod2uni1les21p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p21">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (971, 'mod2uni1les21p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p25">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (964, 'mod2uni1les21p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p18">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (968, 'mod2uni1les21p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p22">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (972, 'mod2uni1les21p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les21p26">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (984, 'mod3uni1les3p15', '<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p15">
<field name = "a" value="have/has been"/>
<field name = "b" value="have/has sent"/>
<field name = "c" value="have/has arrived"/>
<field name = "d" value="have/has left"/>
<field name = "e" value="have/has recorded"/>
<field name = "f" value="have/has stored"/>
<field name = "g" value="have/has launched"/>
<field name = "h" value="have/has cost"/>
<field name = "i" value="have/has spent"/>
<field name = "j" value="have/has wasted"/>
<field name = "k" value="have/has paid"/>
<field name = "l" value="have/has rewarded"/>
<field name = "m" value="have/has cut"/>
<field name = "n" value="have/has done"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (985, 'mod3uni1les4p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p4">
<field name = "a" value="C[or]c"/>
<field name = "b" value="E[or]e"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (986, 'mod3uni1les4p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p9">
<field name = "a" value="E[or]e"/>
<field name = "b" value="B[or]b"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="C[or]c"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (987, 'mod3uni1les4p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p14">
<field name = "a" value="do well"/>
<field name = "b" value="differentiate"/>
<field name = "c" value="take responsibility"/>
<field name = "d" value="practicing"/>
<field name = "e" value="generates"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (988, 'mod3uni1les4p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p15">
<field name = "a" value="good"/>
<field name = "b" value="brands"/>
<field name = "c" value="well-being"/>
<field name = "d" value="returns"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (977, 'mod3uni1les3p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p4">
<field name = "a" value="contact"/>
<field name = "a|1" value="implement"/>
<field name = "b" value="put"/>
<field name = "b|1" value="particular"/>
<field name = "c" value="business"/>
<field name = "c|1" value="social"/>
<field name = "d" value="doing"/>
<field name = "d|1" value="improve"/>
<field name = "e" value="started"/>
<field name = "e|1" value="helping"
<field name = "f" value="page"/>
<field name = "g" value="know"/>
<field name = "h" value="asked"/>
<field name = "h|1" value="given"/>
<field name = "i" value="agenda"/>
<field name = "i|1" value="involved"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (978, 'mod3uni1les3p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p5">
<field name = "a" value="glad"/>
<field name = "b" value="tried"/>
<field name = "c" value="a lot of"/>
<field name = "d" value="useful"/>
<field name = "e" value="get down"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (979, 'mod3uni1les3p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p6">
<field name = "f" value="point "/>
<field name = "g" value="page"/>
<field name = "h" value="asked"/>
<field name = "i" value="insights"/>
<field name = "j" value="high"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (980, 'mod3uni1les3p7', '', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (989, 'mod3uni1les4p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p16">
<field name = "a" value="They were very effective at creating value"/>
<field name = "b" value="I took the opportunity to spread the information"/>
<field name = "c" value="Positive attitude will bring about change"/>
<field name = "d" value="Our well-being depends on your engagement"/>
<field name = "e" value="Our company''s core business is services"/>
<field name = "f" value="He took responsibility for the negative impact"/>
<field name = "g" value="CSR can be a platform for differentiation"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (990, 'mod3uni1les4p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p20">
<field name = "a" value="FALSE"/>
<field name = "b" value="TRUE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="TRUE"/>
<field name = "e" value="FALSE"/>
<field name = "f" value="TRUE"/>
<field name = "g" value="FALSE"/>
<field name = "h" value="FALSE"/>
<field name = "i" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (993, 'mod3uni1les4p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p25">
<field name = "a" value="YES"/>
<field name = "b" value="NO"/>
<field name = "c" value="NO"/>
<field name = "d" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (994, 'mod3uni1les4p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p26">
<field name = "e" value="YES"/>
<field name = "f" value="NO"/>
<field name = "g" value="YES"/>
<field name = "h" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (997, 'mod3uni1les4p29', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p29">
<field name = "a" value="have applied"/>
<field name = "b" value="have reduced"/>
<field name = "c" value="have risen"/>
<field name = "d" value="has cut"/>
<field name = "e" value="have benefited"/>
<field name = "f" value="have established"/>
<field name = "g" value="has decided"/>
<field name = "h" value="has increased"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (998, 'mod3uni1les4p30', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p30">
<field name = "a" value="They haven''t applied the right pricing strategy"/>
<field name = "b" value="Have house prices grown faster than ever before"/>
<field name = "c" value="Have prices fallen in the food industry due to new technologies"/>
<field name = "d" value="ABC hasn''t cut the program sponsorship due to sales decrease"/>
<field name = "e" value="Have companies benefited greatly from the marketing generated by CSR"/>
<field name = "f" value="Companies haven''t reviewed their prices to meet market demands"/>
<field name = "g" value="The company has decided about cutting jobs"/>
<field name = "h" value="The volume of sales hasn''t increased"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (991, 'mod3uni1les4p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p21">
<field name = "a" value="Yes"/>
<field name = "b" value="A new project"/>
<field name = "c" value="She has just arrived"/>
<field name = "d" value="Social responsibility"/>
<field name = "e" value="Disfranchised youngsters"/>
<field name = "f" value="No. They are needy"/>
<field name = "g" value="Productive skills, such as computing, etc"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (995, 'mod3uni1les4p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p27">
<field name = "a" value="TRUE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="FALSE"/>
<field name = "e" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (992, 'mod3uni1les4p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p22">
<field name = "a" value="2"/>
<field name = "b" value="4"/>
<field name = "c" value="9"/>
<field name = "d" value="5"/>
<field name = "e" value="1"/>
<field name = "f" value="6"/>
<field name = "g" value="10"/>
<field name = "h" value="7"/>
<field name = "i" value="3"/>
<field name = "j" value="11"/>
<field name = "k" value="8"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1000, 'mod3uni1les4p31', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p31">
<field name = "a" value="D[or]d"/>
<field name = "b" value="G[or]g"/>
<field name = "c" value="I[or]i"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="L[or]l"/>
<field name = "f" value="C[or]c"/>
<field name = "g" value="K[or]k"/>
<field name = "h" value="J[or]j"/>
<field name = "i" value="H[or]h"/>
<field name = "j" value="B[or]b"/>
<field name = "k" value="F[or]f"/>
<field name = "l" value="E[or]e"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (996, 'mod3uni1les4p28', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les4p28">
<field name = "f" value="FALSE"/>
<field name = "g" value="TRUE"/>
<field name = "h" value="FALSE"/>
<field name = "i" value="TRUE"/>
<field name = "j" value="TRUE"/>
<field name = "k" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1015, 'mod3uni1les6p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p3">
<field name = "d" value="B"/>
<field name = "e" value="A"/>
<field name = "f" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1037, 'mod3uni1les3p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p2">
<field name = "a" value="YES"/>
<field name = "b" value="YES"/>
<field name = "c" value="NO"/>
<field name = "d" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (976, 'mod3uni1les3p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p3">
<field name = "e" value="NO"/>
<field name = "f" value="NO"/>
<field name = "g" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1039, 'mod3uni1les3p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p16">
<field name = "a" value="have"/>
<field name = "a|1" value="been"/>
<field name = "b" value="has"/>
<field name = "b|1" value="left"/>
<field name = "c" value="have had"/>
<field name = "d" value="has kept"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1040, 'mod3uni1les3p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p18">
<field name = "a" value="I''ve"/>
<field name = "b" value="She''s"/>
<field name = "c" value="I''ve"/>
<field name = "d" value="He''s"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1041, 'mod3uni1les3p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p19">
<field name = "e" value="She''s"/>
<field name = "f" value="We''ve"/>
<field name = "g" value="They''ve"/>
<field name = "h" value="We''ve"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1042, 'mod3uni1les3p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p22">
<field name = "a" value="have/has been"/>
<field name = "b" value="have/has sent"/>
<field name = "c" value="have/has arrived"/>
<field name = "d" value="have/has left"/>
<field name = "e" value="have/has recorded"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1043, 'mod3uni1les3p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p23">
<field name = "f" value="have/has stored"/>
<field name = "g" value="have/has launched"/>
<field name = "h" value="have/has cost"/>
<field name = "i" value="have/has spent"/>
<field name = "j" value="have/has wasted"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1044, 'mod3uni1les3p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les3p24">
<field name = "k" value="have/has paid"/>
<field name = "l" value="have/has rewarded"/>
<field name = "m" value="have/has cut"/>
<field name = "n" value="have/has done"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1045, 'mod3uni1les5p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p2">
<field name = "a" value="NO"/>
<field name = "b" value="NO"/>
<field name = "c" value="YES"/>
<field name = "d" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1046, 'mod3uni1les5p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p3">
<field name = "e" value="YES"/>
<field name = "f" value="YES"/>
<field name = "g" value="YES"/>
<field name = "h" value="NO"/>
<field name = "i" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1047, 'mod3uni1les5p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p4">
<field name = "a" value="C[or]c"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="D[or]d"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="E[or]e"/>
<field name = "f" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1048, 'mod3uni1les5p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p5">
<field name = "a" value="results"/>
<field name = "b" value="tasks"/>
<field name = "c" value="skeptical"/>
<field name = "c|1" value="attitude"/>
<field name = "d" value="results"/>
<field name = "d|1" value="job"/>
<field name = "d|1" value="simple"/>
<field name = "e" value="done"/>
<field name = "e|1" value="become"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1049, 'mod3uni1les5p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p12">
<field name = "a" value="2"/>
<field name = "b" value="3"/>
<field name = "c" value="2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1050, 'mod3uni1les5p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p13">
<field name = "d" value="1"/>
<field name = "e" value="2"/>
<field name = "f" value="1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1051, 'mod3uni1les5p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p14">
<field name = "g" value="3"/>
<field name = "h" value="2"/>
<field name = "i" value="2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1052, 'mod3uni1les5p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p15">
<field name = "a" value="They have taken"/>
<field name = "b" value="You have done"/>
<field name = "c" value="Tim has introduced"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1053, 'mod3uni1les5p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p16">
<field name = "d" value="Have you given"/>
<field name = "e" value="The board of directors hasn''t decided"/>
<field name = "f" value="Davis has been"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1054, 'mod3uni1les5p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p17">
<field name = "g" value="All our technicians have had"/>
<field name = "h" value="He has chosen"/>
<field name = "i" value="There has been"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1058, 'mod3uni1les5p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p21">
<field name = "a" value="SINCE[or]since"/>
<field name = "b" value="IN[or]in"/>
<field name = "c" value="FOR[or]for"/>
<field name = "d" value="EVER[or]ever"/>
<field name = "e" value="NEVER[or]never"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1062, 'mod3uni1les5p29', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p29">
<field name = "a" value="N[or]n"/>
<field name = "b" value="N[or]n"/>
<field name = "c" value="V[or]v"/>
<field name = "d" value="N[or]n"/>
<field name = "e" value="N[or]n"/>
<field name = "f" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1066, 'mod3uni1les5p35', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p35">
<field name = "a" value="overdue"/>
<field name = "b" value="purchased"/>
<field name = "c" value="service complaints"/>
<field name = "d" value="interest rates"/>
<field name = "e" value="exchange [and] refund"/>
<field name = "f" value="delivery date"/>
<field name = "g" value="purchasing power"/>
<field name = "h" value="negotiation"/>
<field name = "i" value="manufacturing flaw"/>
<field name = "j" value="rain check"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1055, 'mod3uni1les5p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p18">
<field name = "a" value="R[or]r"/>
<field name = "b" value="W[or]r"/>
<field name = "c" value="W[or]r"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1059, 'mod3uni1les5p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p22">
<field name = "f" value="ON[or]on"/>
<field name = "g" value="AGO[or]ago"/>
<field name = "h" value="FOR[or]for"/>
<field name = "i" value="LAST[or]last"/>
<field name = "j" value="SINCE[or]since"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1063, 'mod3uni1les5p30', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p30">
<field name = "g" value="N[or]n"/>
<field name = "h" value="N[or]n"/>
<field name = "i" value="N[or]n"/>
<field name = "j" value="V[or]v"/>
<field name = "l" value="N[or]V[or]n[or]v"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1067, 'mod3uni1les5p36', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p36">
<field name = "a" value="F[or]f"/>
<field name = "b" value="G[or]g"/>
<field name = "c" value="H[or]h"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="I[or]i"/>
<field name = "f" value="B[or]b"/>
<field name = "g" value="K[or]k"/>
<field name = "h" value="M[or]m"/>
<field name = "i" value="L[or]l"/>
<field name = "j" value="J[or]j"/>
<field name = "k" value="D[or]d"/>
<field name = "l" value="E[or]e"/>
<field name = "m" value="C[or]c"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1056, 'mod3uni1les5p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p19">
<field name = "d" value="R[or]r"/>
<field name = "e" value="W[or]r"/>
<field name = "f" value="R[or]r"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1060, 'mod3uni1les5p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p23">
<field name = "a" value="started"/>
<field name = "b" value="founded"/>
<field name = "c" value="has made"/>
<field name = "d" value="have increased"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1064, 'mod3uni1les5p31', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p31">
<field name = "a" value="V[or]v"/>
<field name = "b" value="N[or]n"/>
<field name = "c" value="N[or]n"/>
<field name = "d" value="N[or]n"/>
<field name = "e" value="N[or]n"/>
<field name = "f" value="V[or]v"/>
<field name = "g" value="N[or]n"/>
<field name = "h" value="N[or]n"/>
<field name = "i" value="N[or]n"/>
<field name = "j" value="N[or]n"/>
<field name = "l" value="V[or]v"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1057, 'mod3uni1les5p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p20">
<field name = "g" value="R[or]r"/>
<field name = "h" value="R[or]r"/>
<field name = "i" value="R[or]r"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1061, 'mod3uni1les5p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p24">
<field name = "e" value="faced"/>
<field name = "f" value="have"/>
<field name = "g" value="introduced"/>
<field name = "h" value="have decreased"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1065, 'mod3uni1les5p32', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les5p32">
<field name = "a" value="V[or]v[or]N[or]n"/>
<field name = "b" value="V[or]v"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="N[or]n"/>
<field name = "e" value="V[or]v[or]N[or]n"/>
<field name = "f" value="N[or]n"/>
<field name = "g" value="V[or]v[or]N[or]n"/>
<field name = "h" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1068, 'mod3uni1les6p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p2">
<field name = "a" value="B"/>
<field name = "b" value="A"/>
<field name = "c" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1016, 'mod3uni1les6p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p4">
<field name = "a" value="Attraction"/>
<field name = "b" value="Wasteful practices"/>
<field name = "c" value="Productivity"/>
<field name = "d" value="Turnover"/>
<field name = "e" value="Workforce"/>
<field name = "f" value="Workspace"/>
<field name = "g" value="Work - life balance"/>
<field name = "h" value="Morale/loyalty/engagement"/>
<field name = "i" value="Go green"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1071, 'mod3uni1les6p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p5">
<field name = "a" value="B"/>
<field name = "b" value="A"/>
<field name = "c" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1072, 'mod3uni1les6p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p6">
<field name = "d" value="C"/>
<field name = "e" value="B"/>
<field name = "f" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1073, 'mod3uni1les6p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p7">
<field name = "g" value="B"/>
<field name = "h" value="B"/>
<field name = "i" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1074, 'mod3uni1les6p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p10">
<field name = "a" value="D[or]d"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1075, 'mod3uni1les6p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p11">
<field name = "a" value="1"/>
<field name = "b" value="2"/>
<field name = "c" value="4"/>
<field name = "d" value="5"/>
<field name = "e" value="6"/>
<field name = "f" value="7"/>
<field name = "g" value="3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1076, 'mod3uni1les6p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p14">
<field name = "a" value="True"/>
<field name = "b" value="False"/>
<field name = "c" value="True"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1077, 'mod3uni1les6p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p15">
<field name = "d" value="True"/>
<field name = "e" value="False"/>
<field name = "f" value="I don''t know"/>
<field name = "g" value="True"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1078, 'mod3uni1les6p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p16">
<field name = "h" value="True"/>
<field name = "i" value="False"/>
<field name = "j" value="False"/>
<field name = "k" value="I don''t know"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1080, 'mod3uni1les6p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p17">
<field name = "a" value="She has written"/>
<field name = "b" value="She is dissatisfied"/>
<field name = "c" value="She has had"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1082, 'mod3uni1les6p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p18">
<field name = "d" value="she has made"/>
<field name = "e" value="She has not been"/>
<field name = "f" value="She has asked"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1086, 'mod3uni1les6p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p23">
<field name = "e" value="I saw that IBM treats its customers with equal respect"/>
<field name = "f" value="Have you ever been to New York"/>
<field name = "g" value="I''ve never had any problems with your products"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1083, 'mod3uni1les6p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p19">
<field name = "g" value="She has paid"/>
<field name = "h" value="She is thinking"/>
<field name = "i" value="She is posting"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1084, 'mod3uni1les6p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p21">
<field name = "a" value="started"/>
<field name = "b" value="founded"/>
<field name = "c" value="has made"/>
<field name = "c|1" value="have increased"/>
<field name = "d" value="faced"/>
<field name = "e" value="have"/>
<field name = "e|1" value="introduced"/>
<field name = "e|2" value="have decreased"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1085, 'mod3uni1les6p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les6p22">
<field name = "a" value="Did you post your complaints for public view"/>
<field name = "b" value="Have you read other consumer experiences"/>
<field name = "c" value="I''ve never shared my own consumer experiences"/>
<field name = "d" value="Have you ever checked how a business is performing"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1087, 'mod3uni1les7p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p2">
<field name = "a" value="NO"/>
<field name = "b" value="YES"/>
<field name = "c" value="YES"/>
<field name = "d" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1089, 'mod3uni1les7p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p3">
=<field name = "e" value="YES"/>
<field name = "f" value="YES"/>
<field name = "g" value="YES"/>
<field name = "h" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1090, 'mod3uni1les7p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p4">
<field name = "a" value="1[or]01"/>
<field name = "b" value="9[or]09"/>
<field name = "c" value="6[or]06"/>
<field name = "d" value="3[or]03"/>
<field name = "e" value="2[or]02"/>
<field name = "f" value="5[or]05"/>
<field name = "g" value="7[or]07"/>
<field name = "h" value="14"/>
<field name = "i" value="4[or]04"/>
<field name = "j" value="8[or]08"/>
<field name = "k" value="10"/>
<field name = "l" value="11"/>
<field name = "m" value="12"/>
<field name = "n" value="13"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1091, 'mod3uni1les7p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p9">
<field name = "a" value="D[or]d"/>
<field name = "b" value="L[or]l"/>
<field name = "c" value="A[or]a"/>
<field name = "d" value="L[or]l"/>
<field name = "e" value="G[or]g"/>
<field name = "f" value="J[or]j"/>
<field name = "g" value="B[or]b"/>
<field name = "h" value="E[or]e"/>
<field name = "i" value="K[or]k"/>
<field name = "j" value="H[or]h"/>
<field name = "k" value="C[or]c"/>
<field name = "l" value="F[or]f"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1092, 'mod3uni1les7p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p11">
<field name = "a" value="controllable"/>
<field name = "a|1" value="changeable"/>
<field name = "a|2" value="profitable"/>
<field name = "a|3" value="payment"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1093, 'mod3uni1les7p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p12">
<field name = "a" value="achievement"/>
<field name = "a|1" value="improvement"/>
<field name = "a|2" value="politeness"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1094, 'mod3uni1les7p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p13">
<field name = "a" value="thankfulness"/>
<field name = "a|1" value="ownership"/>
<field name = "a|2" value="membership"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1095, 'mod3uni1les7p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p14">
<field name = "a" value="partnership"/>
<field name = "b" value="improvement"/>
<field name = "c" value="vulnerable"/>
<field name = "d" value="leadership"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1096, 'mod3uni1les7p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p15">
<field name = "e" value="politeness"/>
<field name = "f" value="doable"/>
<field name = "g" value="enrollment"/>
<field name = "h" value="fairness"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1097, 'mod3uni1les7p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p19">
<field name = "a" value="E[or]e"/>
<field name = "b" value="C[or]c"/>
<field name = "c" value="I[or]i"/>
<field name = "d" value="J[or]j"/>
<field name = "e" value="L[or]l"/>
<field name = "f" value="A[or]a"/>
<field name = "g" value="G[or]g"/>
<field name = "h" value="K[or]k"/>
<field name = "i" value="H[or]h"/>
<field name = "j" value="B[or]b"/>
<field name = "k" value="F[or]f"/>
<field name = "l" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1098, 'mod3uni1les7p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p20">
<field name = "a" value="overeat"/>
<field name = "b" value="overwork"/>
<field name = "c" value="misunderstand"/>
<field name = "d" value="misfortune"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1099, 'mod3uni1les7p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p21">
<field name = "e" value="misbehave"/>
<field name = "f" value="overdo"/>
<field name = "g" value="underestimate"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1100, 'mod3uni1les7p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p22">
<field name = "a" value="overwork"/>
<field name = "b" value="overlap"/>
<field name = "c" value="undergo"/>
<field name = "d" value="underprivileged"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1101, 'mod3uni1les7p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les7p23">
<field name = "e" value="miscalculate"/>
<field name = "f" value="overcome"/>
<field name = "g" value="mislead"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1102, 'mod3uni1les8p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p2">
<field name = "a" value="I don''t know"/>
<field name = "b" value="Technological"/>
<field name = "c" value="A fingerprint system to replace keys"/>
<field name = "d" value="The payback period"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1103, 'mod3uni1les8p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p3">
<field name = "e" value="Security and efficiency"/>
<field name = "f" value="It''s a long-term investment"/>
<field name = "g" value="Only luxury hotels and big corporations may purchase it"/>
<field name = "h" value="No, she hasn''t made up her mind yet"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1104, 'mod3uni1les8p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p4">
<field name = "a" value="invest"/>
<field name = "a|1" value="return"/>
<field name = "b" value="mind"/>
<field name = "c" value="ventures"/>
<field name = "c|1" value="open"/>
<field name = "d" value="used"/>
<field name = "e" value="improve"/>
<field name = "e|1" value="certainly"/>
<field name = "f" value="market"/>
<field name = "g" value="long"/>
<field name = "h" value="profits"/>
<field name = "i" value="time"/>
<field name = "j" value="think"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1105, 'mod3uni1les8p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p10">
<field name = "a" value="People will spend less time at work"/>
<field name = "b" value="More people will work from home"/>
<field name = "c" value="The way people do business may change a lot"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1106, 'mod3uni1les8p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p11">
<field name = "d" value="Brazil might be the world''s biggest economy"/>
<field name = "e" value="China may invest heavily in going green"/>
<field name = "f" value="We may learn how to preserve the environment"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1107, 'mod3uni1les8p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p12">
<field name = "g" value="We might achieve desirable results"/>
<field name = "h" value="Companies will invest more in protecting the environment"/>
<field name = "i" value="Roads may be less crowded"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1108, 'mod3uni1les8p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p14">
<field name = "a" value="W[or]w"/>
<field name = "b" value="R[or]r"/>
<field name = "c" value="R[or]r"/>
<field name = "d" value="W[or]w"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1109, 'mod3uni1les8p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p15">
<field name = "e" value="W[or]w"/>
<field name = "f" value="W[or]w"/>
<field name = "g" value="R[or]r"/>
<field name = "h" value="R[or]r"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1110, 'mod3uni1les8p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p17">
<field name = "a" value="YES"/>
<field name = "b" value="NO"/>
<field name = "c" value="YES"/>
<field name = "d" value="I DON''T KNOW"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1111, 'mod3uni1les8p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p18">
<field name = "e" value="NO"/>
<field name = "f" value="YES"/>
<field name = "g" value="YES"/>
<field name = "h" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1112, 'mod3uni1les8p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p19">
<field name = "a" value="developments"/>
<field name = "b" value="Certainly"/>
<field name = "c" value="update"/>
<field name = "d" value="requires"/>
<field name = "d|1" value="interrupted"/>
<field name = "e" value="overachievers"/>
<field name = "f" value="management"/>
<field name = "g" value="underestimate"/>
<field name = "g|1" value="underqualified"/>
<field name = "h" value="executives"/>
<field name = "i" value="could"/>
<field name = "j" value="research"/>
<field name = "k" value="doable"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1113, 'mod3uni1les8p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p20">
<field name = "a" value="undergo"/>
<field name = "b" value="undersign"/>
<field name = "c" value="undervalue"/>
<field name = "d" value="overlook"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1114, 'mod3uni1les8p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p21">
<field name = "e" value="overlap"/>
<field name = "f" value="overwork"/>
<field name = "g" value="mismanage"/>
<field name = "h" value="misinform"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1115, 'mod3uni1les8p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p22">
<field name = "i" value="misunderstand"/>
<field name = "j" value="achievement"/>
<field name = "k" value="leadership"/>
<field name = "l" value="vulnerable"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1116, 'mod3uni1les8p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p24">
<field name = "a" value="TRUE"/>
<field name = "b" value="FALSE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1117, 'mod3uni1les8p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les8p25">
<field name = "e" value="FALSE"/>
<field name = "f" value="FALSE"/>
<field name = "g" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1118, 'mod3uni1les9p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p3">
<field name = "a" value="C[or]c"/>
<field name = "b" value="E[or]e"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="D[or]d"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>
', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1119, 'mod3uni1les9p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p4">
<field name = "a" value="1"/>
<field name = "b" value="4"/>
<field name = "c" value="5"/>
<field name = "d" value="3"/>
<field name = "e" value="2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1120, 'mod3uni1les9p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p5">
<field name = "a" value="4"/>
<field name = "b" value="1"/>
<field name = "c" value="2"/>
<field name = "d" value="5"/>
<field name = "e" value="3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1121, 'mod3uni1les9p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p6">
<field name = "a" value="made up"/>
<field name = "b" value="codes"/>
<field name = "c" value="seeks"/>
<field name = "d" value="authority"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1124, 'mod3uni1les9p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p12">
<field name = "e" value="N[or]n"/>
<field name = "f" value="N[or]n"/>
<field name = "g" value="N[or]n"/>
<field name = "h" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1125, 'mod3uni1les9p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p13">
<field name = "i" value="A[or]a"/>
<field name = "j" value="V[or]v"/>
<field name = "k" value="N[or]V[or]n[or]v"/>
<field name = "l" value="V[or]v"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1128, 'mod3uni1les9p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p16">
<field name = "u" value="A[or]a"/>
<field name = "v" value="V[or]v"/>
<field name = "w" value="N[or]n"/>
<field name = "x" value="V[or]v"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1129, 'mod3uni1les9p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p17">
<field name = "y" value="A[or]a"/>
<field name = "z" value="A[or]a"/>
<field name = "a" value="N[or]V[or]n[or]v"/>
<field name = "b" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1132, 'mod3uni1les9p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p21">
<field name = "f" value="hired"/>
<field name = "g" value="outgoing"/>
<field name = "h" value="commitment"/>
<field name = "i" value="ethical"/>
<field name = "i|1" value="disclosed"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1133, 'mod3uni1les9p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p22">
<field name = "a" value="B[or]b"/>
<field name = "b" value="D[or]d"/>
<field name = "c" value="I[or]i"/>
<field name = "d" value="H[or]h"/>
<field name = "e" value="M[or]m"/>
<field name = "f" value="K[or]k"/>
<field name = "g" value="J[or]j"/>
<field name = "h" value="A[or]a"/>
<field name = "i" value="C[or]c"/>
<field name = "j" value="F[or]f"/>
<field name = "k" value="L[or]l"/>
<field name = "l" value="E[or]e"/>
<field name = "m" value="G[or]g"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1122, 'mod3uni1les9p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p7">
<field name = "e" value="projects"/>
<field name = "f" value="at hand"/>
<field name = "g" value="ease"/>
<field name = "h" value="protocols"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1126, 'mod3uni1les9p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p14">
<field name = "m" value="N[or]n"/>
<field name = "n" value="N[or]n"/>
<field name = "o" value="N[or]n"/>
<field name = "p" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1130, 'mod3uni1les9p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p18">
<field name = "c" value="A[or]a"/>
<field name = "d" value="n[or]V[or]N[or]v"/>
<field name = "e" value="V[or]v"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1134, 'mod3uni1les9p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p24">
<field name = "a" value="E[or]e"/>
<field name = "b" value="B[or]b"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="E[or]e"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1123, 'mod3uni1les9p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p11">
<field name = "a" value="N[or]n"/>
<field name = "b" value="V[or]v"/>
<field name = "c" value="N[or]n"/>
<field name = "d" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1127, 'mod3uni1les9p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p15">
<field name = "q" value="N[or]n"/>
<field name = "r" value="V[or]v"/>
<field name = "s" value="V[or]v"/>
<field name = "t" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1131, 'mod3uni1les9p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p20">
<field name = "a" value="promotion"/>
<field name = "b" value="goals"/>
<field name = "c" value="common ground"/>
<field name = "d" value="compromise"/>
<field name = "e" value="rapport"/>
<field name = "e|1" value="support"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1135, 'mod3uni1les9p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les9p25">
<field name = "a" value="2"/>
<field name = "b" value="6"/>
<field name = "c" value="3"/>
<field name = "d" value="5"/>
<field name = "e" value="1"/>
<field name = "f" value="4"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1136, 'mod3uni1les10p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p3">
<field name = "a" value="1"/>
<field name = "a|1" value="1"/>
<field name = "a|2" value="1"/>
<field name = "a|3" value="1"/>
<field name = "a|4" value="1"/>
<field name = "a|5" value="7"/>
<field name = "a|6" value="3"/>
<field name = "a|7" value="7"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 919, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1137, 'mod3uni1les10p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p4">
<field name = "a|8" value="7"/>
<field name = "a|9" value="7"/>
<field name = "a|10" value="1"/>
<field name = "a|11" value="2"/>
<field name = "a|12" value="6"/>
<field name = "a|13" value="7"/>
<field name = "a|14" value="5[or]6"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1138, 'mod3uni1les10p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p5">
<field name = "a|15" value="7"/>
<field name = "a|16" value="3"/>
<field name = "a|17" value="3"/>
<field name = "a|18" value="3"/>
<field name = "a|19" value="4"/>
<field name = "a|20" value="3"/>
<field name = "a|21" value="1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1139, 'mod3uni1les10p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p6">
<field name = "a" value="A[or]a"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="N[or]n"/>
<field name = "d" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1140, 'mod3uni1les10p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p7">
<field name = "e" value="A[or]a"/>
<field name = "f" value="N[or]n"/>
<field name = "g" value="N[or]n"/>
<field name = "h" value="N[or]n"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1141, 'mod3uni1les10p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p8">
<field name = "a" value="dress"/>
<field name = "b" value="wear"/>
<field name = "c" value="use"/>
<field name = "d" value="recycle"/>
<field name = "d|1" value="place"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1142, 'mod3uni1les10p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p9">
<field name = "e" value="make"/>
<field name = "f" value="changes"/>
<field name = "f|1" value="received"/>
<field name = "g" value="disclose"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1143, 'mod3uni1les10p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p14">
<field name = "a" value="You must ask your manager about the dress code"/>
<field name = "b" value="You shouldn''t talk with your mouth full"/>
<field name = "c" value="You can wear tennis shoes on Fridays"/>
<field name = "d" value="She must have her badge with her"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1136, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1144, 'mod3uni1les10p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p15">
<field name = "e" value="You mustn''t talk behind people''s back"/>
<field name = "f" value="You can''t start the meeting without the project manager"/>
<field name = "g" value="You must decline an invitation if you can''t go"/>
<field name = "h" value="You should be respectful of the environment"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1145, 'mod3uni1les10p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p17">
<field name = "a" value="W[or]w"/>
<field name = "b" value="R[or]r"/>
<field name = "c" value="R[or]r"/>
<field name = "d" value="W[or]w"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1146, 'mod3uni1les10p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p18">
<field name = "e" value="W[or]w"/>
<field name = "f" value="W[or]w"/>
<field name = "g" value="R[or]r"/>
<field name = "h" value="R[or]r"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1147, 'mod3uni1les10p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p22">
<field name = "a" value="YES"/>
<field name = "b" value="NO"/>
<field name = "c" value="NO"/>
<field name = "d" value="I DON''T KNOW"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1148, 'mod3uni1les10p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p23">
<field name = "e" value="NO"/>
<field name = "f" value="YES"/>
<field name = "g" value="YES"/>
<field name = "h" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1149, 'mod3uni1les10p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p24">
<field name = "a" value="nurture"/>
<field name = "b" value="notice"/>
<field name = "b|1" value="Observe"/>
<field name = "b|2" value="clean"/>
<field name = "c" value="marked"/>
<field name = "c|1" value="during"/>
<field name = "d" value="disturb"/>
<field name = "d|1" value="organized"/>
<field name = "d|2" value="issues"/>
<field name = "e" value="outside"/>
<field name = "f" value="clients"/>
<field name = "f|1" value="shoes"/>
<field name = "g" value="avoid"/>
<field name = "g|1" value="answer">
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1161, 'mod3uni1les11p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p15">
<field name = "d" value="stand for"/>
<field name = "e" value="stand up for"/>
<field name = "f" value="stands out"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1165, 'mod3uni1les11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11">
<field name = "d" value="stand for"/>
<field name = "e" value="stands out"/> 
<field name = "f" value="stand by"/>
<field name = "g" value="stand for"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1150, 'mod3uni1les10p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p25">
<field name = "a" value="avoid"/>
<field name = "b" value="refrain "/>
<field name = "c" value="observe"/>
<field name = "d" value="mind"/>
<field name = "e" value="keep"/>
<field name = "f" value="tidy"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1162, 'mod3uni1les11p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p17">
<field name = "a" value="kept off"/>
<field name = "b" value="kept down"/>
<field name = "c" value="keep up with"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1151, 'mod3uni1les10p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p26">
<field name = "g" value="flush"/>
<field name = "h" value="neatly"/>
<field name = "f" value="organized"/>
<field name = "g" value="slang"/>
<field name = "h" value="essential"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1159, 'mod3uni1les11p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p8">
<field name = "a"   value="been"/>
<field name = "a|1" value="office"/>
<field name = "a|2" value="definitely"/>
<field name = "b"   value="agree"/>
<field name = "b|1" value="plan"/>
<field name = "b|2" value="mind"/>
<field name = "c"   value="open"/>
<field name = "c|1"   value="locate"/>
<field name = "c|2"   value="key"/>
<field name = "d"   value="looked"/>
<field name = "d|1"   value="strong"/>
<field name = "d|2"   value="right"/>
<field name = "e"   value="attractive"/>
<field name = "e|1"   value="down"/>
<field name = "e|2"   value="functional"/>
<field name = "f"   value="needs"/>
<field name = "g"   value="offered"/>
<field name = "g|1"   value="costs"/>
<field name = "g|2"   value="facilities"/>
<field name = "h"   value="looks"/>
<field name = "h|1"   value="almost"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1163, 'mod3uni1les11p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p18">
<field name = "d" value="keep up"/>
<field name = "e" value="kept from"/>
<field name = "f" value="keep up"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1152, 'mod3uni1les10p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les10p27">
<field name = "a" value="YES"/>
<field name = "b" value="NO"/>
<field name = "c" value="YES"/>
<field name = "d" value="YES"/>
<field name = "e" value="NO"/>
<field name = "f" value="NO"/>
<field name = "g" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1153, 'mod3uni1les11p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p2">
<field name = "a" value="A meeting"/>
<field name = "b" value="Expansion plans"/>
<field name = "c" value="3 hours"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1154, 'mod3uni1les11p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p3">
<field name = "d" value="One required participant, and one optional"/>
<field name = "e" value="Santana Toledo"/>
<field name = "f" value="In São Paulo"/>
<field name = "g" value="Yes, there is one"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1155, 'mod3uni1les11p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p5">
<field name = "a" value="NO"/>
<field name = "b" value="YES"/>
<field name = "c" value="YES"/>
<field name = "d" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1156, 'mod3uni1les11p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p6">
<field name = "a" value="C[or]c"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1160, 'mod3uni1les11p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p14">
<field name = "a" value="stands for"/>
<field name = "b" value="stands over"/>
<field name = "c" value="standing by"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1164, 'mod3uni1les11p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les11p20">
<field name = "a" value="stand for"/>
<field name = "b" value="kept on"/>
<field name = "c" value="keep up with"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1166, 'mod3uni1les12p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p2">
<field name = "a" value="C[or]c"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="E[or]e"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1167, 'mod3uni1les12p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p3">
<field name = "a" value="take"/>
<field name = "b" value="coming"/>
<field name = "c" value="ends"/>
<field name = "d" value="unveil"/>
<field name = "e" value="through"/>
<field name = "f" value="busy"/>
<field name = "g" value="bet"/>
<field name = "h" value="choice"/>
<field name = "i" value="format"/>
<field name = "j" value="invited"/>
<field name = "k" value="fit"/>
<field name = "l" value="validate"/>
<field name = "m" value="speakers"/>
<field name = "n" value="head"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1168, 'mod3uni1les12p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p8">
<field name = "a" value="Our team is going to perform a marketing research"/>
<field name = "b" value="The company is going to increase its market share"/>
<field name = "c" value="Many consumers are going to sample the new product"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1169, 'mod3uni1les12p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p9">
<field name = "d" value="Advertising is going to play a very important part"/>
<field name = "e" value="Our budget is going to contemplate the new expenditures"/>
<field name = "f" value="The quarterly results are going to be impressive"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1170, 'mod3uni1les12p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p10">
<field name = "g" value="Our branch is going to start a new promotion"/>
<field name = "h" value="That department is going to be audited next month"/>
<field name = "i" value="We are going to consider your feedback on the matter"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1171, 'mod3uni1les12p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p12">
<field name = "a" value="WRONG"/>
<field name = "b" value="RIGHT"/>
<field name = "c" value="RIGHT"/>
<field name = "d" value="WRONG"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1172, 'mod3uni1les12p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p13">
<field name = "e" value="WRONG"/>
<field name = "f" value="WRONG"/>
<field name = "g" value="RIGHT"/>
<field name = "h" value="RIGHT"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1173, 'mod3uni1les12p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p15">
<field name = "a" value="I DON''T KNOW"/>
<field name = "b" value="TRUE"/>
<field name = "c" value="TRUE"/>
<field name = "d" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1174, 'mod3uni1les12p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p16">
<field name = "e" value="TRUE"/>
<field name = "f" value="I DON''T KNOW"/>
<field name = "g" value="TRUE"/>
<field name = "h" value="TRUE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1175, 'mod3uni1les12p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p17">
<field name = "a" value="will start"/>
<field name = "b" value="will"/>
<field name = "c" value="are going to"/>
<field name = "d" value="will"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1176, 'mod3uni1les12p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p18">
<field name = "e" value="Are"/>
<field name = "e|1" value="going to invite"/>
<field name = "f" value="are going to"/>
<field name = "g" value="am not going to"/>
<field name = "h" value="isn''t going to"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1177, 'mod3uni1les12p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p19">
<field name = "a" value="set up"/>
<field name = "b" value="hurry"/>
<field name = "c" value="let down"/>
<field name = "d" value="least"/>
<field name = "e" value="encouragement"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1179, 'mod3uni1les12p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p20">
<field name = "f" value="functional"/>
<field name = "g" value="lead"/>
<field name = "h" value="led"/>
<field name = "i" value="pick out"/>
<field name = "j" value="amazing"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1180, 'mod3uni1les12p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p21">
<field name = "k" value="run out of"/>
<field name = "l" value="go through"/>
<field name = "m" value="move on"/>
<field name = "n" value="disregarded"/>
<field name = "o" value="taxation"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1181, 'mod3uni1les12p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les12p22">
<field name = "a" value="2[or]02"/>
<field name = "b" value="7[or]07"/>
<field name = "c" value="5[or]05"/>
<field name = "d" value="9[or]09"/>
<field name = "e" value="12"/>
<field name = "f" value="4[or]04"/>
<field name = "g" value="8[or]08"/>
<field name = "h" value="11"/>
<field name = "i" value="6[or]06"/>
<field name = "j" value="10"/>
<field name = "k" value="3[or]03"/>
<field name = "l" value="1[or]01"/>
<field name = "m" value="13"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1182, 'mod3uni1les13p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p2">
<field name = "a" value="TRUE"/>
<field name = "b" value="TRUE"/>
<field name = "c" value="FALSE"/>
<field name = "d" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1183, 'mod3uni1les13p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p3">
<field name = "e" value="TRUE"/>
<field name = "f" value="FALSE"/>
<field name = "g" value="TRUE"/>
<field name = "h" value="TRUE"/>
<field name = "i" value="FALSE"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1184, 'mod3uni1les13p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p4">
<field name = "a" value="3"/>
<field name = "b" value="7"/>
<field name = "c" value="1"/>
<field name = "d" value="4"/>
<field name = "e" value="11"/>
<field name = "f" value="5"/>
<field name = "g" value="2"/>
<field name = "h" value="6"/>
<field name = "i" value="9"/>
<field name = "j" value="8"/>
<field name = "k" value="10"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1185, 'mod3uni1les13p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p5">
<field name = "a" value="wanted"/>
<field name = "a|1" value="done"/>
<field name = "b" value="put"/>
<field name = "b|1" value="prepare"/>
<field name = "c" value="through"/>
<field name = "c|1" value="save"/>
<field name = "c|2" value="noticed"/>
<field name = "c|3" value="pension"/>
<field name = "d" value="give"/>
<field name = "e" value="migrating"/>
<field name = "f" value="stock"/>
<field name = "f|1" value="worked"/>
<field name = "g" value="discuss"/>
<field name = "g|1" value="benefits"/>
<field name = "h" value="opener"/>
<field name = "h|1" value="chance"/>
<field name = "i" value="prepared"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1186, 'mod3uni1les13p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p11">
<field name = "a" value="bankruptcy"/>
<field name = "b" value="entitled"/>
<field name = "c" value="fringe"/>
<field name = "d" value="benefits"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1187, 'mod3uni1les13p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p12">
<field name = "e" value="bankrup"/>
<field name = "f" value="insurance"/>
<field name = "g" value="income"/>
<field name = "h" value="pension"/>
<field name = "i" value="retired"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1188, 'mod3uni1les13p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p13">
<field name = "j" value="retirement"/>
<field name = "k" value="savings"/>
<field name = "l" value="balance"/>
<field name = "m" value="claim"/>
<field name = "n" value="volatility"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1189, 'mod3uni1les13p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p16">
<field name = "a" value="E[or]e"/>
<field name = "b" value="B[or]b"/>
<field name = "c" value="C[or]c"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1190, 'mod3uni1les13p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p17">
<field name = "a" value="healthy"/>
<field name = "b" value="spending"/>
<field name = "c" value="budget"/>
<field name = "d" value="savings"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1191, 'mod3uni1les13p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p18">
<field name = "e" value="rate"/>
<field name = "f" value="volatility"/>
<field name = "g" value="goals"/>
<field name = "h" value="age"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1192, 'mod3uni1les13p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les13p19">
<field name = "i" value="egg"/>
<field name = "j" value="bankruptcy"/>
<field name = "k" value="investments"/>
<field name = "l" value="rainy"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1193, 'mod3uni1les14p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p2">
<field name = "a" value="YES"/>
<field name = "b" value="YES"/>
<field name = "c" value="YES"/>
<field name = "d" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1194, 'mod3uni1les14p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p3">
<field name = "e" value="NO"/>
<field name = "f" value="YES"/>
<field name = "g" value="YES"/>
<field name = "h" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1195, 'mod3uni1les14p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p4">
<field name = "a" value="B[or]b"/>
<field name = "b" value="D[or]d"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="A[or]a"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="C[or]c"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1196, 'mod3uni1les14p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p5">
<field name = "a" value="1"/>
<field name = "a|1" value="10"/>
<field name = "a|2" value="3"/>
<field name = "a|3" value="4"/>
<field name = "a|4" value="5"/>
<field name = "a|5" value="9"/>
<field name = "a|6" value="6"/>
<field name = "a|7" value="2"/>
<field name = "a|8" value="7"/>
<field name = "a|9" value="12"/>
<field name = "a|10" value="8"/>
<field name = "a|11" value="11"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1197, 'mod3uni1les14p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p6">
<field name = "a" value="reminds"/>
<field name = "b" value="wondering"/>
<field name = "c" value="advice"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1198, 'mod3uni1les14p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p7">
<field name = "d" value="work"/>
<field name = "e" value="quite"/>
<field name = "f" value="catch up with"/>
<field name = "g" value="encourage"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1199, 'mod3uni1les14p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p8">
<field name = "h" value="fired"/>
<field name = "i" value="withdraw"/>
<field name = "j" value="fringe"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1200, 'mod3uni1les14p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p14">
<field name = "a" value="are you always leaving"/>
<field name = "b" value="heard"/>
<field name = "b|1" value="living"/>
<field name = "c" value="looks after"/>
<field name = "d" value="take care of"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1201, 'mod3uni1les14p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p15">
<field name = "e" value="know"/>
<field name = "e|1" value="are doing"/>
<field name = "e|2" value="flow"/>
<field name = "f" value="agree"/>
<field name = "f|1" value="started"/>
<field name = "g" value="will"/>
<field name = "g|1" value="help"/>
<field name = "h" value="understand"/>
<field name = "h|1" value="want"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1202, 'mod3uni1les14p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p16">
<field name = "i" value="do you mean"/>
<field name = "j" value="have you been"/>
<field name = "k" value="been"/>
<field name = "l" value="Have"/>
<field name = "l|1" value="finished"/>
<field name = "m" value="have"/>
<field name = "m|1" value="looked"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1203, 'mod3uni1les14p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p17">
<field name = "a" value="haven''t seen"/>
<field name = "a|1" value="met"/>
<field name = "a|2" value="were"/>
<field name = "b" value="went"/>
<field name = "b|1" value="was"/>
<field name = "c" value="have recovered"/>
<field name = "c|1" value="happened"/>
<field name = "d" value="rejected"/>
<field name = "d|1" value="seemed"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1204, 'mod3uni1les14p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p18">
<field name = "e" value="left"/>
<field name = "e|1" value="wasn''t"/>
<field name = "f" value="have"/>
<field name = "f|1" value="been"/>
<field name = "f|2" value="got"/>
<field name = "g" value="have been"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1205, 'mod3uni1les14p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p19">
<field name = "a" value="RIGHT"/>
<field name = "b" value="WRONG"/>
<field name = "c" value="RIGHT"/>
<field name = "d" value="WRONG"/>
<field name = "e" value="RIGHT"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1206, 'mod3uni1les14p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p20">
<field name = "f" value="WRONG"/>
<field name = "g" value="WRONG"/>
<field name = "h" value="RIGHT"/>
<field name = "i" value="RIGHT"/>
<field name = "j" value="WRONG"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1207, 'mod3uni1les14p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p21">
<field name = "a" value="will find"/>
<field name = "b" value="are going to"/>
<field name = "c" value="starts"/>
<field name = "d" value="am going to open"/>
<field name = "e" value="will"/>
<field name = "e|1" value="do"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1208, 'mod3uni1les14p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p22">
<field name = "f" value="am seeing"/>
<field name = "g" value="Are"/>
<field name = "g|1" value="joining"/>
<field name = "h" value="will"/>
<field name = "h|1" value="do"/>
<field name = "j" value="is"/>
<field name = "j|1" value="going to take"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1209, 'mod3uni1les14p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p23">
<field name = "a" value="received"/>
<field name = "a|1" value="have put"/>
<field name = "a|2" value="are"/>
<field name = "b" value="had"/>
<field name = "b|1" value="pays"/>
<field name = "c" value="has joined"/>
<field name = "c|1" value="decided"/>
<field name = "c|2" value="have had"/>
<field name = "d" value="have"/>
<field name = "d|1" value="spent"/>
<field name = "d|2" value="have"/>
<field name = "d|3" value="saved"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1213, 'mod3uni1les14p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p27">
<field name = "e" value="entitled"/>
<field name = "f" value="fringe"/>
<field name = "g" value="withdraw"/>
<field name = "h" value="claim"/>
<field name = "i" value="retirement"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1224, 'mod3uni1les16p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p2">
<field name = "a" value="A3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1228, 'mod3uni1les16p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p6">
<field name = "e" value="E1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1232, 'mod3uni1les16p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p15">
<field name = "a" value="2"/>
<field name = "b" value="3"/>
<field name = "c" value="5"/>
<field name = "d" value="6"/>
<field name = "e" value="4"/>
<field name = "f" value="1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1210, 'mod3uni1les14p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p24">
<field name = "e" value="haven''t used"/>
<field name = "e|1" value="started"/>
<field name = "f" value="will"/>
<field name = "f|1" value="do"/>
<field name = "f|2" value="(have retired"/>
<field name = "f|3" value="will enjoy"/>
<field name = "g" value="does"/>
<field name = "g|1" value="leave"/>
<field name = "g|2" value="is"/>
<field name = "g|3" value="arriving"/>
<field name = "h" value="are buying"/>
<field name = "h|1" value="are doing"/>
<field name = "h|2" value="does"/>
<field name = "h|3" value="arrive"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1225, 'mod3uni1les16p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p3">
<field name = "b" value="B1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1229, 'mod3uni1les16p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p7">
<field name = "a" value="B[or]b"/>
<field name = "b" value="C[or]c"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="F[or]f"/>
<field name = "e" value="A[or]a"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1233, 'mod3uni1les16p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p16">
<field name = "a" value="C[or]c"/>
<field name = "b" value="D[or]d"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="B[or]b"/>
<field name = "e" value="F[or]f"/>
<field name = "f" value="A[or]a"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1211, 'mod3uni1les14p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p25">
<field name = "a" value="D[or]d"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="E[or]e"/>
<field name = "e" value="C[or]c"/>
<field name = "f" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1226, 'mod3uni1les16p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p4">
<field name = "c" value="C2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1230, 'mod3uni1les16p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p8">
<field name = "a" value="I suppose it''s worth checking out"/>
<field name = "b" value="It may turn out very positive"/>
<field name = "c" value="I couldn''t agree more"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1234, 'mod3uni1les16p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p17">
<field name = "a" value="YES"/>
<field name = "b" value="YES"/>
<field name = "c" value="NO"/>
<field name = "d" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1212, 'mod3uni1les14p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les14p26">
<field name = "a" value="bankrupt"/>
<field name = "b" value="stocks"/>
<field name = "c" value="profits"/>
<field name = "d" value="volatility"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1214, 'mod3uni1les15p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p2">
<field name = "a" value="False"/>
<field name = "b" value="True"/>
<field name = "c" value="True"/>
<field name = "d" value="True"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1215, 'mod3uni1les15p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p3">
<field name = "e" value="False"/>
<field name = "f" value="I don''t know"/>
<field name = "g" value="True"/>
<field name = "h" value="True"/>
<field name = "i" value="False"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1216, 'mod3uni1les15p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p4">
<field name = "a" value="1"/>
<field name = "b" value="6"/>
<field name = "c" value="2"/>
<field name = "d" value="3"/>
<field name = "e" value="10"/>
<field name = "f" value="5"/>
<field name = "g" value="7"/>
<field name = "h" value="8"/>
<field name = "i" value="4"/>
<field name = "j" value="9"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1217, 'mod3uni1les15p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p5">
<field name = "a" value="research"/>
<field name = "b" value="ahead"/>
<field name = "c" value="causes"/>
<field name = "d" value="combination"/>
<field name = "d|1" value="pressure"/>
<field name = "d|2" value="depression"/>
<field name = "e" value="stress"/>
<field name = "f" value="problems"/>
<field name = "f|1" value="support"/>
<field name = "g" value="reluctant"/>
<field name = "h" value="vulnerable"/>
<field name = "h|1" value="strategies"/>
<field name = "h|2" value="collapsed"/>
<field name = "h|3" value="turn"/>
<field name = "h|4" value="redundancy"/>
<field name = "i" value="cuts"/>
<field name = "i|1" value="management"/>
<field name = "j" value="pipeline"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1218, 'mod3uni1les15p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p9">
<field name = "a" value="overworked"/>
<field name = "b" value="workload"/>
<field name = "c" value="Flexitime"/>
<field name = "d" value="workaholic"/>
<field name = "e" value="lifestyle"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1219, 'mod3uni1les15p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p10">
<field name = "f" value="pressure"/>
<field name = "f|1" value="cope"/>
<field name = "g" value="helpless"/>
<field name = "h" value="awful"/>
<field name = "i" value="take time off"/>
<field name = "j" value="stress"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1220, 'mod3uni1les15p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p11">
<field name = "a" value="F[or]f"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="E[or]e"/>
<field name = "d" value="C[or]c"/>
<field name = "e" value="B[or]b"/>
<field name = "f" value="D[or]d"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1221, 'mod3uni1les15p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p12">
<field name = "a" value="hotspot"/>
<field name = "b" value="cope"/>
<field name = "c" value="workload"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1222, 'mod3uni1les15p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p13">
<field name = "a" value="awful"/>
<field name = "b" value="threat"/>
<field name = "c" value="be off sick"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1223, 'mod3uni1les15p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les15p14">
<field name = "a" value="lifestyle"/>
<field name = "b" value="workaholic"/>
<field name = "c" value="handle"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1227, 'mod3uni1les16p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p5">
<field name = "d" value="D3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1231, 'mod3uni1les16p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p9">
<field name = "d" value="It might be worth considering"/>
<field name = "e" value="Health and stress go hand in hand"/>
<field name = "f" value="What if we talked to the manager"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1235, 'mod3uni1les16p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p18">
<field name = "e" value="YES"/>
<field name = "f" value="NO"/>
<field name = "g" value="NO"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1239, 'mod3uni1les16p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p23">
<field name = "a" value="2"/>
<field name = "b" value="4"/>
<field name = "c" value="5"/>
<field name = "d" value="3"/>
<field name = "e" value="1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1240, 'mod3uni1les16p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p24">
<field name = "a" value="3"/>
<field name = "b" value="5"/>
<field name = "c" value="4"/>
<field name = "d" value="2"/>
<field name = "e" value="1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1241, 'mod3uni1les16p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p25">
<field name = "a" value="employers"/>
<field name = "a|1" value="fight"/>
<field name = "b" value="productivity"/>
<field name = "b|1" value="absence"/>
<field name = "b|2" value="overall"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1242, 'mod3uni1les16p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p26">
<field name = "c" value="satisfaction"/>
<field name = "c|1" value="productivity"/>
<field name = "c|2" value="general"/>
<field name = "c|3" value="efficient"/>
<field name = "d" value="lead"/>
<field name = "d|1" value="stressed"/>
<field name = "d|2" value="related"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1244, 'mod3uni1les16p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les16p27">
<field name = "e" value="cope"/>
<field name = "e|1" value="health"/>
<field name = "e|2" value="workload"/>
<field name = "e|3" value="fight"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1245, 'mod3uni1les17p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p2">
<field name = "a" value="restructuring"/>
<field name = "b" value="unlikely"/>
<field name = "c" value="look for another job"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1246, 'mod3uni1les17p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p3">
<field name = "d" value="adapting to current demands"/>
<field name = "e" value="enthusiastic about"/>
<field name = "f" value="likely"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1247, 'mod3uni1les17p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p4">
<field name = "a" value="10"/>
<field name = "b" value="6"/>
<field name = "c" value="4"/>
<field name = "d" value="2"/>
<field name = "e" value="8"/>
<field name = "f" value="7"/>
<field name = "g" value="5"/>
<field name = "h" value="3"/>
<field name = "i" value="9"/>
<field name = "j" value="1"/>
<field name = "k" value="11"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1248, 'mod3uni1les17p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p5">
<field name = "a" value="A2"/>
<field name = "b" value="B3"/>
<field name = "c" value="C1"/>
<field name = "d" value="D2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1249, 'mod3uni1les17p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p6">
<field name = "e" value="E1"/>
<field name = "f" value="F3"/>
<field name = "g" value="G3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1250, 'mod3uni1les17p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p7">
<field name = "h" value="H1"/>
<field name = "i" value="I2[and]I3"/>
<field name = "j" value="J1[and]J2[and]J3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1251, 'mod3uni1les17p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p12">
<field name = "a" value="adaptive"/>
<field name = "b" value="aggressive"/>
<field name = "c" value="visible"/>
<field name = "d" value="comprehensive"/>
<field name = "e" value="responsive"/>
<field name = "f" value="sensible"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1253, 'mod3uni1les17p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p15">
<field name = "a" value="A2"/>
<field name = "b" value="B1"/>
<field name = "c" value="C3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1254, 'mod3uni1les17p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p16">
<field name = "d" value="D2"/>
<field name = "e" value="E3"/>
<field name = "f" value="F1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1255, 'mod3uni1les17p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les17p17">
<field name = "g" value="G3"/>
<field name = "h" value="H1"/>
<field name = "i" value="I2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1256, 'mod3uni1les18p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p2">
<field name = "a" value="True"/>
<field name = "b" value="I don''t know"/>
<field name = "c" value="I don''t know"/>
<field name = "d" value="True"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1257, 'mod3uni1les18p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p3">
<field name = "i" value="True"/>
<field name = "j" value="False"/>
<field name = "k" value="True"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1259, 'mod3uni1les18p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p5">
<field name = "a" value="1"/>
<field name = "a|1" value="4"/>
<field name = "a|2" value="8"/>
<field name = "a|3" value="6"/>
<field name = "a|4" value="2"/>
<field name = "a|5" value="7"/>
<field name = "a|6" value="3"/>
<field name = "a|7" value="5"/>
<field name = "a|8" value="9"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1260, 'mod3uni1les18p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p6">
<field name = "a" value="A1[and]A2[and]A3"/>
<field name = "b" value="B1[and]B2"/>
<field name = "c" value="C2"/>
<field name = "d" value="D3"/>
<field name = "e" value="E2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1261, 'mod3uni1les18p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p7">
<field name = "a" value="give"/>
<field name = "b" value="discussed"/>
<field name = "c" value="weren''t "/>
<field name = "d" value="had"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1262, 'mod3uni1les18p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p8">
<field name = "e" value="have"/>
<field name = "f" value="have"/>
<field name = "g" value="practiced"/>
<field name = "h" value="had"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1263, 'mod3uni1les18p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p13">
<field name = "a" value="Would"/>
<field name = "b" value="would"/>
<field name = "c" value="had"/>
<field name = "d" value="had"/>
<field name = "e" value="didn''t have to"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1264, 'mod3uni1les18p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p14">
<field name = "f" value="failed"/>
<field name = "g" value="would"/>
<field name = "h" value="could"/>
<field name = "i" value="was"/>
<field name = "j" value="Would"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1265, 'mod3uni1les18p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p15">
<field name = "a" value="D[or]d"/>
<field name = "b" value="A[or]a"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="E[or]e"/>
<field name = "e" value="C[or]c"/>
<field name = "f" value="B[or]b"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1269, 'mod3uni1les18p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p20">
<field name = "a" value="YES"/>
<field name = "b" value="YES"/>
<field name = "c" value="NO"/>
<field name = "d" value="NO"/>
<field name = "e" value="NO"/>
<field name = "f" value="YES"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1273, 'mod3uni1les18p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p24">
<field name = "h" value="friendly"/>
<field name = "i" value="fast-paced"/>
<field name = "j" value="secure"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1266, 'mod3uni1les18p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p16">
<field name = "a" value="was"/>
<field name = "a|1" value="take"/>
<field name = "b" value="were"/>
<field name = "b|1" value="call"/>
<field name = "c" value="spend"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1270, 'mod3uni1les18p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p21">
<field name = "a" value="9"/>
<field name = "b" value="8"/>
<field name = "c" value="7"/>
<field name = "d" value="6"/>
<field name = "e" value="5"/>
<field name = "f" value="4"/>
<field name = "g" value="3"/>
<field name = "h" value="2"/>
<field name = "i" value="12"/>
<field name = "j" value="11"/>
<field name = "k" value="10"/>
<field name = "l" value="1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1274, 'mod3uni1les18p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p25">
<field name = "a" value="A2"/>
<field name = "b" value="B3"/>
<field name = "c" value="C1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1267, 'mod3uni1les18p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p17">
<field name = "d" value="get"/>
<field name = "d|1" value="could"/>
<field name = "e" value="manage"/>
<field name = "e|1" value="need"/>
<field name = "f" value="do"/>
<field name = "f|1" value="meet"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1271, 'mod3uni1les18p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p22">
<field name = "a" value="like"/>
<field name = "b" value="aggressive"/>
<field name = "c" value="domestic"/>
<field name = "d" value="succeed"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1275, 'mod3uni1les18p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p26">
<field name = "d" value="D3"/>
<field name = "e" value="E2"/>
<field name = "f" value="F3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1268, 'mod3uni1les18p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p18">
<field name = "g" value="spend"/>
<field name = "g|1" value="go"/>
<field name = "h" value="put"/>
<field name = "h|1" value="invest"/>
<field name = "i" value="were"/>
<field name = "i|1" value="become"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1272, 'mod3uni1les18p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p23">
<field name = "e" value="Inconspicuous"/>
<field name = "f" value="ambitious"/>
<field name = "g" value="numerous"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1276, 'mod3uni1les18p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les18p27">
<field name = "g" value="G1"/>
<field name = "h" value="H3"/>
<field name = "i" value="I1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1277, 'mod3uni1les19p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p2">
<field name = "a" value="B[or]b"/>
<field name = "b" value="D[or]d"/>
<field name = "c" value="F[or]f"/>
<field name = "d" value="E[or]e"/>
<field name = "e" value="A[or]a"/>
<field name = "f" value="C[or]c"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1278, 'mod3uni1les19p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p3">
<field name = "a" value="A1"/>
<field name = "b" value="B2"/>
<field name = "c" value="C3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1279, 'mod3uni1les19p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p4">
<field name = "d" value="D1"/>
<field name = "e" value="E2"/>
<field name = "f" value="F2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1280, 'mod3uni1les19p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p5">
<field name = "g" value="G1"/>
<field name = "h" value="H3"/>
<field name = "i" value="I1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1281, 'mod3uni1les19p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p6">
<field name = "a" value="pipeline"/>
<field name = "a|1" value="underway"/>
<field name = "b" value="take"/>
<field name = "b|1" value="rich"/>
<field name = "b|2" value="encouraging"/>
<field name = "c" value="application"/>
<field name = "c|1" value="benefit"/>
<field name = "d" value="budget"/>
<field name = "d|1" value="require"/>
<field name = "e" value="bright"/>
<field name = "e|1" value="free"/>
<field name = "f" value="definitely"/>
<field name = "f|1" value="outline"/>
<field name = "f|2" value="overall"/>
<field name = "f|3" value="over"/>
<field name = "g" value="allow"/>
<field name = "g|1" value="out"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1282, 'mod3uni1les19p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p9">
<field name = "a" value="turn down"/>
<field name = "b" value="turn off"/>
<field name = "c" value="turn on"/>
<field name = "d" value="turn up"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1283, 'mod3uni1les19p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p11">
<field name = "a" value="taken aback"/>
<field name = "b" value="takes over"/>
<field name = "c" value="taking time off"/>
<field name = "d" value="take on"/>
<field name = "e" value="takes down"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1284, 'mod3uni1les19p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p16">
<field name = "a" value="lived up to"/>
<field name = "b" value="looks up to"/>
<field name = "c" value="ran out of"/>
<field name = "d" value="growing up"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1285, 'mod3uni1les19p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p17">
<field name = "e" value="look after"/>
<field name = "f" value="Hold on"/>
<field name = "g" value="make up for"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1286, 'mod3uni1les19p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p18">
<field name = "a" value="set up"/>
<field name = "b" value="build up"/>
<field name = "c" value="show"/>
<field name = "c|1" value="around"/>
<field name = "d" value="pay"/>
<field name = "d|1" value="back"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1287, 'mod3uni1les19p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p19">
<field name = "e" value="turned into"/>
<field name = "f" value="go through"/>
<field name = "g" value="kick in"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1288, 'mod3uni1les19p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p20">
<field name = "a" value="A2"/>
<field name = "b" value="B3"/>
<field name = "c" value="C1"/>
<field name = "d" value="D3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1289, 'mod3uni1les19p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les19p21">
<field name = "e" value="E3"/>
<field name = "f" value="F1"/>
<field name = "g" value="G2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1290, 'mod3uni1les20p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p2">
<field name = "a" value="A1"/>
<field name = "b" value="B2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1291, 'mod3uni1les20p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p3">
<field name = "c" value="C1"/>
<field name = "d" value="D1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1292, 'mod3uni1les20p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p4">
<field name = "e" value="E2"/>
<field name = "f" value="F1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1293, 'mod3uni1les20p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p5">
<field name = "g" value="G1"/>
<field name = "h" value="H2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1294, 'mod3uni1les20p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p6">
<field name = "a" value="A2"/>
<field name = "b" value="B3"/>
<field name = "c" value="C2"/>
<field name = "d" value="D1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1295, 'mod3uni1les20p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p7">
<field name = "e" value="E1"/>
<field name = "f" value="F3"/>
<field name = "g" value="G1"/>
<field name = "h" value="H3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1312, 'mod3uni1les21p2', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p2">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1313, 'mod3uni1les21p3', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p3">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1316, 'mod3uni1les21p6', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p6">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1317, 'mod3uni1les21p7', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p7">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1320, 'mod3uni1les21p10', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p10">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1321, 'mod3uni1les21p11', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p11">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1324, 'mod3uni1les21p14', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p14">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1325, 'mod3uni1les21p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p15">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1328, 'mod3uni1les21p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p18">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1329, 'mod3uni1les21p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p19">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1332, 'mod3uni1les21p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p22">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1333, 'mod3uni1les21p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p23">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1336, 'mod3uni1les21p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p26">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1296, 'mod3uni1les20p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p8">
<field name = "a" value="proud"/>
<field name = "a|1" value="forgotten"/>
<field name = "a|2" value="support"/>
<field name = "a|3" value="suppliers"/>
<field name = "b" value="given"/>
<field name = "b|1" value="income"/>
<field name = "b|2" value="entitled"/>
<field name = "c" value="together"/>
<field name = "c|1" value="feature"/>
<field name = "d" value="ahead"/>
<field name = "d|1" value="strength"/>
<field name = "e" value="provide"/>
<field name = "f" value="issue"/>
<field name = "f|1" value="meantime"/>
<field name = "g" value="priorities"/>
<field name = "g|1" value="resilient "/>
<field name = "h" value="page"/>
<field name = "h|1" value="overall "/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1314, 'mod3uni1les21p4', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p4">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1318, 'mod3uni1les21p8', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p8">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1322, 'mod3uni1les21p12', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p12">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1326, 'mod3uni1les21p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p16">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1330, 'mod3uni1les21p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p20">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1334, 'mod3uni1les21p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p24">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1297, 'mod3uni1les20p15', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p15">
<field name = "a" value="was developed"/>
<field name = "b" value="was made"/>
<field name = "c" value="was designed"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1302, 'mod3uni1les20p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p20">
<field name = "g" value="G1"/>
<field name = "h" value="H2"/>
<field name = "i" value="I2"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1303, 'mod3uni1les20p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p21">
<field name = "a" value="Where was the meeting held"/>
<field name = "b" value="The show was sponsored by a magazine"/>
<field name = "c" value="The project wasn''t concluded before July"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1306, 'mod3uni1les20p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p25">
<field name = "a" value="C"/>
<field name = "b" value="A"/>
<field name = "c" value="E"/>
<field name = "d" value="F"/>
<field name = "e" value="D"/>
<field name = "f" value="B"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1310, 'mod3uni1les20p29', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p29">
<field name = "i" value="were"/>
<field name = "j" value="was awarded"/>
<field name = "k" value="is authorized"/>
<field name = "k|1" value="are deployed"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1298, 'mod3uni1les20p16', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p16">
<field name = "d" value="was released"/>
<field name = "e" value="is inspected"/>
<field name = "f" value="was written"/>
<field name = "f|1" value="was translated"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1304, 'mod3uni1les20p22', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p22">
<field name = "d" value="The deadline will be met"/>
<field name = "e" value="The news will be announced tomorrow"/>
<field name = "f" value="The briefing was written by Jack"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1307, 'mod3uni1les20p26', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p26">
<field name = "a" value="1"/>
<field name = "b" value="6"/>
<field name = "c" value="4"/>
<field name = "d" value="2"/>
<field name = "e" value="5"/>
<field name = "f" value="3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1299, 'mod3uni1les20p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p17">
<field name = "g" value="is used"/>
<field name = "h" value="were asked"/>
<field name = "i" value="were told"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1300, 'mod3uni1les20p18', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p18">
<field name = "a" value="A2"/>
<field name = "b" value="B1"/>
<field name = "c" value="C1"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1308, 'mod3uni1les20p27', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p27">
<field name = "a" value="is opened"/>
<field name = "b" value="was changed"/>
<field name = "c" value="were provided"/>
<field name = "d" value="was created"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1301, 'mod3uni1les20p19', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p19">
<field name = "d" value="D2"/>
<field name = "e" value="E1"/>
<field name = "f" value="F3"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1305, 'mod3uni1les20p23', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p23">
<field name = "g" value="The products were released timely"/>
<field name = "h" value="The equipment was installed correctly"/>
<field name = "i" value="When was the picture taken"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1309, 'mod3uni1les20p28', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les20p28">
<field name = "e" value="was completed"/>
<field name = "f" value="was run"/>
<field name = "g" value="is established"/>
<field name = "h" value="was introduced"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1311, 'mod3uni1les21p1', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p1">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1315, 'mod3uni1les21p5', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p5">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1319, 'mod3uni1les21p9', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p9">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1323, 'mod3uni1les21p13', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p13">
<field name = "a" value="B"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1327, 'mod3uni1les21p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p17">
<field name = "a" value="C"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1331, 'mod3uni1les21p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p21">
<field name = "a" value="D"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (1335, 'mod3uni1les21p25', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod3uni1les21p25">
<field name = "a" value="A"/>
</challenge>
</payload>
</ivela>', 1, 3, 3, NULL, 1143, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (695, 'mod2uni1les2p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les2p17">
<field name = "a" value="I''m calling to complain about your packaging"/>
<field name = "b" value="Are you sending it by express mail"/>
<field name = "c" value="We always use the express mail service"/>
<field name = "d" value="We''re very sorry about this inconvenience"/>
<field name = "e" value="They''re shipping your order today"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 781, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (716, 'mod2uni1les5p20', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p20">
<field name = "a" value="difficult"/>
<field name = "b" value="slow"/>
<field name = "c" value="modern"/>
<field name = "d" value="outdated"/>
<field name = "e" value="expensive"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (722, 'mod2uni1les5p29', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les5p29">
<field name = "a" value="Where can we have the meeting next Friday"/>
<field name = "b" value="Are wheelchair facilities available"/>
<field name = "c" value="I need your help with special needs facilities"/>
<field name = "d" value="You can accommodate 20 people here"/>
<field name = "e" value="How big is the conference room"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (813, 'mod2uni1les6p24', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les6p24">
<field name = "a" value="B"/>
<field name = "b" value="A"/>
<field name = "c" value="C"/>
<field name = "d" value="B"/>
<field name = "e" value="C"/>
<field name = "f" value="A"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 793, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (764, 'mod2uni1les7p21', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les7p21">
<field name="a" value="responsibility"/>
<field name="b" value="unbelievable"/>
<field name="c" value="stand for"/>
<field name="d" value="renewal"/>
<field name="e" value="irresponsibility[and]unsustainability"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 687, 1);
INSERT INTO "IVELA".challenge_items (id, name, "XML", course, discipline, unit, "TIMESTAMP", dependency, weight) VALUES (833, 'mod2uni1les13p17', '<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="challenge"/>
<payload>
<challenge name="mod2uni1les13p17">
<field name = "a" value="5"/>
<field name = "b" value="6"/>
<field name = "c" value="1"/>
<field name = "d" value="2"/>
<field name = "e" value="11"/>
<field name = "f" value="4"/>
<field name = "g" value="9"/>
<field name = "h" value="10"/>
<field name = "i" value="7"/>
<field name = "j" value="12"/>
<field name = "k" value="13"/>
<field name = "l" value="3"/>
<field name = "m" value="8"/>
</challenge>
</payload>
</ivela>', 1, 2, 2, NULL, 818, 1);
