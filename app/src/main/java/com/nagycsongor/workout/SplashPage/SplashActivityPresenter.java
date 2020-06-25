package com.nagycsongor.workout.SplashPage;

import android.os.CountDownTimer;

public class SplashActivityPresenter {
    private SplashActivityDelegate activityDelegate;

    public SplashActivityPresenter(SplashActivityDelegate activityDelegate) {
        this.activityDelegate = activityDelegate;
        startCounter();
    }

    private void startCounter() {
        new CountDownTimer(1500, 100) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                activityDelegate.startLoginActivity();
            }
        }.start();
    }

}
