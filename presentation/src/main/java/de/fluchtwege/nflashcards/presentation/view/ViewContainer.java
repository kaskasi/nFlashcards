package de.fluchtwege.nflashcards.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Viewbinding and Viewinteraction
 */
public abstract class ViewContainer extends FrameLayout {
	public ViewContainer(Context context) {
		super(context);
	}

	public abstract void inflate(final LayoutInflater inflater);

}
