package de.fluchtwege.nflashcards.features.models;

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
