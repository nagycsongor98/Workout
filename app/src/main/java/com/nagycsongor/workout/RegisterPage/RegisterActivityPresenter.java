package com.nagycsongor.workout.RegisterPage;

import android.app.Activity;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nagycsongor.workout.LoginPage.LoginActivity;
import com.nagycsongor.workout.MainPage.MainActivity;
import com.nagycsongor.workout.Models.User;
import com.nagycsongor.workout.R;

public class RegisterActivityPresenter {

    private RegisterActivityDelegate activityDelegate;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public RegisterActivityPresenter(RegisterActivityDelegate activityDelegate) {
        this.activityDelegate = activityDelegate;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public void signUpButtonClick(Activity activity) {
        String name = activityDelegate.getUsernameEditText();
        String email = activityDelegate.getEmailEditText();
        String password = activityDelegate.getPasswordEditText();
        String passwordAgain = activityDelegate.getPasswordAgainEditText();
        boolean isChecked = activityDelegate.getTermsAndConditionsCheckBox();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(passwordAgain) || !isChecked) {
            activityDelegate.createToast(R.string.complete_all_the_fields);

        } else if (!password.equals(passwordAgain)) {
            activityDelegate.createToast(R.string.incorrect_password);
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, task -> {
                        if (task.isSuccessful()) {
                            User user = new User(name);
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                            activityDelegate.createToast(R.string.successfully_registered);
                            activityDelegate.startActivity(MainActivity.class);
                        } else {
                            activityDelegate.createToast(R.string.registration_failed);
                        }
                    });

        }
    }

    public void loginButtonClick() {
        activityDelegate.startActivity(LoginActivity.class);
    }

}
