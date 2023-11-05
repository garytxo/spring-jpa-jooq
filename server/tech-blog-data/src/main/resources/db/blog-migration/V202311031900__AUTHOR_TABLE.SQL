CREATE SCHEMA IF NOT EXISTS tech_blog;

create sequence tech_blog.blog_author_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;


CREATE TABLE tech_blog.blog_author
(
    id         bigint DEFAULT nextval('tech_blog.blog_author_seq'),
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    row_version    integer      DEFAULT NULL,
    row_created_by varchar(255)               NOT NULL,
    row_created_on timestamp    DEFAULT now() NOT NULL,
    row_updated_by varchar(255) DEFAULT NULL,
    row_updated_on timestamp    DEFAULT now(),
    CONSTRAINT pk_blog_author PRIMARY KEY (id)
);
