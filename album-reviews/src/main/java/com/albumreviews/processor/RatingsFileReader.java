package com.albumreviews.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.albumreviews.model.Album;

public class RatingsFileReader {

	public static List<Album> read(String path) {
		List<Album> albums = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String str;

			while ((str = br.readLine()) != null) {
				String[] line = str.split("\\|");
				
				Album album = new Album(line[0], line[1], readRatings(line));
				albums.add(album);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return albums;
	}
	
	private static Integer[] readRatings(String[] line) {
		Integer[] ratings = new Integer[line.length-2];
		
		for (int i = 2; i < line.length; i++) {
			ratings[i-2] = parseInt(line[i]);
		}
		
		return ratings;
	}
	
	private static int parseInt(String str) {
		int val;
		
		try {
			val = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			val = -1;
		}
		
		return val;
	}
}
