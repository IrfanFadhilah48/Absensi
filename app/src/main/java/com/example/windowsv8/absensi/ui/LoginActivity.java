package com.example.windowsv8.absensi.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.presenter.LoginPresenter;
import com.example.windowsv8.absensi.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter loginPresenter;
    private EditText editTextUser, editTextPass;
    private AppCompatButton buttonLogin, buttonPindah;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = findViewById(R.id.editTextNpmLogin);
        editTextPass = findViewById(R.id.editTextPasswordLogin);
        constraintLayout = findViewById(R.id.login_constraint);
        buttonLogin = findViewById(R.id.buttonCek);
        buttonPindah = findViewById(R.id.buttonPindahAdmin);
        progressBar = findViewById(R.id.progressBarLogin);
        progressDialog = new ProgressDialog(this);
        loginPresenter = new LoginPresenter(this, this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUser.getText().toString();
                String pass = editTextPass.getText().toString();
                loginPresenter.checkData(user, pass);
            }
        });

        buttonPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "pindah aplikasi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setBackgroundColor(Color.parseColor("#b4b4b4"));
//        constraintLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void nextActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.buttonCek:
//                String user = editTextUser.getText().toString();
//                String pass = editTextPass.getText().toString();
//                loginPresenter.checkData(user, pass);
//                break;
//
//            case R.id.buttonPindahAdmin:
//                Toast.makeText(this, "pindah aplikasi", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
}
