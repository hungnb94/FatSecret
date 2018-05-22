package com.hb.fatsecret.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.adapter.CustomAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Window.FEATURE_NO_TITLE;

public class SelectCountryDialog extends Dialog {
    Context context;
    String[] countries;

    RecyclerView recyclerView;
    CustomAdapter adapter;

    @BindView(R.id.textView)
    TextView textView;

    public SelectCountryDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_country);

        ButterKnife.bind(this);

        countries = getContext().getResources().getStringArray(R.array.country_list);
        adapter = new CustomAdapter(getContext(), countries);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.ivClose)
    void clickClose(View view) {
        dismiss();
    }

    @OnClick(R.id.textView)
    void clickSave(View view) {
        if (adapter.getSelected() < 0) return;
        String country = countries[adapter.getSelected()];
        if (context instanceof OnCountryDialogListener){
            OnCountryDialogListener listener = (OnCountryDialogListener) context;
            listener.onSelectCountryDialog(country);
        }
        dismiss();
    }

    public interface OnCountryDialogListener{
        void onSelectCountryDialog(String country);
    }
}
