package com.nagycsongor.workout.AddWorkoutPage;

import android.content.Intent;
import android.net.Uri;

public interface AddWorkoutActivityDelegate {
    void setNameEditText(String string);

    void setDatePickerTextView(String string);

    void createDatePickerDialog(int year, int month, int day);

    void startActivity(Intent intent, int requestCode);

    void setImageView(Uri uri);

    String getNameEditText();

    String getBurnedCaloriesEditText();

    String getDatePickerTextView();

    String getDurationEditText();

    void createToast(int stringId);

    void showProgressDialog();

    void setProgressDialogMessage(String string);

    void dismissProgressDialog();

    void createPositiveAlertDialog(int titleStringId);

    void createNegativeAlertDialog(int titleStringId);
}
