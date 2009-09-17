--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = ivela, pg_catalog;

--
-- Name: sq_access; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_access', 1, false);


--
-- Name: sq_address; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_address', 2, true);


--
-- Name: sq_address_type; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_address_type', 1, false);


--
-- Name: sq_answer_auditive_question_student_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_answer_auditive_question_student_exercise', 1, false);


--
-- Name: sq_answer_external_question_student_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_answer_external_question_student_exercise', 1, false);


--
-- Name: sq_answer_student_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_answer_student_exam', 1, false);


--
-- Name: sq_answer_student_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_answer_student_exercise', 1, false);


--
-- Name: sq_answer_subjective_question_student_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_answer_subjective_question_student_exam', 1, false);


--
-- Name: sq_answer_subjective_question_student_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_answer_subjective_question_student_exercise', 1, false);


--
-- Name: sq_attach_post; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_attach_post', 1, false);


--
-- Name: sq_auditive_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_auditive_question', 1, false);


--
-- Name: sq_authentication; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_authentication', 6, true);


--
-- Name: sq_authentication_functionality; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_authentication_functionality', 1, false);


--
-- Name: sq_challenge; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_challenge', 1, true);


--
-- Name: sq_challenge_items; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_challenge_items', 1, true);


--
-- Name: sq_challenger_result; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_challenger_result', 1, false);


--
-- Name: sq_chat; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_chat', 1, false);


--
-- Name: sq_course; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_course', 1, true);


--
-- Name: sq_course_requisite; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_course_requisite', 1, false);


--
-- Name: sq_dictionary; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_dictionary', 1, true);


--
-- Name: sq_discipline; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_discipline', 1, true);


--
-- Name: sq_enrollment; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_enrollment', 1, true);


--
-- Name: sq_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_exam', 1, false);


--
-- Name: sq_exam_requisite; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_exam_requisite', 1, false);


--
-- Name: sq_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_exercise', 1, false);


--
-- Name: sq_exercise_requisite; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_exercise_requisite', 1, false);


--
-- Name: sq_external_answer_student_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_external_answer_student_exam', 1, false);


--
-- Name: sq_external_params; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_external_params', 1, false);


--
-- Name: sq_external_params_external_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_external_params_external_question', 1, false);


--
-- Name: sq_external_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_external_question', 1, false);


--
-- Name: sq_faq; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_faq', 5, true);


--
-- Name: sq_file; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_file', 2, true);


--
-- Name: sq_finished_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_finished_exam', 1, false);


--
-- Name: sq_finished_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_finished_exercise', 1, false);


--
-- Name: sq_finished_unit_content; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_finished_unit_content', 1, true);


--
-- Name: sq_forum; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_forum', 1, false);


--
-- Name: sq_functionality; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_functionality', 1, false);


--
-- Name: sq_grade; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_grade', 1, true);


--
-- Name: sq_grade_note; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_grade_note', 1, false);


--
-- Name: sq_history; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_history', 1, true);


--
-- Name: sq_history_params; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_history_params', 1, true);

--
-- Name: sq_language; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_language', 3, true);



--
-- Name: sq_language_system_user; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_language_system_user', 1, false);


--
-- Name: sq_message; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_message', 1, true);


--
-- Name: sq_news_flash; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_news_flash', 1, true);


--
-- Name: sq_note; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_note', 1, false);


--
-- Name: sq_npd_phrase; Type: SEQUENCE SET; Schema: ivela; OINSERT INTO application_settings VALUES (1, NULL, '${java.io.tmpdir}', '.zip', 5000, true, 'webical', 'webical');wner: ivela
--

SELECT pg_catalog.setval('sq_npd_phrase', 1, false);


--
-- Name: sq_npd_user; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_npd_user', 1, false);


--
-- Name: sq_objective_answer; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_objective_answer', 1, false);


--
-- Name: sq_objective_answer_student_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_objective_answer_student_exam', 1, false);


--
-- Name: sq_objective_answer_student_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_objective_answer_student_exercise', 1, false);


--
-- Name: sq_objective_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_objective_question', 1, false);


--
-- Name: sq_phone; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_phone', 2, true);


--
-- Name: sq_placement_answer; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_placement_answer', 1, false);


--
-- Name: sq_placement_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_placement_question', 1, false);


--
-- Name: sq_post; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_post', 1, false);


--
-- Name: sq_professor; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_professor', 1, false);


--
-- Name: sq_profile; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_profile', 2, true);


--
-- Name: sq_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_question', 1, false);


--
-- Name: sq_question_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_question_exam', 1, false);


--
-- Name: sq_question_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_question_exercise', 1, false);


--
-- Name: sq_question_question_text_id; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_question_question_text_id', 1, false);


