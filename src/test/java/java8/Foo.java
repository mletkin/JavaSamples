package java8;

class Foo {

	private int count = 0;
	String value;

	Foo(String value) {
		this.value = value;
	}

	public Foo() {
		// TODO Auto-generated constructor stub
	}

	public void print(String s) {
		count++;
		System.out.println(s + " -> " + count);
	}

	public static void prints(String s) {
		System.out.println(s);
	}

	public void print() {
		print(value);
	}
}