package de.fluchtwege.nflashcards.presentation;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.List;

import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.presenter.CardListViewModel;
import de.fluchtwege.nflashcards.presentation.view.CardListContainer;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCardListViewModel {

	@Test
	public void CardListViewModelWillUpdateCardListContainer() {
		CardListContainer container = mock(CardListContainer.class);
		CardListViewModel viewModel = new CardListViewModel(container);
		verify(container).update(argThat(new ArgumentMatcher<List<FlashCard>>() {
			@Override
			public boolean matches(Object argument) {
				return true;
			}
		}));
	}
}
