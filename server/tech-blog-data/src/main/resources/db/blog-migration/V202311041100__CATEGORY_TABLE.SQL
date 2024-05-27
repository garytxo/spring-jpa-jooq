create sequence tech_blog.post_category_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

CREATE TABLE tech_blog.post_category
(
    id   bigint       DEFAULT nextval('tech_blog.post_category_seq'),
    name            varchar(255)                     NOT NULL,
    row_version    integer      DEFAULT NULL,
    row_created_by varchar(255)               NOT NULL,
    row_created_on timestamp    DEFAULT now() NOT NULL,
    row_updated_by varchar(255) DEFAULT NULL,
    row_updated_on timestamp    DEFAULT now(),
    CONSTRAINT pk_post_category PRIMARY KEY (id)
);

insert into tech_blog.post_category (name, row_version, row_created_by)
values
    ('TECHNOLOGY',1,'TEST_USER'),
    ('FINANCE',1,'TEST_USER'),
    ('FOOD',1,'TEST_USER'),
    ('ENTERTAINMENT',1,'TEST_USER'),
    ('SCIENCE',1,'TEST_USER'),
    ('MUSIC',1,'TEST_USER'),
    ('SPORT',1,'TEST_USER'),
    ('OTHER',1,'TEST_USER');


CREATE TABLE tech_blog.post_post_category
(
    post_id     bigint NOT NULL,
    category_id bigint NOT NULL,
    CONSTRAINT pk_post_post_category PRIMARY KEY (post_id, category_id),
    CONSTRAINT fk_post_post_category_category_id FOREIGN KEY (category_id) REFERENCES tech_blog.post_category (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_post_post_category_post_id FOREIGN KEY (post_id) REFERENCES tech_blog.blog_post (id) ON UPDATE CASCADE ON DELETE CASCADE
);
