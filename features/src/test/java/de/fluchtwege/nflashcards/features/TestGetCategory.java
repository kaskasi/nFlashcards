package de.fluchtwege.nflashcards.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.GetCategory;
import de.fluchtwege.nflashcards.features.models.Category;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestGetCategory {

	final int categoryId = 0;
	private DataSource dataSource;
	private Category category;
	private List<Category> categories;

	@Before
	public void setUp() {
		dataSource = createDataSource();
		category = new Category("name");
		categories = new ArrayList<>();
		categories.add(category);
	}

	private DataSource createDataSource() {
		final DataSource dataSource = mock(DataSource.class);

		Observable<List<Category>> categoryListObservable = Observable.create(new Observable.OnSubscribe<List<Category>>() {
			@Override
			public void call(Subscriber<? super List<Category>> subscriber) {
				subscriber.onNext(categories);
				subscriber.onCompleted();
			}
		});
		doReturn(categoryListObservable).when(dataSource).getCategories();

		Observable<Category> categoryObservable = Observable.create(new Observable.OnSubscribe<Category>() {
			@Override
			public void call(Subscriber<? super Category> subscriber) {
				subscriber.onNext(category);
				subscriber.onCompleted();
			}
		});
		doReturn(categoryObservable).when(dataSource).getCategory(categoryId);

		return dataSource;
	}


	@Test
	public void GetCategoryWillRequestSpecifiedCategoryFromDataSource() {
		final GetCategory getCategory = new GetCategory();
		getCategory.getCategory(dataSource, categoryId).subscribe();
		verify(dataSource).getCategory(categoryId);
	}

	@Test
	public void GetCategoryWillReturnSpecifiedCategory() {
		final GetCategory getCategory = new GetCategory();
		getCategory.getCategory(dataSource, categoryId).subscribe(new Action1<Category>() {
			@Override
			public void call(Category category) {
				assertEquals(categoryId, category.getId());
			}
		});
	}

	@Test
	public void GetCategoriesWillRequestAllCategoriesFromDataSource() {
		final GetCategory getCategory = new GetCategory();
		getCategory.getCategories(dataSource).subscribe();
		verify(dataSource).getCategories();
	}

	@Test
	public void GetCategoriesWillReturnAllCategories() {
		final GetCategory getCategory = new GetCategory();
		getCategory.getCategories(dataSource).subscribe(new Action1<List<Category>>() {
			@Override
			public void call(List<Category> categoryList) {
				assertEquals(categories, categoryList);
			}
		});
	}

	@After
	public void tearDown() {
		categories = null;
		category = null;
		dataSource = null;
	}

}
