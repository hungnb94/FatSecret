package com.hb.fatsecret.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.hb.fatsecret.R;
import com.hb.fatsecret.activity.QuestionActivity;
import com.hb.fatsecret.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class Welcome5Fragment extends Fragment {
    private final String[] arr = {"kg", "lb"};

    private final int position = 4;

    @BindView(R.id.edittext)
    EditText editText;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.btnNext)
    ImageButton button;

    Unbinder unbinder;

    public Welcome5Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_welcome5, container, false);
        unbinder = ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), R.layout.item_spinner, arr);
        spinner.setAdapter(adapter);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        double answer = QuestionActivity.userObject.getInformation().getCurrentWeight();
        if (answer > 0) editText.setText(String.valueOf(answer));
    }

    @OnTextChanged(R.id.edittext)
    void textChange(CharSequence text) {
        if (TextUtils.isEmpty(text)) button.setAlpha(Constants.ALPHA_HALF);
        else button.setAlpha((float) 1);
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (button.getAlpha() < 1) return;
        double weight = Double.valueOf(editText.getText().toString());
        QuestionActivity.userObject.getInformation().setCurrentWeight(weight);
        QuestionActivity activity = (QuestionActivity) getContext();
        activity.goToNextFragment(position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
