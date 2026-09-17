# =========== Shopping App — E-Commerce REST API ===========

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Live Deployment](https://img.shields.io/badge/deployment-AWS%20Beanstalk-orange)
![Database](https://img.shields.io/badge/database-MySQL%208.0-blue)
![Tests](https://img.shields.io/badge/tests-39%2B-success)
![Java](https://img.shields.io/badge/Java-17%20Corretto-red)

## 🚀 Live Production Deployment

**Live Application:**
http://shoppingmainpr-env.eba-ktmxpcij.ap-south-1.elasticbeanstalk.com

---

## ✅ API Testing & Verification

### Test Evidence — Talend API Tester

| Endpoint          | Method | Status       | Response Time | Result                      |
| ----------------- | ------ | ------------ | ------------- | --------------------------- |
| `/getallproducts` | GET    | **200 OK** ✅ | 599ms         | API responding successfully |
| `/register`       | POST   | **200 OK** ✅ | -             | User registration           |
| `/auth`           | POST   | **200 OK** ✅ | -             | JWT authentication          |

**Database Connection:** ✅ AWS RDS MySQL configured and connected

---

## 📊 Production Metrics — AWS CloudWatch

The following metrics were observed during deployment monitoring:

* ✅ **Environment Health:** 0 Issues
* ✅ **CPU Utilization:** 0.468%
* ✅ **Network In:** 30.2 KB
* ✅ **Network Out:** 35.5 KB
* ✅ **Error Rate:** 0%
* ✅ **Response Time:** <600ms
* ✅ **Uptime:** Stable during observed monitoring period

---

## 📋 Project Overview

A backend **E-Commerce REST API** built with Java and Spring Boot, using Spring Security, JPA/Hibernate, and MySQL to cover the customer purchase lifecycle:

* User registration & authentication
* Product catalog management
* Shopping cart functionality
* Order placement with discount logic
* Payment processing
* Return request handling

**Key Achievement:**
Designed and implemented 9+ interconnected entities with real-world business logic, comprehensive unit testing with **39+ test cases and 82% code coverage**, and deployed the application on AWS Elastic Beanstalk with an AWS RDS MySQL database.

---

## 🏗️ Architecture

### Database Schema — 9+ Entities

```text
Customer
├── Cart
│   └── CartItem → Product → Category
├── Order
│   ├── OrderItem → Product
│   └── Payment
└── ReturnRequest
```

### Layered Architecture

```text
REST Controller Layer
        ↓
Service Layer
(Business Logic)
        ↓
Repository Layer
(Spring Data JPA)
        ↓
MySQL Database
(AWS RDS)
```

---

## 🔧 Core Features

### ✅ Spring Security

* JWT token-based authentication
* BCrypt password hashing
* Role-based access control (Admin, Customer)
* Protected API endpoints

### ✅ Business Logic

* Cart total calculation
* 20% festival discount applied at checkout
* Dual tracking of `totalAmount` and `finalAmount`
* Atomic order placement
* Duplicate-payment prevention
* `@Transactional` for data integrity

### ✅ API Design

* RESTful endpoints with appropriate HTTP methods
* Request validation
* Centralized exception handling
* Consistent error responses
* Layered Controller → Service → Repository architecture

### ✅ Database

* MySQL 8.0
* JPA/Hibernate ORM
* One-to-Many and Many-to-One relationships
* Relational database normalization
* Foreign key relationships

---

## 🧪 Testing — 39+ Test Cases

### Test Coverage Breakdown

| Test Class            |   Tests | Purpose                                      |
| --------------------- | ------: | -------------------------------------------- |
| `CartItemServiceTest` |       8 | Adding/removing cart items                   |
| `OrderServiceTest`    |      11 | Order placement, discounts and payment logic |
| `CartServiceTest`     |      10 | Cart management and calculations             |
| `ProductServiceTest`  |      10 | Product CRUD operations                      |
| **Total**             | **39+** |                                              |

### Testing Stack

* **Framework:** JUnit 5
* **Mocking:** Mockito
* **Testing Pattern:** Arrange-Act-Assert
* **Code Coverage:** 82%

### Test Scenarios

* ✅ Happy-path scenarios
* ✅ Exception handling
* ✅ Business-rule validation
* ✅ Service-layer logic
* ✅ Repository interaction verification
* ✅ Authentication/authorization scenarios

---

## 🛠️ Technology Stack

| Category             | Technology            | Version       |
| -------------------- | --------------------- | ------------- |
| **Language**         | Java                  | 17 (Corretto) |
| **Framework**        | Spring Boot           | 3.x           |
| **Security**         | Spring Security       | 6.x           |
| **Data Access**      | Spring Data JPA       | 3.x           |
| **ORM**              | Hibernate             | 6.x           |
| **Database**         | MySQL                 | 8.0           |
| **Testing**          | JUnit 5 + Mockito     | Latest        |
| **Build Tool**       | Maven                 | 3.9           |
| **Containerization** | Docker                | Latest        |
| **Deployment**       | AWS Elastic Beanstalk | -             |
| **Database Service** | AWS RDS               | MySQL 8.0     |
| **Monitoring**       | AWS CloudWatch        | -             |

---

## 💻 Installation & Local Setup

### Prerequisites

```text
Java 17+
Maven 3.9+
MySQL 8.0
Git
Docker (optional)
```

### Clone & Setup

```bash
# Clone repository
git clone https://github.com/K-Nagasheshu/Shopping-App-E-Commerce-REST-API.git

cd Shopping-App-E-Commerce-REST-API
```

Configure the database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shoppingdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Build Project

```bash
mvn clean package
```

### Run Application

```bash
java -jar target/Shopping-App-1.0.0.jar
```

### Test API Locally

```bash
curl http://localhost:8080/getallproducts
```

---

## 🐳 Docker MySQL Setup

```bash
# Create MySQL container
docker run -d \
  --name mysql-shopping \
  -e MYSQL_ROOT_PASSWORD=root123 \
  -e MYSQL_DATABASE=shoppingdb \
  -p 3306:3306 \
  mysql:8.0
```

Then run the Spring Boot application:

```bash
java -jar target/Shopping-App-1.0.0.jar
```

---

## 🚀 AWS Elastic Beanstalk Deployment

### Environment Details

```text
Platform: Java 17 (Corretto)
Region: ap-south-1 (Asia Pacific - Mumbai)
Load Balancer: Application Load Balancer
Auto-Scaling: Enabled (2-4 instances)
Database: AWS RDS MySQL 8.0
Monitoring: AWS CloudWatch
```

### Deployment Steps

```bash
# 1. Build application
mvn clean package -DskipTests

# 2. Initialize Elastic Beanstalk
eb init -p corretto17 ShoppingApp

# 3. Create environment
eb create shopping-env

# 4. Configure environment variables
eb setenv SPRING_DATASOURCE_URL=jdbc:mysql://[RDS-ENDPOINT]:3306/shoppingdb
eb setenv SPRING_DATASOURCE_USERNAME=admin
eb setenv SPRING_DATASOURCE_PASSWORD=your_password

# 5. Deploy
eb deploy

# 6. Monitor
eb health
eb logs
eb open
```

### RDS MySQL Configuration

```sql
CREATE DATABASE shoppingdb
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

---

## 📝 API Endpoints

### Authentication

```text
POST /register
POST /auth
```

### Products

```text
GET    /getallproducts
GET    /product/{productid}
POST   /addingproduct
PUT    /updateproduct/{productid}
DELETE /deleteproduct/{productid}
```

### Cart

```text
GET    /cart
POST   /cart/add
DELETE /cart/{itemId}
```

### Orders

```text
POST /orders/checkout
GET  /orders/{orderId}
GET  /orders/user/{userId}
```

### Admin

```text
GET /admin/dashboard
```

> Endpoint names should match the actual controller mappings in the project.

---

## 🔐 Security Implementation

### Authentication & Authorization

* JWT token-based authentication
* BCrypt password hashing
* Role-based access control (RBAC)
* Protected authenticated endpoints
* Public access for selected registration, authentication and GET APIs

### Data Protection

* JWT-based stateless authentication
* JPA parameterized queries
* Input validation
* VPC isolation for RDS
* AWS Security Groups for controlled database access

### API Security

* Authorization header validation
* Authenticated request checking
* Admin-only endpoint protection
* User-level data access controls

---

## 📈 Performance & Reliability

### Observed Response Metrics

```text
GET /getallproducts: 599ms
API Response: <600ms
Error Rate: 0%
CPU Utilization: 0.468%
Network In: 30.2 KB
Network Out: 35.5 KB
```

### Scalability

* Auto-scaling configured for 2–4 EC2 instances
* Application Load Balancer
* AWS RDS MySQL database
* CloudWatch monitoring
* Elastic Beanstalk environment health monitoring

---

## 🎓 Learning Outcomes

This project demonstrates:

### Backend Development

* Spring Boot application architecture
* RESTful API development
* Controller-Service-Repository architecture
* Entity relationship mapping
* Business logic implementation

### Database Design

* Relational database modeling
* Database normalization
* JPA/Hibernate ORM
* Transaction management
* Entity relationships

### Security

* JWT authentication flow
* BCrypt password hashing
* Role-based authorization
* Securing REST endpoints

### Testing

* Unit testing with JUnit 5
* Mockito mocking
* Arrange-Act-Assert testing pattern
* Business logic validation
* Exception testing

### Cloud & DevOps

* AWS Elastic Beanstalk deployment
* AWS RDS MySQL setup
* CloudWatch monitoring
* Auto-scaling configuration
* Docker-based MySQL development

### Software Engineering

* Code organization
* Exception handling
* Logging and debugging
* Git/GitHub version control
* API testing

---

## 📊 Project Statistics

```text
Total Lines of Code: 2,500+
Service Classes: 4
Repository Classes: 7
Controller Classes: 3
Entity Classes: 9+
Test Classes: 4
Test Cases: 39+
Code Coverage: 82%
API Endpoints: 15+
```

---

## 🚀 Future Enhancements

* [ ] API documentation with Swagger/OpenAPI 3.0
* [ ] Caching layer using Redis
* [ ] Advanced search and filtering
* [ ] Payment gateway integration (Stripe/Razorpay)
* [ ] Email notifications using JavaMailSender
* [ ] Admin dashboard UI
* [ ] Rate limiting
* [ ] API versioning (v1, v2)
* [ ] Microservices architecture
* [ ] GraphQL support

---

## 🤝 Contributing

Contributions are welcome.

You can:

* Fork the repository
* Submit pull requests
* Report issues
* Suggest improvements

---

## 📄 License

This project is open source and available under the MIT License.

---

## 👨‍💻 Author

**Nagasheshu Kommu**

* 📧 Email: [nagasheshuk22@gmail.com](mailto:nagasheshuk22@gmail.com)
* 🔗 GitHub: https://github.com/K-Nagasheshu
* 💼 LinkedIn: https://linkedin.com/in/nagasheshu-kommu
* 📱 Phone: +91 6305982677
* 📍 Hyderabad, India

---

## 🙏 Acknowledgments

Built with:

* Spring Boot & Spring Security documentation
* AWS documentation and tutorials
* Stack Overflow community
* Developer community resources

---

**Last Updated:** September 17, 2026

**Status:** ✅ Deployed on AWS Elastic Beanstalk

**Testing:** ✅ 39+ Test Cases | 82% Code Coverage

**Database:** ✅ AWS RDS MySQL

**Monitoring:** ✅ AWS CloudWatch
