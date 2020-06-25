package com.nagycsongor.workout.DetailsPage;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nagycsongor.workout.Models.Workout;

import java.io.File;
import java.io.IOException;

public class DetailsActivityPresenter {
    private StorageReference storageReference;
    private DetailsActivityDelegate activityDelegate;

    public DetailsActivityPresenter(DetailsActivityDelegate activityDelegate) {
        this.activityDelegate = activityDelegate;
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        setElements();
    }


    private void setElements() {
        Workout workout = activityDelegate.getWorkout();
        activityDelegate.setNameTextView(workout.getName());
        String calories = "Burned calories: " + workout.getBurnedCalories();
        activityDelegate.setBurnedCaloriesTextView(calories);
        String date = "Date of workout: " + workout.getDate();
        activityDelegate.setDateTextView(date);
        String duration = "Duration of workout (in minutes): " + workout.getDuration();
        activityDelegate.setDurationTextView(duration);

        String url = workout.getImageURL();
        if (url != null) {
            try {
                File file = File.createTempFile("images", "jpg");
                storageReference.child(url).getFile(file)
                        .addOnSuccessListener(taskSnapshot ->
                                activityDelegate.setImageView(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
