package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.GetGroup;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.features.models.Group;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestGetGroup {

	final int groupId = 0;
	private DataSource dataSource;
	private Group group;
	private List<Group> groups;

	@Before
	public void setUp() {
		dataSource = createDataSource();
		group = new Group(new ArrayList<FlashCard>());
		groups = new ArrayList<>();
	}

	private DataSource createDataSource() {
		final DataSource dataSource = mock(DataSource.class);

		Observable<List<Group>> listObservable = Observable.create(new Observable.OnSubscribe<List<Group>>() {
			@Override
			public void call(Subscriber<? super List<Group>> subscriber) {
				subscriber.onNext(groups);
				subscriber.onCompleted();
			}
		});
		doReturn(listObservable).when(dataSource).getGroups();

		Observable<Group> groupObservable = Observable.create(new Observable.OnSubscribe<Group>() {
			@Override
			public void call(Subscriber<? super Group> subscriber) {
				subscriber.onNext(group);
				subscriber.onCompleted();
			}
		});
		doReturn(groupObservable).when(dataSource).getGroup(groupId);

		return dataSource;
	}


	@Test
	public void GetGroupWillRequestSpecifiedGroupFromDataSource() {
		final GetGroup getGroup = new GetGroup();
		getGroup.getGroup(dataSource, groupId).subscribe();
		verify(dataSource).getGroup(groupId);
	}

	@Test
	public void GetGroupWillReturnSpecifiedGroup() {
		final GetGroup getGroup = new GetGroup();
		getGroup.getGroup(dataSource, groupId).subscribe(new Action1<Group>() {
			@Override
			public void call(Group group) {
				assertEquals(groupId, group.getId());
			}
		});
	}

	@Test
	public void GetGroupWillRequestAllGroupsFromDataSource() {
		final GetGroup getGroup = new GetGroup();
		getGroup.getGroups(dataSource).subscribe();
		verify(dataSource).getGroups();
	}

	@Test
	public void GetGroupsWillReturnAllGroups() {
		final GetGroup getGroup = new GetGroup();
		getGroup.getGroups(dataSource).subscribe(new Action1<List<Group>>() {
			@Override
			public void call(List<Group> groupList) {
				assertEquals(groups, groupList);
			}
		});
	}

	@After
	public void tearDown() {
		groups = null;
		group = null;
		dataSource = null;
	}
}
