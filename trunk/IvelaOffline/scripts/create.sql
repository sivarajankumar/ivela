CREATE SCHEMA "IVELA";

CREATE TABLE "IVELA"."SYSTEM_USER"
(
  id numeric(10) NOT NULL,
  email character varying(150) NOT NULL,
  social_number character varying(255) NOT NULL,
  username character varying(20) NOT NULL,
  "PASSWORD" character varying(40) NOT NULL,
  enabled smallint NOT NULL,
  profile numeric(10),
  last_unit_content numeric(10),
  created_at timestamp,
  authentication numeric(10),
  recovery_question character varying(255) NOT NULL,
  recovery_answer character varying(255) NOT NULL,
  CONSTRAINT pk_system_user PRIMARY KEY (id)
);

CREATE TABLE "IVELA".course
(
  id numeric(10) NOT NULL,
  "NAME" character varying(100) NOT NULL,
  description character varying(250),
  image character varying(250),
  target_audience character varying(250),
  contents clob,
  repository_structure clob,
  active smallint DEFAULT 1,
  upload_package_enabled smallint DEFAULT 0,
  custom_toc smallint DEFAULT 0,
  challenge_retries smallint NOT NULL DEFAULT 0,
  challenge_count smallint NOT NULL DEFAULT 0,
  challenge_weight integer NOT NULL DEFAULT 0,
  CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE "IVELA".discipline
(
  id numeric(10) NOT NULL,
  course numeric(10) NOT NULL,
  "NAME" character varying(100) NOT NULL,
  tag character varying(20),
  CONSTRAINT pk_discipline PRIMARY KEY (id),
  CONSTRAINT course_discipline_fk FOREIGN KEY (course)
      REFERENCES "IVELA".course (id)
);

CREATE TABLE "IVELA".unit
(
  id numeric(10) NOT NULL,
  discipline numeric(10) NOT NULL,
  "NAME" character varying(100) NOT NULL,
  active smallint,
  CONSTRAINT pk_unit PRIMARY KEY (id),
  CONSTRAINT discipline_unit_fk FOREIGN KEY (discipline)
      REFERENCES "IVELA".discipline (id)
);

CREATE TABLE "IVELA".unit_content
(
  id numeric(10) NOT NULL,
  order_n integer NOT NULL,
  unit numeric(10) NOT NULL,
  title character varying(255) NOT NULL,
  description clob NOT NULL,
  "TYPE" integer NOT NULL,
  width integer,
  height integer,
  duration time NOT NULL,
  tag character varying(20),
  CONSTRAINT unit_content_pk PRIMARY KEY (id),
  CONSTRAINT unit_unit_content_fk FOREIGN KEY (unit)
      REFERENCES "IVELA".unit (id)
);

CREATE TABLE "IVELA".finished_unit_content
(
  id integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  "SYSTEM_USER" numeric(10),
  course numeric(10),
  unit_content numeric(10),
  CONSTRAINT finished_unit_content_pkey PRIMARY KEY (id),
  CONSTRAINT fk_course_fuc FOREIGN KEY (course)
      REFERENCES "IVELA".course (id),
  CONSTRAINT fk_system_user_fuc FOREIGN KEY ("SYSTEM_USER")
      REFERENCES "IVELA"."SYSTEM_USER" (id),
  CONSTRAINT fk_unit_content_fuc FOREIGN KEY (unit_content)
      REFERENCES "IVELA".unit_content (id)
);

CREATE TABLE "IVELA".challenge
( 
  id integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  challid character varying(100) NOT NULL,
  challvalue double precision,
  uid numeric(10) NOT NULL,
  unit_id integer NOT NULL DEFAULT 0,
  retries smallint NOT NULL DEFAULT 0,
  CONSTRAINT challenge_pkey PRIMARY KEY (id)
);

CREATE TABLE "IVELA".challenge_items
(
  id integer NOT NULL,
  "NAME" character varying(100),
  "XML" clob,
  course numeric(10),
  discipline numeric(10),
  unit numeric(10),
  "TIMESTAMP" timestamp,
  dependency numeric(10),
  weight integer NOT NULL DEFAULT 1,
  CONSTRAINT challenge_items_pkey PRIMARY KEY (id),
  CONSTRAINT fk_course FOREIGN KEY (course)
      REFERENCES "IVELA".course (id),
  CONSTRAINT fk_discipline FOREIGN KEY (discipline)
      REFERENCES "IVELA".discipline (id),
  CONSTRAINT fk_unit FOREIGN KEY (unit)
      REFERENCES "IVELA".unit (id),
  CONSTRAINT challenge_items_name_key UNIQUE ("NAME")
);

CREATE TABLE "IVELA".transcript
(
  id integer NOT NULL,
  grade numeric(10) NOT NULL,
  "SYSTEM_USER" numeric(10) NOT NULL,
  status integer,
  score numeric(10,2),
  average_exercise numeric(10,2),
  average_exam numeric(10,2),
  manual_score numeric(10,2),
  challenges_done smallint DEFAULT 0,
  challenges_weight integer DEFAULT 0,
  average_challenge numeric(7,2) DEFAULT 0,
  challenges_total numeric(10,2) DEFAULT 0,
  CONSTRAINT transcript_pkey PRIMARY KEY (id),
  CONSTRAINT fk_system_user FOREIGN KEY ("SYSTEM_USER")
      REFERENCES "IVELA"."SYSTEM_USER" (id)
);
