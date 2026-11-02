
AFIARENA MASTER ROADMAP
Project Vision

AfiArena is a gaming tournament and leaderboard platform where players can:

Register/Login
Join tournaments
Participate in matches
Earn points
Track rankings
Receive notifications
View analytics
Phase 1 - Authentication Module
Backend
Entities
User

APIs
POST /register

POST /login

GET /profile

Learning
Spring Security

JWT

Authentication

Authorization

Role Based Access

Roles
ADMIN

PLAYER

Frontend Pages
Login

Register

Forgot Password

Phase 2 - User Profile Module
Features
View Profile

Edit Profile

Upload Avatar

Player Statistics

Entity
User

Learning
File Upload

Multipart Requests

Validation

Phase 3 - Tournament Module
Features
Create Tournament

Update Tournament

Delete Tournament

View Tournament

Join Tournament

Leave Tournament

Entities
Tournament

TournamentPlayer

APIs
GET /tournaments

POST /tournaments

POST /join

POST /leave

Frontend
Tournament List

Tournament Details

Tournament Registration

Phase 4 - Dashboard Module
Features
Total Points

Current Rank

Recent Activities

Upcoming Matches

Dashboard Cards
Current Rank

Total Score

Joined Tournaments

Upcoming Matches

Learning
Angular Routing

Angular Services

Reusable Components

Charts

Phase 5 - Matches Module
Features
Create Match

View Match

Submit Match Result

Track Match Status

Entities
Match

MatchResult

Match Status
UPCOMING

LIVE

COMPLETED

Phase 6 - Leaderboard Module
Features
Global Leaderboard

Tournament Leaderboard

Player Ranking

Entity
Leaderboard

APIs
GET /leaderboard

GET /leaderboard/global

GET /leaderboard/tournament/{id}

Frontend
Leaderboard Screen

Top Players Widget

Phase 7 - Notification Module
Features
Tournament Started

Tournament Joined

Score Updated

Rank Improved

Entity
Notification

Frontend
Notification Bell

Notification Center

Phase 8 - Redis Integration
Goal

Avoid frequent database calls.

Cache
Top 100 Players

Global Leaderboard

Tournament Leaderboard

Player Rank

Learning
Redis

Caching

@Cacheable

@CacheEvict

Phase 9 - Kafka Integration
Workflow
Player submits score

↓

Kafka Event

↓

Leaderboard Service

↓

Notification Service

↓

Analytics Service

Kafka Topics
score-submitted

tournament-joined

user-registered

Learning
Producer

Consumer

Topics

Partitions

Event Driven Systems

Phase 10 - Analytics Module
Features
Most Active Players

Most Popular Tournament

Monthly Participation

Tournament Trends

Frontend
Admin Analytics Dashboard

Phase 11 - Docker
Containers
Backend

PostgreSQL

Redis

Kafka

Zookeeper

Files
Dockerfile

docker-compose.yml

Learning
Containerization

Docker Networking

Volumes

Phase 12 - Microservices
Split Monolith
User Service

Tournament Service

Leaderboard Service

Notification Service

Match Service

Infrastructure
API Gateway

Service Discovery

Config Server

Learning
Microservices

Inter Service Communication

Centralized Configuration

Frontend Pages Checklist
✅ Landing Page

✅ Login

✅ Register

✅ Dashboard

✅ Tournament List

✅ Tournament Details

✅ Join Tournament

✅ Matches

✅ Leaderboard

✅ Notifications

✅ Profile

✅ Admin Dashboard

Backend Packages
com.afiarena

├── config

├── controller

├── dto

├── exception

├── model

├── repository

├── security

├── service

├── util

GitHub Structure
afiarena

├── afiarena-ui

├── afiarena-backend

├── docs

├── architecture

└── README.md

Interview Topics Covered
Java

Collections

Streams

Spring Boot

JPA

Hibernate

MySQL/PostgreSQL

Spring Security

JWT

Angular

Redis

Kafka

Docker

Microservices

System Design

Event Driven Architecture

Execution Order (Very Important)
1. JWT Authentication

2. Profile Module

3. Tournament Module

4. Dashboard

5. Match Module

6. Leaderboard

7. Notifications

8. Redis

9. Kafka

10. Analytics

11. Docker

12. Microservices

Final Resume Description
AfiArena is a scalable gaming tournament and leaderboard platform built using Spring Boot, Angular, PostgreSQL, Redis, Kafka, Docker and Microservices Architecture. The platform supports tournament management, player rankings, real-time notifications, analytics and secure JWT-based authentication.


Copy this into AFIARENA_ROADMAP.md and use it as your master checklist. ✅