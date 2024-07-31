# Overview
A cricket score website typically provides real-time match updates, statistic, news, and other cricket-related content. To build such a system using a microservices architecture, break down the functionality into smaller, independent services that can be developed, deployed, and scaled separately.
Following are 5 Microservices for a cricket score website:
1. User management service
2. Match details service
3. Live score service
4. Team management service
5. Player statistic service

# 1. User Management Service
*  User management service manages user accounts, authentication, and authorization. Provides APIs for user registration, login, and profile management. Handles user preferences and settings.
*  In this application, there are two types of users – Admin, User.
*  Admin – Can access and update data.
*  User – Can only read the data created by Admin user.
*  In case of authentication, we can use token-based approach, session-based approach.

# 2. Match Details Service
*  This service manages details about cricket matches (teams, players, venue, start time, etc.).
*  Provides APIs for team lineups, and match status.
*  Handles CRUD operations for match data.
*  Integrates with external data sources for match information.

# 3. Live Score Service
*  Provides real-time score updates for ongoing matches.
*  Exposes APIs for current score, wickets, overs, run rate, etc.
*  Handles data input from scorers or automated systems.

# 4. Team Management Service
*  Stores and manages team data.
*  Provides APIs for team profiles, statistic, and squad details.
*  Calculates real-time statistic during ongoing matches.
*  Handles historical data import and updates.

# 5. Player Statistics Service
*  Stores and manages player performance data.
*  Provides APIs for player profiles, career statistic, and in-match performance.
*  Calculates real-time statistic during ongoing matches.
*  Handles historical data import and updates.

# Deployment Guide
## Pre-requisites
1. Java 17
2. Apache Maven 3.9.8

## Setup steps
1. Download `Spring-Boot-Microservices` Git repository from https://github.com/Hemant77/Spring-Boot-Microservices.git

2. Import `src` directory from IDE - STS.

3. Configure installed Java and Apache Maven inside IDE

4. Run `Maven install` to download all dependencies

5. Run 'Java application' with type `SpringApplication`

6. Application will get started at port 8080, you can test the endpoints using Swagger-ui - http://localhost:8080/api/v1/swagger-ui/index.html

* Create team profile
* Update team profile
* Fetch team profile
* Create squad details
* Update squad details
* Fetch squad details
* Update team statistics
* Fetch team statistics

# Notes
1. Kindly go through the specification document - https://github.com/Hemant77/Spring-Boot-Microservices/blob/master/Cric-App-Specification-Document.docx for better understanding