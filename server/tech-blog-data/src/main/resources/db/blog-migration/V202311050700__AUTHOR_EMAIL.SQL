ALTER TABLE tech_blog.blog_author
    ADD COLUMN email varchar(300);

UPDATE tech_blog.blog_author
SET email = concat(lower(first_name),lower(last_name),lower(id::text),lower('@test.com'))
where id is not null;

ALTER TABLE tech_blog.blog_author
    ALTER COLUMN email SET not null;

ALTER TABLE tech_blog.blog_author
    ADD CONSTRAINT email2_unique unique(email);