package com.nagycsongor.workout.LoginPage;



public interface LoginActivityDelegate {
    void startActivity(Class<?> cls);

    String getEmailEditText();

    String getPasswordEditText();

    void createToast(int stringId);

    void createAlertDialog(int titleId, int messageId);
}
