\---

# Notification API 🚀

A scalable backend Notification API built using Java, Spring Boot, MySQL, Kafka, Docker, Swagger, and AWS.

This project demonstrates real-world backend development concepts including REST APIs, asynchronous messaging, database integration, email notifications, Docker containerization, and cloud deployment using AWS Elastic Beanstalk + RDS.

---

## Features

* Create and manage notifications
* RESTful APIs using Spring Boot
* MySQL database integration using Spring Data JPA
* Kafka producer/consumer implementation
* Email notification support
* Swagger UI API documentation
* Docker support
* AWS Elastic Beanstalk deployment
* AWS RDS MySQL integration
* Environment variable based configuration
* Exception handling and clean layered architecture

---

##Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Apache Kafka
* MySQL
* Maven
* Docker
* Swagger / OpenAPI
* AWS Elastic Beanstalk
* AWS RDS

---

## Project Structure

src/main/java/com/bhuvana/notificationapi

├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── producer
├── consumer
└── exception

---

## API Endpoints

### Create Notification

POST /api/v1/notifications

### Get All Notifications

GET /api/v1/notifications

---

## Sample Request Body

{
"userId": "003",
"type": "EMAIL",
"message": "AWS test",
"destination": "[example@gmail.com](mailto:example@gmail.com)"
}

---

## Swagger UI

Local Swagger URL:

http://localhost:8080/swagger-ui/index.html

AWS Hosted Swagger:

http://notification-api-env.eba-fuwg2p65.ap-south-1.elasticbeanstalk.com/swagger-ui/index.html

---

## Screenshots

api response screenshots attached

## Docker Support

### Run Kafka and Zookeeper

docker-compose up -d

---

##  AWS Deployment

Successfully deployed using:

* AWS Elastic Beanstalk
* AWS RDS MySQL

### Deployment Highlights

* Configured environment variables in Elastic Beanstalk
* Integrated Spring Boot app with AWS RDS
* Hosted live Swagger UI on AWS
* Managed production configuration using application properties

---

## Database Configuration

Environment variables used in AWS:

SPRING_DATASOURCE_URL=

SPRING_DATASOURCE_USERNAME=

SPRING_DATASOURCE_PASSWORD=

---

## Email Configuration

Email notifications implemented using Spring Mail.

Example environment variables:

MAIL_USERNAME=

MAIL_PASSWORD=

---

##  Running Locally

### Clone Repository

git clone https://github.com/Bhuviraju/notification-api.git

### Navigate to Project

cd notification-api

### Build Project

mvn clean install

### Run Application

mvn spring-boot:run

---

## Future Enhancements

* SMS Notification Integration
* Retry Mechanism
* Authentication & Authorization
* Notification Status Tracking
* Rate Limiting
* CI/CD Pipeline
* Kubernetes Deployment

---



## Project Outcome

This project helped in gaining hands-on experience with:

* Backend architecture
* Cloud deployment
* Kafka integration
* Production configuration
* Database connectivity
* REST API development
* Real-world deployment troubleshooting

## Author

Bhuvaneshwari

Backend Developer | Java | Spring Boot | Kafka | AWS

GitHub:
https://github.com/Bhuviraju/notification-api/


