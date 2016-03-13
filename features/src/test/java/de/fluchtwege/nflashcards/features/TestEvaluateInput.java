package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.EvaluateInput;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestEvaluateInput {

	private final int cardId = 0;
	private final int categoryId = 0;
	private final String correctEntry = "correctText";
	private final String wrongEntry = "wrongText";
	private DataSource dataSource;
	private FlashCard card;


	@Before
	public void setUp() {
		dataSource = mock(DataSource.class);
		card = new FlashCard(new ArrayList<CardContent>());
		card.addContent(new CardContent(new Category("categoryName"), correctEntry));
		Observable<FlashCard> observable = Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(Subscriber<? super FlashCard> subscriber) {
				subscriber.onNext(card);
				subscriber.onCompleted();
			}
		});
		doReturn(observable).when(dataSource).getCard(cardId);
	}

	@Test
	public void EvaluateInputWillRequestSpecifiedCardFromDataSource() {
		final EvaluateInput evaluateInput = new EvaluateInput();
		evaluateInput.evaluateInput(dataSource, cardId, categoryId, correctEntry).subscribe();
		verify(dataSource).getCard(cardId);
	}

	@Test
	public void EvaluateInputWillReturnTrueIfEntryMatchesContentFromDataSource() {
		final EvaluateInput evaluateInput = new EvaluateInput();
		evaluateInput.evaluateInput(dataSource, cardId, categoryId, correctEntry).subscribe(new Action1<Boolean>() {
			@Override
			public void call(Boolean result) {
				assertTrue(result);
			}
		});
	}

	@Test
	public void EvaluateInputWillReturnFalseIfEntryMatchesContentFromDataSource() {
		final EvaluateInput evaluateInput = new EvaluateInput();
		evaluateInput.evaluateInput(dataSource, cardId, categoryId, wrongEntry).subscribe(new Action1<Boolean>() {
			@Override
			public void call(Boolean result) {
				assertFalse(result);
			}
		});
	}

	@After
	public void tearDown() {
		dataSource = null;
		card = null;
	}
}
