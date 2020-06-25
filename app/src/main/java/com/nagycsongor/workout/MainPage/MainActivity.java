package com.nagycsongor.workout.MainPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nagycsongor.workout.AddWorkoutPage.AddWorkoutActivity;
import com.nagycsongor.workout.LoginPage.LoginActivity;
import com.nagycsongor.workout.R;

public class MainActivity extends AppCompatActivity implements MainActivityDelegate {

    private FloatingActionButton addFloatingActionButton;
    private RecyclerView recyclerView;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");
        initializesElements();
        presenter = new MainActivityPresenter(this);
        presenter.createRecyclerView(this);
        setAddFloatingActionButtonOnClickListener();

    }

    private void initializesElements() {
        this.addFloatingActionButton = findViewById(R.id.addFloatingActionButton);
        this.recyclerView = findViewById(R.id.workoutRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        presenter.onOptionsItemSelected(item);
        return true;
    }

    private void setAddFloatingActionButtonOnClickListener() {
        this.addFloatingActionButton.setOnClickListener(v -> {
            presenter.addFloatingActionButtonClick();
        });
    }


    @Override
    public void createRecyclerView(RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setRecyclerView(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openAddWorkoutActivity() {
        Intent intent = new Intent(getApplicationContext(), AddWorkoutActivity.class);
        startActivity(intent);
    }
}
