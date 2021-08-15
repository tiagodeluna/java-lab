package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComparingAndSorting {

    public static void main (String ... args) {
        Animal panda = new Animal(1, "panda");
        Animal lion = new Animal(2, "lion");

        System.out.println("> panda.compareTo(lion) = " + panda.compareTo(lion));

        System.out.println("> AnimalComparator.compare(panda, lion) = " + new AnimalComparator().compare(panda, lion));

        System.out.println("\nAn Animal collection was created. Testing different ways of sorting elements...");
        var animals = new ArrayList<Animal>();
        animals.add(panda);
        animals.add(lion);
        Collections.sort(animals);
        System.out.println("> Collections.sort(List<Animal>) = " + animals.toString());

        animals.sort((o1, o2) -> {
            System.out.println("Sorting a list of Animal using a Comparator implemented with lambda expression...");
            return o2.id - o1.id;
        });
        System.out.println("> List.sort(<lambda comparator>) = " + animals.toString());

        System.out.println("Sorting a list of Animal using a Comparator implemented with Comparator.comparing()...");
        Comparator<Animal> comparator =
            Comparator.comparing(Animal::getId)
                .thenComparing(Animal::getName)
                .reversed();

        animals.sort(comparator);
        System.out.println("> List.sort(myComparator) = " + animals.toString());
    }

    /**
     * Implement Comparable<T> to allow a class (T) to implement compareTo().
     */
    static class Animal implements Comparable<Animal> {
        private int id;
        private String name;

        Animal(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Animal other) {
            System.out.println("...Comparing animals using Animal.compareTo(other)");
            return id - other.id;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Implement Comparator<T> to create a custom comparator for an specific type (T).
     */
    static class AnimalComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            System.out.println("...Comparing animals using Comparator.compare(a1,a2)");
            return o1.id - o2.id;
        }
    }
}
