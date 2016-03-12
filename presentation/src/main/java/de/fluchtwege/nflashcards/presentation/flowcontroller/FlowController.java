package de.fluchtwege.nflashcards.presentation.flowcontroller;

import de.fluchtwege.nflashcards.presentation.presenter.ViewModel;

/**
 * controls user interactions
 * @param <T>
 */
public interface FlowController<T extends ViewModel> {
	void perform(T viewModel);
}
