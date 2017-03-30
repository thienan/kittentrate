package mvp.view.custom;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import manulorenzo.me.kittentrate.R;
import mvp.view.game.GameFragment;

/**
 * Created by Manuel Lorenzo
 */

public class NameScoreDialogFragment extends DialogFragment {
    @BindView(R.id.name_edittext)
    EditText nameEditText;
    @BindView(R.id.score_textview)
    TextView scoreTextView;
    int score;

    public NameScoreDialogFragment() {

    }

    public static NameScoreDialogFragment newInstance(int score) {
        Bundle args = new Bundle();
        args.putInt(GameFragment.SCORE_BUNDLE_KEY, score);
        NameScoreDialogFragment fragment = new NameScoreDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_ScoreDialog);
        if (getArguments() != null && getArguments().getInt(GameFragment.SCORE_BUNDLE_KEY, 0) != 0) {
            score = getArguments().getInt(GameFragment.SCORE_BUNDLE_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(R.string.score_dialog_title);
        View view = inflater.inflate(R.layout.name_score_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_ScoreDialog);
        scoreTextView.setText("Score: " + score);

        nameEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setDimAmount(50);
        // Call super onResume after sizing
        super.onResume();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // TODO Save name and score to DB
        super.onDismiss(dialog);
    }
}
