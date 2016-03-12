package de.fluchtwege.nflashcards.features.models;

import java.util.List;

/**
 * Created by Maraqopa on 09/03/16.
 */
public class Group {
	List<FlashCard> cards;
	private int id;

	public Group(List<FlashCard> cards) {
		this.cards = cards;
	}

	public List<FlashCard> getCards() {
		return cards;
	}

	public void setId(int id) {
		this.id = id;
	}
}
