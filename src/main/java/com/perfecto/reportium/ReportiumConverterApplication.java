package com.perfecto.reportium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spoon.Launcher;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.RegexFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ReportiumConverterApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ReportiumConverterApplication.class, args);
        Launcher spoon = new Launcher();
        spoon.getEnvironment().setNoClasspath(true);
        spoon.addInputResource("C:\\dev\\hackathon\\DemoSeleniumProject\\src");
        spoon.run();
        Factory factory = spoon.getFactory();
        // list all packages of the model
        for (CtPackage p : factory.Package().getAll()) {
            System.out.println("package: " + p.getQualifiedName());
        }
        // list all classes of the model
        for (CtType<?> s : factory.Class().getAll()) {
            Set<CtMethod<?>> methods = s.getMethods();
            for (CtMethod<?> method : methods) {
                CtBlock<?> body = method.getBody();
                List<CtStatement> statements = body.getStatements();
                for (CtStatement statement : statements) {
                    statement.getElements(new NameFilter<>("driver"));
                }
            }

        }
    }
}
