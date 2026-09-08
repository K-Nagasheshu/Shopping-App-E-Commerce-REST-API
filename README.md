# Shopping App — Spring Boot REST API

A REST API for a shopping/e-commerce application built with **Spring Boot**, **Spring Data JPA**, and **Spring Security (JWT)**. Supports customer registration & login, product catalog management, cart management, order placement with a festival discount, payments, and return requests.

## Tech Stack

- **Java 17**
- **Spring Boot 3.5.16**
- **Spring Data JPA** (Hibernate) — MySQL
- **Spring Security** — JWT-based authentication (OAuth2 Resource Server, RSA-signed tokens)
- **BCrypt** — password hashing
- **Maven**

## Features

- **Customer accounts** — registration with encrypted passwords, JWT-based login
- **Category & Product management** — CRUD for products, organized by category
- **Cart** — add/update/remove items; cart total is shown **without discount**
- **Orders** — placing an order applies a flat **20% festival discount**; both the pre-discount (`totalAmount`) and post-discount (`finalAmount`) values are recorded. Placing an order automatically creates the corresponding order items and clears the cart.
- **Payments** — one payment per order (duplicate payments are blocked)
- **Return requests** — customer-supplied return reason
- **Centralized error handling** — a global exception handler returns consistent, descriptive error responses instead of generic server errors

## Business Rules

| Rule | Where it applies |
|---|---|
| No discount when adding to cart | Cart / CartItem |
| Flat 20% discount, applied only at checkout | Order (`totalAmount` = before discount, `finalAmount` = after discount) |
| One payment per order | Payment |
| Cart is emptied after an order is placed | Order |
| Account creation automatically provisions a cart | Customer registration |

## Authentication Flow

1. **`POST /register`** — create an account (password is hashed with BCrypt). A cart is created automatically.
2. **`POST /auth`** — log in with username/password; response contains a signed JWT.
3. For every other endpoint, send the token in the request header:
   ```
   Authorization: Bearer <token>
   ```
   Tokens expire 30 minutes after issue.

## API Reference

### Auth
| Method | Endpoint | Description |
|---|---|---|
| POST | `/register` | Create a new customer account |
| POST | `/auth` | Log in, receive a JWT |

### Customer
| Method | Endpoint | Description |
|---|---|---|
| GET | `/customer/{customerid}` | Get customer by ID |
| PUT | `/customer/{customerid}` | Update customer name |

### Category
| Method | Endpoint | Description |
|---|---|---|
| POST | `/newcategory` | Create a category |
| GET | `/category` | List all categories |
| GET | `/category/{categoryid}` | Get a category by ID |

### Product
| Method | Endpoint | Description |
|---|---|---|
| POST | `/addingproduct` | Create a product |
| GET | `/getallproducts` | List all products |
| GET | `/product/{productid}` | Get a product by ID |
| PUT | `/product/{productid}` | Update a product |
| DELETE | `/product/{productid}` | Delete a product |

### Cart
| Method | Endpoint | Description |
|---|---|---|
| GET | `/seecustomer/{customerid}` | View a customer's cart (total shown without discount) |
| DELETE | `/deletecartitem/{cartitemid}` | Remove a cart item |
| POST | `/addingproduct/{productid}/customer/{customerid}/quantity/{quantity}` | Add a product to cart |
| PUT | `/updateproduct/{productid}/customer/{customerid}/size/{productsize}` | Update size of a cart item |
| DELETE | `/deleteproduct/{productid}/customer/{customerid}` | Remove a product from cart |

### Order
| Method | Endpoint | Description |
|---|---|---|
| POST | `/createneworder/{customerid}` | Place an order from the customer's cart (applies 20% discount, creates order items, clears cart) |
| GET | `/showorders/{customerid}` | List a customer's orders |
| PUT | `/updateorder/{customerid}/orders/{orderid}/updatemessage` | Update order status |
| GET | `/order/{orderid}` | List items in an order |
| GET | `/order/{orderid}/product/{productid}` | Get a specific item in an order |

### Payment
| Method | Endpoint | Description |
|---|---|---|
| POST | `/payment/{orderid}/googlepay` | Pay for an order (blocked if already paid) |
| GET | `/viewpayment/{orderid}` | View payment for an order |

### Return Request
| Method | Endpoint | Description |
|---|---|---|
| POST | `/addreturn/{orderid}?returnreason=...` | Raise a return request with a reason |
| GET | `/seereturn/{orderid}` | View a return request |
| PUT | `/updatereturn/{orderid}` | Update a return request |

> All endpoints above except `/register` and `/auth` require a valid `Authorization: Bearer <token>` header.

## Getting Started

### Prerequisites
- JDK 17+
- Maven
- MySQL

### Setup
1. Clone the repository.
2. Configure your database connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/<your_db>
   spring.datasource.username=<your_username>
   spring.datasource.password=<your_password>
   ```
   > It's recommended to supply these via environment variables rather than committing real credentials.
3. Run the app:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be available at `http://localhost:5000` (or your configured port).

## Testing

A typical end-to-end test flow:
1. `POST /register` → create an account
2. `POST /auth` → log in, copy the token
3. Add the token as a Bearer token on all further requests
4. `POST /newcategory`, `POST /addingproduct` → set up the catalog
5. `POST /addingproduct/{productid}/customer/{customerid}/quantity/{quantity}` → add to cart
6. `GET /seecustomer/{customerid}` → verify cart total (no discount)
7. `POST /createneworder/{customerid}` → place order, verify `totalAmount` vs `finalAmount`
8. `GET /order/{orderid}` → verify order items were created
9. `POST /payment/{orderid}/googlepay` → pay; repeat to confirm duplicate payments are blocked
10. `POST /addreturn/{orderid}?returnreason=...` → raise a return, verify the reason is stored correctly

## Known Limitations / Roadmap

- Passwords are hashed but the `password` field is still returned in API responses — planned to be excluded from serialization.
- Role-based access control (admin vs. customer permissions) is not yet implemented; any authenticated user can access all endpoints.
- Database credentials should be moved to environment variables before deploying.
