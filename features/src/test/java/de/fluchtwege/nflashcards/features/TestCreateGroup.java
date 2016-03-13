package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.CreateGroup;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.features.models.Group;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCreateGroup {
	private DataSource dataSource;
	private Group group;

	private List<FlashCard> cards;

	private final ArgumentMatcher<Group> matcher = new ArgumentMatcher<Group>() {
		@Override
		public boolean matches(Object argument) {
			return argument instanceof Group;
		}
	};

	@Before
	public void setUp() {
		dataSource = mock(DataSource.class);
		cards = new ArrayList<>();
		cards.add(new FlashCard(new ArrayList<CardContent>()));
		group = new Group(cards);
		Observable<Void> observable = Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
		doReturn(observable).when(dataSource).createGroup(argThat(matcher));
	}

	@Test
	public void CreateGroupWillSaveGroupToRepository() {
		CreateGroup createGroup = new CreateGroup();
		createGroup.createGroup(dataSource, cards).subscribe();
		verify(dataSource).createGroup(argThat(matcher));
	}

	@Test
	public void CreateGroupWillReturnCreatedGroup() {
		CreateGroup createGroup = new CreateGroup();
		createGroup.createGroup(dataSource, cards).subscribe(new Action1<Group>() {
			@Override
			public void call(Group groupFromDataSource) {
				assertEquals(group.getCards(), groupFromDataSource.getCards());
			}
		});
	}

	@After
	public void tearDown() {
		dataSource  = null;
		group = null;
	}
}
