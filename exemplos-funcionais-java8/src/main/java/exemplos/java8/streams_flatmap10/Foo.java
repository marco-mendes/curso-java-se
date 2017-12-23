/**
 * 
 */
package exemplos.java8.streams_flatmap10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco Mendes
 * @since 2017
 */
class Foo {
	String name;
	List<Bar> bars = new ArrayList<>();

	Foo(String name) {
		this.name = name;
	}
}

class Bar {
	String name;

	Bar(String name) {
		this.name = name;
	}
}
