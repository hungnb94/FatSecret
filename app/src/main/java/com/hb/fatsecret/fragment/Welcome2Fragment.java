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
import com.hb.fatsecret.model.User;
import com.hb.fatsecret.utils.Constants;
import com.hb.fatsecret.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class Welcome2Fragment extends Fragment {
    private final String[] arr = {"kg", "lb"};

    private final int position = 1;

    @BindView(R.id.edittext)
    EditText editText;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.btnNext)
    ImageButton button;

    Unbinder unbinder;

    public Welcome2Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_welcome2, container, false);
        unbinder = ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), R.layout.item_spinner, arr);
        spinner.setAdapter(adapter);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        User user = QuestionActivity.userObject;
        double value = user.getInformation().getTargetWeight();
        if (value > 0) editText.setText(String.valueOf(value));
    }

//    @OnClick(R.id.ivClose)
//    void back(View view) {
//        getFragmentManager().popBackStack();
//    }

    @OnTextChanged(R.id.edittext)
    void textChange(CharSequence text) {
        if (TextUtils.isEmpty(text)) button.setAlpha(Constants.ALPHA_HALF);
        else button.setAlpha((float) 1);
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (button.getAlpha() < 1) return;
        QuestionActivity activity = (QuestionActivity) getContext();
        double weight = Double.valueOf(editText.getText().toString());
        if (arr[spinner.getSelectedItemPosition()].equalsIgnoreCase("lb")){
            weight = Utility.changeLbToKg(weight);
        }
        QuestionActivity.userObject.getInformation().setTargetWeight(weight);
        activity.goToNextFragment(position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
