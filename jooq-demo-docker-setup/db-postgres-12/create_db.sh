#!/bin/bash
set -e

echo "Creating databases: ${DB_NAME}"

for db in $(echo "${DB_NAME}" | tr ',' ' '); do
  echo "Creating database: $db"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE DATABASE $db OWNER ${POSTGRES_USER};
EOSQL
done
