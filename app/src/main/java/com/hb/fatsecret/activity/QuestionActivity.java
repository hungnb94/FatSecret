package com.hb.fatsecret.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hb.fatsecret.R;
import com.hb.fatsecret.adapter.MyPagerAdapter;
import com.hb.fatsecret.fragment.SignUpFragment;
import com.hb.fatsecret.model.Information;
import com.hb.fatsecret.model.User;
import com.hb.fatsecret.utils.CustomViewPager;
import com.hb.fatsecret.utils.SwipeDirection;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hb.fatsecret.utils.Constants.CHOICE_MAINTAIN_WEIGHT;

public class QuestionActivity extends AppCompatActivity {
    @BindView(R.id.ivClose)
    ImageView ivClose;

    private SpringDotsIndicator dotsIndicator;
    private CustomViewPager viewPager;
    private MyPagerAdapter adapter;

    public static User userObject;

    public static Drawable stateNormal, stateClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initParams();
        initView();
    }

    private void initParams() {
        userObject = new User();
        userObject.setInformation(new Information());

        stateNormal = ActivityCompat.getDrawable(this, R.drawable.border_button);
        stateClick = ActivityCompat.getDrawable(this, R.drawable.border_button_click);
    }

    private void initView() {
        dotsIndicator = findViewById(R.id.dotsIndicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAllowedSwipeDirection(SwipeDirection.none);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
        dotsIndicator.setDotsClickable(false);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivClose)
    void finishActivity(View view) {
        backPressed();
    }

    public void goToNextFragment(int position) {
        if (position == 0) {
            if (userObject.getInformation().getGoal() == CHOICE_MAINTAIN_WEIGHT) {
                adapter.setFragment2(false);
            } else {
                adapter.setFragment2(true);
            }
            ivClose.setImageResource(R.drawable.ic_back);
        }
        if (viewPager.getCurrentItem() < adapter.getCount() - 1) {
            if (adapter.getCount() == 8) {
                viewPager.setCurrentItem(position + 1);
            } else {
                if (position == 0) {
                    viewPager.setCurrentItem(1);
                } else {
                    viewPager.setCurrentItem(position);
                }
            }
        } else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(android.R.id.content, new SignUpFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        backPressed();
    }

    private void backPressed() {
        if (viewPager.getCurrentItem() == 0) {
            finish();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            if (viewPager.getCurrentItem() == 0)
                ivClose.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        }
    }
}
