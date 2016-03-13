package de.fluchtwege.nflashcards.presentation;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.presentation.presenter.CreateCardViewModel;
import de.fluchtwege.nflashcards.presentation.view.CreateCardContainer;
import rx.subjects.PublishSubject;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Maraqopa on 13/03/16.
 */
public class TestCreateCardViewModel {

	@Test
	public void WhenCreateCardViewModelIsCreatedCreateCardContainerIsUpdated() {
		CreateCardContainer container = mock(CreateCardContainer.class);
		doReturn(PublishSubject.create()).when(container).getClickButtonSubject();

		CreateCardViewModel viewModel = new CreateCardViewModel(container);
		verify(container).update(argThat(new ArgumentMatcher<List<Category>>() {
			@Override
			public boolean matches(Object argument) {
				return true;
			}
		}));
	}

	@Test
	public void CreateCardViewModelWillSubscribeToCardCreatedSubject() {
		CreateCardContainer container = mock(CreateCardContainer.class);
		doReturn(PublishSubject.create()).when(container).getClickButtonSubject();

		CreateCardViewModel viewModel = new CreateCardViewModel(container);
		verify(container).getClickButtonSubject();
	}

	@Test
	public void WhenEnteringDataIsDoneViewModalWillCreateCard() {
		CreateCardContainer container = mock(CreateCardContainer.class);
		PublishSubject<List<CardContent>> subject = PublishSubject.create();
		doReturn(subject).when(container).getClickButtonSubject();

		CreateCardViewModel viewModel = spy(new CreateCardViewModel(container));
		final List<CardContent> content = new ArrayList<>();
		subject.onNext(content);
		subject.onCompleted();
		verify(viewModel).createCard(content);

	}

	@Test
	public void WhenCardHasBeenSavedToDataSourceCreateCardContainerIsDone() {
		CreateCardContainer container = mock(CreateCardContainer.class);
		doReturn(PublishSubject.create()).when(container).getClickButtonSubject();

		CreateCardViewModel viewModel = new CreateCardViewModel(container);
		viewModel.createCard(new ArrayList<CardContent>());
		verify(container).done();
	}

}
