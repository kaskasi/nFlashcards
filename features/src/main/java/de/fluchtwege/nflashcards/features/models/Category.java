package de.fluchtwege.nflashcards.features.models;

/**
 * Created by Maraqopa on 09/03/16.
 */
public class Category {
	private String name;
	private int id;

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}
}
