package com.nagycsongor.workout.MainPage;

import androidx.recyclerview.widget.RecyclerView;

public interface MainActivityDelegate {
    void createRecyclerView(RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager);

    void setRecyclerView(RecyclerView.Adapter adapter);

    void openLoginActivity();

    void openAddWorkoutActivity();
}
