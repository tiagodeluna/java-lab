module zoo.animal.talks {
	exports zoo.animal.talks.content to zoo.staff;
	exports zoo.animal.talks.media;
	exports zoo.animal.talks.schedule;

	requires transitive zoo.animal.care;
}