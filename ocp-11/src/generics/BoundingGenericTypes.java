package generics;

import generics.model.Crate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * A wildcard generic type is an unknown generic type represented with '?'.
 * It can be used in three different ways:
 *  - Unbounded Wildcards : immutable, accepts instances of any type.
 *  - Upper-Bounded Wildcards : immutable, uses "extends", accepts the referred class or its sub-classes, collection becomes a producer.
 *  - Lower-Bounded Wildcards : uses "super", accepts the referred class or its super-classes, collection becomes a consumer.
 */
public class BoundingGenericTypes {

    static class Vehicle {}
    static class Car extends Vehicle {}
    static class Ferrari extends Car {}

    public static void main(String... args) {
        /*
         * Demo Unbounded Wildcards
        * */
        List<String> words = List.of("This", "is", "weird");
        demoUnboundedWildcard(words);
        List<Integer> numbers = List.of(1, 10, 100);
        demoUnboundedWildcard(numbers);

        /*
        * Demo Upper-Bounded Wildcards
        * */
        List<Ferrari> ferraris = new ArrayList<>();
        ferraris.add(new Ferrari());
//        List<Car> cars = ferraris; //Does not compile, but...
        List<? extends Car> cars = ferraris;
        // See more details in the method below
        demoUpperBoundedWildcard(cars);

        /*
        * Demo Lower-Bounded Wildcards
        * */
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Ferrari());
//        List<Car> cars2 = vehicles; //Does not compile, but...
        List<? super Car> cars2 = vehicles;
        // See more details in the method below
        demoLowerBoundedWildcard(cars2);
    }

    /**
     * This is basically a list of "whatever". It is treated as if all elements are of type Object, but at the same time
     * it accepts a list of any type (List<String>, List<Pokemon>, etc). That wouldn't be the case if we used
     * List<Object> instead.
     * @param list List of whatever.
     */
    static void demoUnboundedWildcard(List<?> list) {
        for (Object o: list) {
            System.out.println(o);
        }
//        list.add("text"); //Does not compile since the unbounded generics are immutable
        list.removeIf(o -> o instanceof String);
//        list.removeIf(String::isEmpty); //Does not compile because isEmpty is not defined on Object
    }
    /**
     * Using '? extends', or Upper-Bounded Wildcards, the list becomes logically immutable, since it is not possible to infer
     * which actual type it contains.
     */
    static void demoUpperBoundedWildcard(List<? extends Car> list) {
        Car car = list.get(0); //Every Ferrari is a car
        Vehicle vehicle = list.get(0); //Every Ferrari/Car is a Vehicle
//        Ferrari ferrari = list.get(2); //Does not compile, since we cannot guarantee that it is a Ferrari.

        //Since we used '? extends' the list became logically immutable, so none of these lines compile
//        list.add(new Car());
//        list.add(new Object());
//        list.add(new Vehicle());
//        list.add(new Ferrari());
    }

    /**
     * Useing '? super', or Lower-Bounded Wildcards, we are telling Java that the list will be a list of {@link Car} or any
     * its superclasses. But as we can't determine which actual type it is, only the exact type (Car) and its subtypes are
     * accepted when adding elements.
     */
    static void demoLowerBoundedWildcard(List<? super Car> list) {
        //None of these lines compile, since the list became a consumer
//        Car car = list.get(0); //Maybe it is a Vehicle or Object...
//        Vehicle vehicle = list.get(1); //Maybe it is an Object...
//        Ferrari ferrari = list.get(2); //It could not be a Ferrari...

        // Only the exact type or subtypes are allowed
//        list.add(new Vehicle());
//        list.add(new Object());
        list.add(new Car());
        list.add(new Ferrari());
    }
}
