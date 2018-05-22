package com.hb.fatsecret.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hb.fatsecret.R;
import com.hb.fatsecret.fragment.SignInFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void clickStart(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvSignIn)
    void clickSignIn(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SignInFragment fragment = new SignInFragment();
        ft.replace(android.R.id.content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @OnClick(R.id.tvTerms)
    void showTerms(View view) {
    }

    @OnClick(R.id.tvPrivacyPolicy)
    void showPolicy(View view) {
    }
}
