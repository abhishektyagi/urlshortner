# UrlShortner

---
## About
Basic url Shortening application in java 8. 
Support for Rate-limiting. Can be configured from config.yml (configuration file)
ratelimit client id is the ip address, can be changes or configured.

Support multi-node setup for rate limiting. 

Uses In memory distributed data grid means data will be partitioned across nodes so can handle n-1 node loss, and has MYSQL as persistent layer.


How to start the UrlShortner application
---

1. Run `mvn clean install` to build the application
1. Start application with `java -jar target/urlshortner-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see applications health url `http://localhost:8081/healthcheck`

---
## Docker
Docker file is included.

### To build
`$ docker build -t urlshortner .`
### To run
`docker run -it --rm -p 8080:8080 -p 8081:8081 -e TEMPLATE="urlshrtnr, %s" urlshortner`

