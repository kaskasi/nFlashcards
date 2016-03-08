package de.fluchtwege.nflashcards.features.interactors;

import de.fluchtwege.nflashcards.features.Feature;
import de.fluchtwege.nflashcards.features.boundaries.IRepository;
import de.fluchtwege.nflashcards.features.models.FlashCard;

public class CreateCard extends Feature {

	private final IRepository repository;

	public CreateCard(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute() {
		FlashCard card = new FlashCard();
		repository.saveFlashcard(card);
	}
}
