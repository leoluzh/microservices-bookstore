# defaul shell
SHELL = /bin/bash

# Rule "help"
.PHONY: help
.SILENT: help
help:
	echo "Use make [rule]"
	echo "Rules:"
	echo ""
	echo "build 		- build application and generate docker image"
	echo "run-app		- run application on docker"
	echo "stop-app	    - stop application"
	echo "rm-app		- remove application from docker"
	echo ""
	echo ""
	echo ""
	echo "check		    - check tools versions"
	echo "help		    - show this message"

build:
	./mvnw clean install package -Dmaven.test.skip=true; \
	docker build --force-rm -t bookstore-service-discovery .

run-app: stop-app rm-app
	docker run --name bookstore-service-discovery -p 8080:8080 -d bookstore-service-discovery:latest

stop-app:
	- docker stop bookstore-service-discovery

rm-app:	stop-app
	- docker rm bookstore-service-discovery