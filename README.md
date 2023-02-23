
# city-list
The main motivation of this project is listing and editing cities in the world.

# Quick Start
**Pre-requisites**
- deployment scripts are based on docker-compose.
- Docker Environment should be installed
- Also docker-compose is a must

## Deployment
All the deployment related files are placed under deployment (see [deployment](./deployment)) folder under the repo.
1. Clone project to your local
2. To bootstrap app run the following command
     ```bash
   docker-compose -f /path/to/repo/deployment/docker-compose.yml --env-file /path/to/repo/deployment/.env up -d
     ```
It may take ~4mins minutes to wake-up. \
See more on the following diagram; \
[Deployment Life-Cycle](./documentation/deployment-life-cycle-v1.png)

After build operation completed, backend module will start populating cities from CSV file (see [cities.csv](./city-list-api/city-list-rest/src/main/resources/data/cities.csv)). \
Meanwhile, you can start using application.

You can check running containers. It should be similar to something following; \
![docker ps output](./documentation/screenshots/ss-docker-ps.png)

Open browser and open http://localhost:4000/sign-in
![sign-in](./documentation/screenshots/ss-sign-in.png)

After sign in you will be redirected to home page
![sign-in](./documentation/screenshots/ss-home.png)

As you can see on the screenshot at app bar, full name and role of the user are visible. \
For example in this screenshot, our user's full name is **EDIT USER** and it has **ROLE_ALLOW_EDIT**

### IMPORTANT NOTE 1
Environment variables at .env (see [.env](./deployment/.env)) are important. Host and Port values for backend and front-end modules are stored at this variable.

### IMPORTANT NOTE 2
If you change the values then you the see the affects of your change you shuld build images by running the following command
````bash
 docker-compose -f /path/to/repo/deployment/docker-compose.yml build --no-cache
````

# The Big Picture
The project consists of the following modules;
1. PostgreSQL Database
2. Spring Boot RESTful Webservice
3. ReactJS Client

See more on the following diagram; \
[The Big Picture](./documentation/the-big-picture-v1.png)

# Authentication & Authorization
For the authentication JWT flow was used.  You can check details on the following diagram\
[Auth Flow](./documentation/auth-flow-v1.drawio.png)

# Extra
All other documentation related files and diagrams collected under [documentation](./documentation) folder.
