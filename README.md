
### Backend

#### Environment
* Java 8 or above
* Maven 3.x
* Port 8090

#### Notes
* You are welcome to use additional libraries.
* This project uses an in-memory database so it's not necessary to connect it to another DB.
* data.sql and schema.sql will be executed when you run "mvn clean spring-boot:run'
* This project uses Basic Authentication, the following are the users that you can use to log in.

```
    username: admin
    password: admin123
    role: ADMIN
    
    username: user
    password: user123
    role: USER
```

#### Tasks
* Implement a GET request that returns a list of teachers based on the query params, "filter" and "pageable".
    
    * The filter should support multiple key-value pairs and be used as part of the SQL query.
    * The pageable (sort,page,size), for more info see [Spring Data](https://docs.spring.io/spring-data/rest/docs/current-SNAPSHOT/reference/html/#paging-and-sorting).

```javascript
    // Example 1: http://localhost:8099/api/teachers?filter=name:john,email:user1@example.org should return the following data
    [
       {
          "id": 1,
          "name": "John Wuu",
          "email": "user1@example.org",
          "faculty": {
            "id": 1,
            "name": "English"
          }
      }
    ]
```

```javascript
    // Example 2: http://localhost:8099/api/teachers?filter=name:john&page=1&size=10&sort=desc should return the following data
    [
       {
          "id": 1,
          "name": "John Wuu",
          "email": "user1@example.org",
          "faculty": {
            "id": 1,
            "name": "English"
          }
      },
      ...
      {
          "id": 10,
          "name": "John Graverly",
          "email": "user10@example.org",
          "faculty": {
            "id": 5,
            "name": "Creative Arts"
          }
      }
    ]
```

* Implement a POST request to create a new teacher. For example, the payload should look like the following.

```javascript
    {
        "name": "Teacher's name",
        "email": "teacher's email",
        "faculty_id": 3
    }
```

### Frontend

#### Environment
* NodeJS 9.x or above
* Port 8080

#### Notes
* Feel free to use additional libraries.
* Follow the following steps to run the frontend project
    * Run 'npm install'
    * Run 'npm run serve'
* Calling backend API from the Vue component will give you CORS issue and when that happens please handle it in backend or frontend.

#### Tasks
* Create a Vue component for showing a list of teachers, where it makes an HTTP GET request to /api/teachers.  
* Create a Vue component for creating a new teacher, where it makes an HTTP POST request to /api/teachers.
* Install a frontend framework or component library and use some of the components provided to improve the UI/UX of the frontend.