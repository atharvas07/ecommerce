package com.ecomm.usermanagementsvc.domain.config;

import com.ecomm.mircrosvclib.logger.RequestResponseLoggingFilter;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean
    public Filter requestResponseLoggingFilter() {
        return new RequestResponseLoggingFilter();
    }
}