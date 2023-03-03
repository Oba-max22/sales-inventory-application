
# Description: Makefile for the Spring Boot application
build:
	mvn clean package

start:
	mvn spring-boot:run