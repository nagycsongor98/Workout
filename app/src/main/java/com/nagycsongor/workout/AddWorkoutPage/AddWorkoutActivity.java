package com.nagycsongor.workout.AddWorkoutPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nagycsongor.workout.R;

public class AddWorkoutActivity extends AppCompatActivity implements AddWorkoutActivityDelegate {

    private EditText nameEditText;
    private EditText burnedCaloriesEditText;
    private TextView datePickerTextView;
    private EditText durationEditText;
    private ImageView imageView;
    private TextView takePictureTextView;
    private TextView choosePictureTextView;
    private Button saveButton;
    private ProgressDialog progressDialog;
    private AddWorkoutActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);
        getSupportActionBar().hide();
        initializesElements();
        this.presenter = new AddWorkoutActivityPresenter(this);
        setNameEditTextOnTouchListener();
        setDatePickerTextViewOnClickListener();
        setTakePictureTextViewOnClickListener();
        setChoosePictureTextViewOnClickListener();
        setSaveButtonOnClickListener();
    }

    private void initializesElements() {
        this.nameEditText = findViewById(R.id.nameEditText);
        this.burnedCaloriesEditText = findViewById(R.id.burnedCaloriesEditText);
        this.datePickerTextView = findViewById(R.id.datePickerTextView);
        this.durationEditText = findViewById(R.id.durationEditText);
        this.imageView = findViewById(R.id.imageView);
        this.takePictureTextView = findViewById(R.id.takePictureTextView);
        this.choosePictureTextView = findViewById(R.id.choosePictureTextView);
        this.saveButton = findViewById(R.id.saveButton);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle(getString(R.string.uploading));
    }

    private void setNameEditTextOnTouchListener() {
        this.nameEditText.setOnTouchListener((v, event) -> {
            presenter.nameEditTextTouched();
            return false;
        });
    }


    private void setDatePickerTextViewOnClickListener() {
        this.datePickerTextView.setOnClickListener(v -> {
            presenter.datePickerTextViewOnClick();
        });
    }


    private void setSaveButtonOnClickListener() {
        this.saveButton.setOnClickListener(v -> {
            presenter.saveButtonClick(this);
        });
    }

    private void setTakePictureTextViewOnClickListener() {
        this.takePictureTextView.setOnClickListener(v -> {
            presenter.takePictureTextViewClick();
        });
    }

    private void setChoosePictureTextViewOnClickListener() {
        this.choosePictureTextView.setOnClickListener(v -> {
            presenter.choosePictureTextViewClick();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setNameEditText(String string) {
        nameEditText.setText(string);
    }

    @Override
    public void setDatePickerTextView(String string) {
        datePickerTextView.setText(string);
    }

    @Override
    public void createDatePickerDialog(int year, int month, int day) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    presenter.onDateSelected(year1, monthOfYear, dayOfMonth);
                },
                year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void startActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setImageView(Uri uri) {
        Glide.with(this)
                .load(uri)
                .into(imageView);
    }

    @Override
    public String getNameEditText() {
        return nameEditText.getText().toString();
    }

    @Override
    public String getBurnedCaloriesEditText() {
        return burnedCaloriesEditText.getText().toString();
    }

    @Override
    public String getDatePickerTextView() {
        return datePickerTextView.getText().toString();
    }

    @Override
    public String getDurationEditText() {
        return durationEditText.getText().toString();
    }

    @Override
    public void createToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void setProgressDialogMessage(String string) {
        progressDialog.setMessage(string);
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void createPositiveAlertDialog(int titleStringId) {
        new AlertDialog.Builder(this)
                .setTitle(titleStringId)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    finish();
                }).show();
    }

    @Override
    public void createNegativeAlertDialog(int titleStringId) {
        new AlertDialog.Builder(this)
                .setTitle(titleStringId)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                })
                .show();
    }
}
