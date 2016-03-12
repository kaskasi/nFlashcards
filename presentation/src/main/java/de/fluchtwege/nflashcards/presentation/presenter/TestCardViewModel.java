package de.fluchtwege.nflashcards.presentation.presenter;

import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.view.TestCardContainer;
import rx.subjects.PublishSubject;

public class TestCardViewModel extends ViewModel<TestCardContainer> {

	private final PublishSubject<String> subject;

	public TestCardViewModel(final TestCardContainer testCardContainer) {
		container = testCardContainer;
		subject = PublishSubject.create();
	}

	public void update(FlashCard flashCard, int categoryIndex) {
		container.setPublishSubject(subject);
		container.update(flashCard, categoryIndex);
	}

	public PublishSubject<String> getTextChangeSubject() {
		return subject;
	}

	public void updateTest(final boolean testCorrect) {
		container.updateTest(testCorrect);
	}

	public void testDone() {
		container.post(new Runnable() {
			@Override
			public void run() {
				container.testDone();
			}
		});
	}

}
