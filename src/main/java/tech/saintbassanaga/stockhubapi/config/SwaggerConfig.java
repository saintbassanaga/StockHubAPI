package tech.saintbassanaga.stockhubapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Stock Hub API",
                version = "1.0",
                description = "StockMaster API is a robust and scalable solution for managing inventory, stock levels, and sales operations." +
                        " This API is designed for businesses looking to streamline their stock management processes with a reliable backend system." +
                        " The initial version lays the foundation for future scalability and modularity, allowing for easy transition into a microservices architecture.",
                contact = @Contact(
                        name = "Saint Paul Bassanaga",
                        url = "www.saintbassanaga.tech",
                        email = "saintbassanaga01@icloud.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server"),
                @Server(url = "https://api.stockhub.com", description = "Production server")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
    // No need for @Bean in this case since @OpenAPIDefinition is used.
}