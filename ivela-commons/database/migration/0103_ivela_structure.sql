DELETE from Attach_Post;
DELETE from file;
DELETE from post;
DELETE from topic;
DELETE from forum;
ALTER TABLE forum DROP COLUMN student_link_post;
ALTER TABLE forum DROP COLUMN student_upload_repository;
ALTER TABLE forum ADD COLUMN topics_count integer NOT NULL DEFAULT 0;
ALTER TABLE forum ADD COLUMN course integer DEFAULT 0;
ALTER TABLE ONLY forum ADD CONSTRAINT fk_forum_course FOREIGN KEY (course) REFERENCES course(id);
ALTER TABLE forum ALTER COLUMN student_create_topic SET DEFAULT true;
ALTER TABLE forum ALTER COLUMN student_upload_post SET DEFAULT true;
ALTER TABLE file ADD COLUMN grade integer DEFAULT 0;
ALTER TABLE topic ADD COLUMN posts_count integer NOT NULL DEFAULT 0;
ALTER TABLE topic ADD COLUMN last_post_id integer;
ALTER TABLE topic ADD COLUMN last_post_date timestamp without time zone;
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
CREATE SEQUENCE sq_subscription START WITH 1 INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1;
ALTER SEQUENCE sq_subscription OWNED BY subscription.id;
ALTER TABLE ONLY subscription ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);
CREATE INDEX index_subscription_on_cat_type ON subscription(category, type);
CREATE INDEX index_subscription_on_cat_id ON subscription(category_id);
CREATE TABLE delayed_mail (
    id integer NOT NULL,
    retries smallint NOT NULL DEFAULT 0, 
    recipient character varying(79) NOT NULL,
    sender character varying(79),
    subject character varying(200) NOT NULL,
    body text
);
CREATE SEQUENCE sq_delayed_mail START WITH 1 INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1;
ALTER SEQUENCE sq_delayed_mail OWNED BY delayed_mail.id;
ALTER TABLE ONLY delayed_mail ADD CONSTRAINT delayed_mail_pkey PRIMARY KEY (id);
CREATE TABLE grade_unit_content (
    id numeric(10,0) NOT NULL,
    grade numeric(10,0) NOT NULL,
    unit_content numeric(10,0) NOT NULL,
    start_datetime timestamp without time zone,
    mail_flag boolean
);
ALTER TABLE ivela.grade_unit_content OWNER TO ivela;
CREATE SEQUENCE sq_grade_unit_content
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
ALTER TABLE ivela.sq_grade_unit_content OWNER TO ivela;
ALTER SEQUENCE sq_grade_unit_content OWNED BY grade_unit_content.id;
ALTER TABLE grade_unit_content ALTER COLUMN id SET DEFAULT nextval('sq_grade_unit_content'::regclass);
ALTER TABLE ONLY grade_unit_content ADD CONSTRAINT pk_grade_unit_content PRIMARY KEY (id);
ALTER TABLE ONLY grade_unit_content ADD CONSTRAINT grade_user_grade_unit_content_fk FOREIGN KEY (grade) REFERENCES grade(id);
ALTER TABLE ONLY grade_unit_content ADD CONSTRAINT unit_content_user_grade_unit_content_fk FOREIGN KEY (unit_content) REFERENCES unit_content(id);