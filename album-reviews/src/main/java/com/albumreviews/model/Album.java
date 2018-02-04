package com.albumreviews.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album implements Comparable<Album> {

	private String artist;
	private String name;
	private Integer[] songRatings;
	private BigDecimal rating;
	
	public Album(String artist, String name, Integer[] songRatings) {
		this.artist = artist;
		this.name = name;
		this.songRatings = songRatings;
	}
	
	public void calculate(RatingCalculator calculator) {
		this.rating = calculator.calculate(songRatings);
	}

	@Override
	public int compareTo(Album that) {
		//Compare by ratings
		int ratingsComparation = this.rating.compareTo(that.rating);
		if (ratingsComparation != 0) {
			return ratingsComparation;
		}
		
		//Compare by album length - the lower the best (reversed)
		if (this.songRatings.length > that.songRatings.length) {
			return 1;
		} else if (this.songRatings.length < that.songRatings.length) {
			return -1;
		}
		//Compare by artist name (reversed)
		return (-1)*this.artist.compareTo(that.artist);
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s - %s", this.artist, this.name, this.rating);
	}
}
