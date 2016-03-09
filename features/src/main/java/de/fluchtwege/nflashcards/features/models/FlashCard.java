package de.fluchtwege.nflashcards.features.models;

import java.util.ArrayList;
import java.util.List;

public class FlashCard {
	private List<Content> items = new ArrayList<>();

	public FlashCard(List<Content> items) {
		this.items = items;
	}

	public List<Content> get() {
		return items;
	}

	public Content get(int index) {
		return items.get(index);
	}

	public void addContent(Content content) {
		items.add(content);
	}

	public void removeContent(int contentId) {
		items.remove(contentId);
	}
}
