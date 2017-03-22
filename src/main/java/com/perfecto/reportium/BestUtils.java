package com.perfecto.reportium;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.factory.CodeFactory;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.FactoryImpl;

/**
 * Created by galb on 3/22/2017.
 */
@Component
public class BestUtils {

    CtStatement createReportingContext(CtLocalVariable ctLocalVariable) {
        Launcher spoon = new Launcher();
        Factory factory = spoon.getFactory();

        String varName = ctLocalVariable.getSimpleName();
        String contextString = "PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder().withWebDriver(" + varName + ").build();";
        return factory.createCodeSnippetStatement(contextString);
    }

    CtStatement createReportingClient() {
        Launcher spoon = new Launcher();
        Factory factory = spoon.getFactory();

        String contextString = "ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);";
        return factory.createCodeSnippetStatement(contextString);
    }
}
