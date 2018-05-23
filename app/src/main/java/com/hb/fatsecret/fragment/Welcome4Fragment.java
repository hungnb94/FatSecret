package com.hb.fatsecret.fragment;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.QuestionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.hb.fatsecret.utils.Constants.CHOICE_ACTIVE;
import static com.hb.fatsecret.utils.Constants.CHOICE_LOW_ACTIVE;
import static com.hb.fatsecret.utils.Constants.CHOICE_SEDENTARY;
import static com.hb.fatsecret.utils.Constants.CHOICE_VERY_ACTIVE;

public class Welcome4Fragment extends Fragment {
    private final String TAG = Welcome4Fragment.class.getSimpleName();

    private final int position = 3;

    int choice;

    @BindView(R.id.llSedentary)
    LinearLayout llSedentary;
    @BindView(R.id.tvSedentaryTitle)
    TextView tvSedentaryTitle;
    @BindView(R.id.tvSedentaryDetail)
    TextView tvSedentaryDetail;
    @BindView(R.id.llLowActive)
    LinearLayout llLowActive;
    @BindView(R.id.tvLowActiveTitle)
    TextView tvLowActiveTitle;
    @BindView(R.id.tvLowActiveDetail)
    TextView tvLowActiveDetail;
    @BindView(R.id.llActive)
    LinearLayout llActive;
    @BindView(R.id.tvActiveTitle)
    TextView tvActiveTitle;
    @BindView(R.id.tvActiveDetail)
    TextView tvActiveDetail;
    @BindView(R.id.llVeryActive)
    LinearLayout llVeryActive;
    @BindView(R.id.tvVeryActiveTitle)
    TextView tvVeryActiveTitle;
    @BindView(R.id.tvVeryActiveDetail)
    TextView tvVeryActiveDetail;
    @BindView(R.id.btnNext)
    ImageButton button;

    Unbinder unbinder;


    public Welcome4Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome4, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        int answer = QuestionActivity.userObject.getInformation().getActivityLevel();
        choice = answer;
        if (choice == CHOICE_SEDENTARY) {
            choiceSedentary();
        } else if (choice == CHOICE_LOW_ACTIVE) {
            choiceLowActive();
        } else if (choice == CHOICE_ACTIVE) {
            choiceActive();
        } else if (choice == CHOICE_VERY_ACTIVE) {
            choiceVeryActive();
        }
    }

    @OnClick(R.id.llSedentary)
    void clickSedentary(View view) {
        choiceSedentary();
    }

    void choiceSedentary() {
        Log.e(TAG, "Choice sedentary");
        if (choice == CHOICE_SEDENTARY) return;

        int prevChoice = choice;
        choice = CHOICE_SEDENTARY;

        button.setAlpha(Float.valueOf(1));

        llSedentary.setBackground(QuestionActivity.stateClick);
        tvSedentaryTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));
        tvSedentaryDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));
        if (prevChoice == CHOICE_LOW_ACTIVE) {
            llLowActive.setBackground(QuestionActivity.stateNormal);
            tvLowActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvLowActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_ACTIVE) {
            llActive.setBackground(QuestionActivity.stateNormal);
            tvActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_VERY_ACTIVE) {
            llVeryActive.setBackground(QuestionActivity.stateNormal);
            tvVeryActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvVeryActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        }
    }

    @OnClick(R.id.llLowActive)
    void clickLowActive(View view) {
        choiceLowActive();
    }

    /**
     * User choice Low Active
     */
    void choiceLowActive() {
        Log.e(TAG, "Choice low active");
        if (choice == CHOICE_LOW_ACTIVE) return;
        int prevChoice = choice;
        choice = CHOICE_LOW_ACTIVE;

        button.setAlpha(Float.valueOf(1));

        llLowActive.setBackground(QuestionActivity.stateClick);
        tvLowActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));
        tvLowActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));

        if (prevChoice == CHOICE_SEDENTARY) {
            llSedentary.setBackground(QuestionActivity.stateNormal);
            tvSedentaryTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvSedentaryDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_ACTIVE) {
            llActive.setBackground(QuestionActivity.stateNormal);
            tvActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_VERY_ACTIVE) {
            llVeryActive.setBackground(QuestionActivity.stateNormal);
            tvVeryActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvVeryActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        }
    }

    @OnClick(R.id.llActive)
    void clickActive(View view) {
        choiceActive();
    }

    private void choiceActive() {
        Log.e(TAG, "Choice active");
        if (choice == CHOICE_ACTIVE) return;
        int prevChoice = choice;
        choice = CHOICE_ACTIVE;

        button.setAlpha(Float.valueOf(1));

        llActive.setBackground(QuestionActivity.stateClick);
        tvActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));
        tvActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));

        if (prevChoice == CHOICE_SEDENTARY) {
            llSedentary.setBackground(QuestionActivity.stateNormal);
            tvSedentaryTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvSedentaryDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_LOW_ACTIVE) {
            llLowActive.setBackground(QuestionActivity.stateNormal);
            tvLowActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvLowActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_VERY_ACTIVE) {
            llVeryActive.setBackground(QuestionActivity.stateNormal);
            tvVeryActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvVeryActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        }
    }

    @OnClick(R.id.llVeryActive)
    void clickVeryActive(View view) {
        choiceVeryActive();
    }

    private void choiceVeryActive() {
        Log.e(TAG, "Choice very active");
        if (choice == CHOICE_VERY_ACTIVE) return;
        int prevChoice = choice;
        choice = CHOICE_VERY_ACTIVE;

        button.setAlpha(Float.valueOf(1));

        llVeryActive.setBackground(QuestionActivity.stateClick);
        tvVeryActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));
        tvVeryActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));

        if (prevChoice == CHOICE_LOW_ACTIVE) {
            llLowActive.setBackground(QuestionActivity.stateNormal);
            tvLowActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvLowActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_SEDENTARY) {
            llSedentary.setBackground(QuestionActivity.stateNormal);
            tvSedentaryTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvSedentaryDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        } else if (prevChoice == CHOICE_ACTIVE) {
            llActive.setBackground(QuestionActivity.stateNormal);
            tvActiveTitle.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
            tvActiveDetail.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorWhite));
        }
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (button.getAlpha() < 1) return;
        QuestionActivity.userObject.getInformation().setActivityLevel(choice);
        QuestionActivity activity = (QuestionActivity) getContext();
        activity.goToNextFragment(position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
