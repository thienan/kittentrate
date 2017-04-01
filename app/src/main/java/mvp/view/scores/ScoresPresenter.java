package mvp.view.scores;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import mvp.model.repository.ScoresLoader;
import mvp.model.repository.model.PlayerScore;

/**
 * Created by Manuel Lorenzo
 */

public class ScoresPresenter implements ScoresContract.Presenter, LoaderManager.LoaderCallbacks<List<PlayerScore>> {
    private final ScoresLoader scoresLoader;
    private final LoaderManager loaderManager;
    private final ScoresContract.View view;

    ScoresPresenter(ScoresContract.View view, ScoresLoader scoresLoader, LoaderManager loaderManager) {
        this.scoresLoader = scoresLoader;
        this.loaderManager = loaderManager;
        this.view = view;
    }

    @Override
    public void start() {
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<List<PlayerScore>> onCreateLoader(int id, Bundle args) {
        view.showLoadingView();
        return scoresLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<PlayerScore>> loader, List<PlayerScore> data) {
        if (data.isEmpty()) {
            view.showEmptyView();
        } else {
            view.showScores(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<PlayerScore>> loader) {

    }
}