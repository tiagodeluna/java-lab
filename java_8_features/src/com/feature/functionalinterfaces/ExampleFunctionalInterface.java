package com.feature.functionalinterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ExampleFunctionalInterface {

	public static void main(String[] args) {
		List<Person> list = new ArrayList<>();
		list.add(new Person("Joseph Campbell"));
		list.add(new Person("Anna Lena"));
		list.add(new Person("Tiago Luna"));
		list.add(new Person("Raissa Luna"));
		list.add(new Person("Charles Lewis"));
		list.add(new Person("Lunara Sparks"));
		list.add(new Person("Matilda Luna"));
		
		//Passes a lambda expression that satisfies the functional interface
		printPersonsWithLuna(list,
				p -> p.getName().contains("Luna"));		
	}
	
	//Uses the Predicate functional interface (defined in java.util.function) instantiated with the lambda expression
	public static void printPersonsWithLuna(
	    List<Person> roster, Predicate<Person> tester) {
	    for (Person p : roster) {
	        if (tester.test(p)) {
	            p.printPerson();
	        }
	    }
	}
	
	
}
