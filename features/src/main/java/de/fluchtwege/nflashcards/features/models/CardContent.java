package de.fluchtwege.nflashcards.features.models;

/**
 * Created by Maraqopa on 11/03/16.
 */
public class CardContent<T> {

	private final Category category;
	private T value;

	public CardContent(Category category, T value) {
		this.category = category;
		this.value = value;
	}

	public Category getCategory() {
		return category;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
