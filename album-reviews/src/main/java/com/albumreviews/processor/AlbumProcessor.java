package com.albumreviews.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.albumreviews.model.Album;

public class AlbumProcessor {

	private static final String FILE_PATH = "src\\main\\resources\\ratings.txt";

	public void process() {
		//Load data
		List<Album> albums = RatingsFileReader.read(FILE_PATH);
		
		//Calculate ratings
		albums.forEach(a -> a.calculate(array -> {
			int number = 0;
			float sum = 0;
			for (int i=0; i < array.length; i++) {
				if (array[i] >= 0) {
					number += 1;
					sum += (array[i] == 5) ? 5.5 : array[i];
//					sum += array[i];
				}
			}
			
			return BigDecimal.valueOf((sum * 2 ) / number).setScale(2, RoundingMode.DOWN);
		}));
		
		//Order records
		albums.sort((a, b) -> b.compareTo(a));
		
		//Print result
		int i = 1;
		for (Album album : albums) {
			System.out.println(String.format("%s. %s", i++, album));
		}
	}
}
