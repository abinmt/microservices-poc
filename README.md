Run the services in the following order

1. spring-cloud-config-server
2. discovery-server
3. movie-info-service
4. ratings-data-service
5. movie-catalog-service

Config Server
http://localhost:8888/application/default

Hystrix 
http://localhost:8081/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8081%2Factuator%2Fhystrix.stream

Spring Eureka
http://localhost:8761/

Request
http://localhost:8081/catalog/550

Response

[
    {
        "name": "Fight Club",
        "desc": "Nice",
        "rating": 4
    },
    {
        "name": "The Poseidon Adventure",
        "desc": "Nice",
        "rating": 3
    }
]