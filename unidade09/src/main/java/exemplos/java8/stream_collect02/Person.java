/**
 * 
 */
package exemplos.java8.stream_collect02;

/**
 * @author Marco Mendes
 * @since 2017
 */
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
