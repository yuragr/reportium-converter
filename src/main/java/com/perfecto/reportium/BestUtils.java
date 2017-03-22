package com.perfecto.reportium;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.Launcher;
import spoon.reflect.code.CtCodeElement;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.JavaOutputProcessor;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by galb on 3/22/2017.
 */
@Component
public class BestUtils {

    private final static String REPORTIUM_CLIENT_PREFIX = "com.perfecto.reportium.client.";
    private final static String PERFECTO_EXECUTION_CONTEXT_PREFIX = "com.perfecto.reportium.model.";
    @Autowired
    private Factory factory;

    CtStatement createReportingContext(String varName) {
        String contextString = PERFECTO_EXECUTION_CONTEXT_PREFIX + "PerfectoExecutionContext perfectoExecutionContext = new " + PERFECTO_EXECUTION_CONTEXT_PREFIX + "PerfectoExecutionContext.PerfectoExecutionContextBuilder().withWebDriver(" + varName + ").build()";
        return factory.createCodeSnippetStatement(contextString);
    }

    private CtStatement getReportingComment() {
        String comment = "Auto generated code by Perfecto";
        return factory.createComment(comment, CtComment.CommentType.INLINE);
    }

    CtStatement createReportingClient() {
        String contextString = REPORTIUM_CLIENT_PREFIX + "ReportiumClient reportiumClient = new " + REPORTIUM_CLIENT_PREFIX + "ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext)";
        return factory.createCodeSnippetStatement(contextString);
    }

    private void compile() {
        // TODO do not use the source code path
        JavaOutputProcessor fileOutput = new JavaOutputProcessor(new File(ReportiumConverterApplication.sourceCodePath), new DefaultJavaPrettyPrinter(factory.getEnvironment()));
        fileOutput.setFactory(factory);
        for (CtType<?> type : factory.Class().getAll()) {
            fileOutput.process(type);
        }
    }

    public void insertStatementsAfter(DriverAssignmentStatement assignmentStatement, CtStatement... ctNewStatements) {
        ArrayUtils.reverse(ctNewStatements);
        for (CtStatement ctNewStatement: ctNewStatements) {
            assignmentStatement.getStatement().insertAfter(ctNewStatement);
        }
        assignmentStatement.getStatement().insertAfter(getReportingComment());
        compile();
    }
}
