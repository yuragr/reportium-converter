package com.perfecto.reportium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.reflect.code.CtStatement;

/**
 * Created by yurig on 22-Mar-17.
 */
@Component
public class ReportiumConverter {
    @Autowired
    DriverLocator driverLocator;

    @Autowired
    BestUtils bestUtils;

    public void convert() {
        DriverAssignmentStatement driverAssignmentStatement = driverLocator.getDriverVariable();

        CtStatement ctStatementContext = bestUtils.createReportingContext(driverAssignmentStatement.getDriverVariableName());
        CtStatement ctStatementClient = bestUtils.createReportingClient();

        bestUtils.insertStatementsAfter(driverAssignmentStatement, ctStatementContext, ctStatementClient);
    }
}
