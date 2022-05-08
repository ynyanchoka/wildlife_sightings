# Wildlife tracker
By Ymelda Monari



## Table of contents
+ [Description](#Description)
+ [Project resources](#project-resources)
+ [Behaviour Driven Development (BDDs](#BDD)
+ [Setup/Installation Requirements](#setupinstallation-requirements)
+ [Technologies used](#technologies-used)
+ [Contact Information](#contact-information)
+ [Copyright and License](#copyright-and-license-information)


## Description
This is a java web application that allows rangers to track wildlife sightings in an area. The application tracks two categories of wildlife that is, endangered and the ones that are not. The user must specify the type of wildlife.
## Project resources
The following is a live-link to the project's site

Link : `.....`

## Behaviour Driven Development (BDD)
### Input
- User fills a form that specifies the hero's details
### Output
- Output is stored in a database.

## Setup/Installation Requirements
#### To recreate database:

1.Launch postgres

2.Type in psql -U (username) then run the following
- CREATE DATABASE wildlife_tracker;
- \c wildlife_tracker;

- CREATE TABLE animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);

- CREATE TABLE sightings (id serial PRIMARY KEY, animalId int, location varchar, rangerName varchar, timestamp timestamp);

- CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;




#### To clone the repository:
- Clone this repository using:
  git clone 'https://github.com/ynyanchoka/wildlife_sightings.git'
- Navigate to the directory:
  cd wildlife_sightings
- Open the directory with your preferred text editor.

## Technologies used
+ IntelliJ IDEA
+ Java
+ Gradle
+ Heroku
+ Postgres




## Contact information
+ Ymelda Monari : `monaryymelda@gmail.com`

## Copyright and license information

Copyright (c) 2022 [click here to view license](LICENSE)
