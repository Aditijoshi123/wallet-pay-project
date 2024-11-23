# wallet-pay-project
Project Description
This project is a microservices-based backend application for an Wallet Pay system developed using Spring Boot in Java. The application facilitates wallet-to-wallet payments and enables users to add funds to their wallets by integrating Razorpay payment gateway. The architecture is designed with security in mind by incorporating Spring Security for API protection. The communication between the services leverages asynchronous messaging via Apache Kafka.

The system consists of four independent microservices:

USER SERVICE: Manages user registration and authentication.
NOTIFICATION SERVICE: Sends notifications for various events.
WALLET SERVICE: Handles wallet operations such as balance updates and fund additions.
TRANSACTION SERVICE: Manages transactions and ensures the flow of funds between wallets.

Diagram Explanation
![wallet_pay drawio](https://github.com/user-attachments/assets/95f53f6c-2b4c-4772-9254-482f81abf261)

The architecture can be explained as follows:

User Registration:
The Web Application sends a POST /user request to the USER SERVICE, which stores user details in the User DB.
Upon successful user creation, the user_created event is sent to the NOTIFICATION SERVICE through Kafka, triggering a notification update in the Notification DB.

Adding Money to Wallet:
The Web Application sends a POST /addMoney request to the WALLET SERVICE.
The Wallet Service validates the user details by making an internal POST /validate request to the USER SERVICE.
Upon successful validation, the Wallet Service updates the wallet balance in the Wallet DB and emits a wallet_updated event to the NOTIFICATION SERVICE, which updates the notification logs.

Wallet-to-Wallet Transactions:
A POST /transaction request is sent to the TRANSACTION SERVICE to initiate a transaction.
The Transaction Service updates the Transaction DB and sends the txn_init event to the Wallet Service to process the wallet balances.
After successful processing, a txn_completed event is emitted back to update the Transaction Service and Notification Service.

Transaction Status:
Users can retrieve the status of a transaction by calling the GET /status endpoint of the Transaction Service, which fetches the details from the Transaction DB.
