package com.nagycsongor.workout.RegisterPage;

public interface RegisterActivityDelegate {
    void startActivity(Class<?> cls);

    String getUsernameEditText();

    String getEmailEditText();

    String getPasswordEditText();

    String getPasswordAgainEditText();

    boolean getTermsAndConditionsCheckBox();

    void createToast(int stringId);
}
