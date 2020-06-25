package com.nagycsongor.workout.AddWorkoutPage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nagycsongor.workout.Models.Workout;
import com.nagycsongor.workout.R;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class AddWorkoutActivityPresenter {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PICK_IMAGE = 2;
    private AddWorkoutActivityDelegate activityDelegate;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Calendar calendar;
    private Uri pictureUri;

    public AddWorkoutActivityPresenter(AddWorkoutActivityDelegate activityDelegate) {
        this.activityDelegate = activityDelegate;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        calendar = Calendar.getInstance();
        setNameEditText();
        setDatePickerTextView();
    }

    private void setNameEditText() {
        String name = "Workout_" + calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.DAY_OF_MONTH);
        activityDelegate.setNameEditText(name);
    }

    private void setDatePickerTextView() {
        String date = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        activityDelegate.setDatePickerTextView(date);
    }

    public void nameEditTextTouched() {
        activityDelegate.setNameEditText("");
    }

    public void datePickerTextViewOnClick() {
        activityDelegate.createDatePickerDialog(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void onDateSelected(int year, int month, int day) {
        String chosenDate = year + "/" + (month + 1) + "/" + day;
        activityDelegate.setDatePickerTextView(chosenDate);
    }

    public void takePictureTextViewClick() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        activityDelegate.startActivity(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void choosePictureTextViewClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityDelegate.startActivity(intent, REQUEST_PICK_IMAGE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            pictureUri = data.getData();
            activityDelegate.setImageView(pictureUri);
        }

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            pictureUri = data.getData();
            activityDelegate.setImageView(pictureUri);
        }
    }

    public void saveButtonClick(Activity activity) {
        String name = activityDelegate.getNameEditText();
        String burnedCalories = activityDelegate.getBurnedCaloriesEditText();
        String date = activityDelegate.getDatePickerTextView();
        String duration = activityDelegate.getDurationEditText();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(burnedCalories) || TextUtils.isEmpty(date) || TextUtils.isEmpty(duration)) {
            activityDelegate.createToast(R.string.complete_all_the_fields);
        } else {
            activityDelegate.showProgressDialog();
            if (pictureUri != null) {
                String imageName = "workout_" + calendar.getTime() + ".jpg";
                storageReference.child(imageName).putFile(pictureUri)
                        .addOnSuccessListener(taskSnapshot -> {
                            Workout workout = new Workout(name, Integer.valueOf(burnedCalories), date, Integer.valueOf(duration), calendar.getTime(), imageName);
                            saveWorkout(workout, activity);
                        })
                        .addOnFailureListener(e -> {
                            activityDelegate.dismissProgressDialog();
                            activityDelegate.createNegativeAlertDialog(R.string.save_failed);
                        })
                        .addOnProgressListener(taskSnapshot -> {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            activityDelegate.setProgressDialogMessage("Uploaded " + (int) progress + "%");
                        });
            } else {
                Workout workout = new Workout(name, Integer.valueOf(burnedCalories), date, Integer.valueOf(duration), calendar.getTime(), null);
                saveWorkout(workout, activity);
            }


        }
    }

    private void saveWorkout(Workout workout, Activity activity) {
        String key = databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("workouts").push().getKey();
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("workouts").child(key).setValue(workout)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        activityDelegate.dismissProgressDialog();
                        activityDelegate.createPositiveAlertDialog(R.string.successful_saved);
                    } else {
                        activityDelegate.dismissProgressDialog();
                        activityDelegate.createNegativeAlertDialog(R.string.save_failed);
                    }
                });
    }
}
