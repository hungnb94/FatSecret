package com.hb.fatsecret.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hb.fatsecret.R;
import com.hb.fatsecret.dialog.SelectCountryDialog;
import com.hb.fatsecret.preference.PreferenceManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity
        implements SelectCountryDialog.OnCountryDialogListener {
    TextView tvRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.action_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        tvRegion = findViewById(R.id.tvRegion);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.llLogout)
    void logout(View view){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setMessage(getResources().getString(R.string.sure_to_logout))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceManager pre = new PreferenceManager(SettingActivity.this);
                        pre.setFirstTimeLaunch(true);
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        if(user!=null) auth.signOut();
                        Intent intent = new Intent(SettingActivity.this, SplashActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    @OnClick(R.id.llRegion)
    void selectRegion(View view){
        SelectCountryDialog dialog = new SelectCountryDialog(this);
        dialog.show();
    }

    @Override
    public void onSelectCountryDialog(String country) {
        tvRegion.setText(country);
    }
}
