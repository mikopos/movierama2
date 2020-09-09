#MovieRama

The objective of this project is to build a small social sharing platform, in which the
users can share their favorites movies. In this platform also the users have the ability
to express their feelings about other users movies by either like them or hate them.
The abilities of adding a movie and vote for a movie are only available to registered
users.

##Getting Started

These instructions will get you a copy of the project up and running on your local machine 
for development and testing purposes.

###Prerequisites
Create mySQL database with name 'movierama'

###Installing
* Clone the repository
* Open terminal and navigate to project folder
* Execute command
```mvn springboot:run -Dspring-boot.run.profiles=dev```
* Open browser and navigate to ```http:\\localhost:8081```

###Testing with Postman
* Sign-up endpoint ```https://movierama2.herokuapp.com/user/registration```

RequestBody : 
```json
{
    "username":"testUser",
    "password":"testUser123",
    "matchingPassword":"testUser123",
    "firstName":"Test",
    "lastName":"User"
}
```

* Add Movie Endpoint ```https://movierama2.herokuapp.com/movie/addMovie```

RequestBody :
```json
{
    "title":"Star Wars",
    "description":"This is a Star Wars Movie ",
    "user":{
        "username":"testUser",
        "password":"testUser123",
        "matchingPassword":"testUser123",
        "firstName":"Test",
        "lastName":"User"
    }
}
```

* Vote Movie Endpoint ```https://movierama2.herokuapp.com/vote/movie```

RequestBody :

```json
{
    "user":{
        "id":"2",
        "username":"secondUser",
        "password":"secondUser321",
        "matchingPassword":"secondUser321",
        "firstName":"Second",
        "lastName":"User"
    },
    "movie":{
        "id":"1",
        "title":"Star Wars",
        "description":"This is a Star Wars Movie ",
        "user":{
          "username":"testUser",
          "password":"testUser123",
          "matchingPassword":"testUser123",
          "firstName":"Test",
          "lastName":"User"
           }
    },
    "vote":"true"
}
```

###Built With
* Java 8
* Springboot 2
* MySQL
* Thymeleaf

###Github repo code
```https://github.com/mikopos/movierama2/```
###Deployed on Heroku
```https://movierama2.herokuapp.com/```

###Disclaimer

UI is only for demostration