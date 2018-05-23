package com.hb.fatsecret.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
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
import com.hb.fatsecret.utils.Utility;
import com.hb.fatsecret.utils.WeightStandard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class Welcome6Fragment extends Fragment {
    private final String[] arr = {"cm", "ft", "in"};
    private final int position = 5;

    @BindView(R.id.edittext)
    EditText editText;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.btnNext)
    ImageButton button;

    Unbinder unbinder;
    private double height;

    public Welcome6Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_welcome6, container, false);
        unbinder = ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), R.layout.item_spinner, arr);
        spinner.setAdapter(adapter);
        updateValueFromActivity();
        return view;
    }

    private void updateValueFromActivity() {
        double height = QuestionActivity.userObject.getInformation().getHeight();
        if (height > 0) editText.setText(String.valueOf(height));
    }

    @OnTextChanged(R.id.edittext)
    void textChange(CharSequence text) {
        if (TextUtils.isEmpty(text)) button.setAlpha(Constants.ALPHA_HALF);
        else button.setAlpha((float) 1);
    }

    @OnClick(R.id.btnNext)
    void clickNext(View view) {
        if (button.getAlpha() < 1) return;
        //Check user weight
        //If too low or too high then alert to user
        double weight = QuestionActivity.userObject.getInformation().getCurrentWeight();
        height = Double.valueOf(editText.getText().toString());
        if (arr[spinner.getSelectedItemPosition()].equalsIgnoreCase("in")) {
            height = Utility.changeInToCm(height);
        } else if (arr[spinner.getSelectedItemPosition()].equalsIgnoreCase("ft")) {
            height = Utility.changeFtToCm(height);
        }
        double heightInMet = height / 100; //height in (unit) met
        int goal = QuestionActivity.userObject.getInformation().getGoal();
        int bodyType = WeightStandard.getBMIWeightType(weight, heightInMet, true);
        if (bodyType == Constants.BMI_UNDERWEIGHT) {
            if (goal == Constants.CHOICE_WEIGHT_LOSS || goal == Constants.CHOICE_MAINTAIN_WEIGHT) {
                String title = getResources().getString(R.string.please_note);
                String message = getResources().getString(R.string.very_low_weight)
                        + "\n\n" + getResources().getString(R.string.you_sure);
                showAlertDialog(title, message);
                return;
            }
        }
        if (bodyType == Constants.BMI_OVERWEIGHT
                || bodyType == Constants.BMI_OBESE_GRADE_1
                || bodyType == Constants.BMI_OBESE_GRADE_2
                || bodyType == Constants.BMI_OBESE_GRADE_3) {
            if (goal == Constants.CHOICE_WEIGHT_GAIN || goal == Constants.CHOICE_MAINTAIN_WEIGHT) {
                String title = getResources().getString(R.string.please_note);
                String message = getResources().getString(R.string.very_high_weight)
                        + "\n\n" + getResources().getString(R.string.you_sure);
                showAlertDialog(title, message);
                return;
            }
        }
        QuestionActivity.userObject.getInformation().setHeight(height);
        QuestionActivity activity = (QuestionActivity) getContext();
        activity.goToNextFragment(position);
    }

    /**
     * Show alert dialog
     *
     * @param title   of dialog
     * @param message of dialog
     */
    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        QuestionActivity.userObject.getInformation().setHeight(height);
                        QuestionActivity activity = (QuestionActivity) getContext();
                        activity.goToNextFragment(position);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
