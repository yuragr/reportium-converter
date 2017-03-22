package com.perfecto.reportium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ReportiumConverterApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(ReportiumConverterApplication.class, args);
        ReportiumConverter reportiumConverter = context.getBean(ReportiumConverter.class);
        reportiumConverter.convert(args[0]);
    }
}
