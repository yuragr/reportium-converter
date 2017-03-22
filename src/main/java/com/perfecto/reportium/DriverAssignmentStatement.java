package com.perfecto.reportium;

import spoon.reflect.code.CtStatement;

/**
 * Created by yurig on 22-Mar-17.
 */
public class DriverAssignmentStatement {
    private CtStatement statement;
    private String driverVariableName;

    public CtStatement getStatement() {
        return statement;
    }

    public void setStatement(CtStatement statement) {
        this.statement = statement;
    }

    public String getDriverVariableName() {
        return driverVariableName;
    }

    public void setDriverVariableName(String driverVariableName) {
        this.driverVariableName = driverVariableName;
    }
}
