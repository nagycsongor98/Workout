package com.nagycsongor.workout.RegisterPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nagycsongor.workout.R;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityDelegate {

    private RegisterActivityPresenter presenter;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;
    private CheckBox termsAndConditionsCheckBox;
    private Button signUpButton;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        this.presenter = new RegisterActivityPresenter(this);
        initializesElements();
        setSignUpButtonOnClickListener();
        setLoginButtonOnClickListener();
    }

    private void initializesElements() {
        this.usernameEditText = findViewById(R.id.userNameEditText);
        this.emailEditText = findViewById(R.id.emailEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
        this.passwordAgainEditText = findViewById(R.id.passwordConfirmationEditText);
        this.termsAndConditionsCheckBox = findViewById(R.id.termsAndConditionsCheckBox);
        this.signUpButton = findViewById(R.id.signUpButton);
        this.loginTextView = findViewById(R.id.loginTextView);
    }

    private void setSignUpButtonOnClickListener() {
        this.signUpButton.setOnClickListener(v -> {
            presenter.signUpButtonClick(this);
        });
    }

    private void setLoginButtonOnClickListener() {
        this.loginTextView.setOnClickListener(v -> {
            presenter.loginButtonClick();
        });
    }

    @Override
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        finish();
    }

    @Override
    public String getUsernameEditText() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getEmailEditText() {
        return emailEditText.getText().toString();
    }

    @Override
    public String getPasswordEditText() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getPasswordAgainEditText() {
        return passwordAgainEditText.getText().toString();
    }

    @Override
    public boolean getTermsAndConditionsCheckBox() {
        return termsAndConditionsCheckBox.isChecked();
    }

    @Override
    public void createToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }
}
