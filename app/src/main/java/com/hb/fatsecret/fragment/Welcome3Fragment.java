package com.hb.fatsecret.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.QuestionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.hb.fatsecret.utils.Constants.CHOICE_FEMALE;
import static com.hb.fatsecret.utils.Constants.CHOICE_MALE;

public class Welcome3Fragment extends WelcomeBaseFragment {
    private final String TAG = Welcome3Fragment.class.getSimpleName();

    private final int position = 2;

    @BindView(R.id.ivFemale)
    ImageView ivFemale;
    @BindView(R.id.ivMale)
    ImageView ivMale;
    @BindView(R.id.btnNext)
    ImageButton button;

    int choice;

    Unbinder unbinder;

    public Welcome3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome3, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        Object value = QuestionActivity.answers.get(position);
        if (value instanceof Integer) {
            int i = (int) value;
            if (i == CHOICE_FEMALE) {
                chooseFemale();
            } else if (i == CHOICE_MALE) {
                chooseMale();
            }
        }
    }

//    @OnClick(R.id.ivClose)
//    void back(View view) {
//        getFragmentManager().popBackStack();
//    }

    @OnClick(R.id.ivFemale)
    void clickFemale(View view) {
        chooseFemale();
    }

    private void chooseFemale() {
        Log.e(TAG, "Choice female");
        if (choice==CHOICE_FEMALE) return;

        int prevChoice = choice;
        choice = CHOICE_FEMALE;
        ivFemale.setBackground(QuestionActivity.stateClick);
        ivFemale.setImageResource(R.drawable.onboarding_image_female_selected);
        if(prevChoice==CHOICE_MALE) {
            ivMale.setBackground(QuestionActivity.stateNormal);
            ivMale.setImageResource(R.drawable.onboarding_image_male_disabled);
        }
        button.setAlpha((float) 1);
    }

    @OnClick(R.id.ivMale)
    void clickMale(View view) {
        chooseMale();
    }

    private void chooseMale() {
        Log.e(TAG, "Choice male");
        if (choice==CHOICE_MALE) return;

        int prevChoice= choice;
        choice = CHOICE_MALE;
        ivMale.setBackground(QuestionActivity.stateClick);
        ivMale.setImageResource(R.drawable.onboarding_image_male_selected);
        if (prevChoice==CHOICE_FEMALE){
            ivFemale.setBackground(QuestionActivity.stateNormal);
            ivFemale.setImageResource(R.drawable.onboarding_image_female_disabled);
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
