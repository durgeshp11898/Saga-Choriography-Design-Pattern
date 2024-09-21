Saga Choreography Design Pattern in Microservices
Description:
This repository demonstrates the Saga Choreography Design Pattern in a microservices architecture. Saga is a design pattern used to manage distributed transactions in a microservices system, ensuring consistency without relying on a central transaction coordinator. Choreography involves each service executing its transaction and publishing an event, triggering the next service to act, creating a distributed sequence of events.

Tech Stack:
Java
Spring Boot
Apache Kafka (for event-driven communication)
Docker (for containerization)
MySQL / PostgreSQL (for persistence)
REST APIs
Key Concepts:
Saga Pattern: A series of local transactions where each service is responsible for completing its part of the transaction and triggering the next step through events.
Choreography: No central orchestrator is involved; each service listens for events and acts based on them, creating a decentralized workflow.
Microservices: Independent, loosely coupled services handling different parts of the transaction (e.g., Order Service, Payment Service, Inventory Service).
Features:
Demonstrates distributed transactions across multiple services.
Event-based communication using Kafka.
Failure handling with compensating transactions (rollbacks when needed).
Example scenario: Ordering a product, making a payment, and updating inventory.
How to Run:
Clone the repository.
bash
Copy code
git clone https://github.com/your-repo/saga-choreography.git
Set up Docker and start the containers.
bash
Copy code
docker-compose up
Run the services individually.
bash
Copy code
cd service-name
mvn spring-boot:run
Test the flow using REST API or Postman to trigger the transaction process.
Contributing:
Feel free to contribute by submitting pull requests for improvements or adding new services.
