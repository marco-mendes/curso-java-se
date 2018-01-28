package javaoito.exemplo03;

/**
 * @author Marco Mendes
 * @since 2018
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
