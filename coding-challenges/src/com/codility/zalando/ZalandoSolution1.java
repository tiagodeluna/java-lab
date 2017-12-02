package com.codility.zalando;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZalandoSolution1 {
	static final String regex =
			"([a-zA-Z]+)\\.([a-zA-Z]+),\\s([a-zA-Z]+),\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})";
	static final String datePattern = "yyyy-MM-dd hh:mm:ss";

	public static void main(String[] args) {
		String str = "photo.jpg, Warsaw, 2013-09-05 14:08:15"
				+"john.png, London, 2015-06-20 15:13:22"
				+"myFriends.png, Warsaw, 2013-09-05 14:07:13"
				+"Eiffel.jpg, Paris, 2015-07-23 08:03:02"
				+"pisatower.jpg, Paris, 2015-07-22 23:59:59"
				+"BOB.jpg, London, 2015-08-05 00:02:03"
				+"notredame.png, Paris, 2015-09-01 12:00:00"
				+"me.jpg, Warsaw, 2013-09-06 15:40:22"
				+"a.png, Warsaw, 2016-02-13 13:33:50"
				+"b.jpg, Warsaw, 2016-01-02 15:12:22"
				+"c.jpg, Warsaw, 2016-01-02 14:34:30"
				+"d.jpg, Warsaw, 2016-01-02 15:15:01"
				+"e.png, Warsaw, 2016-01-02 09:49:09"
				+"f.png, Warsaw, 2016-01-02 10:55:32"
				+"g.jpg, Warsaw, 2016-02-29 22:13:11";

        System.out.println(solution(str));
		
	}
	
	public static String solution(String S) {
		Map<String, List<Photo>> photos = new HashMap<>();
        
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(S);
		int index = 0;
		
		while(!m.hitEnd()) {
			
		    if (m.find()) {
		    	Date date = null;
		    	try {
		    		DateFormat format = new SimpleDateFormat(datePattern);
		    		date = format.parse(m.group(4));
				} catch (ParseException e) {
					System.err.println("Error converting date");
				}

		    	Photo photo = new Photo(index, m.group(3), m.group(2), date);
		    	if (!photos.containsKey(photo.getCity())) {
		    		photos.put(photo.getCity(), new ArrayList<Photo>());
		    	}
		    	photos.get(photo.getCity()).add(photo);
		    	index++;
		    }
		}
		
		//Sort all items
		photos.forEach((key,list) -> list.sort((first, second) -> first.date.compareTo(second.date)));
		
		List<Photo> resultList = new ArrayList<>();
		
		//Set the number
		photos.forEach((key,list) -> {
			int length = Integer.toString(list.size()).length();
			int i = 1;
			for (Photo o : list) {
				o.setNumber(String.format("%0"+length+"d", i++));
			}
		});

		for(List<Photo> list : photos.values()) {
			resultList.addAll(list);
		}
		
		resultList.sort((first, second) -> first.index - second.index);
		
		StringBuffer sb = new StringBuffer();

		resultList.forEach((o)-> sb.append(o.toString()+"\n"));
//		resultList.forEach((o)-> sb.append(o.toString()));
		
		return sb.toString();
	}
	
	static class Photo {
		private int index;
		private String number;
        private String city;
        private String extension;
        private Date date;
        
		public Photo(int index, String city, String extension, Date date) {
			super();
			this.index = index;
			this.city = city;
			this.extension = extension;
			this.date = date;
		}
		
		public int getIndex() {
			return index;
		}

		public void setNumber(String number) {
			this.number = number;
		}
		
		public String getNumber() {
			return number;
		}

		public String getCity() {
			return city;
		}

		public String getExtension() {
			return extension;
		}

		public Date getDate() {
			return date;
		}

		@Override
		public String toString() {
			return String.format("%s%s.%s", city, number, extension);
		}
    }

}
