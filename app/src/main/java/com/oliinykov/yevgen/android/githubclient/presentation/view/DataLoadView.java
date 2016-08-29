package com.oliinykov.yevgen.android.githubclient.presentation.view;

/**
 * Interface representing a View that will use to load data.
 */
public interface DataLoadView {

    /**
     * Shows/hide the progress of data loading.
     *
     * @param show If {@code true} - the progress will be shown, otherwise hide.
     */
    void showProgress(final boolean show);

    /**
     * Shows error message.
     *
     * @param errorMessage Message to show.
     */
    void showErrorMessage(String errorMessage);

}
