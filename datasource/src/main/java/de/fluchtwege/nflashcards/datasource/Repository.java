package de.fluchtwege.nflashcards.datasource;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.IRepository;
import de.fluchtwege.nflashcards.features.models.FlashCard;

public class Repository implements IRepository {

	private List<FlashCard> cards = new ArrayList<>();

	@Override
	public void saveFlashcard(FlashCard card) {
		cards.add(card);
	}


}
