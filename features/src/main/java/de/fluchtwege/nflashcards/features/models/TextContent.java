package de.fluchtwege.nflashcards.features.models;

/**
 * Created by Maraqopa on 09/03/16.
 */
public class TextContent  {

	private final String text;
	private Category category;

	public TextContent(final String text, final Category category) {
		this.text = text;
		this.category = category;
	}

	public String getText() {
		return text;
	}

	public Category getCategory() {
		return category;
	}
}
