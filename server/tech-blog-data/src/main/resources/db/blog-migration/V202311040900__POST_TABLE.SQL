create sequence tech_blog.blog_post_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;


CREATE TABLE tech_blog.blog_post
(
    id   bigint       DEFAULT nextval('tech_blog.blog_post_seq'),
    author_id      bigint                     NOT NULL,
    title       varchar                    NOT NULL,
    description varchar      DEFAULT NULL,
    tags        varchar      DEFAULT NULL,
    status VARCHAR(100)                    NOT NULL,
    row_version    integer      DEFAULT NULL,
    row_created_by varchar(255)               NOT NULL,
    row_created_on timestamp    DEFAULT now() NOT NULL,
    row_updated_by varchar(255) DEFAULT NULL,
    row_updated_on timestamp    DEFAULT now(),
    CONSTRAINT pk_blog_post PRIMARY KEY (id),
    CONSTRAINT fk_blog_author FOREIGN KEY (author_id) REFERENCES tech_blog.blog_author (id)


);
