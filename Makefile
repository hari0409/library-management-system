.PHONY: run_spring run_db stop_db clean_db run_all
# Default target: runs the Spring Boot application
run_spring:
	mvn spring-boot:run
# Target to start the PostgreSQL Docker container
run_db:
	docker run -d \
  		--name springboot-mysql \
		-e MYSQL_ROOT_PASSWORD=root \
		-e MYSQL_USER=admin \
		-e MYSQL_PASSWORD=admin \
		-p 3306:3306 \
		-v /Users/haribaskars/Development/Java/revise/data:/var/lib/mysql \
		mysql:latest
# Target to stop the Docker container
stop_db:
	docker stop springboot-mysql && docker rm springboot-mysql

# Target to clean up database data
clean_db:
	rm -rf /Users/haribaskars/Development/Java/revise/data/*
# Combined target to start both the database and Spring Boot
run_all: run_db run_spring