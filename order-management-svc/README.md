# Order Management Service

### Overview

The **Order Management Service** is a key component of the e-commerce platform responsible for managing customer orders,
cart functionality, and related operations. It provides endpoints to facilitate cart updates, order details retrieval,
and processing of orders.

### Features

- Add and modify products in the cart.
- Retrieve cart details.
- Checkout from the cart and place an order.
- Retrieve details of all orders for a user.
- Fetch specific details or status for an order.
- Update the status of an order or payment.

### API Endpoints

#### Cart Management APIs (CartController)

- **POST `/cart/update`**
    - Description: Add or update products in the user's cart.
    - Headers: Requires `user-id` header.
    - Body: Instance of `UpdateCartClientRequest`.
    - Response: Returns a `BaseResponse` indicating success or failure.

- **GET `/cart`**
    - Description: Retrieve current details of the user's cart.
    - Headers: Requires `user-id` header.
    - Response: Returns the cart details in a `BaseResponse`.

- **POST `/cart/checkout`**
    - Description: Checkout the cart and create an order based on the items in the cart.
    - Headers: Requires `user-id` header.
    - Body: Instance of `CheckoutCartClientRequest`.
    - Response: Returns the order creation status or errors in a `BaseResponse`.

#### Order Management APIs (OrderController)

- **GET `/order/`**
    - Description: Retrieve all orders for a user.
    - Headers: Requires `user-id` header.
    - Response: Returns a list of all orders in a `BaseResponse`.

- **GET `/order/details/{orderId}`**
    - Description: Fetch details for a specific order by order ID.
    - Headers: Requires `user-id` header.
    - Path Variables: `orderId`.
    - Response: Returns order details in a `BaseResponse`.

- **GET `/order/status/{orderId}`**
    - Description: Get the current status of a specific order.
    - Headers: Requires `user-id` header.
    - Path Variables: `orderId`.
    - Response: Returns order status in a `BaseResponse`.

- **POST `/order/status/updates/{orderId}`**
    - Description: Update the status of a specific order.
    - Path Variables: `orderId`.
    - Response: Returns the status update result in a `BaseResponse`.

- **GET `/order/payment/updates/{orderId}`**
    - Description: Update payment details for a specific order.
    - Path Variables: `orderId`.
    - Query Parameters: `transactionId` (string) and `paymentStatus` (string).
    - Response: Returns the payment update result in a `BaseResponse`.

### Data Models

- **UpdateCartClientRequest**: Represents the request payload for updating cart items.
- **CheckoutCartClientRequest**: Represents the request payload for cart checkout.
- **BaseResponse**: Standardized response object returned by all endpoints for status information and optional data.

### Technology Stack

- **Framework**: Spring Boot
- **Language**: Java 17+
- **Libraries**: Spring Web, Lombok, Jakarta EE
- **Build Tool**: Maven
