package com.perfecto.reportium;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;
import java.util.Set;

/**
 * Created by yurig on 22-Mar-17.
 */
@Component
public class DriverLocator {

    @Autowired
    Factory factory;
    @Autowired
    private BestUtils bestUtils;

    public DriverAssignmentStatement getDriverVariable() {

        // TODO search for members
        // TODO search only for classes with the RemoteWebDriver imports
        DriverAssignmentStatement driverAssignmentStatement = null;
        for (CtType<?> s : factory.Class().getAll()) {
            Set<CtMethod<?>> methods = s.getMethods();
            for (CtMethod<?> method : methods) {
                CtBlock<?> body = method.getBody();
                List<CtStatement> statements = body.getStatements();
                for (CtStatement statement : statements) {

                    String simpleName = statement.getElements(new TypeFilter<>(CtAssignment.class))
                            .stream()
                            .filter(access -> RemoteWebDriver.class.getSimpleName().equals(access.getType().getSimpleName()))
                            .map(ctAssignment -> ctAssignment.getAssigned().toString())
                            .findFirst()
                            .orElse(null);

                    if (simpleName == null) {
                        simpleName = statement.getElements(new TypeFilter<>(CtLocalVariable.class))
                                .stream()
                                .filter(variable -> RemoteWebDriver.class.getSimpleName().equals(variable.getType().getSimpleName()))
                                .map(ctLocalVariable -> ctLocalVariable.getSimpleName())
                                .findFirst()
                                .orElse(null);
                    }

                    if (simpleName != null) {
                        driverAssignmentStatement = new DriverAssignmentStatement();
                        driverAssignmentStatement.setDriverVariableName(simpleName);
                        driverAssignmentStatement.setStatement(statement);
                        return driverAssignmentStatement;
                    }
                }
            }
        }
        return driverAssignmentStatement;
    }
}
