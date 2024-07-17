# connexion-back
- dev: http://localhost:8089/api/v1
- prod: http://localhost:8008/api/v

## Route
- personnal: **/personnal**
  - GET: **/allPersonnals**
  - POST: **/save**
  - PUT: **/edit/{id}**
  - DELETE: **/delete/{id}**

- user: **/user**
  - GET by id: **/user/{id}**
  - GET: **/allUsers**
  - POST: **/save**
  - PUT: **/edit/{id}**
  - DELETE: **/delete/{id}**
