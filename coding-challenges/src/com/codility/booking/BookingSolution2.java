package com.codility.booking;
import java.util.ArrayList;
import java.util.List;

public class BookingSolution2 {

	public static void main(String[] args) {
		BookingSolution2 s = new BookingSolution2();
		Point3D a1 = s.new Point3D(0, 5, 4);
		Point3D a2 = s.new Point3D(0, 0, -3);
		Point3D a3 = s.new Point3D(-2, 1, -6);
		Point3D a4 = s.new Point3D(1, -2, 2);
		Point3D a5 = s.new Point3D(1, 1, 1);
		Point3D a6 = s.new Point3D(4, -4, 3);
		
		Point3D[] A = {a1, a2, a3, a4, a5, a6};
		s.solution(A);
	}
	
	public int solution(Point3D[] points) {
		List<Double> result = new ArrayList<>();
		for(Point3D P : points) {
			double r = Math.pow(P.x, 2)+Math.pow(P.y, 2)+Math.pow(P.z, 2);
			if (!result.contains(r)) {
				result.add(r);
			}
		}
		
		System.out.println(result.size());
		return result.size();
	}

	public class Point3D {
		  public int x;
		  public int y;
		  public int z;

		  public Point3D(int x, int y, int z) {
			  this.x = x;
			  this.y = y;
			  this.z = z;
		  }
	}
}