--
-- Name: sq_question_text; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_question_text', 1, false);


--
-- Name: sq_sentence_auditive_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_sentence_auditive_question', 1, false);



--
-- Name: sq_student_exam; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_student_exam', 1, false);


--
-- Name: sq_student_exercise; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_student_exercise', 1, false);


--
-- Name: sq_system_user; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_system_user', 2, true);


--
-- Name: sq_system_user_chat; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_system_user_chat', 1, false);


--
-- Name: sq_system_user_functionality; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_system_user_functionality', 1, false);


--
-- Name: sq_system_user_placement_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_system_user_placement_question', 1, false);


--
-- Name: sq_system_user_unit_status; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_system_user_unit_status', 1, false);


--
-- Name: sq_topic; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_topic', 1, false);


--
-- Name: sq_transcript; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_transcript', 1, true);


--
-- Name: sq_tutor; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_tutor', 1, false);


--
-- Name: sq_unit; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_unit', 1, true);


--
-- Name: sq_unit_content; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_unit_content', 1, true);


--
-- Name: sq_user_voice; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_user_voice', 1, false);


--
-- Name: sq_voice_feedback; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_voice_feedback', 1, false);


--
-- Name: sq_word_sentence_auditive_question; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_word_sentence_auditive_question', 1, false);


--
-- Name: sq_word_voice; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_word_voice', 1, false);


--
-- Name: sq_ws_enabled; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('sq_ws_enabled', 1, true);


--
-- Name: system_user_authentication_seq; Type: SEQUENCE SET; Schema: ivela; Owner: ivela
--

SELECT pg_catalog.setval('system_user_authentication_seq', 1, false);


SET search_path = public, pg_catalog;

--
-- Name: application_settings_applicationsettingsid_seq; Type: SEQUENCE SET; Schema: public; Owner: ivela
--

SELECT pg_catalog.setval('application_settings_applicationsettingsid_seq', 1, true);


--
-- Name: calendar_calendarid_seq; Type: SEQUENCE SET; Schema: public; Owner: ivela
--

SELECT pg_catalog.setval('calendar_calendarid_seq', 1, true);


--
-- Name: event_eventid_seq; Type: SEQUENCE SET; Schema: public; Owner: ivela
--

SELECT pg_catalog.setval('event_eventid_seq', 1, true);


--
-- Name: options_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ivela
--

SELECT pg_catalog.setval('options_id_seq', 1, false);


--
-- Name: settings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ivela
--

SELECT pg_catalog.setval('settings_id_seq', 1, false);


--
-- Name: user_settings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ivela
--

SELECT pg_catalog.setval('user_settings_id_seq', 1, true);


SET search_path = ivela, pg_catalog;

--
-- Data for Name: authentication; Type: TABLE DATA; Schema: ivela; Owner: ivela
--

INSERT INTO authentication VALUES (1, 'ROLE_ADMIN', 'Administrator Permission');
INSERT INTO authentication VALUES (2, 'ROLE_COORD', 'Coordinator Permission');
INSERT INTO authentication VALUES (3, 'ROLE_TUTOR', 'Tutor Permission');
INSERT INTO authentication VALUES (4, 'ROLE_EAD_USER', 'EAD Student Permission');
INSERT INTO authentication VALUES (5, 'ROLE_USER', 'User Permission');
INSERT INTO authentication VALUES (6, 'ROLE_PROFESSOR', 'Professor Permission');



--
-- Data for Name: language; Type: TABLE DATA; Schema: ivela; Owner: ivela
--

INSERT INTO language VALUES (1, 'English (USA)', 'en_US');
INSERT INTO language VALUES (2, 'Portuguese (Brazil)', 'pt_BR');
INSERT INTO language VALUES (3, 'Spanish', 'es_SP');




INSERT INTO profile VALUES (1, 'werwerewrewwrew', 'e', 'tttttttttttt', 1, NULL, '', '/uploads/profiles/1/jdamicopb.jpg', NULL, NULL, 1, 1);


--
-- Data for Name: student_exam; Type: TABLE DATA; Schema: ivela; Owner: ivela
--




INSERT INTO system_user VALUES (1, 'ivela@ivela.com', '', 'admin', 'c3005c8647d31653a03b226bb55bc18b', true, 1, NULL, NULL, 1);








INSERT INTO ws_enabled VALUES (1, '127.0.0.1');




--
-- Data for Name: application_settings; Type: TABLE DATA; Schema: public; Owner: ivela
-- INSERT INTO application_settings VALUES (1, NULL, '${java.io.tmpdir}', '.zip', 5000, true, 'webical', 'webical');





--
-- PostgreSQL database dump complete
--

