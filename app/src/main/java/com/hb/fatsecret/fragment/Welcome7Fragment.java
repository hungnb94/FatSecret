package com.hb.fatsecret.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.QuestionActivity;
import com.hb.fatsecret.utils.Constants;

import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Welcome7Fragment extends Fragment {
    private final String TAG = Welcome7Fragment.class.getSimpleName();

    private final int position = 6;

    @BindView(R.id.datePicker)
    DatePicker datePicker;
    @BindView(R.id.btnNext)
    ImageButton button;

    Unbinder unbinder;

    public Welcome7Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_welcome7, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        String dateStr = QuestionActivity.userObject.getInformation().getDateOfBirth();
        if (dateStr != null) {
            StringTokenizer tokenizer = new StringTokenizer(dateStr, Constants.SEPERATOR);
            try {
                int year = Integer.parseInt(tokenizer.nextToken());
                int month = Integer.parseInt(tokenizer.nextToken());
                int dayOfMonth = Integer.parseInt(tokenizer.nextToken());
                datePicker.init(year, month, dayOfMonth, null);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        String date = datePicker.getYear()
                + Constants.SEPERATOR + datePicker.getMonth()
                + Constants.SEPERATOR + datePicker.getDayOfMonth();
        Log.i(TAG, "Date of birth: " + date);
        QuestionActivity.userObject.getInformation().setDateOfBirth(date);
        QuestionActivity activity = (QuestionActivity) getContext();
        activity.goToNextFragment(position);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
