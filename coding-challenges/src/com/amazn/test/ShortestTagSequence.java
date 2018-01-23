package com.amazn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finds the shortest sequence containing all the specified tags in a list of available tags. 
 * 
 * @author Tiago Luna
 */
public class ShortestTagSequence {

	public static void main(String[] args) {
		List<String> targetList = new ArrayList<>();
		targetList.add("abc");
		targetList.add("2");

		List<String> availableTagList = new ArrayList<>();
		availableTagList.add("aaa");
		availableTagList.add("abc");
		availableTagList.add("k");
		availableTagList.add("2");
		availableTagList.add("2");
		availableTagList.add("abc");
		availableTagList.add("2");

		ShortestTagSequence main = new ShortestTagSequence();
		List<Integer> substrings = main.subStringsLessKDist(targetList, availableTagList);
		System.out.println(substrings);
	}

	public List<Integer> subStringsLessKDist(List<String> targetList, List<String> availableTagList) {
		List<Integer> sequenceList = new ArrayList<>();

		//Check the params limits 
		if (targetList == null || targetList.isEmpty()
				|| availableTagList == null || availableTagList.isEmpty()) {
			sequenceList.add(0);
			return sequenceList;
		}

		Map<String, Integer> occurrences = new HashMap<>();
		TagSequence seq = null;

		for (int i = 0; i < availableTagList.size(); i++) {
			String tag = availableTagList.get(i);

			if (targetList.contains(tag)) {
				occurrences.put(tag, i);
				//Checks if the 'occurrences' map has all the records in 'targetList' by comparing their sizes.
				// In this case, a new sequence has been identified.
				if (occurrences.size() == targetList.size()) {
					TagSequence newSeq = findSequence(occurrences);
					//Checks if the new sequence is smaller than the previous one.
					if (seq == null || newSeq.compareTo(seq) < 0) {
						seq = newSeq;
					}
				}
			}
		}

		//If no sequence was found, return [0].
		if (seq == null) {
			sequenceList.add(0);
			return sequenceList;
		}
		
		sequenceList.add(seq.getFirst());
		sequenceList.add(seq.getLast());
		return sequenceList;
	}
	
	public TagSequence findSequence(Map<String, Integer> occurrences) {
		int first = Integer.MAX_VALUE;
		int last = Integer.MIN_VALUE;
		
		for(Integer index : occurrences.values()) {
			first = first > index ? index : first;
			last = last < index ? index : last;
		}
		
		return new TagSequence(first, last);
	}

	class TagSequence implements Comparable<TagSequence> {
		private int first;
		private int last;
		
		public TagSequence(int first, int last) {
			super();
			this.first = first;
			this.last = last;
		}

		public int size() {
			return last - first;
		}

		public int getFirst() {
			return first;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public int getLast() {
			return last;
		}

		public void setLast(int last) {
			this.last = last;
		}

		@Override
		public int compareTo(TagSequence that) {
			final int equal = 0;
			final int lower = -1;
			final int higher = 1;
			
			if (this == that) {
				return equal;
			}
			
			if (this.size() < that.size() ||
					(this.size() == that.size() && this.first < that.first)) {
				return lower;
			}
			if (this.size() > that.size() ||
					(this.size() == that.size() && this.first > that.first)) {
				return higher;
			}
			
			return equal;
		}
	}
}
