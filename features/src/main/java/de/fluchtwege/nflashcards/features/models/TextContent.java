package de.fluchtwege.nflashcards.features.models;

/**
 * Created by Maraqopa on 09/03/16.
 */
public class TextContent implements Content<String> {

	private final String text;

	public TextContent(final String text) {
		this.text = text;
	}

	@Override
	public String get() {
		return text;
	}
}
