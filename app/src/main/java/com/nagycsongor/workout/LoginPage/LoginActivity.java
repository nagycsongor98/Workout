package com.nagycsongor.workout.LoginPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nagycsongor.workout.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityDelegate{
    private LoginActivityPresenter presenter;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;
    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        presenter = new LoginActivityPresenter(this);
        initializesElements();
        setLoginButtonOnClickListener();
        setSignUpButtonOnClickListener();
        setForgotPasswordTextViewOnClickListener();
    }


    @Override
    public void createAlertDialog(int titleId, int messageId) {
        EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleId)
                .setMessage(messageId)
                .setView(editText)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    if(editText.getText() != null && !editText.getText().toString().isEmpty()) {
                        presenter.alertDialogResult(editText.getText().toString());
                    }
                })
                .show();

    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    private void initializesElements() {
        this.emailEditText = findViewById(R.id.emailEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
        this.loginButton = findViewById(R.id.loginButton);
        this.signUpTextView = findViewById(R.id.signUpTextView);
        this.forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
    }

    private void setLoginButtonOnClickListener() {
        this.loginButton.setOnClickListener(v -> {
            presenter.loginButtonClick(this);
        });
    }

    private void setSignUpButtonOnClickListener() {
        this.signUpTextView.setOnClickListener(v -> {
            presenter.signUpButtonClick();
        });
    }

    private void setForgotPasswordTextViewOnClickListener() {
        forgotPasswordTextView.setOnClickListener(v -> {
            presenter.forgotPasswordTextViewClick();
        });
    }

    @Override
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        finish();
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
    public void createToast(int stringId) {
        Toast.makeText(this,stringId,Toast.LENGTH_LONG).show();
    }
}
