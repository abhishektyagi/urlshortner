# UrlShortner

---
## About
Basic url Shortening application in java 8. 
Support for Rate-limiting. Can be configured from config.yml (configuration file)

Support multi-node setup for rate limiting. 

Uses In memory distributed data grid, and has no persistent layer.


How to start the UrlShortner application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/urlshortner-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

---
##Docker
Docker file is included.

### To build
`$ docker build -t urlshortner .`
### To run
`docker run -it --rm -p 8080:8080 -p 8081:8081 -e TEMPLATE="urlshrtnr, %s" urlshortner`

