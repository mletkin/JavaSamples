package java8;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Example for lazy evaluation with lambda expressions
 */
public class lazyEvaluationTest {

	@Test
	public void evaluateWithSideEffect() {
		System.out.println("result is " + firstNotNull(f(1), f(5), f(2)));
	}

	@Test
	public void evaluateWithoutSideEffect() {
		System.out.println("result is " + firstNotNull(()->f(1), ()->f(5), ()->f(2)));
	}

	public <T> T firstNotNull(Supplier<T>... values) {
		return  Stream.of(values).map(Supplier::get).filter(Objects::nonNull).findFirst().orElse(null);
	}

	public <T> T firstNotNull(T... values) {
		return  Stream.of(values).filter(Objects::nonNull).findFirst().orElse(null);
	}

	public String f(int n) {
		System.out.println("f -> " + n);
		return n >= 5 ? "" + 5 : null;
	}
}
