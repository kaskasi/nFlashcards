package de.fluchtwege.nflashcards.features.boundaries;

import de.fluchtwege.nflashcards.features.models.FlashCard;

public interface IRepository {

	void saveFlashcard(FlashCard card);
}
