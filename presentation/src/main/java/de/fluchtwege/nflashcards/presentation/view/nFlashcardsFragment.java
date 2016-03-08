package de.fluchtwege.nflashcards.presentation.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fluchtwege.nflashcards.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class nFlashcardsFragment extends Fragment {

	public nFlashcardsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_nflashcards, container, false);
	}
}
