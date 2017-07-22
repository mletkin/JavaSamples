package java8;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Examples for the use of lambda expressions with streams
 */
public class StreamWithLambdaExpressionsTest {

	String[] one = {"1", "2", "3"};
	String[] two= {"4", "5", "6"};
	String[] three = {"7", "8", "9"};

	@Test
	public void distinct() {
		getStream().flatMap(Arrays::stream).distinct().forEach(System.out::println);
	}

	@Test
	public void boundInstanceReference() {
		Foo foo = new Foo();
		getStream().flatMap(Arrays::stream).forEach(foo::print);
	}

	@Test
	public void unboundInstanceReference() {
		getStream().flatMap(Arrays::stream).map(Foo::new).forEach(Foo::print);
	}

	@Test
	public void StaticReference() {
		getStream().flatMap(Arrays::stream).forEach(Foo::prints);
	}

	private Stream<String[]> getStream() {
		return Stream.of(one, two, three);
	}

	@Test
	public void resultIsOptional() {
		int result = getStream().flatMap(Arrays::stream).mapToInt(Integer::getInteger).filter(p -> p > 10).findFirst().orElse(4711);
		System.out.println(result);
	}
}
