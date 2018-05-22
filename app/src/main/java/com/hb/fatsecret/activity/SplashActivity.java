package com.hb.fatsecret.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.preference.PreferenceManager;
import com.hb.fatsecret.utils.Constants;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        PreferenceManager pre = new PreferenceManager(this);
        if (!pre.isFirstTimeLaunch()) {
            openMainAcitivity();
            return;
        }

        imageView = findViewById(R.id.imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                SplashActivity.this,
                                imageView,
                                getResources().getString(R.string.icon_app));
                startActivity(intent, options.toBundle());
                finish();
            }
        }, Constants.SPLASH_LENGTH);
    }

    /**
     * Open MainActivity
     */
    private void openMainAcitivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
