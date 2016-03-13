package de.fluchtwege.nflashcards.presentation.flowcontroller;

import android.os.Handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import de.fluchtwege.nflashcards.features.interactors.GetCard;
import de.fluchtwege.nflashcards.features.interactors.EvaluateInput;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.presenter.TestCardViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static de.fluchtwege.nflashcards.presentation.activity.nFlashcardsActivity.repository;

public class TestFlowController implements FlowController<TestCardViewModel> {

	private int numberOfCards;
	private Set<Integer> testedCards = new HashSet<>();

	@Override
	public void perform(final TestCardViewModel viewModel) {
		new GetCard().getCards(repository).subscribe(new Action1<List<FlashCard>>() {
			@Override
			public void call(final List<FlashCard> cards) {
				testNextCard(viewModel, cards, 0);
				numberOfCards = cards.size();
			}
		});
	}

	private void testNextCard(final TestCardViewModel viewModel, final List<FlashCard> cards, final int index) {
		final FlashCard card = cards.get(index);
		testedCards.add(index);
		//int numberOfCategories = card.getItems().size();
		viewModel.update(card, 3);
		viewModel
				.getTextChangeSubject()
				.debounce(750, TimeUnit.MILLISECONDS)
				.subscribe(new Action1<String>() {
					@Override
					public void call(String changedText) {
						checkEntry(changedText, cards, viewModel, index);
					}
				});
	}

	private void checkEntry(final String changedText, final List<FlashCard> cards, final TestCardViewModel viewModel, final int index) {
		new EvaluateInput().evaluateInput(repository, index, 3, changedText)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Boolean>() {
					@Override
					public void call(Boolean testCorrect) {
						viewModel.updateTest(testCorrect);
						if (testCorrect) {
							new Handler().postDelayed(new Runnable() {
								@Override
								public void run() {
									if (index + 1 < numberOfCards) {
										testNextCard(viewModel, cards, index + 1);
									} else {
										viewModel.testDone();
									}
								}
							}, 750);

						}
					}
				});
	}

}
