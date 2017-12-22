/**
 * 
 */
package exemplos.java8.lambda01;

import java.math.BigDecimal;

/**
 * @author Marco Mendes
 * @since 2017
 * 1/1000 (IEEE 754)
 */
public class Developer implements Comparable<Developer> {
	
	private String name;
	private BigDecimal salary;
	private int age;
	
	public Developer(String name, BigDecimal salary, int age) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
	@Override
	public String toString() {
		return "Developer [name=" + name + ", salary=" + salary + ", age=" + age + "]";
	}

	public int compareTo(Developer outro) {
		return this.getName().compareTo(outro.getName());
	}
}
