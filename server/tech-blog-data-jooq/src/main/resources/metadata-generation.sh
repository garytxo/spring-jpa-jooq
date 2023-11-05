#!/bin/sh

project_root_dir=$1 # The root directory of the project

echo 'Installing snapshots in maven local repo'

cd "$project_root_dir"

mvn clean install -DskipTests

echo 'Beginning flyway migrations'

cd "$project_root_dir/tech-blog-data"

mvn org.flywaydb:flyway-maven-plugin:migrate -Pjooq_codegen

echo 'Starting jOOQ code generation'

cd "$project_root_dir/tech-blog-data-jooq"

mvn org.jooq:jooq-codegen-maven:generate -Pjooq_codegen

echo 'jOOQ code generation Completed'