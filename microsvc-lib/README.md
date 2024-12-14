# **E-Commerce Microservices Utility Library**

This library is a utility package designed to be **shared across multiple microservices**. It speeds up development by
providing **exceptional logging, common API request/response models, utilities, and reusable components** for seamless
integration within Spring-based backend systems.

---

## **Features**

### 1. **Exception Logging and Handling**

- **`RequestResponseLoggingFilter`**:
    - Provides in-depth logging for incoming HTTP requests and responses, including headers, request body, status codes,
      and processing duration for better debugging and monitoring.
- **`CustomException`**:
    - A custom exception class allowing modular handling of error cases across services.
- **`GlobalExceptionHandler`**:
    - Centralized exception handling mechanism for validation errors and generic exceptions.
- **`ExceptionHandler`**:
    - Utility for detecting specific database constraint errors like duplicate email, usernames, or mobile.

### 2. **Standardized Models**

- **`BaseResponse`**:
    - A standardized response model for all API responses with support for success, client errors (4xx), and server
      errors (5xx).
    - Predefined methods for generating `success` or `error` responses.
- **`BaseRequest`**:
    - A reusable base request wrapper that can be extended for creating specific API request objects in services.

### 3. **Utility Classes**

#### a. **JSON Utilities (`JsonUtils`)**

- Simplifies JSON serialization and deserialization.
- Utility for removing keys from JSON strings, converting objects to JSON, parsing JSON to objects, and more.

#### b. **Enumeration Utilities (`EnumUtils`)**

- Provides reusable enums like:
    - **`RazorpayPaymentStatus`**: For payment processing states (e.g., "created", "captured", "failed").
    - **`ProductDeliveryStatus`**: For order tracking states (e.g., "initiated", "delivered", "out for delivery").
- Includes methods to:
    - Fetch enum by status or sequence number.
    - Identify successful payment states.
    - Transition between statuses for delivery handling.

#### c. **Common Utilities (`CommonUtils`)**

- Provides methods to:
    - Generate **unique Order IDs**.
    - Calculate MD5 hash for sensitive data encryption purposes.

#### d. **Signature Utilities (`Signature`)**

- Implements **RFC 2104 HMAC algorithm** for generating secure signatures for API authentication or webhook validation.

---

## **Installation**

To use this library across your services, include it as a Maven/Gradle dependency:

### **Maven:**

```xml

<dependency>
    <groupId>com.ecomm</groupId>
    <artifactId>microservice-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

### **Gradle:**

```gradle
implementation 'com.ecomm:microservice-lib:1.0.0'
```

---

## **How to Use**

### 1. **Request and Response Logging**

- Add `RequestResponseLoggingFilter` as a filter in your application's configuration:
   ```java
   @Component
   public class WebConfig implements WebMvcConfigurer {
       @Bean
       public Filter requestResponseLoggingFilter() {
           return new RequestResponseLoggingFilter();
       }
   }
   ```
- The filter logs detailed request/response information for all incoming HTTP calls.

### 2. **Centralized Exception Handling**

- Extend `GlobalExceptionHandler` and register it as a controller advice in your service to automatically handle:
    - Validation errors (e.g., missing or invalid fields in requests).
    - Generic exceptions (e.g., 500 Internal Server Errors).

### 3. **Base Models**

#### a. **BaseResponse**

Use this for consistent responses from your services:

   ```java

@PostMapping("/login")
public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest request) {
    // Success Example
    BaseResponse response = BaseResponse.getSuccessResponse("Login Successful");
    return response.toResponseEntity();

    // Error Example
    BaseResponse error = BaseResponse.getErrorResponse("Invalid credentials");
    return error.toResponseEntity();
}
   ```

### 4. **JsonUtils**

#### Usage Examples:

- **Convert Object to JSON:**
   ```java
   String jsonResponse = JsonUtils.getJSON(new User("John", "Doe"));
   ```
- **Parse JSON to Object:**
   ```java
   User user = JsonUtils.getBeanByJson(jsonResponse, User.class);
   ```

- **Remove Key from JSON:**
   ```java
   String updatedJson = JsonUtils.removeKeyFromJSONString(originalJson, "keyName");
   ```

### 5. **EnumUtils**

#### a. **Payment Status Checking**:

   ```java
   RazorpayPaymentStatus status = RazorpayPaymentStatus.fromStatus("authorized");
   if(RazorpayPaymentStatus.

isSuccess(status)){
        // Process the successful payment
        }
   ```

#### b. **Delivery Status Transition**:

   ```java
   ProductDeliveryStatus currentStatus = ProductDeliveryStatus.ORDER_PLACED;
ProductDeliveryStatus nextStatus = currentStatus.getNextStatus();
   ```

### 6. **Common Utilities**

#### Generate Order IDs:

   ```java
   String orderId = CommonUtils.generateOrderId();
   ```

#### MD5 Hashing:

   ```java
   String hash = CommonUtils.encodeMD5("my-secret-data");
   ```

### 7. **Signature Generation**

Generate secure HMAC signatures for APIs or webhooks:

   ```java
   String signature = Signature.calculateRFC2104HMAC(data, secretKey);
   ```

---

## **Folder Structure Overview**

```
src/
├── logger/                # Logging utilities (e.g., RequestResponseLoggingFilter)
├── models/                # Base models for request/response handling
├── exception/             # Custom exceptions and handlers
├── utils/                 # Utility classes (JSON, Enums, MD5, Signature)
```

---

## **Benefits**

- **Reusability**: Shared across multiple services for consistent API responses, exception handling, and utilities.
- **Developer Efficiency**: Predefined utilities (e.g., JSON and Enum utilities) save development time.
- **Enhanced Debugging**: Detailed request and response logging for improved observability.
- **Error Handling**: Centralized mechanism to handle exceptions gracefully.
- **Security Features**: Built-in HMAC signature generation and MD5 hash utilities for secure communication.

---