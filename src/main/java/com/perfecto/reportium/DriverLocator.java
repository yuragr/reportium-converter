package com.perfecto.reportium;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.Launcher;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Created by yurig on 22-Mar-17.
 */
@Component
public class DriverLocator {

    @Autowired
    private BestUtils bestUtils;

    @Autowired
    Factory factory;

    public CtLocalVariable getDriverVariable() {

        CtLocalVariable driverLocalVariable = null;

        // TODO search for members
        // TODO search only for classes with the RemoteWebDriver imports

        for (CtType<?> s : factory.Class().getAll()) {
            Set<CtMethod<?>> methods = s.getMethods();
            for (CtMethod<?> method : methods) {
                CtBlock<?> body = method.getBody();
                List<CtStatement> statements = body.getStatements();
                for (CtStatement statement : statements) {
                    driverLocalVariable = statement.getElements(new TypeFilter<>(CtLocalVariable.class))
                            .stream()
                            .filter(variable -> RemoteWebDriver.class.getSimpleName().equals(variable.getType().getSimpleName()))
                            .findFirst()
                            .orElse(null);
                    if (driverLocalVariable != null){
                        break;
                    }
                }
                if (driverLocalVariable != null)
                    break;
            }
            if (driverLocalVariable != null)
                break;
        }
        return driverLocalVariable;
    }
}
