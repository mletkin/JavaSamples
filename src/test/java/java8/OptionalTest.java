package java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;

/**
 * Examples for the use of the Optional monad
 */
public class OptionalTest {

	@Test
	public void test() {
	}

	Optional<String> f() {
		return Optional.ofNullable("test");
	}

	@Test
	public void instanzenErzeugen() {

		// Optional erzeugen aus einem Wert der garantiert nicht null ist
		Optional<String> test2 = Optional.of("test");
		assertEquals("test", test2.get());

		// Optional erzeugen aus einem Wert der null sein kann
		Optional<String> test1 = Optional.ofNullable("test");
		assertEquals("test", test1.get());

		Optional<Object> nichts = Optional.ofNullable(null);
		assertFalse(nichts.isPresent());
	}

	// Optional.of geht mit "null" garantiert schief
	@Test(expected = NullPointerException.class)
	public void erzeugenAusNullMitOfGehtNicht() {
		Optional<Object> foo = Optional.of(null);
	}

	@Test
	public void checkForNull() {

		// Wie man mit Optionals null-Vergleiche vermeidet
		Optional<String> x = Optional.of("test");
		if (x.isPresent() && x.get().equals("test")) {
			// ...
			assertEquals("test", x.get());
		}

		assertFalse(Optional.empty().isPresent());
	}

	@Test
	public void defaultWertTest() {

		// Default-Wert direkt angeben
		assertEquals("bar", Optional.empty().orElse("bar"));

		// Default-Wert über eine Factory-Methode
		assertEquals("foo", Optional.empty().orElseGet(() -> "foo"));
	}

	@Test
	public void filterTest() {

		// Filter prüft den Inhalt des Optional und liefert ein Optional als Ergebnis
		Optional<String> x = Optional.of("test");
		assertEquals("test", x.filter(p -> "test".equals(p)).get());

		Optional<String> y = Optional.of("falsch");
		assertFalse(y.filter(p -> "test".equals(p)).isPresent());
	}

	@Test
	public void ifPresentTest() {

		// Ist das optional nicht leer, wird die Methode mit dem Inhalt ausgeführt
		Optional.of("test").ifPresent(System.out::println);

		// Ist das optional leer, passiet nichts
		Optional.empty().ifPresent(System.out::println);
	}

	@Test
	public void mapTest() {

		Optional<String> x = Optional.of(" test ");

		// map führt das Lambda auf dem Inhalt aus und liefert ein neues Optional
		assertEquals(Integer.valueOf(6), x.map(String::length).get());

		// map kann (wie filter) verkettet werden
		assertEquals(Integer.valueOf(4), x.map(String::trim).map(String::length).get());

		// ist das Optional leer, ist auch das Ergebnis leer -- ohne NPE!
		Optional<String> leer = Optional.empty();
		assertNull(leer.map(String::trim).orElse(null));
		assertNull(leer.map(String::length).orElse(null));
		assertNull(leer.map(String::trim).map(String::length).orElse(null));
	}

	@Test
	public void defaultTest() {
		assertEquals(Optional.ofNullable(getName()).orElse(this.getDefaultName()), "default");
		assertEquals(Optional.ofNullable(getName()).orElseGet(() -> this.getDefaultName()), "default");
		assertEquals(Optional.ofNullable(getName()).orElseGet(this::getDefaultName), "default");
	}

	private String getDefaultName() {
		System.out.println("default used");
		return "default";
	}

	private String getName() {
		return null;
	}

}