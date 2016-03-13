package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.CreateCard;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCreateCard {

	private DataSource dataSource;
	private FlashCard card;
	private List<CardContent> contentList;

	private final ArgumentMatcher<FlashCard>  matcher = new ArgumentMatcher<FlashCard>() {
		@Override
		public boolean matches(Object argument) {
			return argument instanceof FlashCard;
		}
	};

	@Before
	public void setUp() {
		dataSource = mock(DataSource.class);
		contentList = new ArrayList<>();
		contentList.add(new CardContent(new Category("name"), "value"));
		card = new FlashCard(contentList);
		Observable<Void> observable = Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
		doReturn(observable).when(dataSource).createCard(argThat(matcher));
	}

	@Test
	public void CreateCardWillSaveCardToRepository() {
		CreateCard createCard = new CreateCard();
		createCard.createCard(dataSource, contentList).subscribe();
		verify(dataSource).createCard(argThat(matcher));
	}

	@Test
	public void CreateCardWillReturnCreatedCard() {
		CreateCard createCard = new CreateCard();
		createCard.createCard(dataSource, contentList).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				assertEquals(card.getItems(), flashCard.getItems());
			}
		});
	}

	@After
	public void tearDown() {
		dataSource  = null;
		card = null;
	}
}
