package com.perfecto.reportium.spoon;

import org.junit.Test;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.declaration.CtType;

import static spoon.testing.Assert.assertThat;

/**
 * Created by yurig on 22-Mar-17.
 */
public class SpoonTest {
    @Test
    public void test() {
        final SpoonAPI spoon = new Launcher();
        spoon.addInputResource("src/test/java/com/perfecto/reportium/spoon/SpoonTestClass.java");
        spoon.run();

        final CtType<SpoonTestClass> type = spoon.getFactory().Type().get(SpoonTestClass.class);
        assertThat(type.getField("i")).withProcessor(new MyProcessor()).isEqualTo("public int j;");
    }
}
