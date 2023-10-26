# dir
A very simple directory deployed as a web service (HATEOAS) with Spring.
It uses spring boot, security, hateoas, data-rest and data-jpa.

Current version: 0.2.5

Use it at your own risk.

## A simple directory
* Long **id**: _BIGINT(19)_, not nullable, pk and auto-generated
* String **idStr**: _VARCHAR(255)_, nullable and unique
* Long **idNum**: _BIGINT(19)_, nullable and unique
* String **email**: _VARCHAR(255)_, nullable
* String **password**: _VARCHAR(255)_, nullable
* Timestamp **timestamp**: _TIMESTAMP_, nullable
* Boolean **enabled**: _BOOLEAN_, nullable 

 
## Before build and use it, consider...
 * Change user names and password in CustomWebSecurityConfigurerAdapter
 class.
 * Add or remove un/necessary users in CustomWebSecurityConfigurerAdapter
 class.
 * Change default h2 user and password.
 * Change default path to h2 console

Default users are:
 * Admin:Admin
 * User:User
 * External:External

 Passwords are hashed using Bcrypt. To generate new passwords:
 ```console
htpasswd -n -B -C 5 User
 ```
 
### Why the External user role?
Imagine you have an external service which needs to use the directory
(e.g: login service). You can provide the security credentials of a
("ROLE_EXTERNAL") user so that it can check whether the login is granted
or not.

ROLE_EXTERNAL users can only access projection data. See CustomWebSecurityConfigurerAdapter.class

Edit UserProtected.class and add the getters you need. This getters will populate
the JSON Response.

## Build and run

### With Docker

#### Clone, build and run
```console
git clone https://github.com/edugovi/dir.git
cd dir
docker buildx build --tag dir:0.2.5 -f docker/Dockerfile .
docker container run -d -p 9090:9090 --name dir dir:0.2.5
```

#### Send Requests
##### Create user
```console
curl --user 'Admin:Admin' -H "Content-Type: application/json" -X POST -d '{"idStr":"99999999Z","idNum":99999999,"password":"mypassword","email":"test@test.com","timestamp":"1990-01-01T00:00:00.000Z","enabled":true}' http://localhost:9090/users
```

##### Check user credentials

Check out application.properties and set **dev.edugovi.dir.main-identifier** field (id, idStr or idNum) to determine the main identifier used to check if a user exists. The identifier and the password will be used to check users:

```console
curl --user 'External:External' --silent --location --data-urlencode 'id={idnum}' --data-urlencode 'password={password}' -X POST http://localhost:9090/users/check
```

##### Get user
```console
curl --user Admin:Admin -X GET http://localhost:9090/users
curl --user Admin:Admin -X GET http://localhost:9090/users/1
curl --user Admin:Admin -X GET http://localhost:9090/users/1?projection=userProtected
curl --user Admin:Admin -X GET http://localhost:9090/users/search/idnum?is=99999999
```

##### Logs
```console
docker container logs -f dir
```

#### Stop and remove
```console
docker container rm -f dir
docker image rm dir:0.2.5
```
