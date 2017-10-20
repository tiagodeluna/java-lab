package com.feature.lambdaexpressions.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleListLambdaExpressions {

	private static List<String> names = Arrays.asList("22", "4444", "1", "333", "666666", "55555");

	
	public static void main(String[] args) {
		map();
		mapToPrimitiveStream();
		filterAndCollect();
		sort();
		sortReversed();
	}

	private static void map() {
		System.out.println(":: MAP ::");
		//Converting a list of strings into a stream with all lengths
		Stream<Integer> lengths = names
				.stream()
				.map(name -> name.length());
		//Print result
		lengths.forEach(System.out::println);
	}
	
	private static void mapToPrimitiveStream() {
		System.out.println(":: MAP TO INT ::");
		//Converting a list of strings into a int stream with all lengths
		names.stream()
			.mapToInt(name -> name.length())
			.forEach(System.out::println);
	}
	
	private static void filterAndCollect() {
		System.out.println(":: FILTER AND COLLECT ::");
		//Apply a filter
		Stream<String> lengths = names.stream().filter(n -> n.length() > 3);
		//Convert the stream to a List again
		lengths.collect(Collectors.toList())
			.forEach(System.out::println);
	}
	
	private static void sort() {
		System.out.println(":: SORT ::");
		names.sort((first, second) -> first.hashCode() - second.hashCode());
		names.forEach(System.out::println);
	}
	
	private static void sortReversed() {
		System.out.println(":: SORT REVERSED ::");
		names.sort(
				Comparator.comparing(String::hashCode)
					.reversed());
		names.forEach(System.out::println);
	}
	
}
