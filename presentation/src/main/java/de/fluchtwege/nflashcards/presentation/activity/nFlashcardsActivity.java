package de.fluchtwege.nflashcards.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.datasource.Repository;
import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.CreateCard;
import de.fluchtwege.nflashcards.features.interactors.CreateCategory;
import de.fluchtwege.nflashcards.features.interactors.EditCard;
import de.fluchtwege.nflashcards.features.interactors.GetCard;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.functions.Action1;

public class nFlashcardsActivity extends AppCompatActivity {

	public static DataSource repository = new Repository();
	private LinearLayout view;

	private Category kanji;
	private Category hiragana;
	private Category katakana;
	private Category deutsch;
	private FlashCard ki;
	private FlashCard hon;
	private FlashCard yama;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nflashcards);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		view = (LinearLayout) findViewById(R.id.card);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "ADD");
		menu.add(1, 2, 0, "TEST");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			startActivity(new Intent(this, CreateCardActivity.class));
			return true;
		} else if (item.getItemId() == 2) {
			startActivity(new Intent(this, TestCardActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();

		new CreateCategory().createCategory(repository, new Category("Kanji")).subscribe(new Action1<Category>() {
			@Override
			public void call(Category category) {
				kanji = category;
			}
		});
		new CreateCategory().createCategory(repository, new Category("Hiragana")).subscribe(new Action1<Category>() {
			@Override
			public void call(Category category) {
				hiragana = category;
			}
		});
		new CreateCategory().createCategory(repository, new Category("Katagana")).subscribe(new Action1<Category>() {
			@Override
			public void call(Category category) {
				katakana = category;
			}
		});
		new CreateCategory().createCategory(repository, new Category("Deustch")).subscribe(new Action1<Category>() {

			@Override
			public void call(Category category) {
				deutsch = category;
			}
		});




		new CreateCard().createCard(repository).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				flashCard.addContent(new CardContent(kanji, "木"));
				flashCard.addContent(new CardContent(hiragana, "き"));
				flashCard.addContent(new CardContent(katakana, "モク"));
				flashCard.addContent(new CardContent(deutsch, "Baum"));
				new EditCard().editCard(repository, flashCard, flashCard.getId()).subscribe(new Action1<FlashCard>() {
					@Override
					public void call(FlashCard flashCard) {
						ki = flashCard;
					}
				});

			}
		});

		new CreateCard().createCard(repository).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				flashCard.addContent(new CardContent(kanji, "本"));
				flashCard.addContent(new CardContent(hiragana, "ほん"));
				flashCard.addContent(new CardContent(katakana, "ホン"));
				flashCard.addContent(new CardContent(deutsch, "Buch"));
				new EditCard().editCard(repository, flashCard, flashCard.getId()).subscribe(new Action1<FlashCard>() {
					@Override
					public void call(FlashCard flashCard) {
						hon = flashCard;
					}
				});

			}
		});


		new CreateCard().createCard(repository).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				flashCard.addContent(new CardContent(kanji, "山"));
				flashCard.addContent(new CardContent(hiragana, "やま"));
				flashCard.addContent(new CardContent(katakana, "サン"));
				flashCard.addContent(new CardContent(deutsch, "Berg"));
				new EditCard().editCard(repository, flashCard, flashCard.getId()).subscribe(new Action1<FlashCard>() {
					@Override
					public void call(FlashCard flashCard) {
						yama = flashCard;
					}
				});

			}
		});

		new GetCard().getCards(repository).subscribe(new Action1<List<FlashCard>>() {
			@Override
			public void call(List<FlashCard> cards) {
				view.removeAllViews();
				for (FlashCard card : cards) {
					for (CardContent content : card.getItems()) {
						final TextView textView = new TextView(nFlashcardsActivity.this);
						final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
						textView.setLayoutParams(params);
						textView.setText(content.getCategory().getName() + " : " + content.getValue());
						view.addView(textView);
					}
				}
			}
		});
	}



	/*repository = new Repository();
		new CreateCard().createCard(repository).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				flashCard.addContent(new TextContent("Fluss", new Category("dt")));
				flashCard.addContent(new TextContent("川", new Category("kanji")));
				flashCard.addContent(new TextContent("かわ", new Category("hiragana")));
				new EditCard().editCard(repository, flashCard, 0).
						subscribeOn(Schedulers.io()).
						observeOn(AndroidSchedulers.mainThread()).
						subscribe(new Action1<FlashCard>() {
							@Override
							public void call(FlashCard editedCard) {

							}
						});

			}
		});



		<!--
       日
       にち、
       ヒ


       中
       なか
       チュウ


       山
       やま
       サン


       川
       かわ

       猫
       ねこ
       Katze

       中
       なか
       Mitte

       *-->
*/

}
