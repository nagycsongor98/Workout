package com.nagycsongor.workout.MainPage;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nagycsongor.workout.Adapters.WorkoutsListAdapter;
import com.nagycsongor.workout.Models.Workout;
import com.nagycsongor.workout.R;

import java.util.ArrayList;

public class MainActivityPresenter {

    private MainActivityDelegate activityDelegate;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ArrayList<Workout> workouts;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public MainActivityPresenter(MainActivityDelegate activityDelegate) {
        this.activityDelegate = activityDelegate;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid()).child("workouts");
        this.workouts = new ArrayList<>();
    }

    public void onStart(Context context) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workouts.clear();
                for (DataSnapshot voteSnapshot : dataSnapshot.getChildren()) {
                    Workout workout = voteSnapshot.getValue(Workout.class);
                    workouts.add(workout);
                }
                adapter = new WorkoutsListAdapter(context, workouts);
                activityDelegate.setRecyclerView(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void createRecyclerView(Context context) {
        layoutManager = new LinearLayoutManager(context);
        adapter = new WorkoutsListAdapter(context, workouts);
        activityDelegate.createRecyclerView(adapter, layoutManager);
    }

    public void onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            logout();
        }
    }

    private void logout() {
        firebaseAuth.signOut();
        activityDelegate.openLoginActivity();
    }

    public void addFloatingActionButtonClick() {
        activityDelegate.openAddWorkoutActivity();
    }
}
