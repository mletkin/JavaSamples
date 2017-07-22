package java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * Examples for the use of lambda expressions
 */
public class LambdaExpressionTest {

	@Test
	public void applyLambda() {

		// Lambda expressions werden durch Objekte repräsentiert und müssen daher functional Interfaces implementieren
		// Function-JavaDoc: Conceptually, a functional interface has exactly one abstract method.
		// Die Annotation selbst ist dabei nicht erforderlich.
		// Die Auswertung erfolgt über die definierte Methode

		// Function String -> String
		Function<String, String> lambda = (p) -> "Test " + p;
		System.out.println(lambda.apply("1"));

		// Funktion ohne Rückgabewert
		Consumer<String> my = (s) -> System.out.println(s);
		my.accept("Test 2");

		// Funktion ohne Parameter (mit Rückgabewert)
		Supplier<String> ny = () -> "Test 3";
		System.out.println(ny.get());
	}

	@Test
	public void lambdaAlsParameter() {

		Function<String, String> lambda = (p) -> "Test " + p;

		// Zur Auswertung kann auch eine eigene Funktion verwendet werden.
		// Zugleich ein Beispiel, wie Lambdas als Parameter verwendet werden
		System.out.println(executeString(lambda, "4"));

		// Execute via generic
		System.out.println(execute(lambda, "5"));
	}

	private String executeString(Function<String,String> fkt, String value) {
		return fkt.apply(value);
	}

	private <T> T execute(Function<T,T> fkt, T value) {
		return fkt.apply(value);
	}

	@Test
	public void lambdaNotation() {

		// dekarierter Type
		System.out.println(execute(  (String p) -> "Test " + p,                      "1"));

		// abgleiteter typ (Klammern optional bei einem Paremter)
		System.out.println(execute(  (p) -> "Test " + p,                             "2"));
		System.out.println(execute(   p  -> "Test " + p,                             "3"));

		// Mit Block und return-Statment (NB: ";" vor "}")
		System.out.println(execute(  (p) -> {String erg = "Test " + p; return erg;}, "4"));

		// ohne Parameter
		// () -> 42

		// mehrere Paremeter (immer mit Klammern, ENTWEDER deklarieret ODER abgeleitet)
		// (int a, int b) -> a+b

		// Var-Arg geht auch (... und [] sind äquivalent)
		// (int... x) -> ..
		// (int[] x) -> ..
	}

	@Test
	public void methodReferences() {
		// static reference: Statische Methode die den Wert als Argument übernimmt
		System.out.println(executeString(LambdaExpressionTest::staticDuplicate, "1"));

		// bound instance reference: Methode mit Angabe eines Objektes die den Wert als Argument übernimmt
		System.out.println(executeString(this::duplicate, "2"));

		// unbound instance regerence: Die Methode wird auf den Parameter angewandt! Wichtig für Streams
		System.out.println(executeString(String::trim, " test 3 "));
	}

	private static String staticDuplicate(String str) {
		return str + str;
	}

	private String duplicate(String str) {
		return str + str;
	}

	@Test
	public void useContext() {

		// (effektiv) finale Werte aus dem Kontext können im Lambda verwendet werden
		String context = "test";
		System.out.println(execute(p -> context + " " + p, "1"));
		// context = "foo"; // geht nicht!

		// Über eine Lambda-Factory-Method "inject" kann der Kontext injiziert werden (Currying)
		System.out.println(execute(inject("first"), "2"));
		System.out.println(execute(inject("next"), "3"));
	}

	private Function<String, String> inject(final String context) {
		// Der Parameter ist final und konfiguriert so das Lambda mit einem Parameter
		// Das Argument ist statisch und kann über Kontext-Änderung nicht variiert werden
		return p -> context + " " + p;
	}

	@Test
	public void useContextObject() {
		Foo foo = new Foo();
		// Lambda mit Context-Verwendung definieren
		Function<String, String> fkt = p -> foo.value + " " + p;
		System.out.println(execute(fkt, "1"));
		// foo = new Foo(); // geht nicht!

		// Änderung des Context zieht Änderung des Lambdas nach sich
		// foo ist zwar immutable, aber nicht der Inhalt
		foo.value = "changed";
		System.out.println(execute(fkt, "1"));
	}

}
