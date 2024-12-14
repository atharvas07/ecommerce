# Spring Cloud Gateway with JWT Authentication

## Overview

Spring Cloud Gateway is used to route requests to microservices or endpoints. This example demonstrates implementing
JWT (JSON Web Token) token-based authentication for securing requests passing through the Gateway.

## Key Concepts

## Token Validation Flow Example

1. The client sends requests to the Gateway with a JWT token in the `Authorization` header. The token is used to
   authenticate and identify the user for securing the request.
2. The `JwtTokenFilter` intercepts the request and extracts the token from the `Authorization` header.
3. The extracted token is validated for signature, expiration, and claims using a security utility or JWT provider
   library (e.g., io.jsonwebtoken).
4. Upon successful validation, the `JwtTokenFilter` extracts the `userId` and `sessionId` claims from the token.
5. The `sessionId` is validated against the current active sessions in a session store (e.g., an in-memory cache or
   distributed session service).
6. The user's `userId` is added as a new downstream header (`user-id`) to the request forwarded to the appropriate
   microservice.
7. The API paths that do not require authentication or validation (e.g., `/login`, `/public/**`) are maintained in an
   exclusion list. These paths bypass the `JwtTokenFilter` to allow unrestricted access.
8. If the token is invalid, expired, or the session is not active, the Gateway returns a `401 Unauthorized` response.

## References

- Spring Cloud Gateway documentation
- JWT specification (https://jwt.io)
