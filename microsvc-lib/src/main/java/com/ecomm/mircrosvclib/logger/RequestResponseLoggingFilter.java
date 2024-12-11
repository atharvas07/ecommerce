package com.ecomm.mircrosvclib.logger;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class RequestResponseLoggingFilter implements Filter {

    private static final Logger logger = getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            // Calculate duration after processing
            long duration = System.currentTimeMillis() - startTime;

            try {
                logRequestAndResponse(wrappedRequest, wrappedResponse, duration);
            } catch (JSONException e) {
                throw new RuntimeException("Error while creating log entry", e);
            }

            // Ensure the response body is copied back to the actual response object
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequestAndResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, long duration) throws JSONException {
        if (request != null && response != null) {
            String method = request.getMethod();
            String endpoint = request.getRequestURI();
            String requestBody = getContentAsString(request.getContentAsByteArray(), request.getCharacterEncoding());
            int status = response.getStatus();
            String responseBody = getContentAsString(response.getContentAsByteArray(), response.getCharacterEncoding());

            Map<String, String> requestHeaders = getRequestHeaders(request);

            JSONObject logEntry = new JSONObject();
            logEntry.put("method", method);
            logEntry.put("endpoint", endpoint);
            logEntry.put("status", status);
            logEntry.put("duration", duration + "ms");
            logEntry.put("headers", requestHeaders);
            logEntry.put("requestBody", parseToObject(requestBody));
            logEntry.put("responseBody", parseToObject(responseBody));

            logger.info(logEntry);
        }
    }

    private String getContentAsString(byte[] content, String encoding) {
        try {
            return new String(content, encoding != null ? encoding : StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return "[UNREADABLE]";
        }
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headers = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        // Read all headers and put them in a map
        while (headerNames != null && headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }

        return headers;
    }

    private Object parseToObject(String content) {
        if (content == null || content.isBlank()) {
            return "";
        }
        try {
            return new JSONObject(content);
        } catch (Exception e) {
            return content;
        }
    }
}