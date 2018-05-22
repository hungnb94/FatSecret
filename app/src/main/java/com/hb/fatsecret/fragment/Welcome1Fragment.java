package com.hb.fatsecret.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.QuestionActivity;
import com.hb.fatsecret.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.hb.fatsecret.utils.Constants.CHOICE_MAINTAIN_WEIGHT;
import static com.hb.fatsecret.utils.Constants.CHOICE_WEIGHT_GAIN;
import static com.hb.fatsecret.utils.Constants.CHOICE_WEIGHT_LOSS;

public class Welcome1Fragment extends Fragment {
    private final String TAG = Welcome1Fragment.class.getSimpleName();

    private final int position = 0;

    @BindView(R.id.tvWeightLoss)
    TextView tvWeightLoss;
    @BindView(R.id.tvMaintainWeight)
    TextView tvMaintainWeight;
    @BindView(R.id.tvWeightGain)
    TextView tvWeightGain;
    @BindView(R.id.btnNext)
    ImageButton button;

    int choice;

    Unbinder unbinder;

    public Welcome1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome1, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        Object value = QuestionActivity.answers.get(position);
        if (value instanceof Integer) {
            int i = (int) value;
            if (i == CHOICE_WEIGHT_LOSS) {
                chooseWeightLoss();
            } else if (i == CHOICE_MAINTAIN_WEIGHT) {
                chooseMaintainWeight();
            } else if (i == CHOICE_WEIGHT_GAIN) {
                chooseWeightGain();
            }
        }
    }

//    @OnClick(R.id.ivClose)
//    void closeActivity(View view) {
//        getActivity().finish();
//    }

    @OnClick(R.id.tvWeightLoss)
    void clickWeightLoss(View view) {
        chooseWeightLoss();
    }

    private void chooseWeightLoss() {
        Log.e(TAG, "Choice weight loss");
        if (choice==CHOICE_WEIGHT_LOSS) return;
        int prevChoice = choice;
        choice = CHOICE_WEIGHT_LOSS;
        if (prevChoice == choice) return;
        Utility.setBackgroundDrawable(tvWeightLoss, QuestionActivity.stateClick);
        tvWeightLoss.setTextColor(Color.BLACK);
        if (prevChoice == CHOICE_MAINTAIN_WEIGHT) {
            Utility.setBackgroundDrawable(tvMaintainWeight, QuestionActivity.stateNormal);
            tvMaintainWeight.setTextColor(Color.WHITE);
        } else if (prevChoice == CHOICE_WEIGHT_GAIN) {
            Utility.setBackgroundDrawable(tvWeightGain, QuestionActivity.stateNormal);
            tvWeightGain.setTextColor(Color.WHITE);
        }
        button.setAlpha((float) 1);
    }

    @OnClick(R.id.tvMaintainWeight)
    void clickMaintainWeight(View view) {
        chooseMaintainWeight();
    }

    private void chooseMaintainWeight() {
        Log.e(TAG, "Choice maintain weight");
        int prevChoice = choice;
        choice = CHOICE_MAINTAIN_WEIGHT;
        if (prevChoice == choice) return;
        Utility.setBackgroundDrawable(tvMaintainWeight, QuestionActivity.stateClick);
        tvMaintainWeight.setTextColor(Color.BLACK);
        if (prevChoice == CHOICE_WEIGHT_LOSS) {
            Utility.setBackgroundDrawable(tvWeightLoss, QuestionActivity.stateNormal);
            tvWeightLoss.setTextColor(Color.WHITE);
        } else if (prevChoice == CHOICE_WEIGHT_GAIN) {
            Utility.setBackgroundDrawable(tvWeightGain, QuestionActivity.stateNormal);
            tvWeightGain.setTextColor(Color.WHITE);
        }
        button.setAlpha((float) 1);
    }

    @OnClick(R.id.tvWeightGain)
    void clickWeightGain(View view) {
        chooseWeightGain();
    }

    private void chooseWeightGain() {
        Log.e(TAG, "Choice weight gain");
        int prevChoice = choice;
        choice = CHOICE_WEIGHT_GAIN;
        if (prevChoice == choice) return;
        Utility.setBackgroundDrawable(tvWeightGain, QuestionActivity.stateClick);
        tvWeightGain.setTextColor(Color.BLACK);
        if (prevChoice == CHOICE_MAINTAIN_WEIGHT) {
            Utility.setBackgroundDrawable(tvMaintainWeight, QuestionActivity.stateNormal);
            tvWeightGain.setTextColor(Color.WHITE);
        } else if (prevChoice == CHOICE_WEIGHT_LOSS) {
            Utility.setBackgroundDrawable(tvWeightLoss, QuestionActivity.stateNormal);
            tvWeightLoss.setTextColor(Color.WHITE);
        }
        button.setAlpha((float) 1);
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (button.getAlpha() < 1) return;
        QuestionActivity activity = (QuestionActivity) getContext();
        activity.answerQuestion(position, choice);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
