package com.hb.fatsecret.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hb.fatsecret.R;
import com.hb.fatsecret.dialog.SelectCountryDialog;
import com.hb.fatsecret.model.User;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SelectCountryDialog.OnCountryDialogListener, View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();

    public static FirebaseUser firebaseUser;
    private DatabaseReference ref;
    private User userObject;

    TextView tvUsername, tvEmail, tvRegion;
    LinearLayout llRegion;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name_lowercase));

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference()
                .child("users").child(firebaseUser.getUid());
        initView();
        addEvent();
    }

    private void initView() {
        ButterKnife.bind(this);

        navigationView = findViewById(R.id.nav_view);
        View headerMain = navigationView.getHeaderView(0);

        tvUsername = headerMain.findViewById(R.id.tvUsername);
        tvEmail = headerMain.findViewById(R.id.tvEmail);
        tvRegion = headerMain.findViewById(R.id.tvRegion);

        llRegion = headerMain.findViewById(R.id.llRegion);
    }

    void addEvent(){
        navigationView.setNavigationItemSelectedListener(this);
        llRegion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llRegion:
                selectRegion();
                break;
        }
    }

    void selectRegion(){
        drawer.closeDrawer(GravityCompat.START);

        SelectCountryDialog dialog = new SelectCountryDialog(this);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_home) {

        } else if (id == R.id.action_diary) {

        } else if (id == R.id.action_reports) {

        } else if (id == R.id.action_weight) {

        } else if (id == R.id.action_diet_calendar) {

        } else if (id == R.id.action_photo_album) {

        } else if (id == R.id.action_my_professionals) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSelectCountryDialog(String country) {
        Log.e(TAG, "Select country " + country);
        tvRegion.setText(country);
    }
}
