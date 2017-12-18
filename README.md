# PA165 Project 


## Rest 
Rest is accesible by "web" subproject (“mvn clean install && cd web && mvn tomcat7:run”) 

### examples:

all teams
```sh
$ http://localhost:8080/pa165/api/v1/teams
```

specific team by id
```sh
$ http://localhost:8080/pa165/api/v1/teams/1
```

all players
```sh
$ http://localhost:8080/pa165/api/v1/players
```

specific player by id
```sh
$ http://localhost:8080/pa165/api/v1/players/1
```


all matches
```sh
$ http://localhost:8080/pa165/api/v1/matches
```

specific match by id
```sh
$ http://localhost:8080/pa165/api/v1/matches/1
```


all player results
```sh
$ http://localhost:8080/pa165/api/v1/results
```

specific player result by id
```sh
$ http://localhost:8080/pa165/api/v1/results/1
```