package com.nagycsongor.workout.LoginPage;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nagycsongor.workout.MainPage.MainActivity;
import com.nagycsongor.workout.R;
import com.nagycsongor.workout.RegisterPage.RegisterActivity;

public class LoginActivityPresenter {
    private LoginActivityDelegate activityDelegate;
    private FirebaseAuth firebaseAuth;

    public LoginActivityPresenter(LoginActivityDelegate activityDelegate) {
        this.activityDelegate = activityDelegate;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void onStart(){
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            activityDelegate.startActivity(MainActivity.class);
        }
    }

    public void loginButtonClick(Activity activity){
        String email = activityDelegate.getEmailEditText();
        String password = activityDelegate.getPasswordEditText();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            activityDelegate.createToast(R.string.complete_all_the_fields);
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, task -> {
                        if (task.isSuccessful()) {
                            activityDelegate.startActivity(MainActivity.class);
                        } else {
                            activityDelegate.createToast(R.string.login_failed);
                        }
                    });
        }
    }

    public void signUpButtonClick(){
        activityDelegate.startActivity(RegisterActivity.class);
    }


    public void forgotPasswordTextViewClick(){
        activityDelegate.createAlertDialog(R.string.forgot_password,R.string.write_email_address);
    }

    public void alertDialogResult(String email){
        firebaseAuth.sendPasswordResetEmail(email);

    }
}
