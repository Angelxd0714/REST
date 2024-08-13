package com.main.rest.conf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Product API",
                version = "1.0",
                description = "Product API Documentation",
                contact = @Contact(
                        name = "Angel Santibañez",
                        email = "angelxd0714@gmail.com"
                )

        ),
        servers = {
                @Server(url = "http://localhost:8060", description = "Local server")
        }, tags = {
        @Tag(name = "Product", description = "Product API")
}

)
@SecurityScheme(

        type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer", bearerFormat = "JWT",
        description = "Enter JWT token"
)
public class SwaggerConf {
}
