
start-services:
	docker-compose -f docker-compose-db-postgres.yml up -d --build --force-recreate

stop-services:
	docker-compose -f docker-compose-db-postgres.yml down --remove-orphans

restart-services:
	docker-compose -f docker-compose-db-postgres.yml down -v && \
    docker-compose -f docker-compose-db-postgres.yml up -d --build --force-recreate

start-localstack-service:
	docker-compose -f docker-compose-localstack.yml up -d --build --force-recreate

stop-localstack-service:
	docker-compose -f docker-compose-localstack.yml down

restart-localstack-service:
	docker-compose -f docker-compose-localstack.yml down -v && docker-compose -f docker-compose-localstack.yml up -d --build --force-recreate

start-postgres-db-service:
	docker-compose -f docker-compose-db-postgres.yml up -d --build --force-recreate

stop-postgres-db-service:
	docker-compose -f docker-compose-db-postgres.yml down

restart-postgres-db-service:
	docker-compose -f docker-compose-db-postgres.yml down -v && docker-compose -f docker-compose-db-postgres.yml up -d --build --force-recreate
