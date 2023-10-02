package org.mletkin.samples.spel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelTest {

    ExpressionParser parser = new SpelExpressionParser();

    private Object evaluate(String term) throws NoSuchMethodException, SecurityException {
        StandardEvaluationContext context = mkContext();
        Expression exp = parser.parseExpression(term);
        return exp.getValue(context);
    }

    private StandardEvaluationContext mkContext() throws NoSuchMethodException {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("now", new Now());
        context.setVariable("zahl", 15);
        context.registerFunction("inc", SpelTest.class.getDeclaredMethod("inc", Integer.class));
        return context;
    }

    @Test
    public void calculateYear() throws NoSuchMethodException, SecurityException {
        assertThat(evaluate("#now.year()")).isEqualTo(2022);
    }

    @Test
    public void returnString() throws NoSuchMethodException, SecurityException {
        assertThat(evaluate("'Hello SPEL'")).isEqualTo("Hello SPEL");
    }

    @Test
    public void calcAddition() throws NoSuchMethodException, SecurityException {
        assertThat(evaluate("19+1")).isEqualTo(20);
    }

    @Test
    public void inc() throws NoSuchMethodException, SecurityException {
        assertThat(evaluate("#inc(10)")).isEqualTo(11);
        assertThat(evaluate("#inc(#zahl)")).isEqualTo(16);
    }

    public static Integer inc(Integer x) {
        return x + 1;
    }
}
