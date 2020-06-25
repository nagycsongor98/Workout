package com.nagycsongor.workout.DetailsPage;

import com.nagycsongor.workout.Models.Workout;

import java.io.File;

public interface DetailsActivityDelegate {
    Workout getWorkout();

    void setNameTextView(String string);

    void setBurnedCaloriesTextView(String string);

    void setDateTextView(String string);

    void setDurationTextView(String string);

    void setImageView(File file);
}
