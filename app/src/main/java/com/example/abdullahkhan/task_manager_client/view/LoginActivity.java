package com.example.abdullahkhan.task_manager_client.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkhan.task_manager_client.R;
import com.example.abdullahkhan.task_manager_client.controller.Api_Functions;
import com.example.abdullahkhan.task_manager_client.network.User_Retrofit;
import com.example.abdullahkhan.task_manager_client.model.LoginResponse;
import com.example.abdullahkhan.task_manager_client.model.User;
import com.example.abdullahkhan.task_manager_client.network.RetrofitInstance;

import butterknife.ButterKnife;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.abdullahkhan.task_manager_client.view.MainActivity.auth;

public class LoginActivity extends Main2Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private ProgressDialog progressDialog;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login)  Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Log.d(TAG,"checking on reate Login Activity");

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }
    public void login() {
        Log.d(TAG, "Login");
        progressDialog = new ProgressDialog(LoginActivity.this);
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        Api_Functions api_functions;

        api_functions = new Api_Functions(this,null);
        api_functions.loginUser(email,password,sharedPref);

        Log.d(TAG,"checking asynchnorous");


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        Log.d(TAG,"On login success");
        _loginButton.setEnabled(true);

        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

        progressDialog.dismiss();
        finish();
    }

    public void onLoginFailed() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("status", null);
        editor.apply();

        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
        progressDialog.dismiss();
        finish();
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
