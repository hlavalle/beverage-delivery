<h1 align="center">
  <br>
  Beverage Delivery
  <br>
</h1>

<h4 align="center">API for beverage delivery logistics</h4>

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.2.5-brightgreen.svg" />
    </a>
    <a alt="Maven">
        <img src="https://img.shields.io/badge/Maven-v3.6-blue.svg" />
    </a>
    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v19-yellowgreen.svg" />
    </a>
</p>

## Table of Contents ##
1. [Architecture and Design](#architecture-and-design)
2. [Assumed Assumptions](#assumed-assumptions)
3. [How to Execute](#how-to-execute)

## Architecture and Design ##

The API was developed in Java and uses the Spring Boot framework.

It provides 3 operations:
1. Delivery order registration
2. Vehicle registration
3. Query on ranking of vehicles to attend a delivery order

Details of the requests provided on a Swagger UI (available when application is running)
* http://localhost:9000/swagger-ui.html

All data is stored in memory. Delivery order and vehicle data are stored on a H2 database.
Java sets and maps were used to support the calculus of the shortest distance between an vehicle and a store, using Dijkstra algorithm.

References:
* https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
* https://www.baeldung.com/java-dijkstra

## Assumed Assumptions ##

* Data is not persisted, so is available only during application execution.

* Cargo capacity of vehicles are not configurable, and is assumed the following capacty and types:
  * **10**: Vehicle type A
  * **20**: Vehicle type B
  * **30**: Vehicle type C
  * **40**: Vehicle type D
  * **50**: Vehicle type E

* Locations and distances are also not configurable. Is assumed the following:

![](./graph.png)

## How to Execute ##

Command to build the container :

```
docker build -t beveragedelivery-docker .
```

Command to run the container :

```
docker run -p 9000:8080 beveragedelivery-docker
```

The API can be accessed over http://localhost:9000

A Postman collection is also available [here](./beverage-delivery.postman_collection.json)
                        
