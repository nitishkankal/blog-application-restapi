# Blog-Application-restapi

## To Run this project 

### Clone the repo
   ```sh
   git clone https://github.com/nitishkankal/blog-application-restapi.git
   ```
   
- Create Mysql DB  "userdb"
- Update mysql config username password in application.properties
- run blogschema.sql schema will be genrated

Run spring APP
- mvn spring-boot:run

Run API using postman-

Exposed API URLs - 

##Authentication
###1. Register
  ```sh
   localhost:8080/api/auth/register
   ```
   
 ```json
   {
    "username":"abc",
    "email":"abc@gmail.com",
    "password":"123",
    "firstName":"abc",
    "lastName":"zxc",
    "phone":"123"
    }
 ```


###2.login- login api will return JWT toekn, which we can use it to access other APIs, 
Add token in Postman tab Authorization -> type Bearer token
```sh
localhost:8080/api/auth/login
 ```
  ```json
   {
 "username":"abc",
"password":"123"
    }
 ```
 
### blogapp/posts/createPost
 ```sh
localhost:8080/api/blogapp/posts/createPost
 ```
```json
{
    
    "title": "second post",
    "body": "yes posted",
    "username": "abc",
    "photos": [{
        "title": "pic",
        "url": "/abc.jpeg"
    },{
        "title": "pic2",
        "url": "/abc2.jpeg"
    }]
}
```
 
 
 
 ### GET blogapp/posts/get all
 ```sh
localhost:8080/api/blogapp/posts/all
 ```
 


 ###  GET blogapp/posts/ get by id
 ```sh
localhost:8080/api/blogapp/posts/get/id
 ```


 ###  blogapp/posts/updatePost
 ```sh
localhost:8080/api/blogapp/posts/update/14
 ```
```json
{
    
    "title": "second post updated lah",
    "body": "yes updated post",
    "username": "abc",
    "photos": [{
        "title": "pic",
        "url": "/abc.jpeg"
    },{
        "title": "pic2",
        "url": "/abc2.jpeg"
    }]
}
```

 ### POST blogapp/posts/delete post
 ```sh
localhost:8080/api/blogapp/posts/delete/9
 ```
