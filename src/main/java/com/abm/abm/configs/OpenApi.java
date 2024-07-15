package com.abm.abm.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
       info = @Info(
               title = "ABM API",
               version = "1.0",
               description = "Clients, Products, Carts & Invoices"
       )
)
public class OpenApi {
}

