#!/bin/bash

set_server_as_working_directory() {
	dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
	echo "dir $dir"
	cd $dir/../server
}

set_server_as_working_directory

echo "*************************************"
echo "**** Starting JOOQ code generation"
echo "*************************************"


mvn generate-sources -Pgenerate_db_metadata

echo "*************************************"
echo "**** Completed JOOQ code generation"
echo "*************************************"

cd data-transfer-data-jooq

echo "Installing JOOQ code generation to local artifactory"
mvn clean install -DskipTests=true
echo "Completed JOOQ code generation to local artifactory"

