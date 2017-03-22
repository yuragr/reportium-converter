package com.perfecto.reportium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.reflect.code.CtLocalVariable;

/**
 * Created by yurig on 22-Mar-17.
 */
@Component
public class ReportiumConverter {
    @Autowired
    DriverLocator driverLocator;

    public void convert(String sourceCodePath) {
        CtLocalVariable driverVariable = driverLocator.getDriverVariable(sourceCodePath);
        System.out.println(driverVariable);
    }
}
