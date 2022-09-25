package com.sql.exp.spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.sql.exp" })
@EnableJpaRepositories(basePackages = { "com.sql.exp" })
@EntityScan(basePackages = { "com.sql.exp" })
@OpenAPIDefinition(info = @Info(title = "stephen"))
public class SqlBoot {
    private static final Logger logger = LoggerFactory.getLogger(SqlBoot.class);

    public static void main(String[] args) {
        logger.info("stephen says hi");
        SpringApplication.run(SqlBoot.class, args);
    }
}
