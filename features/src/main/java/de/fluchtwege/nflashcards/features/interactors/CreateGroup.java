package de.fluchtwege.nflashcards.features.interactors;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Group;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Maraqopa on 11/03/16.
 */
public class CreateGroup {

	public Observable<Group> createCard(final DataSource dataSource, final List<FlashCard> cards) {
		return Observable.create(new Observable.OnSubscribe<Group>() {
			@Override
			public void call(final Subscriber<? super Group> subscriber) {
				final Group group = new Group(cards);
				dataSource.createGroup(group).subscribe(new Action1<Void>() {
					@Override
					public void call(Void aVoid) {
						subscriber.onNext(group);
					}
				});
			}
		});
	}
}
