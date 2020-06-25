package com.nagycsongor.workout.SplashPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.nagycsongor.workout.LoginPage.LoginActivity;
import com.nagycsongor.workout.R;

public class SplashActivity extends AppCompatActivity implements SplashActivityDelegate {

    private SplashActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        presenter = new SplashActivityPresenter(this);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
