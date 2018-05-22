package com.hb.fatsecret.fragment;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.QuestionActivity;
import com.hb.fatsecret.dialog.SelectCountryDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Welcome8Fragment extends Fragment
        implements SelectCountryDialog.OnCountryDialogListener{

    private final int position = 7;

    @BindView(R.id.llThisCountry)
    LinearLayout llThisCountry;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.llOtherCountry)
    LinearLayout llOtherCountry;
    @BindView(R.id.btnNext)
    ImageButton button;

    Unbinder unbinder;

    public Welcome8Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_welcome8, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        Object answer = QuestionActivity.answers.get(position);
        if (answer instanceof String){
            String str = (String) answer;
            updateCountry(str);
        }
    }

    @OnClick(R.id.llThisCountry)
    void clickThisCountry(View view) {
        chooseThisCountry();
    }

    private void chooseThisCountry() {
        llThisCountry.setBackground(QuestionActivity.stateClick);
        imageView.setImageResource(R.drawable.region_icon_region_selected);
        textView.setTextColor(ActivityCompat.getColor(getContext(), R.color.colorBlack));
        button.setAlpha(Float.valueOf(1));
    }

    @OnClick(R.id.llOtherCountry)
    void clickOtherCountry(View view) {
        SelectCountryDialog dialog = new SelectCountryDialog(getContext());
        dialog.show();
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (button.getAlpha() < 1) return;
        QuestionActivity activity = (QuestionActivity) getContext();
        activity.answerQuestion(position, textView.getText().toString());
    }

    private void updateCountry(String country) {
        textView.setText(country);
        chooseThisCountry();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSelectCountryDialog(String country) {
        updateCountry(country);
    }
}
