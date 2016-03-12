package de.fluchtwege.nflashcards.features.models;

import java.util.ArrayList;
import java.util.List;

public class FlashCard {

	int id;

	public int getId() {
		return id;
	}

	private List<CardContent> items = new ArrayList<>();

	public FlashCard(final List<CardContent> items) {
		this.items = items;
	}

	public List<CardContent> getItems() {
		return items;
	}

	public CardContent getCardContent(int index) {
		return items.get(index);
	}

	public int addContent(CardContent content) {
		items.add(content);
		return items.size() - 1;
	}

	public void removeContent(int contentId) {
		items.remove(contentId);
	}

	public void setId(int id) {
		this.id = id;
	}
}
