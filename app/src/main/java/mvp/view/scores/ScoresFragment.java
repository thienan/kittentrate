package mvp.view.scores;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import manulorenzo.me.kittentrate.R;
import mvp.model.entity.PhotoEntity;
import mvp.model.repository.GameRepository;
import mvp.model.repository.ScoresLoader;
import mvp.model.repository.local.GameLocalDataSource;
import mvp.model.repository.model.PlayerScore;
import mvp.model.repository.remote.GameRemoteDataSource;
import mvp.model.rest.NetworkCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScoresFragment extends Fragment implements ScoresContract.View, NetworkCallback {
    @BindView(R.id.scores_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.empty_textview)
    TextView emptyTextView;
    ScoresAdapter scoresAdapter;

    public ScoresFragment() {
    }

    public static ScoresFragment newInstance() {
        Bundle args = new Bundle();
        ScoresFragment fragment = new ScoresFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scores, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext().getApplicationContext(), RecyclerView.VERTICAL, false));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameLocalDataSource gameLocalDataSource = new GameLocalDataSource(getContext().getApplicationContext());
        GameRemoteDataSource gameRemoteDataSource = new GameRemoteDataSource();

        GameRepository gameRepository = new GameRepository(gameLocalDataSource, gameRemoteDataSource);

        ScoresLoader scoresLoader = new ScoresLoader(getContext().getApplicationContext(), gameRepository);
        ScoresPresenter scoresPresenter = new ScoresPresenter(this, scoresLoader, getLoaderManager());
        scoresPresenter.start();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showScores(List<PlayerScore> data) {
        emptyTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (scoresAdapter == null) {
            scoresAdapter = new ScoresAdapter(data);
            recyclerView.setAdapter(scoresAdapter);
        } else {
            scoresAdapter.setData(data);
            scoresAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showEmptyView() {
        emptyTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<PhotoEntity> success) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public void onFailure(Throwable t) {
        throw new UnsupportedOperationException("Unsupported");
    }
}