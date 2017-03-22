package com.perfecto.reportium.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtField;

/**
 * Created by yurig on 22-Mar-17.
 */
public class MyProcessor extends AbstractProcessor<CtField<?>> {
    @Override
    public void process(CtField<?> element) {
        element.setSimpleName("j");
    }
}
