# HuntKingdom
This project consists of providing all services through a Symfony 3.4 application to fulfill the need of hunters all around Tunisia.
## Quick Start
In order to run this, you will first need to make sure that a few things are installed on your computer such as ** PHP/Composer/Symfony**.

Make sure that MySQL database is already created, by importing the `hunt.sql` file  

Once everything is installed properly you can follow these steps.

- To check requirements:
```sh
symfony check:requirements
```
- To **start** the project:
```sh
symfony server:start -d
```
- To check routers of the project:
```sh
php bin/console debug:router
```
- In order to test the app visit this URL
```
http://localhost:8001/MainView/MainIndex
```
- Once you are done you can **stop** the project:
```sh
symfony server:stop
```

## Tools
These are the required tools for this project with their respective versions:
| Tools | Version used | Purpose |
| ------ | ------ | ------ |
| PHP | 7.4 | Scripting language geared towards web development |
| Composer | N/A | application-level package manager for the PHP programming language  |
| Symfony | Symfony 3.4.49 | PHP web application framework and a set of reusable PHP components/libraries |