package com.nagycsongor.workout.DetailsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nagycsongor.workout.R;
import com.nagycsongor.workout.Models.Workout;

import java.io.File;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityDelegate {
    public final static String KEY = "key";
    private DetailsActivityPresenter presenter;
    private TextView nameTextView;
    private ImageView imageView;
    private TextView burnedCaloriesTextView;
    private TextView dateTextView;
    private TextView durationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();
        initializesElements();
        presenter = new DetailsActivityPresenter(this);
    }

    private void initializesElements() {
        this.nameTextView = findViewById(R.id.nameTextView);
        this.imageView = findViewById(R.id.imageView);
        this.burnedCaloriesTextView = findViewById(R.id.burnedCaloriesTextView);
        this.dateTextView = findViewById(R.id.dateTextView);
        this.durationTextView = findViewById(R.id.durationTextView);
    }

    @Override
    public Workout getWorkout() {
        return (Workout) this.getIntent().getSerializableExtra(KEY);
    }

    @Override
    public void setNameTextView(String string) {
        this.nameTextView.setText(string);
    }

    @Override
    public void setBurnedCaloriesTextView(String string) {
        this.burnedCaloriesTextView.setText(string);
    }

    @Override
    public void setDateTextView(String string) {
        this.dateTextView.setText(string);
    }

    @Override
    public void setDurationTextView(String string) {
        this.durationTextView.setText(string);
    }

    @Override
    public void setImageView(File file) {
        Glide.with(this)
                .load(file)
                .into(this.imageView);
    }
}

