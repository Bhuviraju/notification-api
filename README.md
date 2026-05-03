\## Kafka-Based Notification System



A scalable \*\*event-driven backend system\*\* built using \*\*Java, Spring Boot, and Apache Kafka\*\*, designed to handle asynchronous notification processing (Email/SMS simulation) with reliability and fault tolerance.



\---



\## Overview



This project demonstrates how modern backend systems handle \*\*asynchronous communication\*\* using Kafka. Instead of processing requests synchronously, notifications are pushed to a message queue and processed independently.



\---



\## Architecture



\* \*\*Producer Service\*\* → Publishes notification events to Kafka

\* \*\*Kafka Broker\*\* → Handles message queue and distribution

\* \*\*Consumer Service\*\* → Processes notifications asynchronously

\* \*\*Database\*\* → Stores notification status (SENT / PENDING / FAILED)



\---



\## Tech Stack



\* \*\*Backend:\*\* Java, Spring Boot

\* \*\*Messaging:\*\* Apache Kafka

\* \*\*Database:\*\* PostgreSQL / H2

\* \*\*API Testing:\*\* Postman

\* \*\*API Documentation:\*\* Swagger (OpenAPI)

\* \*\*Containerization:\*\* Docker, Docker Compose



\---



\## Workflow



1\. Client sends notification request via REST API

2\. Producer publishes message to Kafka topic

3\. Kafka queues the message

4\. Consumer reads message asynchronously

5\. Notification is processed (Email/SMS simulation)

6\. Status updated in database



\---



\## Features



\* Event-driven architecture using Kafka

\* Asynchronous processing for better scalability

\* Retry mechanism for failed notifications

\* Status tracking (SENT / PENDING / FAILED)

\* REST API integration

\* Swagger UI for API documentation

\* Dockerized setup for easy deployment



\---



\## 📷 Swagger UI



Swagger provides an interactive interface to test APIs.



Access after running the application:



```

http://localhost:8080/swagger-ui/index.html

```



\---



\## Docker Setup



\### Prerequisites



\* Docker installed

\* Docker Compose installed



\### Run the Application



```bash

docker-compose up -d

```



This will start:



\* Kafka

\* Zookeeper

\* Spring Boot Application



\---



\##  API Endpoints



\### Create Notification



```http

POST /notifications

```



\*\*Request Body:\*\*



```json

{

&#x20; "userId": "401",

&#x20; "type": "EMAIL",

&#x20; "destination": "test@gmail.com",

&#x20; "message": "Test notification"

}

```



\---



\### Get All Notifications



```http

GET /notifications

```



\---



\## Testing



\* Use \*\*Postman\*\* to test APIs

\* Verify message flow in Kafka

\* Check database for status updates



\---



\## Sample Response



```json

{

&#x20; "id": 1,

&#x20; "userId": "401",

&#x20; "type": "EMAIL",

&#x20; "destination": "test@gmail.com",

&#x20; "message": "Status success test",

&#x20; "status": "SENT",

&#x20; "createdAt": "2026-05-03T22:07:31"

}

```



\---



\## Key Learnings



\* Event-driven architecture design

\* Kafka producer-consumer model

\* Asynchronous system handling

\* API documentation using Swagger

\* Containerization using Docker

\* Handling failures and retries



\---



\## Future Enhancements



\* Add email/SMS integration (real services)

\* Implement authentication (JWT)

\* Add monitoring (Prometheus + Grafana)

\* Deploy to cloud (AWS / GCP)

\* Add rate limiting \& circuit breaker



\---



\## Author



\*\*Bhuvaneshwari P\*\*

Backend Developer | Java | Spring Boot | Kafka



\---



