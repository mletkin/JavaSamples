package org.ully.samples.spel;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelPerformanceTest {

    private static final int REPETITIONS = 1_0000_000;

    private ExpressionParser parser = new SpelExpressionParser();

    private StandardEvaluationContext mkContext() throws NoSuchMethodException {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("now", new Now());
        context.setVariable("zahl", 15);
        context.registerFunction("inc", SpelPerformanceTest.class.getDeclaredMethod("inc", Integer.class));
        return context;
    }

    public static Integer inc(Integer x) {
        return x + 1;
    }

    @Test
    public void fake() {

    }

    @ParameterizedTest
    @ValueSource(strings = { "1", "1+1", "#now.year()", "#inc(2)", "#inc(#zahl)" })
    public void preCompiled(String expression) throws NoSuchMethodException {
        StandardEvaluationContext context = mkContext();
        Expression exp = parser.parseExpression(expression);

        Duration time = duration(REPETITIONS, () -> exp.getValue(context));

        System.out.println("pre " + print(expression, time));
    }

    @ParameterizedTest
    @ValueSource(strings = { "1", "1+1", "#now.year()", "#inc(2)", "#inc(#zahl)" })
    public void postCompiled(String expression) throws NoSuchMethodException {
        StandardEvaluationContext context = mkContext();

        Duration time = duration(REPETITIONS, () -> {
            Expression exp = parser.parseExpression(expression);
            exp.getValue(context);
        });

        System.out.println("pre " + print(expression, time));
    }

    private String print(String expression, Duration time) {
        long ms = time.toMillis();
        long ns = time.toNanos() / REPETITIONS;

        return expression + ": " + ms + "ms total, each " + ns + "ns";
    }

    private Duration duration(int times, Action action) {
        LocalTime start = LocalTime.now();
        for (int n = 0; n < 1000000; n++) {
            action.act();
        }
        LocalTime end = LocalTime.now();
        return Duration.between(start, end);
    }
}
