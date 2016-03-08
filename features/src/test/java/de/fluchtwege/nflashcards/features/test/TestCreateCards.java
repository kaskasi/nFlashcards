package de.fluchtwege.nflashcards.features.test;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import de.fluchtwege.nflashcards.features.boundaries.IRepository;
import de.fluchtwege.nflashcards.features.interactors.CreateCard;
import de.fluchtwege.nflashcards.features.models.FlashCard;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCreateCards {

	@Test
	public void CreateCardWillSaveCardToRepository() {
		IRepository repository = mock(IRepository.class);
		CreateCard createCard = new CreateCard(repository);
		createCard.execute();
		verify(repository).saveFlashcard(argThat(new ArgumentMatcher<FlashCard>() {
			@Override
			public boolean matches(Object argument) {
				return argument instanceof FlashCard;
			}
		}));
	}
}
