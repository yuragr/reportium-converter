package com.perfecto.reportium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.factory.Factory;

import java.io.IOException;

@Configuration
@SpringBootApplication
public class ReportiumConverterApplication {

    public static String sourceCodePath = null;

    @Bean
    public Factory getFactory() {
        Launcher spoon = new Launcher();
        Environment environment = spoon.getEnvironment();
        environment.setNoClasspath(true);
        environment.setAutoImports(true);
        environment.setCommentEnabled(true);
        spoon.addInputResource(sourceCodePath);
        spoon.run();
        return spoon.getFactory();
    }

    public static void main(String[] args) throws IOException {
        sourceCodePath = args[0];
        ConfigurableApplicationContext context = SpringApplication.run(ReportiumConverterApplication.class, args);
        ReportiumConverter reportiumConverter = context.getBean(ReportiumConverter.class);
        reportiumConverter.convert();
    }
}
