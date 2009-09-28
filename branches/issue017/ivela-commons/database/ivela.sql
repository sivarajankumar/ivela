--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: ivela; Type: SCHEMA; Schema: -; Owner: ivela
--

CREATE SCHEMA ivela;


ALTER SCHEMA ivela OWNER TO ivela;

SET search_path = ivela, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: access; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE access (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    end_datetime timestamp without time zone NOT NULL,
    start_datetime timestamp without time zone NOT NULL
);


ALTER TABLE ivela.access OWNER TO ivela;

--
-- Name: address; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE address (
    id numeric(10,0) NOT NULL,
    profile numeric(10,0),
    address_type numeric(10,0),
    location character varying(150) NOT NULL,
    number character varying(10) NOT NULL,
    additional_information character varying(50),
    neighborhood character varying(50),
    zip_code character varying(20) NOT NULL,
    city character varying(100) NOT NULL,
    state character varying(100),
    country smallint DEFAULT 1 NOT NULL
);


ALTER TABLE ivela.address OWNER TO ivela;

--
-- Name: address_type; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE address_type (
    id numeric(10,0) NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE ivela.address_type OWNER TO ivela;

--
-- Name: answer_auditive_question_student_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_auditive_question_student_exam (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    answer_student_exam numeric(10,0) NOT NULL,
    times integer NOT NULL,
    score numeric(10,2) DEFAULT 0
);


ALTER TABLE ivela.answer_auditive_question_student_exam OWNER TO ivela;

--
-- Name: answer_auditive_question_student_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_auditive_question_student_exercise (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    answer_student_exercise numeric(10,0) NOT NULL,
    times integer DEFAULT 1 NOT NULL,
    score numeric(10,2) DEFAULT 0
);


ALTER TABLE ivela.answer_auditive_question_student_exercise OWNER TO ivela;

--
-- Name: answer_external_question_student_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_external_question_student_exercise (
    id numeric(10,0) NOT NULL,
    aproved boolean NOT NULL,
    result_value character varying(10) NOT NULL,
    answer_student_exercise numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL
);


ALTER TABLE ivela.answer_external_question_student_exercise OWNER TO ivela;

--
-- Name: answer_student_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_student_exam (
    id numeric(10,0) NOT NULL,
    student_exam numeric(10,0) NOT NULL,
    type integer
);


ALTER TABLE ivela.answer_student_exam OWNER TO ivela;

--
-- Name: answer_student_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_student_exercise (
    id numeric(10,0) NOT NULL,
    student_exercise numeric(10,0) NOT NULL,
    type integer
);


ALTER TABLE ivela.answer_student_exercise OWNER TO ivela;

--
-- Name: COLUMN answer_student_exercise.type; Type: COMMENT; Schema: ivela; Owner: ivela
--

COMMENT ON COLUMN answer_student_exercise.type IS 'tipo da questao';


--
-- Name: answer_subjective_question_student_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_subjective_question_student_exam (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    answer_student_exam numeric(10,0) NOT NULL,
    answer text,
    score numeric(10,2) DEFAULT 0
);


ALTER TABLE ivela.answer_subjective_question_student_exam OWNER TO ivela;

--
-- Name: answer_subjective_question_student_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE answer_subjective_question_student_exercise (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    answer_student_exercise numeric(10,0) NOT NULL,
    answer text NOT NULL,
    score numeric(10,2) DEFAULT 0
);


ALTER TABLE ivela.answer_subjective_question_student_exercise OWNER TO ivela;

--
-- Name: attach_post; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE attach_post (
    id numeric(10,0) NOT NULL,
    post numeric(10,0) NOT NULL,
    file numeric(10,0) NOT NULL
);


ALTER TABLE ivela.attach_post OWNER TO ivela;

--
-- Name: auditive_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE auditive_question (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    conf_file character varying(255) NOT NULL,
    chance_sentence integer DEFAULT 3 NOT NULL
);


ALTER TABLE ivela.auditive_question OWNER TO ivela;

--
-- Name: authentication; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE authentication (
    id numeric(10,0) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE ivela.authentication OWNER TO ivela;

--
-- Name: authentication_functionality; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE authentication_functionality (
    id numeric(10,0) NOT NULL,
    authentication integer NOT NULL,
    functionality numeric(10,0) NOT NULL
);


ALTER TABLE ivela.authentication_functionality OWNER TO ivela;

--
-- Name: challenge; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE challenge (
    id numeric(10,0) NOT NULL,
    challid character varying(100) NOT NULL,
    challvalue double precision,
    uid numeric(10,0) NOT NULL
);


ALTER TABLE ivela.challenge OWNER TO ivela;

--
-- Name: challenge_items; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE challenge_items (
    id integer NOT NULL,
    name character varying(100),
    xml text,
    course numeric(10,0),
    discipline numeric(10,0),
    unit numeric(10,0),
    "timestamp" timestamp without time zone,
    dependency numeric(10,0)
);


ALTER TABLE ivela.challenge_items OWNER TO ivela;

--
-- Name: challenger_result; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE challenger_result (
    id integer NOT NULL,
    mark double precision,
    system_user numeric(10,0),
    grade numeric(10,0),
    unit_content numeric(10,0),
    teacher numeric(10,0),
    type integer,
    xml_fk_id numeric(10,0)
);


ALTER TABLE ivela.challenger_result OWNER TO ivela;

--
-- Name: chat; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE chat (
    id numeric(10,0) NOT NULL,
    grade numeric(10,0) NOT NULL,
    title character varying(150) NOT NULL,
    description character varying(255),
    start_datetime timestamp without time zone NOT NULL,
    end_datetime timestamp without time zone NOT NULL
);


ALTER TABLE ivela.chat OWNER TO ivela;

--
-- Name: course; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE course (
    id numeric(10,0) NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(250),
    image character varying(250),
    target_audience character varying(250),
    contents text,
    repository_structure text,
    active boolean DEFAULT true,
    upload_package_enabled boolean DEFAULT false,
    challenge_itens_enabled boolean DEFAULT false
);


ALTER TABLE ivela.course OWNER TO ivela;

--
-- Name: course_requisite; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE course_requisite (
    id numeric(10,0) NOT NULL,
    course numeric(10,0) NOT NULL,
    requisite numeric(10,0) NOT NULL
);


ALTER TABLE ivela.course_requisite OWNER TO ivela;

--
-- Name: dictionary; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE dictionary (
    id numeric(10,0) NOT NULL,
    title character varying(255),
    description text,
    created_at date
);


ALTER TABLE ivela.dictionary OWNER TO ivela;

--
-- Name: discipline; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE discipline (
    id numeric(10,0) NOT NULL,
    course numeric(10,0) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE ivela.discipline OWNER TO ivela;

--
-- Name: enrollment; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE enrollment (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    grade numeric(10,0) NOT NULL,
    status integer DEFAULT 0 NOT NULL,
    start_datetime timestamp without time zone
);


ALTER TABLE ivela.enrollment OWNER TO ivela;

--
-- Name: exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE exam (
    id numeric(10,0) NOT NULL,
    title character varying(100) NOT NULL,
    start_datetime timestamp without time zone NOT NULL,
    end_datetime timestamp without time zone NOT NULL,
    duration integer DEFAULT 0 NOT NULL,
    questions_per_page integer DEFAULT 5 NOT NULL,
    randomize_questions_order boolean DEFAULT false NOT NULL,
    navigable boolean DEFAULT true NOT NULL,
    order_n integer,
    unit_content numeric(10,0) NOT NULL,
    weight integer,
    created_at timestamp without time zone,
    created_by numeric,
    active boolean DEFAULT true
);


ALTER TABLE ivela.exam OWNER TO ivela;

--
-- Name: COLUMN exam.active; Type: COMMENT; Schema: ivela; Owner: ivela
--

COMMENT ON COLUMN exam.active IS 'deletado ou nÃ£o';


--
-- Name: exam_requisite; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE exam_requisite (
    id integer NOT NULL,
    exam numeric(10,0) NOT NULL,
    requisite numeric(10,0) NOT NULL
);


ALTER TABLE ivela.exam_requisite OWNER TO ivela;

--
-- Name: exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE exercise (
    id numeric(10,0) NOT NULL,
    chances integer NOT NULL,
    navigable boolean NOT NULL,
    title character varying(100) NOT NULL,
    created_by numeric(10,0) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    description character varying(255) NOT NULL,
    weight integer,
    order_n integer,
    unit_content numeric(10,0) NOT NULL,
    active boolean DEFAULT true
);


ALTER TABLE ivela.exercise OWNER TO ivela;

--
-- Name: exercise_requisite; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE exercise_requisite (
    id integer NOT NULL,
    exercise numeric(10,0) NOT NULL,
    requisite numeric(10,0) NOT NULL
);


ALTER TABLE ivela.exercise_requisite OWNER TO ivela;

--
-- Name: external_answer_student_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE external_answer_student_exam (
    id numeric(10,0) NOT NULL,
    result_value character varying(10) NOT NULL,
    question numeric(10,0) NOT NULL,
    aproved boolean NOT NULL,
    answer_student_exam numeric(10,0) NOT NULL
);


ALTER TABLE ivela.external_answer_student_exam OWNER TO ivela;

--
-- Name: external_params; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE external_params (
    id numeric(10,0) NOT NULL,
    value character varying(50),
    key character varying(100)
);


ALTER TABLE ivela.external_params OWNER TO ivela;

--
-- Name: external_params_external_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE external_params_external_question (
    id numeric(10,0) NOT NULL,
    external_question numeric(10,0) NOT NULL,
    external_params numeric(10,0) NOT NULL
);


ALTER TABLE ivela.external_params_external_question OWNER TO ivela;

--
-- Name: external_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE external_question (
    id numeric(10,0) NOT NULL,
    url_binary character varying(255) NOT NULL,
    url_result character varying(255) NOT NULL,
    question numeric(10,0) NOT NULL,
    bin_type integer
);


ALTER TABLE ivela.external_question OWNER TO ivela;

--
-- Name: faq; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE faq (
    id numeric(10,0) NOT NULL,
    question character varying(150) NOT NULL,
    answer text NOT NULL,
    date timestamp without time zone NOT NULL,
    created_by numeric(10,0) NOT NULL
);


ALTER TABLE ivela.faq OWNER TO ivela;

--
-- Name: file; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE file (
    id numeric(10,0) NOT NULL,
    course numeric(10,0) NOT NULL,
    title character varying(250) NOT NULL,
    author character varying(100),
    description character varying(255),
    keywords character varying(255),
    filename character varying(100) NOT NULL,
    mimetype character varying(50) NOT NULL,
    sent_by numeric(10,0) NOT NULL,
    upload_date timestamp without time zone NOT NULL,
    path character varying(100) NOT NULL,
    grade integer DEFAULT 0
);


ALTER TABLE ivela.file OWNER TO ivela;

--
-- Name: finished_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE finished_exam (
    id integer NOT NULL,
    system_user numeric(10,0),
    exam numeric(10,0),
    unit_content numeric(10,0)
);


ALTER TABLE ivela.finished_exam OWNER TO ivela;

--
-- Name: finished_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE finished_exercise (
    id integer NOT NULL,
    system_user numeric(10,0),
    exercise numeric(10,0),
    unit_content numeric(10,0)
);


ALTER TABLE ivela.finished_exercise OWNER TO ivela;

--
-- Name: finished_unit_content; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE finished_unit_content (
    id integer NOT NULL,
    system_user numeric(10,0),
    course numeric(10,0),
    unit_content numeric(10,0)
);


ALTER TABLE ivela.finished_unit_content OWNER TO ivela;

--
-- Name: forum; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE forum (
    id numeric(10,0) NOT NULL,
    grade numeric(10,0),
    title character varying(100) NOT NULL,
    description character varying(255),
    student_create_topic boolean DEFAULT true NOT NULL,
    student_upload_post boolean DEFAULT true NOT NULL,       
    public boolean DEFAULT false NOT NULL,
    created_by numeric(10,0) NOT NULL,
    topics_count integer NOT NULL DEFAULT 0, 
    course integer DEFAULT 0
);


ALTER TABLE ivela.forum OWNER TO ivela;

--
-- Name: functionality; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE functionality (
    id numeric(10,0) NOT NULL,
    name character varying(50) NOT NULL,
    action character varying(250) NOT NULL,
    method character varying(80) NOT NULL,
    description character varying(150) NOT NULL
);


ALTER TABLE ivela.functionality OWNER TO ivela;

--
-- Name: grade; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE grade (
    id numeric(10,0) NOT NULL,
    course numeric(10,0) NOT NULL,
    name character varying(20) NOT NULL,
    period character varying(20) NOT NULL,
    max_students integer NOT NULL,
    status integer DEFAULT 1 NOT NULL,
    requires_enrollment_validation boolean DEFAULT true NOT NULL,
    coordinator numeric(10,0) NOT NULL,
    start_datetime timestamp without time zone NOT NULL,
    end_datetime timestamp without time zone NOT NULL,
    max_duration integer DEFAULT 0 NOT NULL
);


ALTER TABLE ivela.grade OWNER TO ivela;

--
-- Name: grade_note; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE grade_note (
    id numeric(10,0) NOT NULL,
    grade numeric(10,0) NOT NULL,
    title character varying(100) NOT NULL,
    description text,
    datetime timestamp without time zone NOT NULL,
    created_by numeric(10,0) NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE ivela.grade_note OWNER TO ivela;

--
-- Name: history; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE history (
    id integer NOT NULL,
    title character varying(200) NOT NULL,
    description text,
    datetime timestamp without time zone NOT NULL,
    system_user numeric(10,0) NOT NULL
);


ALTER TABLE ivela.history OWNER TO ivela;

--
-- Name: history_params; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE history_params (
    id integer NOT NULL,
    history numeric(10,0) NOT NULL,
    param character varying(100) NOT NULL,
    value character varying(100)
);


ALTER TABLE ivela.history_params OWNER TO ivela;

--
-- Name: language; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE language (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    code character varying(5) NOT NULL
);


ALTER TABLE ivela.language OWNER TO ivela;


--
-- Name: language_system_user; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE language_system_user (
    id numeric(10,0) NOT NULL,
    language integer NOT NULL,
    system_user numeric(10,0) NOT NULL,
    reading integer NOT NULL,
    writing integer NOT NULL,
    comprehension integer NOT NULL,
    speaking integer NOT NULL
);


ALTER TABLE ivela.language_system_user OWNER TO ivela;

--
-- Name: message; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE message (
    id numeric(10,0) NOT NULL,
    sender numeric(10,0) NOT NULL,
    recipient numeric(10,0) NOT NULL,
    title character varying(255) NOT NULL,
    description text,
    datetime timestamp without time zone NOT NULL,
    read boolean DEFAULT false NOT NULL,
    s_delete boolean DEFAULT false NOT NULL,
    r_delete boolean DEFAULT false NOT NULL
);


ALTER TABLE ivela.message OWNER TO ivela;

--
-- Name: news_flash; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE news_flash (
    id integer NOT NULL,
    message text,
    sender numeric(10,0) NOT NULL,
    receiver numeric(10,0) NOT NULL,
    read boolean NOT NULL
);


ALTER TABLE ivela.news_flash OWNER TO ivela;

--
-- Name: note; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE note (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    title character varying(100) NOT NULL,
    description text,
    datetime timestamp without time zone NOT NULL
);


ALTER TABLE ivela.note OWNER TO ivela;

--
-- Name: npd_phrase; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE npd_phrase (
    id integer NOT NULL,
    npd_user numeric(10,0) NOT NULL,
    phrase integer,
    tries integer
);


ALTER TABLE ivela.npd_phrase OWNER TO ivela;

--
-- Name: npd_user; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE npd_user (
    id numeric(10,0) NOT NULL
);


ALTER TABLE ivela.npd_user OWNER TO ivela;

--
-- Name: objective_answer; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE objective_answer (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    answer text NOT NULL
);


ALTER TABLE ivela.objective_answer OWNER TO ivela;

--
-- Name: objective_answer_student_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE objective_answer_student_exam (
    id numeric(10,0) NOT NULL,
    answer_student_exam numeric(10,0) NOT NULL,
    objective_answer numeric(10,0) NOT NULL
);


ALTER TABLE ivela.objective_answer_student_exam OWNER TO ivela;

--
-- Name: objective_answer_student_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE objective_answer_student_exercise (
    id numeric(10,0) NOT NULL,
    objective_answer numeric(10,0) NOT NULL,
    answer_student_exercise numeric(10,0) NOT NULL
);


ALTER TABLE ivela.objective_answer_student_exercise OWNER TO ivela;

--
-- Name: objective_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE objective_question (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    correct_answer numeric(10,0) NOT NULL
);


ALTER TABLE ivela.objective_question OWNER TO ivela;

--
-- Name: phone; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE phone (
    id numeric(10,0) NOT NULL,
    profile numeric(10,0) NOT NULL,
    number character varying(20) NOT NULL,
    prefix character varying(10)
);


ALTER TABLE ivela.phone OWNER TO ivela;

--
-- Name: placement_answer; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE placement_answer (
    id integer NOT NULL,
    question integer NOT NULL,
    answer character varying(250) NOT NULL
);


ALTER TABLE ivela.placement_answer OWNER TO ivela;

--
-- Name: placement_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE placement_question (
    id integer NOT NULL,
    question text NOT NULL,
    type integer DEFAULT 0 NOT NULL,
    correct_answer integer NOT NULL,
    level integer NOT NULL
);


ALTER TABLE ivela.placement_question OWNER TO ivela;

--
-- Name: post; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE post (
    id numeric(10,0) NOT NULL,
    topic numeric(10,0) NOT NULL,
    title character varying(100) NOT NULL,
    created_by numeric(10,0) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    message text NOT NULL
);


ALTER TABLE ivela.post OWNER TO ivela;

--
-- Name: professor; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE professor (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    grade numeric(10,0) NOT NULL
);


ALTER TABLE ivela.professor OWNER TO ivela;

--
-- Name: profile; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE profile (
    id numeric(10,0) NOT NULL,
    first_name character varying(150),
    initials character varying(10),
    last_name character varying(150),
    birth_date date,
    social_number character varying(30),
    photo character varying(150),
    gender integer DEFAULT 0,
    disabilities boolean DEFAULT false,
    ethnicity numeric(10,0),
    language numeric(10,0)
);


ALTER TABLE ivela.profile OWNER TO ivela;

--
-- Name: question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE question (
    id numeric(10,0) NOT NULL,
    question text NOT NULL,
    type integer NOT NULL,
    created_by numeric(10,0) NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE ivela.question OWNER TO ivela;

--
-- Name: question_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE question_exam (
    id numeric(10,0) NOT NULL,
    weight integer NOT NULL,
    question numeric(10,0) NOT NULL,
    exam numeric(10,0) NOT NULL,
    required boolean,
    active boolean
);


ALTER TABLE ivela.question_exam OWNER TO ivela;

--
-- Name: question_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE question_exercise (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    weight integer NOT NULL,
    exercise numeric(10,0) NOT NULL,
    required boolean,
    active boolean
);


ALTER TABLE ivela.question_exercise OWNER TO ivela;

--
-- Name: question_question_text; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE question_question_text (
    id numeric(10,0) NOT NULL,
    question_text numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL
);


ALTER TABLE ivela.question_question_text OWNER TO ivela;

--
-- Name: question_text; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE question_text (
    id numeric(10,0) NOT NULL,
    audio character varying(255),
    text text
);


ALTER TABLE ivela.question_text OWNER TO ivela;

--
-- Name: sentence_auditive_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE sentence_auditive_question (
    id numeric(10,0) NOT NULL,
    question numeric(10,0) NOT NULL,
    sentence character varying(100) NOT NULL,
    file character varying(70) NOT NULL,
    sequence integer DEFAULT 1 NOT NULL
);


ALTER TABLE ivela.sentence_auditive_question OWNER TO ivela;

--
-- Name: sq_access; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_access
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_access OWNER TO ivela;

--
-- Name: sq_access; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_access OWNED BY access.id;


--
-- Name: sq_address; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_address
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_address OWNER TO ivela;

--
-- Name: sq_address; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_address OWNED BY address.id;


--
-- Name: sq_address_type; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_address_type
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_address_type OWNER TO ivela;

--
-- Name: sq_address_type; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_address_type OWNED BY address_type.id;


--
-- Name: sq_answer_auditive_question_student_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_answer_auditive_question_student_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_answer_auditive_question_student_exercise OWNER TO ivela;

--
-- Name: sq_answer_auditive_question_student_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_answer_auditive_question_student_exercise OWNED BY answer_auditive_question_student_exercise.id;


--
-- Name: sq_answer_external_question_student_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_answer_external_question_student_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_answer_external_question_student_exercise OWNER TO ivela;

--
-- Name: sq_answer_external_question_student_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_answer_external_question_student_exercise OWNED BY answer_external_question_student_exercise.id;


--
-- Name: sq_answer_student_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_answer_student_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_answer_student_exam OWNER TO ivela;

--
-- Name: sq_answer_student_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_answer_student_exam OWNED BY answer_student_exam.id;


--
-- Name: sq_answer_student_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_answer_student_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_answer_student_exercise OWNER TO ivela;

--
-- Name: sq_answer_student_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_answer_student_exercise OWNED BY answer_student_exercise.id;


--
-- Name: sq_answer_subjective_question_student_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_answer_subjective_question_student_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_answer_subjective_question_student_exam OWNER TO ivela;

--
-- Name: sq_answer_subjective_question_student_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_answer_subjective_question_student_exam OWNED BY answer_subjective_question_student_exam.id;


--
-- Name: sq_answer_subjective_question_student_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_answer_subjective_question_student_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_answer_subjective_question_student_exercise OWNER TO ivela;

--
-- Name: sq_answer_subjective_question_student_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_answer_subjective_question_student_exercise OWNED BY answer_subjective_question_student_exercise.id;


--
-- Name: sq_attach_post; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_attach_post
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_attach_post OWNER TO ivela;

--
-- Name: sq_attach_post; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_attach_post OWNED BY attach_post.id;


--
-- Name: sq_auditive_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_auditive_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_auditive_question OWNER TO ivela;

--
-- Name: sq_auditive_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_auditive_question OWNED BY auditive_question.id;


--
-- Name: sq_authentication; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_authentication
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_authentication OWNER TO ivela;

--
-- Name: sq_authentication; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_authentication OWNED BY authentication.id;


--
-- Name: sq_authentication_functionality; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_authentication_functionality
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_authentication_functionality OWNER TO ivela;

--
-- Name: sq_authentication_functionality; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_authentication_functionality OWNED BY authentication_functionality.id;


--
-- Name: sq_challenge; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_challenge
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_challenge OWNER TO ivela;

--
-- Name: sq_challenge; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_challenge OWNED BY challenge.id;


--
-- Name: sq_challenge_items; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_challenge_items
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_challenge_items OWNER TO ivela;

--
-- Name: sq_challenge_items; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_challenge_items OWNED BY challenge_items.id;


--
-- Name: sq_challenger_result; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_challenger_result
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_challenger_result OWNER TO ivela;

--
-- Name: sq_challenger_result; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_challenger_result OWNED BY challenger_result.id;


--
-- Name: sq_chat; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_chat
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_chat OWNER TO ivela;

--
-- Name: sq_chat; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_chat OWNED BY chat.id;



--
-- Name: sq_course; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_course
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_course OWNER TO ivela;

--
-- Name: sq_course; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_course OWNED BY course.id;


--
-- Name: sq_course_requisite; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_course_requisite
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_course_requisite OWNER TO ivela;

--
-- Name: sq_course_requisite; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_course_requisite OWNED BY course_requisite.id;


--
-- Name: sq_dictionary; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_dictionary
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_dictionary OWNER TO ivela;

--
-- Name: sq_dictionary; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_dictionary OWNED BY dictionary.id;


--
-- Name: sq_discipline; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_discipline
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_discipline OWNER TO ivela;

--
-- Name: sq_discipline; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_discipline OWNED BY discipline.id;


--
-- Name: sq_enrollment; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_enrollment
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_enrollment OWNER TO ivela;

--
-- Name: sq_enrollment; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_enrollment OWNED BY enrollment.id;


--
-- Name: sq_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_exam OWNER TO ivela;

--
-- Name: sq_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_exam OWNED BY exam.id;


--
-- Name: sq_exam_requisite; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_exam_requisite
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_exam_requisite OWNER TO ivela;

--
-- Name: sq_exam_requisite; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_exam_requisite OWNED BY exam_requisite.id;


--
-- Name: sq_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_exercise OWNER TO ivela;

--
-- Name: sq_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_exercise OWNED BY exercise.id;


--
-- Name: sq_exercise_requisite; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_exercise_requisite
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_exercise_requisite OWNER TO ivela;

--
-- Name: sq_exercise_requisite; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_exercise_requisite OWNED BY exercise_requisite.id;


--
-- Name: sq_external_answer_student_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_external_answer_student_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_external_answer_student_exam OWNER TO ivela;

--
-- Name: sq_external_answer_student_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_external_answer_student_exam OWNED BY external_answer_student_exam.id;


--
-- Name: sq_external_params; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_external_params
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_external_params OWNER TO ivela;

--
-- Name: sq_external_params; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_external_params OWNED BY external_params.id;


--
-- Name: sq_external_params_external_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_external_params_external_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_external_params_external_question OWNER TO ivela;

--
-- Name: sq_external_params_external_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_external_params_external_question OWNED BY external_params_external_question.id;


--
-- Name: sq_external_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_external_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_external_question OWNER TO ivela;

--
-- Name: sq_external_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_external_question OWNED BY external_question.id;


--
-- Name: sq_faq; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_faq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_faq OWNER TO ivela;

--
-- Name: sq_faq; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_faq OWNED BY faq.id;


--
-- Name: sq_file; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_file
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_file OWNER TO ivela;

--
-- Name: sq_file; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_file OWNED BY file.id;


--
-- Name: sq_finished_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_finished_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_finished_exam OWNER TO ivela;

--
-- Name: sq_finished_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_finished_exam OWNED BY finished_exam.id;


--
-- Name: sq_finished_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_finished_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_finished_exercise OWNER TO ivela;

--
-- Name: sq_finished_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_finished_exercise OWNED BY finished_exercise.id;


--
-- Name: sq_finished_unit_content; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_finished_unit_content
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_finished_unit_content OWNER TO ivela;

--
-- Name: sq_finished_unit_content; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_finished_unit_content OWNED BY finished_unit_content.id;


--
-- Name: sq_forum; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_forum
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_forum OWNER TO ivela;

--
-- Name: sq_forum; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_forum OWNED BY forum.id;


--
-- Name: sq_functionality; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_functionality
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_functionality OWNER TO ivela;

--
-- Name: sq_functionality; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_functionality OWNED BY functionality.id;


--
-- Name: sq_grade; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_grade
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_grade OWNER TO ivela;

--
-- Name: sq_grade; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_grade OWNED BY grade.id;


--
-- Name: sq_grade_note; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_grade_note
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_grade_note OWNER TO ivela;

--
-- Name: sq_grade_note; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_grade_note OWNED BY grade_note.id;


--
-- Name: sq_history; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_history
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_history OWNER TO ivela;

--
-- Name: sq_history; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_history OWNED BY history.id;


--
-- Name: sq_history_params; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_history_params
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_history_params OWNER TO ivela;

--
-- Name: sq_history_params; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_history_params OWNED BY history_params.id;

--
-- Name: sq_language; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_language
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_language OWNER TO ivela;

--
-- Name: sq_language; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_language OWNED BY language.id;


--
-- Name: sq_language_system_user; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_language_system_user
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_language_system_user OWNER TO ivela;

--
-- Name: sq_language_system_user; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_language_system_user OWNED BY language_system_user.id;


--
-- Name: sq_message; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_message
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_message OWNER TO ivela;

--
-- Name: sq_message; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_message OWNED BY message.id;


--
-- Name: sq_news_flash; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_news_flash
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_news_flash OWNER TO ivela;

--
-- Name: sq_news_flash; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_news_flash OWNED BY news_flash.id;


--
-- Name: sq_note; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_note
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_note OWNER TO ivela;

--
-- Name: sq_note; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_note OWNED BY note.id;


--
-- Name: sq_npd_phrase; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_npd_phrase
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_npd_phrase OWNER TO ivela;

--
-- Name: sq_npd_phrase; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_npd_phrase OWNED BY npd_phrase.id;


--
-- Name: sq_npd_user; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_npd_user
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_npd_user OWNER TO ivela;

--
-- Name: sq_npd_user; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_npd_user OWNED BY npd_user.id;


--
-- Name: sq_objective_answer; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_objective_answer
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_objective_answer OWNER TO ivela;

--
-- Name: sq_objective_answer; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_objective_answer OWNED BY objective_answer.id;


--
-- Name: sq_objective_answer_student_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_objective_answer_student_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_objective_answer_student_exam OWNER TO ivela;

--
-- Name: sq_objective_answer_student_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_objective_answer_student_exam OWNED BY objective_answer_student_exam.id;


--
-- Name: sq_objective_answer_student_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_objective_answer_student_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_objective_answer_student_exercise OWNER TO ivela;

--
-- Name: sq_objective_answer_student_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_objective_answer_student_exercise OWNED BY objective_answer_student_exercise.id;


--
-- Name: sq_objective_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_objective_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_objective_question OWNER TO ivela;

--
-- Name: sq_objective_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_objective_question OWNED BY objective_question.id;


--
-- Name: sq_phone; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_phone
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_phone OWNER TO ivela;

--
-- Name: sq_phone; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_phone OWNED BY phone.id;


--
-- Name: sq_placement_answer; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_placement_answer
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_placement_answer OWNER TO ivela;

--
-- Name: sq_placement_answer; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_placement_answer OWNED BY placement_answer.id;


--
-- Name: sq_placement_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_placement_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_placement_question OWNER TO ivela;

--
-- Name: sq_placement_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_placement_question OWNED BY placement_question.id;


--
-- Name: sq_post; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_post
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_post OWNER TO ivela;

--
-- Name: sq_post; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_post OWNED BY post.id;


--
-- Name: sq_professor; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_professor
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_professor OWNER TO ivela;

--
-- Name: sq_professor; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_professor OWNED BY professor.id;


--
-- Name: sq_profile; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_profile
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_profile OWNER TO ivela;

--
-- Name: sq_profile; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_profile OWNED BY profile.id;


--
-- Name: sq_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_question OWNER TO ivela;

--
-- Name: sq_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_question OWNED BY question.id;


--
-- Name: sq_question_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_question_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_question_exam OWNER TO ivela;

--
-- Name: sq_question_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_question_exam OWNED BY question_exam.id;


--
-- Name: sq_question_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_question_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_question_exercise OWNER TO ivela;

--
-- Name: sq_question_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_question_exercise OWNED BY question_exercise.id;


--
-- Name: sq_question_question_text_id; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_question_question_text_id
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_question_question_text_id OWNER TO ivela;

--
-- Name: sq_question_question_text_id; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_question_question_text_id OWNED BY question_question_text.id;


--
-- Name: sq_question_text; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_question_text
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_question_text OWNER TO ivela;

--
-- Name: sq_question_text; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_question_text OWNED BY question_text.id;


--
-- Name: sq_sentence_auditive_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_sentence_auditive_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_sentence_auditive_question OWNER TO ivela;

--
-- Name: sq_sentence_auditive_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_sentence_auditive_question OWNED BY sentence_auditive_question.id;


--
-- Name: student_exam; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE student_exam (
    id numeric(10,0) NOT NULL,
    exam numeric(10,0) NOT NULL,
    student numeric(10,0) NOT NULL,
    status integer NOT NULL,
    score double precision,
    manual_score double precision,
    grade numeric(10,0)
);


ALTER TABLE ivela.student_exam OWNER TO ivela;

--
-- Name: sq_student_exam; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_student_exam
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_student_exam OWNER TO ivela;

--
-- Name: sq_student_exam; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_student_exam OWNED BY student_exam.id;


--
-- Name: student_exercise; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE student_exercise (
    id numeric(10,0) NOT NULL,
    student numeric(10,0) NOT NULL,
    status integer NOT NULL,
    exercise numeric(10,0) NOT NULL,
    score double precision,
    chances integer DEFAULT (-1) NOT NULL,
    manual_score double precision,
    grade numeric(10,0)
);


ALTER TABLE ivela.student_exercise OWNER TO ivela;

--
-- Name: sq_student_exercise; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_student_exercise
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_student_exercise OWNER TO ivela;

--
-- Name: sq_student_exercise; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_student_exercise OWNED BY student_exercise.id;


--
-- Name: system_user; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE system_user (
    id numeric(10,0) NOT NULL,
    email character varying(150) NOT NULL,
    social_number character varying(30) NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(40) NOT NULL,
    enabled boolean NOT NULL,
    profile numeric(10,0),
    last_unit_content numeric(10,0),
    created_at timestamp without time zone,
    authentication numeric(10,0)
);


ALTER TABLE ivela.system_user OWNER TO ivela;

--
-- Name: sq_system_user; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_system_user
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_system_user OWNER TO ivela;

--
-- Name: sq_system_user; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_system_user OWNED BY system_user.id;


--
-- Name: system_user_chat; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE system_user_chat (
    id numeric(10,0) NOT NULL,
    chat numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL
);


ALTER TABLE ivela.system_user_chat OWNER TO ivela;

--
-- Name: sq_system_user_chat; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_system_user_chat
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_system_user_chat OWNER TO ivela;

--
-- Name: sq_system_user_chat; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_system_user_chat OWNED BY system_user_chat.id;


--
-- Name: system_user_functionality; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE system_user_functionality (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    functionality numeric(10,0) NOT NULL
);


ALTER TABLE ivela.system_user_functionality OWNER TO ivela;

--
-- Name: sq_system_user_functionality; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_system_user_functionality
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_system_user_functionality OWNER TO ivela;

--
-- Name: sq_system_user_functionality; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_system_user_functionality OWNED BY system_user_functionality.id;


--
-- Name: system_user_placement_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE system_user_placement_question (
    id integer NOT NULL,
    placement_question integer NOT NULL,
    system_user numeric(10,0) NOT NULL
);


ALTER TABLE ivela.system_user_placement_question OWNER TO ivela;

--
-- Name: sq_system_user_placement_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_system_user_placement_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_system_user_placement_question OWNER TO ivela;

--
-- Name: sq_system_user_placement_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_system_user_placement_question OWNED BY system_user_placement_question.id;


--
-- Name: system_user_unit_status; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE system_user_unit_status (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    unit numeric(10,0) NOT NULL,
    status numeric(10,1) NOT NULL
);


ALTER TABLE ivela.system_user_unit_status OWNER TO ivela;

--
-- Name: sq_system_user_unit_status; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_system_user_unit_status
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_system_user_unit_status OWNER TO ivela;

--
-- Name: sq_system_user_unit_status; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_system_user_unit_status OWNED BY system_user_unit_status.id;


--
-- Name: topic; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE topic (
    id numeric(10,0) NOT NULL,
    forum numeric(10,0) NOT NULL,
    title character varying(150) NOT NULL,
    created_by numeric(10,0) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    description character varying(255),
    posts_count integer NOT NULL DEFAULT 0,
    last_post_id integer,
    last_post_date timestamp without time zone
);


ALTER TABLE ivela.topic OWNER TO ivela;

--
-- Name: sq_topic; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_topic
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_topic OWNER TO ivela;

--
-- Name: sq_topic; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_topic OWNED BY topic.id;


--
-- Name: transcript; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE transcript (
    id integer NOT NULL,
    grade numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    status integer,
    score numeric(10,2),
    average_exercise numeric(10,2),
    average_exam numeric(10,2),
    manual_score numeric(10,2)
);


ALTER TABLE ivela.transcript OWNER TO ivela;

--
-- Name: sq_transcript; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_transcript
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_transcript OWNER TO ivela;

--
-- Name: sq_transcript; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_transcript OWNED BY transcript.id;


--
-- Name: tutor; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE tutor (
    id numeric(10,0) NOT NULL,
    system_user numeric(10,0) NOT NULL,
    grade numeric(10,0) NOT NULL
);


ALTER TABLE ivela.tutor OWNER TO ivela;

--
-- Name: sq_tutor; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_tutor
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_tutor OWNER TO ivela;

--
-- Name: sq_tutor; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_tutor OWNED BY tutor.id;


--
-- Name: unit; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE unit (
    id numeric(10,0) NOT NULL,
    discipline numeric(10,0) NOT NULL,
    name character varying(100) NOT NULL,
    active boolean
);


ALTER TABLE ivela.unit OWNER TO ivela;

--
-- Name: sq_unit; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_unit
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_unit OWNER TO ivela;

--
-- Name: sq_unit; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_unit OWNED BY unit.id;


--
-- Name: unit_content; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE unit_content (
    id numeric(10,0) NOT NULL,
    order_n integer NOT NULL,
    unit numeric(10,0) NOT NULL,
    title character varying(255) NOT NULL,
    description text NOT NULL,
    type integer NOT NULL,
    width integer,
    height integer
);


ALTER TABLE ivela.unit_content OWNER TO ivela;

--
-- Name: sq_unit_content; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_unit_content
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_unit_content OWNER TO ivela;

--
-- Name: sq_unit_content; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_unit_content OWNED BY unit_content.id;


--
-- Name: user_voice; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE user_voice (
    id numeric(10,0) NOT NULL,
    name character varying(255)
);


ALTER TABLE ivela.user_voice OWNER TO ivela;

--
-- Name: sq_user_voice; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_user_voice
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_user_voice OWNER TO ivela;

--
-- Name: sq_user_voice; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_user_voice OWNED BY user_voice.id;


--
-- Name: voice_feedback; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE voice_feedback (
    mark integer,
    coment text,
    id integer NOT NULL
);


ALTER TABLE ivela.voice_feedback OWNER TO ivela;

--
-- Name: sq_voice_feedback; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_voice_feedback
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_voice_feedback OWNER TO ivela;

--
-- Name: sq_voice_feedback; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_voice_feedback OWNED BY voice_feedback.id;


--
-- Name: word_sentence_auditive_question; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE word_sentence_auditive_question (
    id numeric(10,0) NOT NULL,
    sentence numeric(10,0) NOT NULL,
    word character varying(25) NOT NULL,
    file character varying(50) NOT NULL,
    sequence integer DEFAULT 1 NOT NULL,
    chances integer NOT NULL
);


ALTER TABLE ivela.word_sentence_auditive_question OWNER TO ivela;

--
-- Name: sq_word_sentence_auditive_question; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_word_sentence_auditive_question
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_word_sentence_auditive_question OWNER TO ivela;

--
-- Name: sq_word_sentence_auditive_question; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_word_sentence_auditive_question OWNED BY word_sentence_auditive_question.id;


--
-- Name: word_voice; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE word_voice (
    id integer NOT NULL,
    user_voice numeric(10,0),
    word character varying(255),
    tries integer
);


ALTER TABLE ivela.word_voice OWNER TO ivela;

--
-- Name: sq_word_voice; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_word_voice
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_word_voice OWNER TO ivela;

--
-- Name: sq_word_voice; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_word_voice OWNED BY word_voice.id;


--
-- Name: ws_enabled; Type: TABLE; Schema: ivela; Owner: ivela; Tablespace: 
--

CREATE TABLE ws_enabled (
    id integer NOT NULL,
    ip character varying NOT NULL
);


ALTER TABLE ivela.ws_enabled OWNER TO ivela;

--
-- Name: sq_ws_enabled; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE sq_ws_enabled
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_ws_enabled OWNER TO ivela;

--
-- Name: sq_ws_enabled; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE sq_ws_enabled OWNED BY ws_enabled.id;


--
-- Name: system_user_authentication_seq; Type: SEQUENCE; Schema: ivela; Owner: ivela
--

CREATE SEQUENCE system_user_authentication_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.system_user_authentication_seq OWNER TO ivela;

--
-- Name: system_user_authentication_seq; Type: SEQUENCE OWNED BY; Schema: ivela; Owner: ivela
--

ALTER SEQUENCE system_user_authentication_seq OWNED BY system_user.authentication;


SET search_path = public, pg_catalog;

--
-- Name: _auth_user; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE _auth_user (
    username character varying(250) NOT NULL,
    userpass character varying(250) NOT NULL
);


ALTER TABLE public._auth_user OWNER TO ivela;

--
-- Name: _auth_userrole; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE _auth_userrole (
    username character varying(250) NOT NULL,
    role character varying(250) NOT NULL
);


ALTER TABLE public._auth_userrole OWNER TO ivela;

--
-- Name: application_settings_applicationsettingsid_seq; Type: SEQUENCE; Schema: public; Owner: ivela
--

CREATE SEQUENCE application_settings_applicationsettingsid_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.application_settings_applicationsettingsid_seq OWNER TO ivela;

--
-- Name: application_settings; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE application_settings (
    applicationsettingsid bigint DEFAULT nextval('application_settings_applicationsettingsid_seq'::regclass) NOT NULL,
    custompagetitle text,
    pluginworkpath text,
    pluginpackageextension text,
    calendarrefreshtimems integer,
    plugincleanupenabled boolean,
    configurationusername text,
    configurationpassword text
);


ALTER TABLE public.application_settings OWNER TO ivela;

--
-- Name: application_settings_plugin_paths; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE application_settings_plugin_paths (
    applicationsettingsid bigint NOT NULL,
    plugin_path text
);


ALTER TABLE public.application_settings_plugin_paths OWNER TO ivela;

--
-- Name: application_settings_resource_paths; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE application_settings_resource_paths (
    applicationsettingsid bigint NOT NULL,
    resource_path text
);


ALTER TABLE public.application_settings_resource_paths OWNER TO ivela;

--
-- Name: calendar_calendarid_seq; Type: SEQUENCE; Schema: public; Owner: ivela
--

CREATE SEQUENCE calendar_calendarid_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.calendar_calendarid_seq OWNER TO ivela;

--
-- Name: calendar; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE calendar (
    calendarid bigint DEFAULT nextval('calendar_calendarid_seq'::regclass) NOT NULL,
    name text NOT NULL,
    type text NOT NULL,
    url text NOT NULL,
    username text,
    password text,
    visible boolean,
    offsetfrom bigint,
    offsetto bigint,
    lastrefreshtimestamp bigint,
    usrif character varying(255) NOT NULL
);


ALTER TABLE public.calendar OWNER TO ivela;

--
-- Name: event_eventid_seq; Type: SEQUENCE; Schema: public; Owner: ivela
--

CREATE SEQUENCE event_eventid_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.event_eventid_seq OWNER TO ivela;

--
-- Name: event; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event (
    eventid bigint DEFAULT nextval('event_eventid_seq'::regclass) NOT NULL,
    calendarid bigint NOT NULL,
    clazz text,
    description text,
    geo text,
    location text,
    organizer text,
    status text,
    summary text,
    transp text,
    uid text,
    url text,
    allday boolean,
    created timestamp without time zone,
    dtstart timestamp without time zone,
    lastmod timestamp without time zone,
    dtstamp timestamp without time zone,
    seq integer,
    dtend timestamp without time zone,
    duration character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE public.event OWNER TO ivela;

--
-- Name: event_attach; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_attach (
    eventid bigint NOT NULL,
    attach text
);


ALTER TABLE public.event_attach OWNER TO ivela;

--
-- Name: event_attendee; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_attendee (
    eventid bigint NOT NULL,
    attendee text
);


ALTER TABLE public.event_attendee OWNER TO ivela;

--
-- Name: event_categories; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_categories (
    eventid bigint NOT NULL,
    categories text
);


ALTER TABLE public.event_categories OWNER TO ivela;

--
-- Name: event_comment; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_comment (
    eventid bigint NOT NULL,
    comment text
);


ALTER TABLE public.event_comment OWNER TO ivela;

--
-- Name: event_contact; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_contact (
    eventid bigint NOT NULL,
    contact text
);


ALTER TABLE public.event_contact OWNER TO ivela;

--
-- Name: event_exdate; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_exdate (
    eventid bigint NOT NULL,
    exdate timestamp without time zone
);


ALTER TABLE public.event_exdate OWNER TO ivela;

--
-- Name: event_exrule; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_exrule (
    eventid bigint NOT NULL,
    exrule text
);


ALTER TABLE public.event_exrule OWNER TO ivela;

--
-- Name: event_rdate; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_rdate (
    eventid bigint NOT NULL,
    rdate timestamp without time zone
);


ALTER TABLE public.event_rdate OWNER TO ivela;

--
-- Name: event_related; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_related (
    eventid bigint NOT NULL,
    related text
);


ALTER TABLE public.event_related OWNER TO ivela;

--
-- Name: event_resources; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_resources (
    eventid bigint NOT NULL,
    resources text
);


ALTER TABLE public.event_resources OWNER TO ivela;

--
-- Name: event_rrule; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_rrule (
    eventid bigint NOT NULL,
    rrule text
);


ALTER TABLE public.event_rrule OWNER TO ivela;

--
-- Name: event_rstatus; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_rstatus (
    eventid bigint NOT NULL,
    rstatus text
);


ALTER TABLE public.event_rstatus OWNER TO ivela;

--
-- Name: event_xprops; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE event_xprops (
    id bigint NOT NULL,
    xprop_value text,
    xprop_name character varying(255) NOT NULL
);


ALTER TABLE public.event_xprops OWNER TO ivela;

--
-- Name: options_id_seq; Type: SEQUENCE; Schema: public; Owner: ivela
--

CREATE SEQUENCE options_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.options_id_seq OWNER TO ivela;

--
-- Name: options; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE options (
    id bigint DEFAULT nextval('options_id_seq'::regclass) NOT NULL,
    settings bigint,
    name character varying(255) DEFAULT NULL::character varying,
    value bytea,
    settings_id bigint NOT NULL
);


ALTER TABLE public.options OWNER TO ivela;

--
-- Name: plugin_settings; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE plugin_settings (
    id bigint NOT NULL,
    pluginclass character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE public.plugin_settings OWNER TO ivela;

--
-- Name: settings_id_seq; Type: SEQUENCE; Schema: public; Owner: ivela
--

CREATE SEQUENCE settings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.settings_id_seq OWNER TO ivela;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE settings (
    id bigint DEFAULT nextval('settings_id_seq'::regclass) NOT NULL
);


ALTER TABLE public.settings OWNER TO ivela;

--
-- Name: user_plugin_settings; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE user_plugin_settings (
    id bigint NOT NULL,
    pluginclass character varying(255) DEFAULT NULL::character varying,
    usuario character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE public.user_plugin_settings OWNER TO ivela;

--
-- Name: user_settings; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE user_settings (
    id bigint NOT NULL,
    defaultcalendarview integer,
    firstdayofweek integer,
    numberofagendadays integer,
    dateformat character varying(255) DEFAULT NULL::character varying,
    timeformat character varying(255) DEFAULT NULL::character varying,
    usuario character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE public.user_settings OWNER TO ivela;

--
-- Name: user_settings_id_seq; Type: SEQUENCE; Schema: public; Owner: ivela
--

CREATE SEQUENCE user_settings_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.user_settings_id_seq OWNER TO ivela;

--
-- Name: user_table; Type: TABLE; Schema: public; Owner: ivela; Tablespace: 
--

CREATE TABLE user_table (
    usrif character varying(255) NOT NULL,
    firstname text,
    lastnameprefix text,
    lastname text,
    birthdate date
);


ALTER TABLE public.user_table OWNER TO ivela;

SET search_path = ivela, pg_catalog;

--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE access ALTER COLUMN id SET DEFAULT nextval('sq_access'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE address ALTER COLUMN id SET DEFAULT nextval('sq_address'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE address_type ALTER COLUMN id SET DEFAULT nextval('sq_address_type'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE answer_auditive_question_student_exercise ALTER COLUMN id SET DEFAULT nextval('sq_answer_auditive_question_student_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE answer_external_question_student_exercise ALTER COLUMN id SET DEFAULT nextval('sq_answer_external_question_student_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE answer_student_exam ALTER COLUMN id SET DEFAULT nextval('sq_answer_student_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE answer_student_exercise ALTER COLUMN id SET DEFAULT nextval('sq_answer_student_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE answer_subjective_question_student_exam ALTER COLUMN id SET DEFAULT nextval('sq_answer_subjective_question_student_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE answer_subjective_question_student_exercise ALTER COLUMN id SET DEFAULT nextval('sq_answer_subjective_question_student_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE attach_post ALTER COLUMN id SET DEFAULT nextval('sq_attach_post'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE auditive_question ALTER COLUMN id SET DEFAULT nextval('sq_auditive_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE authentication ALTER COLUMN id SET DEFAULT nextval('sq_authentication'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE authentication_functionality ALTER COLUMN id SET DEFAULT nextval('sq_authentication_functionality'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE challenge_items ALTER COLUMN id SET DEFAULT nextval('sq_challenge_items'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE challenger_result ALTER COLUMN id SET DEFAULT nextval('sq_challenger_result'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE chat ALTER COLUMN id SET DEFAULT nextval('sq_chat'::regclass);



--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE course ALTER COLUMN id SET DEFAULT nextval('sq_course'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE course_requisite ALTER COLUMN id SET DEFAULT nextval('sq_course_requisite'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE dictionary ALTER COLUMN id SET DEFAULT nextval('sq_dictionary'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE discipline ALTER COLUMN id SET DEFAULT nextval('sq_discipline'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE enrollment ALTER COLUMN id SET DEFAULT nextval('sq_enrollment'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE exam ALTER COLUMN id SET DEFAULT nextval('sq_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE exam_requisite ALTER COLUMN id SET DEFAULT nextval('sq_exam_requisite'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE exercise ALTER COLUMN id SET DEFAULT nextval('sq_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE exercise_requisite ALTER COLUMN id SET DEFAULT nextval('sq_exercise_requisite'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE external_answer_student_exam ALTER COLUMN id SET DEFAULT nextval('sq_external_answer_student_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE external_params ALTER COLUMN id SET DEFAULT nextval('sq_external_params'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE external_params_external_question ALTER COLUMN id SET DEFAULT nextval('sq_external_params_external_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE external_question ALTER COLUMN id SET DEFAULT nextval('sq_external_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE faq ALTER COLUMN id SET DEFAULT nextval('sq_faq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE file ALTER COLUMN id SET DEFAULT nextval('sq_file'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE finished_exam ALTER COLUMN id SET DEFAULT nextval('sq_finished_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE finished_exercise ALTER COLUMN id SET DEFAULT nextval('sq_finished_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE finished_unit_content ALTER COLUMN id SET DEFAULT nextval('sq_finished_unit_content'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE forum ALTER COLUMN id SET DEFAULT nextval('sq_forum'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE functionality ALTER COLUMN id SET DEFAULT nextval('sq_functionality'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE grade ALTER COLUMN id SET DEFAULT nextval('sq_grade'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE grade_note ALTER COLUMN id SET DEFAULT nextval('sq_grade_note'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE history ALTER COLUMN id SET DEFAULT nextval('sq_history'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE history_params ALTER COLUMN id SET DEFAULT nextval('sq_history_params'::regclass);



--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE language ALTER COLUMN id SET DEFAULT nextval('sq_language'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE language_system_user ALTER COLUMN id SET DEFAULT nextval('sq_language_system_user'::regclass);



--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE message ALTER COLUMN id SET DEFAULT nextval('sq_message'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE news_flash ALTER COLUMN id SET DEFAULT nextval('sq_news_flash'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE note ALTER COLUMN id SET DEFAULT nextval('sq_note'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE npd_phrase ALTER COLUMN id SET DEFAULT nextval('sq_npd_phrase'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE npd_user ALTER COLUMN id SET DEFAULT nextval('sq_npd_user'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE objective_answer ALTER COLUMN id SET DEFAULT nextval('sq_objective_answer'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE objective_answer_student_exam ALTER COLUMN id SET DEFAULT nextval('sq_objective_answer_student_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE objective_answer_student_exercise ALTER COLUMN id SET DEFAULT nextval('sq_objective_answer_student_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE objective_question ALTER COLUMN id SET DEFAULT nextval('sq_objective_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE phone ALTER COLUMN id SET DEFAULT nextval('sq_phone'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE placement_answer ALTER COLUMN id SET DEFAULT nextval('sq_placement_answer'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE placement_question ALTER COLUMN id SET DEFAULT nextval('sq_placement_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE post ALTER COLUMN id SET DEFAULT nextval('sq_post'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE professor ALTER COLUMN id SET DEFAULT nextval('sq_professor'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE profile ALTER COLUMN id SET DEFAULT nextval('sq_profile'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE question ALTER COLUMN id SET DEFAULT nextval('sq_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE question_exam ALTER COLUMN id SET DEFAULT nextval('sq_question_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE question_exercise ALTER COLUMN id SET DEFAULT nextval('sq_question_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE question_question_text ALTER COLUMN id SET DEFAULT nextval('sq_question_question_text_id'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE question_text ALTER COLUMN id SET DEFAULT nextval('sq_question_text'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE sentence_auditive_question ALTER COLUMN id SET DEFAULT nextval('sq_sentence_auditive_question'::regclass);



--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE student_exam ALTER COLUMN id SET DEFAULT nextval('sq_student_exam'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE student_exercise ALTER COLUMN id SET DEFAULT nextval('sq_student_exercise'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE system_user ALTER COLUMN id SET DEFAULT nextval('sq_system_user'::regclass);


--
-- Name: authentication; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE system_user ALTER COLUMN authentication SET DEFAULT nextval('system_user_authentication_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE system_user_chat ALTER COLUMN id SET DEFAULT nextval('sq_system_user_chat'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE system_user_functionality ALTER COLUMN id SET DEFAULT nextval('sq_system_user_functionality'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE system_user_placement_question ALTER COLUMN id SET DEFAULT nextval('sq_system_user_placement_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE system_user_unit_status ALTER COLUMN id SET DEFAULT nextval('sq_system_user_unit_status'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE topic ALTER COLUMN id SET DEFAULT nextval('sq_topic'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE transcript ALTER COLUMN id SET DEFAULT nextval('sq_transcript'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE tutor ALTER COLUMN id SET DEFAULT nextval('sq_tutor'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE unit ALTER COLUMN id SET DEFAULT nextval('sq_unit'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE unit_content ALTER COLUMN id SET DEFAULT nextval('sq_unit_content'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE user_voice ALTER COLUMN id SET DEFAULT nextval('sq_user_voice'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE voice_feedback ALTER COLUMN id SET DEFAULT nextval('sq_voice_feedback'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE word_sentence_auditive_question ALTER COLUMN id SET DEFAULT nextval('sq_word_sentence_auditive_question'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE word_voice ALTER COLUMN id SET DEFAULT nextval('sq_word_voice'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: ivela; Owner: ivela
--

ALTER TABLE ws_enabled ALTER COLUMN id SET DEFAULT nextval('sq_ws_enabled'::regclass);


--
-- Name: access_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY access
    ADD CONSTRAINT access_pk PRIMARY KEY (id);


--
-- Name: address_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pk PRIMARY KEY (id);


--
-- Name: address_type_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY address_type
    ADD CONSTRAINT address_type_pk PRIMARY KEY (id);


--
-- Name: attach_post_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY attach_post
    ADD CONSTRAINT attach_post_pk PRIMARY KEY (id);


--
-- Name: auditive_question_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY auditive_question
    ADD CONSTRAINT auditive_question_pk PRIMARY KEY (id);


--
-- Name: challenge_items_name_key; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY challenge_items
    ADD CONSTRAINT challenge_items_name_key UNIQUE (name);


--
-- Name: challenge_items_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY challenge_items
    ADD CONSTRAINT challenge_items_pkey PRIMARY KEY (id);


--
-- Name: challenge_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY challenge
    ADD CONSTRAINT challenge_pkey PRIMARY KEY (id);


--
-- Name: challenger_result_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY challenger_result
    ADD CONSTRAINT challenger_result_pkey PRIMARY KEY (id);

--
-- Name: dictionary_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY dictionary
    ADD CONSTRAINT dictionary_pkey PRIMARY KEY (id);


--
-- Name: enrollment_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY enrollment
    ADD CONSTRAINT enrollment_pk PRIMARY KEY (id);


--
-- Name: exam_requisite_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY exam_requisite
    ADD CONSTRAINT exam_requisite_pkey PRIMARY KEY (id);


--
-- Name: exercise_requisite_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY exercise_requisite
    ADD CONSTRAINT exercise_requisite_pkey PRIMARY KEY (id);


--
-- Name: finished_exam_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY finished_exam
    ADD CONSTRAINT finished_exam_pkey PRIMARY KEY (id);


--
-- Name: finished_exercise_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY finished_exercise
    ADD CONSTRAINT finished_exercise_pkey PRIMARY KEY (id);


--
-- Name: finished_unit_content_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY finished_unit_content
    ADD CONSTRAINT finished_unit_content_pkey PRIMARY KEY (id);


--
-- Name: grade_note_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY grade_note
    ADD CONSTRAINT grade_note_pk PRIMARY KEY (id);


--
-- Name: grade_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT grade_pk PRIMARY KEY (id);


--
-- Name: history_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY history
    ADD CONSTRAINT history_pk PRIMARY KEY (id);


--
-- Name: news_flash_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY news_flash
    ADD CONSTRAINT news_flash_pkey PRIMARY KEY (id);


--
-- Name: npd_phrase_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY npd_phrase
    ADD CONSTRAINT npd_phrase_pkey PRIMARY KEY (id);


--
-- Name: npd_user_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY npd_user
    ADD CONSTRAINT npd_user_pkey PRIMARY KEY (id);


--
-- Name: phone_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY phone
    ADD CONSTRAINT phone_pk PRIMARY KEY (id);


--
-- Name: pk_answer_auditive_question_student_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_auditive_question_student_exam
    ADD CONSTRAINT pk_answer_auditive_question_student_exam PRIMARY KEY (id);


--
-- Name: pk_answer_auditive_question_student_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_auditive_question_student_exercise
    ADD CONSTRAINT pk_answer_auditive_question_student_exercise PRIMARY KEY (id);


--
-- Name: pk_answer_external_question_student_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_external_question_student_exercise
    ADD CONSTRAINT pk_answer_external_question_student_exercise PRIMARY KEY (id);


--
-- Name: pk_answer_student_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_student_exam
    ADD CONSTRAINT pk_answer_student_exam PRIMARY KEY (id);


--
-- Name: pk_answer_student_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_student_exercise
    ADD CONSTRAINT pk_answer_student_exercise PRIMARY KEY (id);


--
-- Name: pk_answer_subjective_question_student_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_subjective_question_student_exam
    ADD CONSTRAINT pk_answer_subjective_question_student_exam PRIMARY KEY (id);


--
-- Name: pk_answer_subjective_question_student_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY answer_subjective_question_student_exercise
    ADD CONSTRAINT pk_answer_subjective_question_student_exercise PRIMARY KEY (id);


--
-- Name: pk_authentication; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY authentication
    ADD CONSTRAINT pk_authentication PRIMARY KEY (id);


--
-- Name: pk_authentication_functionality; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY authentication_functionality
    ADD CONSTRAINT pk_authentication_functionality PRIMARY KEY (id);


--
-- Name: pk_chat; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY chat
    ADD CONSTRAINT pk_chat PRIMARY KEY (id);


--
-- Name: pk_course; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY course
    ADD CONSTRAINT pk_course PRIMARY KEY (id);


--
-- Name: pk_course_requisite; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY course_requisite
    ADD CONSTRAINT pk_course_requisite PRIMARY KEY (id);


--
-- Name: pk_discipline; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY discipline
    ADD CONSTRAINT pk_discipline PRIMARY KEY (id);


--
-- Name: pk_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY exam
    ADD CONSTRAINT pk_exam PRIMARY KEY (id);


--
-- Name: pk_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY exercise
    ADD CONSTRAINT pk_exercise PRIMARY KEY (id);


--
-- Name: pk_external_answer_student_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY external_answer_student_exam
    ADD CONSTRAINT pk_external_answer_student_exam PRIMARY KEY (id);


--
-- Name: pk_external_params; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY external_params
    ADD CONSTRAINT pk_external_params PRIMARY KEY (id);


--
-- Name: pk_external_params_external_question; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY external_params_external_question
    ADD CONSTRAINT pk_external_params_external_question PRIMARY KEY (id);


--
-- Name: pk_external_question; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY external_question
    ADD CONSTRAINT pk_external_question PRIMARY KEY (id);


--
-- Name: pk_faq; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY faq
    ADD CONSTRAINT pk_faq PRIMARY KEY (id);


--
-- Name: pk_file; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY file
    ADD CONSTRAINT pk_file PRIMARY KEY (id);


--
-- Name: pk_forum; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY forum
    ADD CONSTRAINT pk_forum PRIMARY KEY (id);


--
-- Name: pk_functionality; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY functionality
    ADD CONSTRAINT pk_functionality PRIMARY KEY (id);


--
-- Name: pk_language; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY language
    ADD CONSTRAINT pk_language PRIMARY KEY (id);


--
-- Name: pk_language_system_user; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY language_system_user
    ADD CONSTRAINT pk_language_system_user PRIMARY KEY (id);


--
-- Name: pk_message; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT pk_message PRIMARY KEY (id);


--
-- Name: pk_note; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY note
    ADD CONSTRAINT pk_note PRIMARY KEY (id);


--
-- Name: pk_objective_answer; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY objective_answer
    ADD CONSTRAINT pk_objective_answer PRIMARY KEY (id);


--
-- Name: pk_objective_answer_student_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY objective_answer_student_exam
    ADD CONSTRAINT pk_objective_answer_student_exam PRIMARY KEY (id);


--
-- Name: pk_objective_answer_student_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY objective_answer_student_exercise
    ADD CONSTRAINT pk_objective_answer_student_exercise PRIMARY KEY (id);


--
-- Name: pk_objective_question; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY objective_question
    ADD CONSTRAINT pk_objective_question PRIMARY KEY (id);


--
-- Name: pk_post; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY post
    ADD CONSTRAINT pk_post PRIMARY KEY (id);


--
-- Name: pk_professor; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY professor
    ADD CONSTRAINT pk_professor PRIMARY KEY (id);


--
-- Name: pk_question; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY question
    ADD CONSTRAINT pk_question PRIMARY KEY (id);


--
-- Name: pk_question_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY question_exam
    ADD CONSTRAINT pk_question_exam PRIMARY KEY (id);


--
-- Name: pk_question_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY question_exercise
    ADD CONSTRAINT pk_question_exercise PRIMARY KEY (id);


--
-- Name: pk_question_question_text; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY question_question_text
    ADD CONSTRAINT pk_question_question_text PRIMARY KEY (id);


--
-- Name: pk_question_text; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY question_text
    ADD CONSTRAINT pk_question_text PRIMARY KEY (id);


--
-- Name: pk_student_exam; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY student_exam
    ADD CONSTRAINT pk_student_exam PRIMARY KEY (id);


--
-- Name: pk_student_exercise; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY student_exercise
    ADD CONSTRAINT pk_student_exercise PRIMARY KEY (id);


--
-- Name: pk_system_user; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY system_user
    ADD CONSTRAINT pk_system_user PRIMARY KEY (id);


--
-- Name: pk_system_user_chat; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY system_user_chat
    ADD CONSTRAINT pk_system_user_chat PRIMARY KEY (id);


--
-- Name: pk_system_user_functionality; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY system_user_functionality
    ADD CONSTRAINT pk_system_user_functionality PRIMARY KEY (id);


--
-- Name: pk_system_user_unit_status; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY system_user_unit_status
    ADD CONSTRAINT pk_system_user_unit_status PRIMARY KEY (id);


--
-- Name: pk_topic; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY topic
    ADD CONSTRAINT pk_topic PRIMARY KEY (id);


--
-- Name: pk_tutor; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY tutor
    ADD CONSTRAINT pk_tutor PRIMARY KEY (id);


--
-- Name: pk_unit; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY unit
    ADD CONSTRAINT pk_unit PRIMARY KEY (id);


--
-- Name: placement_answer_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY placement_answer
    ADD CONSTRAINT placement_answer_pk PRIMARY KEY (id);


--
-- Name: placement_question_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY placement_question
    ADD CONSTRAINT placement_question_pk PRIMARY KEY (id);


--
-- Name: profile_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_pk PRIMARY KEY (id);


--
-- Name: sentence_auditive_question_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY sentence_auditive_question
    ADD CONSTRAINT sentence_auditive_question_pk PRIMARY KEY (id);



--
-- Name: system_user_placement_question_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY system_user_placement_question
    ADD CONSTRAINT system_user_placement_question_pk PRIMARY KEY (id);


--
-- Name: transcript_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY transcript
    ADD CONSTRAINT transcript_pkey PRIMARY KEY (id);


--
-- Name: unit_content_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY unit_content
    ADD CONSTRAINT unit_content_pk PRIMARY KEY (id);


--
-- Name: user_voice_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY user_voice
    ADD CONSTRAINT user_voice_pkey PRIMARY KEY (id);


--
-- Name: word_sentence_auditive_question_pk; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY word_sentence_auditive_question
    ADD CONSTRAINT word_sentence_auditive_question_pk PRIMARY KEY (id);


--
-- Name: word_voice_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY word_voice
    ADD CONSTRAINT word_voice_pkey PRIMARY KEY (id);


--
-- Name: ws_enabled_ip_key; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY ws_enabled
    ADD CONSTRAINT ws_enabled_ip_key UNIQUE (ip);


--
-- Name: ws_enabled_pkey; Type: CONSTRAINT; Schema: ivela; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY ws_enabled
    ADD CONSTRAINT ws_enabled_pkey PRIMARY KEY (id);


SET search_path = public, pg_catalog;

--
-- Name: _auth_user_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY _auth_user
    ADD CONSTRAINT _auth_user_pkey PRIMARY KEY (username);


--
-- Name: _auth_userrole_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY _auth_userrole
    ADD CONSTRAINT _auth_userrole_pkey PRIMARY KEY (username, role);


--
-- Name: application_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY application_settings
    ADD CONSTRAINT application_settings_pkey PRIMARY KEY (applicationsettingsid);


--
-- Name: calendar_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY calendar
    ADD CONSTRAINT calendar_pkey PRIMARY KEY (calendarid);


--
-- Name: event_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_pkey PRIMARY KEY (eventid);


--
-- Name: event_xprops_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY event_xprops
    ADD CONSTRAINT event_xprops_pkey PRIMARY KEY (id, xprop_name);


--
-- Name: options_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY options
    ADD CONSTRAINT options_pkey PRIMARY KEY (id);


--
-- Name: plugin_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY plugin_settings
    ADD CONSTRAINT plugin_settings_pkey PRIMARY KEY (id);


--
-- Name: settings_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- Name: user_plugin_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY user_plugin_settings
    ADD CONSTRAINT user_plugin_settings_pkey PRIMARY KEY (id);


--
-- Name: user_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY user_settings
    ADD CONSTRAINT user_settings_pkey PRIMARY KEY (id);


--
-- Name: user_table_pkey; Type: CONSTRAINT; Schema: public; Owner: ivela; Tablespace: 
--

ALTER TABLE ONLY user_table
    ADD CONSTRAINT user_table_pkey PRIMARY KEY (usrif);


--
-- Name: application_settings_plugin_paths_applicationsettingsid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX application_settings_plugin_paths_applicationsettingsid_idx ON application_settings_plugin_paths USING btree (applicationsettingsid);


--
-- Name: application_settings_resource_paths_applicationsettingsid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX application_settings_resource_paths_applicationsettingsid_idx ON application_settings_resource_paths USING btree (applicationsettingsid);


--
-- Name: calendar_userid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX calendar_userid_idx ON calendar USING btree (usrif);


--
-- Name: event_attach_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_attach_eventid_idx ON event_attach USING btree (eventid);


--
-- Name: event_attendee_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_attendee_eventid_idx ON event_attendee USING btree (eventid);


--
-- Name: event_calendarid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_calendarid_idx ON event USING btree (calendarid);


--
-- Name: event_categories_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_categories_eventid_idx ON event_categories USING btree (eventid);


--
-- Name: event_comment_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_comment_eventid_idx ON event_comment USING btree (eventid);


--
-- Name: event_contact_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_contact_eventid_idx ON event_contact USING btree (eventid);


--
-- Name: event_exdate_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_exdate_eventid_idx ON event_exdate USING btree (eventid);


--
-- Name: event_exrule_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_exrule_eventid_idx ON event_exrule USING btree (eventid);


--
-- Name: event_rdate_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_rdate_eventid_idx ON event_rdate USING btree (eventid);


--
-- Name: event_related_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_related_eventid_idx ON event_related USING btree (eventid);


--
-- Name: event_resources_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_resources_eventid_idx ON event_resources USING btree (eventid);


--
-- Name: event_rrule_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_rrule_eventid_idx ON event_rrule USING btree (eventid);


--
-- Name: event_rstatus_eventid_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_rstatus_eventid_idx ON event_rstatus USING btree (eventid);


--
-- Name: event_xprops_id_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX event_xprops_id_idx ON event_xprops USING btree (id);


--
-- Name: options_settings_id_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX options_settings_id_idx ON options USING btree (settings_id);


--
-- Name: options_settings_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX options_settings_idx ON options USING btree (settings);


--
-- Name: plugin_settings_id_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX plugin_settings_id_idx ON plugin_settings USING btree (id);


--
-- Name: user_plugin_settings_id_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX user_plugin_settings_id_idx ON user_plugin_settings USING btree (id);


--
-- Name: user_plugin_settings_user_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX user_plugin_settings_user_idx ON user_plugin_settings USING btree (usuario);


--
-- Name: user_settings_id_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX user_settings_id_idx ON user_settings USING btree (id);


--
-- Name: user_settings_user_idx; Type: INDEX; Schema: public; Owner: ivela; Tablespace: 
--

CREATE INDEX user_settings_user_idx ON user_settings USING btree (usuario);


SET search_path = ivela, pg_catalog;

--
-- Name: address_type_address_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_type_address_fk FOREIGN KEY (address_type) REFERENCES address_type(id);


--
-- Name: answer_student_exam_answer_auditive_question_student_exam_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_auditive_question_student_exam
    ADD CONSTRAINT answer_student_exam_answer_auditive_question_student_exam_fk FOREIGN KEY (answer_student_exam) REFERENCES answer_student_exam(id);


--
-- Name: answer_student_exam_answer_subjective_question_student_exam_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_subjective_question_student_exam
    ADD CONSTRAINT answer_student_exam_answer_subjective_question_student_exam_fk FOREIGN KEY (answer_student_exam) REFERENCES answer_student_exam(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: answer_student_exam_external_answer_student_exam_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY external_answer_student_exam
    ADD CONSTRAINT answer_student_exam_external_answer_student_exam_fk FOREIGN KEY (answer_student_exam) REFERENCES answer_student_exam(id);


--
-- Name: answer_student_exam_objective_answer_student_exam_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_answer_student_exam
    ADD CONSTRAINT "answer_student_exam_objective_answer_student_exam_fk " FOREIGN KEY (answer_student_exam) REFERENCES answer_student_exam(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: answer_student_exercise_answer_auditive_question_student_exe66; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_auditive_question_student_exercise
    ADD CONSTRAINT answer_student_exercise_answer_auditive_question_student_exe66 FOREIGN KEY (answer_student_exercise) REFERENCES answer_student_exercise(id);


--
-- Name: answer_student_exercise_answer_external_question_student_exe908; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_external_question_student_exercise
    ADD CONSTRAINT answer_student_exercise_answer_external_question_student_exe908 FOREIGN KEY (answer_student_exercise) REFERENCES answer_student_exercise(id);


--
-- Name: answer_student_exercise_answer_subjective_question_student_e971; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_subjective_question_student_exercise
    ADD CONSTRAINT answer_student_exercise_answer_subjective_question_student_e971 FOREIGN KEY (answer_student_exercise) REFERENCES answer_student_exercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: answer_student_exercise_objective_answer_student_exercise_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_answer_student_exercise
    ADD CONSTRAINT "answer_student_exercise_objective_answer_student_exercise_fk " FOREIGN KEY (answer_student_exercise) REFERENCES answer_student_exercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: auditive_question_sentence_auditive_question_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY sentence_auditive_question
    ADD CONSTRAINT auditive_question_sentence_auditive_question_fk FOREIGN KEY (question) REFERENCES auditive_question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: authentication_authentication_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY authentication_functionality
    ADD CONSTRAINT authentication_authentication_fk FOREIGN KEY (authentication) REFERENCES authentication(id);


--
-- Name: course_discipline_fk 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY discipline
    ADD CONSTRAINT "course_discipline_fk 	" FOREIGN KEY (course) REFERENCES course(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: course_grade_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT course_grade_fk FOREIGN KEY (course) REFERENCES course(id);


--
-- Name: discipline_unit_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY unit
    ADD CONSTRAINT discipline_unit_fk FOREIGN KEY (discipline) REFERENCES discipline(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: english_placement_answer_english_placement_question_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY placement_question
    ADD CONSTRAINT english_placement_answer_english_placement_question_fk FOREIGN KEY (correct_answer) REFERENCES placement_answer(id);


--
-- Name: english_placement_question_english_placement_answer_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY placement_answer
    ADD CONSTRAINT english_placement_question_english_placement_answer_fk FOREIGN KEY (question) REFERENCES placement_question(id);



--
-- Name: exam_exam_student_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY student_exam
    ADD CONSTRAINT "exam_exam_student_fk " FOREIGN KEY (exam) REFERENCES exam(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: external_params_external_params_external_question_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY external_params_external_question
    ADD CONSTRAINT "external_params_external_params_external_question_fk " FOREIGN KEY (external_params) REFERENCES external_params(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: external_question_external_params_external_question_fk 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY external_params_external_question
    ADD CONSTRAINT "external_question_external_params_external_question_fk 	" FOREIGN KEY (external_question) REFERENCES external_question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_answer_objective_question; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_question
    ADD CONSTRAINT fk_answer_objective_question FOREIGN KEY (correct_answer) REFERENCES objective_answer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_attach_post_file; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY attach_post
    ADD CONSTRAINT fk_attach_post_file FOREIGN KEY (file) REFERENCES file(id);


--
-- Name: fk_attach_post_post; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY attach_post
    ADD CONSTRAINT fk_attach_post_post FOREIGN KEY (post) REFERENCES post(id);


--
-- Name: fk_authentication; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user
    ADD CONSTRAINT fk_authentication FOREIGN KEY (authentication) REFERENCES authentication(id);


--
-- Name: fk_course; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenge_items
    ADD CONSTRAINT fk_course FOREIGN KEY (course) REFERENCES course(id);


--
-- Name: fk_course; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY file
    ADD CONSTRAINT fk_course FOREIGN KEY (course) REFERENCES course(id);


--
-- Name: fk_course; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_unit_content
    ADD CONSTRAINT fk_course FOREIGN KEY (course) REFERENCES course(id);


--
-- Name: fk_course_requisite_course; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY course_requisite
    ADD CONSTRAINT fk_course_requisite_course FOREIGN KEY (course) REFERENCES course(id);


--
-- Name: fk_course_requisite_requisite; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY course_requisite
    ADD CONSTRAINT fk_course_requisite_requisite FOREIGN KEY (requisite) REFERENCES course(id);


--
-- Name: fk_created_by ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exam
    ADD CONSTRAINT "fk_created_by " FOREIGN KEY (created_by) REFERENCES system_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_discipline; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenge_items
    ADD CONSTRAINT fk_discipline FOREIGN KEY (discipline) REFERENCES discipline(id);


--
-- Name: fk_exam; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exam_requisite
    ADD CONSTRAINT fk_exam FOREIGN KEY (exam) REFERENCES exam(id);


--
-- Name: fk_exam; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_exam
    ADD CONSTRAINT fk_exam FOREIGN KEY (exam) REFERENCES exam(id);


--
-- Name: fk_exam_question_exam ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question_exam
    ADD CONSTRAINT "fk_exam_question_exam " FOREIGN KEY (exam) REFERENCES exam(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_exercise; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exercise_requisite
    ADD CONSTRAINT fk_exercise FOREIGN KEY (exercise) REFERENCES exercise(id);


--
-- Name: fk_exercise; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_exercise
    ADD CONSTRAINT fk_exercise FOREIGN KEY (exercise) REFERENCES exercise(id);


--
-- Name: fk_exercise 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY student_exercise
    ADD CONSTRAINT "fk_exercise 	" FOREIGN KEY (exercise) REFERENCES exercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_exercise_question_exercise; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question_exercise
    ADD CONSTRAINT fk_exercise_question_exercise FOREIGN KEY (exercise) REFERENCES exercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_file_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY file
    ADD CONSTRAINT fk_file_system_user FOREIGN KEY (sent_by) REFERENCES system_user(id);


--
-- Name: fk_grade; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenger_result
    ADD CONSTRAINT fk_grade FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: fk_grade; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY transcript
    ADD CONSTRAINT fk_grade FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: fk_grade; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY student_exercise
    ADD CONSTRAINT fk_grade FOREIGN KEY (grade) REFERENCES grade(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_grade ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY student_exam
    ADD CONSTRAINT "fk_grade " FOREIGN KEY (grade) REFERENCES grade(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_npd_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY npd_phrase
    ADD CONSTRAINT fk_npd_user FOREIGN KEY (npd_user) REFERENCES npd_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_post_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY post
    ADD CONSTRAINT fk_post_system_user FOREIGN KEY (created_by) REFERENCES system_user(id);


--
-- Name: fk_post_topic; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY post
    ADD CONSTRAINT fk_post_topic FOREIGN KEY (topic) REFERENCES topic(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_question_answer; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_answer
    ADD CONSTRAINT fk_question_answer FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_question_objective_question 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_question
    ADD CONSTRAINT "fk_question_objective_question 	" FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_question_question_exam ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question_exam
    ADD CONSTRAINT "fk_question_question_exam " FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_question_question_exercise; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question_exercise
    ADD CONSTRAINT fk_question_question_exercise FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_receiver; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY news_flash
    ADD CONSTRAINT fk_receiver FOREIGN KEY (receiver) REFERENCES system_user(id);


--
-- Name: fk_requisite; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exam_requisite
    ADD CONSTRAINT fk_requisite FOREIGN KEY (requisite) REFERENCES exam(id);


--
-- Name: fk_requisite; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exercise_requisite
    ADD CONSTRAINT fk_requisite FOREIGN KEY (requisite) REFERENCES exercise(id);


--
-- Name: fk_sender; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY news_flash
    ADD CONSTRAINT fk_sender FOREIGN KEY (sender) REFERENCES system_user(id);


--
-- Name: fk_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_unit_content
    ADD CONSTRAINT fk_system_user FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: fk_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_exercise
    ADD CONSTRAINT fk_system_user FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: fk_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_exam
    ADD CONSTRAINT fk_system_user FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: fk_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenger_result
    ADD CONSTRAINT fk_system_user FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: fk_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY transcript
    ADD CONSTRAINT fk_system_user FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: fk_teacher; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenger_result
    ADD CONSTRAINT fk_teacher FOREIGN KEY (teacher) REFERENCES system_user(id);


--
-- Name: fk_topic_forum; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY topic
    ADD CONSTRAINT fk_topic_forum FOREIGN KEY (forum) REFERENCES forum(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_topic_system_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY topic
    ADD CONSTRAINT fk_topic_system_user FOREIGN KEY (created_by) REFERENCES system_user(id);


--
-- Name: fk_unit; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenge_items
    ADD CONSTRAINT fk_unit FOREIGN KEY (unit) REFERENCES unit(id);


--
-- Name: fk_unit_content; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_unit_content
    ADD CONSTRAINT fk_unit_content FOREIGN KEY (unit_content) REFERENCES unit_content(id);


--
-- Name: fk_unit_content; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_exam
    ADD CONSTRAINT fk_unit_content FOREIGN KEY (unit_content) REFERENCES unit_content(id);


--
-- Name: fk_unit_content; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY finished_exercise
    ADD CONSTRAINT fk_unit_content FOREIGN KEY (unit_content) REFERENCES unit_content(id);


--
-- Name: fk_unit_content; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY challenger_result
    ADD CONSTRAINT fk_unit_content FOREIGN KEY (unit_content) REFERENCES unit_content(id);


--
-- Name: fk_unit_content; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exam
    ADD CONSTRAINT fk_unit_content FOREIGN KEY (unit_content) REFERENCES unit_content(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_unit_content 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exercise
    ADD CONSTRAINT "fk_unit_content 	" FOREIGN KEY (unit_content) REFERENCES unit_content(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_user_chat_chat; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_chat
    ADD CONSTRAINT fk_user_chat_chat FOREIGN KEY (chat) REFERENCES chat(id);


--
-- Name: fk_user_chat_user; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_chat
    ADD CONSTRAINT fk_user_chat_user FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: fk_user_voice; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY word_voice
    ADD CONSTRAINT fk_user_voice FOREIGN KEY (user_voice) REFERENCES user_voice(id);


--
-- Name: functionality_authentication_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY authentication_functionality
    ADD CONSTRAINT functionality_authentication_fk FOREIGN KEY (functionality) REFERENCES functionality(id);


--
-- Name: functionality_system_user_functionality_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_functionality
    ADD CONSTRAINT functionality_system_user_functionality_fk FOREIGN KEY (functionality) REFERENCES functionality(id);


--
-- Name: grade_chat_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY chat
    ADD CONSTRAINT grade_chat_fk FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: grade_enrollment_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY enrollment
    ADD CONSTRAINT grade_enrollment_fk FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: grade_forum_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY forum
    ADD CONSTRAINT grade_forum_fk FOREIGN KEY (grade) REFERENCES grade(id);

ALTER TABLE ONLY forum ADD CONSTRAINT fk_forum_course FOREIGN KEY (course) REFERENCES course(id);

--
-- Name: grade_grade_bulletin_board_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY grade_note
    ADD CONSTRAINT grade_grade_bulletin_board_fk FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: grade_professor_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY professor
    ADD CONSTRAINT grade_professor_fk FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: grade_tutor_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY tutor
    ADD CONSTRAINT grade_tutor_fk FOREIGN KEY (grade) REFERENCES grade(id);


--
-- Name: language_language_system_user_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY language_system_user
    ADD CONSTRAINT language_language_system_user_fk FOREIGN KEY (language) REFERENCES language(id);


--
-- Name: objective_answer_objective_answer_student_exam_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_answer_student_exam
    ADD CONSTRAINT objective_answer_objective_answer_student_exam_fk FOREIGN KEY (objective_answer) REFERENCES objective_answer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: objective_answer_objective_answer_student_exercise_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY objective_answer_student_exercise
    ADD CONSTRAINT "objective_answer_objective_answer_student_exercise_fk " FOREIGN KEY (objective_answer) REFERENCES objective_answer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: placement_question_system_user_placement_question_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_placement_question
    ADD CONSTRAINT placement_question_system_user_placement_question_fk FOREIGN KEY (placement_question) REFERENCES placement_question(id);


--
-- Name: profile_address_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY address
    ADD CONSTRAINT profile_address_fk FOREIGN KEY (profile) REFERENCES profile(id);


--
-- Name: profile_phone_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY phone
    ADD CONSTRAINT profile_phone_fk FOREIGN KEY (profile) REFERENCES profile(id);


--
-- Name: profile_system_user_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user
    ADD CONSTRAINT profile_system_user_fk FOREIGN KEY (profile) REFERENCES profile(id);


--
-- Name: question_answer_auditive_question_student_exam_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_auditive_question_student_exam
    ADD CONSTRAINT question_answer_auditive_question_student_exam_fk FOREIGN KEY (question) REFERENCES question(id);


--
-- Name: question_answer_auditive_question_student_exercise_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_auditive_question_student_exercise
    ADD CONSTRAINT question_answer_auditive_question_student_exercise_fk FOREIGN KEY (question) REFERENCES question(id);


--
-- Name: question_answer_external_question_student_exercise_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_external_question_student_exercise
    ADD CONSTRAINT question_answer_external_question_student_exercise_fk FOREIGN KEY (question) REFERENCES question(id);


--
-- Name: question_answer_subjective_question_student_exam_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_subjective_question_student_exam
    ADD CONSTRAINT "question_answer_subjective_question_student_exam_fk " FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: question_answer_subjective_question_student_exercise_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_subjective_question_student_exercise
    ADD CONSTRAINT "question_answer_subjective_question_student_exercise_fk " FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: question_auditive_question_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY auditive_question
    ADD CONSTRAINT question_auditive_question_fk FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: question_external_answer_student_exam_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY external_answer_student_exam
    ADD CONSTRAINT question_external_answer_student_exam_fk FOREIGN KEY (question) REFERENCES question(id);


--
-- Name: question_external_question_fk 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY external_question
    ADD CONSTRAINT "question_external_question_fk 	" FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: question_questions_question_text_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question_question_text
    ADD CONSTRAINT question_questions_question_text_fk FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: question_text_questions_question_text_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question_question_text
    ADD CONSTRAINT question_text_questions_question_text_fk FOREIGN KEY (question_text) REFERENCES question_text(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: sentence_auditive_question_word_sentence_auditive_question_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY word_sentence_auditive_question
    ADD CONSTRAINT "sentence_auditive_question_word_sentence_auditive_question_fk " FOREIGN KEY (sentence) REFERENCES sentence_auditive_question(id) ON UPDATE CASCADE ON DELETE CASCADE;



--
-- Name: student_exam_answer_student_exam_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_student_exam
    ADD CONSTRAINT "student_exam_answer_student_exam_fk " FOREIGN KEY (student_exam) REFERENCES student_exam(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: student_exercise_answer_student_exercise_fk 	; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY answer_student_exercise
    ADD CONSTRAINT "student_exercise_answer_student_exercise_fk 	" FOREIGN KEY (student_exercise) REFERENCES student_exercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: system_user_access_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY access
    ADD CONSTRAINT system_user_access_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_enrollment_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY enrollment
    ADD CONSTRAINT system_user_enrollment_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_exercise_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY exercise
    ADD CONSTRAINT system_user_exercise_fk FOREIGN KEY (created_by) REFERENCES system_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: system_user_faq_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY faq
    ADD CONSTRAINT system_user_faq_fk FOREIGN KEY (created_by) REFERENCES system_user(id);


--
-- Name: system_user_forum_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY forum
    ADD CONSTRAINT system_user_forum_fk FOREIGN KEY (created_by) REFERENCES system_user(id);


--
-- Name: system_user_grade_bulletin_board_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY grade_note
    ADD CONSTRAINT system_user_grade_bulletin_board_fk FOREIGN KEY (created_by) REFERENCES system_user(id);


--
-- Name: system_user_grade_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT system_user_grade_fk FOREIGN KEY (coordinator) REFERENCES system_user(id);


--
-- Name: system_user_language_system_user_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY language_system_user
    ADD CONSTRAINT system_user_language_system_user_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_message_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY message
    ADD CONSTRAINT system_user_message_fk FOREIGN KEY (sender) REFERENCES system_user(id);


--
-- Name: system_user_message_recipient_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY message
    ADD CONSTRAINT system_user_message_recipient_fk FOREIGN KEY (recipient) REFERENCES system_user(id);


--
-- Name: system_user_note_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY note
    ADD CONSTRAINT system_user_note_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_professor_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY professor
    ADD CONSTRAINT system_user_professor_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_question_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY question
    ADD CONSTRAINT system_user_question_fk FOREIGN KEY (created_by) REFERENCES system_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: system_user_student_exam_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY student_exam
    ADD CONSTRAINT "system_user_student_exam_fk " FOREIGN KEY (student) REFERENCES system_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: system_user_student_exercise_fk ; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY student_exercise
    ADD CONSTRAINT "system_user_student_exercise_fk " FOREIGN KEY (student) REFERENCES system_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: system_user_system_user_functionality_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_functionality
    ADD CONSTRAINT system_user_system_user_functionality_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_system_user_placement_question_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_placement_question
    ADD CONSTRAINT system_user_system_user_placement_question_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_system_user_unit_status_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_unit_status
    ADD CONSTRAINT system_user_system_user_unit_status_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: system_user_tutor_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY tutor
    ADD CONSTRAINT system_user_tutor_fk FOREIGN KEY (system_user) REFERENCES system_user(id);


--
-- Name: unit_system_user_unit_status_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY system_user_unit_status
    ADD CONSTRAINT unit_system_user_unit_status_fk FOREIGN KEY (unit) REFERENCES unit(id);


--
-- Name: unit_unit_content_fk; Type: FK CONSTRAINT; Schema: ivela; Owner: ivela
--

ALTER TABLE ONLY unit_content
    ADD CONSTRAINT unit_unit_content_fk FOREIGN KEY (unit) REFERENCES unit(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Create Table Subscription
--
CREATE TABLE subscription (
    id integer NOT NULL,
    system_user integer NOT NULL,
    recipient character varying(79),   
    category character varying(50) NOT NULL,
    category_id integer NOT NULL,
    type character varying(50) NOT NULL,
    custom_layout character varying(89),
    retry boolean NOT NULL DEFAULT true
);

ALTER TABLE ivela.subscription OWNER TO ivela;
CREATE SEQUENCE sq_subscription START WITH 1 INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1;
ALTER SEQUENCE sq_subscription OWNED BY subscription.id;
ALTER TABLE ONLY subscription ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);
CREATE INDEX index_subscription_on_cat_type ON subscription(category, type);
CREATE INDEX index_subscription_on_cat_id ON subscription(category_id);

--
-- Create Table Delayed Mail
--
CREATE TABLE delayed_mail (
    id integer NOT NULL,
    retries smallint NOT NULL DEFAULT 0, 
    recipient character varying(79) NOT NULL,
    sender character varying(79),
    subject character varying(200) NOT NULL,
    body text
);

ALTER TABLE ivela.delayed_mail OWNER TO ivela;
CREATE SEQUENCE sq_delayed_mail START WITH 1 INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1;
ALTER SEQUENCE sq_delayed_mail OWNED BY delayed_mail.id;
ALTER TABLE ONLY delayed_mail ADD CONSTRAINT delayed_mail_pkey PRIMARY KEY (id);

--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

