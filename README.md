# City List Project

## Motivation
The main motivation of this project is listing and editing cities in the world.

## System User & Roles
There are three role exists on the system.

### Roles
| Role Name       	| Permission                                                     	|
|-----------------	|----------------------------------------------------------------	|
| ROLE_ADMIN      	| only allowed to see home page                                  	|
| ROLE_ALLOW_EDIT 	| allowed to see all pages, and editing cities                   	|
| ROLE_READ_ONLY  	| allowed to see all pages but **not allowed** to editing cities 	|

### Users
| Username 	| Password 	| Role            	|
|----------	|----------	|-----------------	|
| admin    	| admin    	| ROLE_ADMIN      	|
| edit     	| admin    	| ROLE_ALLOW_EDIT 	|
| read     	| admin    	| ROLE_READ_ONLY  	|

To bootstrap project on your local easily with docker-compose [Quick Start](./quick-start.md) could be checked 

## Outline
1. [Quick Start](./quick-start.md)
2. [Big Picture](./big-picture.md)
3. [Authentication & Authorization](./auth.md)
4. [Extra](./quick-start.md)
