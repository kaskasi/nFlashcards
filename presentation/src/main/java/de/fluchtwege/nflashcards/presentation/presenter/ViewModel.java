package de.fluchtwege.nflashcards.presentation.presenter;

import de.fluchtwege.nflashcards.presentation.view.ViewContainer;

/**
 * Viewcontroller
 */
public abstract class ViewModel<T extends ViewContainer> {

	protected T container;

	public void setViewContainer(T viewContainer){
		container = viewContainer;
	}

	public void destroy() {
		container = null;
	}

}
