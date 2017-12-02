package com.codility.lessons;

public class FrogJmp {

	public static void main(String[] args) {
		System.out.println(solution(10, 10, 30));
	}
	
	static int solution(int X, int Y, int D) {
		return (Y - X + (D - 1)) / D;
	}

}
