package com.perfecto.reportium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtLocalVariable;
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
        CtLocalVariable driverVariable = driverLocator.getDriverVariable();
        System.out.println(driverVariable);

        CtStatement ctStatementContext = bestUtils.createReportingContext(driverVariable);
        CtStatement ctStatementClient = bestUtils.createReportingClient();

        driverVariable.insertAfter(ctStatementClient);
        driverVariable.insertAfter(ctStatementContext);

        bestUtils.compile();
    }
}
