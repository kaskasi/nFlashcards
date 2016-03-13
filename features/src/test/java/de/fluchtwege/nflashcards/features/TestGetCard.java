package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.GetCard;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestGetCard {

	private FlashCard card;
	private ArrayList<FlashCard> cards;
	final int cardId = 0;
	private DataSource dataSource;


	@Before
	public void setUp() {
		card = new FlashCard(new ArrayList<CardContent>());
		cards = new ArrayList<>();
		cards.add(card);
		dataSource = createDataSource();
	}

	private DataSource createDataSource() {
		final DataSource dataSource = mock(DataSource.class);

		Observable<List<FlashCard>> cardListObservable = Observable.create(new Observable.OnSubscribe<List<FlashCard>>() {
			@Override
			public void call(Subscriber<? super List<FlashCard>> subscriber) {
				subscriber.onNext(cards);
				subscriber.onCompleted();
			}
		});
		doReturn(cardListObservable).when(dataSource).getCards();

		Observable<FlashCard> cardObservable = Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(Subscriber<? super FlashCard> subscriber) {
				subscriber.onNext(card);
				subscriber.onCompleted();
			}
		});
		doReturn(cardObservable).when(dataSource).getCard(cardId);

		return dataSource;
	}

	@Test
	public void GetCardWillRequestSpecifiedCardFromDataSource() {
		final GetCard getCard = new GetCard();
		getCard.getCard(dataSource, cardId).subscribe();
		verify(dataSource).getCard(cardId);
	}

	@Test
	public void GetCardWillReturnSpecifiedCard() {
		final GetCard getCard = new GetCard();
		getCard.getCard(dataSource, cardId).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				assertEquals(cardId, flashCard.getId());
			}
		});
	}

	@Test
	public void GetCardsWillRequestAllCardsFromDataSource() {
		final GetCard getCard = new GetCard();
		getCard.getCards(dataSource).subscribe();
		verify(dataSource).getCards();
	}

	@Test
	public void GetCardsWillReturnAllCards() {
		final GetCard getCard = new GetCard();
		getCard.getCards(dataSource).subscribe(new Action1<List<FlashCard>>() {
			@Override
			public void call(List<FlashCard> flashCards) {
				assertEquals(cards, flashCards);
			}
		});
	}

	@After
	public void tearDown() {
		card = null;
		cards = null;
		dataSource = null;
	}
}
