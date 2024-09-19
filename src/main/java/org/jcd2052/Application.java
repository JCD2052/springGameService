package org.jcd2052;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("TASK MANAGER API")
                .version("1.0")
                .description("This API exposes endpoints for users to manage their tasks.");

        return new OpenAPI()
                .info(info);
    }
}
