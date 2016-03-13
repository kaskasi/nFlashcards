package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.CreateCategory;
import de.fluchtwege.nflashcards.features.models.Category;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCreateCategory {
	private DataSource dataSource;
	private Category category;

	@Before
	public void setUp() {
		dataSource = mock(DataSource.class);
		category = new Category("name");
		Observable<Category> observable = Observable.create(new Observable.OnSubscribe<Category>() {
			@Override
			public void call(Subscriber<? super Category> subscriber) {
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
		doReturn(observable).when(dataSource).createCategory(category);
	}

	@Test
	public void CreateCategoryWillSaveCategoryToRepository() {
		CreateCategory createCategory = new CreateCategory();
		createCategory.createCategory(dataSource, category).subscribe();
		verify(dataSource).createCategory(category);
	}

	@Test
	public void CreateCategoryWillReturnCreatedCategory() {
		CreateCategory createCategory = new CreateCategory();
		createCategory.createCategory(dataSource, category).subscribe(new Action1<Category>() {
			@Override
			public void call(Category categoryFromDataSource) {
				assertEquals(category, categoryFromDataSource);
			}
		});
	}

	@After
	public void tearDown() {
		dataSource  = null;
		category = null;
	}
}
