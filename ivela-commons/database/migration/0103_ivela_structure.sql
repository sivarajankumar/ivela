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


