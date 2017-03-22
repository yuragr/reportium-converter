package com.perfecto.reportium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.JavaOutputProcessor;

import java.io.File;

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

    CtStatement getReportingComment() {
        String comment = "Auto generated code by Perfecto";
        return factory.createComment(comment, CtComment.CommentType.INLINE);
    }

    CtStatement createReportingClient() {
        String contextString = REPORTIUM_CLIENT_PREFIX + "ReportiumClient reportiumClient = new " + REPORTIUM_CLIENT_PREFIX + "ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext)";
        return factory.createCodeSnippetStatement(contextString);
    }

    public void compile() {
        // TODO do not use the source code path
        JavaOutputProcessor fileOutput = new JavaOutputProcessor(new File(ReportiumConverterApplication.sourceCodePath), new DefaultJavaPrettyPrinter(factory.getEnvironment()));
        fileOutput.setFactory(factory);
        for (CtType<?> type : factory.Class().getAll()) {
            fileOutput.process(type);
        }
    }
}
