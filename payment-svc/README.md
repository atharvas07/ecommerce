# Payment Service (payment-svc)

## Overview

This service is designed to manage payment processing and integrates with the Razorpay SDK for handling payment-related
operations.
It includes functionalities for creating orders, verifying payments, and managing webhooks efficiently, among other
scheduled tasks.

## Razorpay SDK Methods Used

This service integrates with the Razorpay SDK and utilizes the following methods:

- `razorpay.orders.create()`: To create a payment order.
- `razorpay.payments.fetch()`: To fetch payment details via a payment ID.
- `razorpay.utils.verifyPaymentSignature()`: To validate webhook payloads and ensure authenticity.
- `razorpay.payments.capture()`: To capture manually authorized payments.

## Webhook Handling

The service is equipped with a webhook endpoint that manages asynchronous events sent by Razorpay.

**Managed Events**:

- `payment.captured`: Update payment status to 'Captured'.
- `payment.failed`: Handle failure cases by updating the payment's status.
- `order.paid`: Mark the entire order as paid and proceed with updating order status.

**Endpoint**: `/webhook/razorpay`

**Validation**: Incoming webhook payloads are validated using the Razorpay SDK's `verifyPaymentSignature` method.

## Scheduled Tasks

Scheduled methods are primarily used to retrieve payment status and details once a payment is completed on payment
gateway pages.

### Fetch Payment Scheduler

- **Purpose**: This scheduled task periodically checks for the payment status of transactions in the `CREATED` state and
  updates the transaction and order details accordingly.
- **Frequency**: Runs every 2 minutes (Cron: `0 */2 * * * *`).
- **Process**:
    1. Fetches all transactions with a status of `CREATED` from the `transactionsDetailsRepository`.
    2. For each transaction, retrieves payment link status from Razorpay using the `paymentRefNumber`.
    3. If payment details exist, additional payment information is fetched and mapped into
       `FetchPaymentDetailsResponse`.
    4. Updates the transaction entity with payment details such as fee, method, payment ID, status, tax, bank, VPA,
       etc., and saves it in `transactionsDetailsRepository`.
    5. Updates the corresponding order payment status and saves status updates in `ecommOrderStatusUpdatesRepository`.
    6. Logs all updates for traceability.
- **Error Handling**:
    - Catches exceptions at multiple levels (e.g., network errors, parsing issues).
    - Logs error messages and stack traces for debugging.

## Payment APIs

Below are the APIs provided by the `PaymentController` class for payment-related operations:

**1. Retry Payment API**

- **HTTP Method**: POST
- **Endpoint**: `/payment/retry-payment/{orderId}`
- **Description**: Retries the creation of a payment link for a specific order.
- **Headers**:
    - `session-id`
    - `user-id`
- **Path Variable**: `orderId` (ID of the order).
- **Response**: Returns a `BaseResponse` wrapped in a `ResponseEntity`.

**2. Webhook API**

- **HTTP Method**: POST
- **Endpoint**: `/payment/webhook`
- **Description**: Processes asynchronous Razorpay events through webhook payloads. Utilizes validation and updates
  payment or order status based on the event.
- **Request Body**: Accepts a generic JSON request.
- **Response**: Void (no response returned to the client).

**3. Create Payment Link API**

- **HTTP Method**: GET
- **Endpoint**: `/payment/pg/generate`
- **Description**: Generates a payment link for a user and associated order with payment details.
- **Query Parameters**:
    - `userId`
    - `customerName`
    - `customerEmail`
    - `customerContact`
    - `amount`
    - `orderId`
- **Response**: Returns a string containing the generated payment link URL.

**4. Get Payment Details API**

- **HTTP Method**: GET
- **Endpoint**: `/payment/details/{orderId}`
- **Description**: Fetches payment details for a specific order.
- **Headers**:
    - `user-id` (ID of the user querying details).
- **Path Variable**: `orderId` (ID of the order).
- **Response**: Returns a `BaseResponse` wrapped in a `ResponseEntity` with payment details and status.

## Libraries and Frameworks

- Jakarta EE
- Spring Boot
- Spring Web
- Razorpay Official SDK
- Lombok
