package de.fluchtwege.nflashcards.presentation.presenter;

import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.view.TestCardContainer;
import rx.subjects.PublishSubject;

/**
 * Created by Maraqopa on 11/03/16.
 */
public class TestCardViewModel implements ViewModel{

	private final TestCardContainer container;
	private final PublishSubject<String> subject;

	public TestCardViewModel(TestCardContainer container) {
		this.container = container;
		subject = PublishSubject.create();
		container.setPublishSubject(subject);
	}

	public void update(FlashCard flashCard, int categoryIndex) {
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
