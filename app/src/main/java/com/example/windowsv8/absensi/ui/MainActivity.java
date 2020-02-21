package com.example.windowsv8.absensi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.presenter.MainPresenter;
import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.view.MainView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MainView ,View.OnClickListener {

    private CardView cardView, cardView1, cardView2, cardView3, cardView4,cardView5;
    private SessionManager sessionManager;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    String tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        tes = user.get(SessionManager.TAG_ID);
        Log.e("hasilnya", tes);

        cardView = findViewById(R.id.cardView1);
        cardView1 = findViewById(R.id.cardView2);
        cardView2 = findViewById(R.id.cardView3);
        cardView3 = findViewById(R.id.cardView4);
        cardView4 = findViewById(R.id.cardView5);
        cardView5 = findViewById(R.id.cardView6);
        progressBar = findViewById(R.id.progressBarLogout);
        presenter = new MainPresenter(this, this, sessionManager);

        cardView.setOnClickListener(this);
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardView1:
                startActivity(new Intent(this, AbsensiActivity.class));
                finish();
                break;
            case R.id.cardView2:
                startActivity(new Intent(this, ListJamKerjaActivity.class));
                finish();
                break;
            case R.id.cardView3:
                startActivity(new Intent(this, PerhitunganGajiActivity.class));
                finish();
                break;
            case R.id.cardView4:
                startActivity(new Intent(this, ListAbsensiActivity.class));
                finish();
                break;
            case R.id.cardView5:
                presenter.logout();
                break;
            case R.id.cardView6:
                startActivity(new Intent(this, FingerprintActivity.class));
                finish();
                break;
        }

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setBackgroundResource(R.color.trasnparent);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void nextActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
