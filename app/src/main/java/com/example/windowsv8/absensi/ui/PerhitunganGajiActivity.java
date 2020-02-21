package com.example.windowsv8.absensi.ui;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.model.LihatGaji;
import com.example.windowsv8.absensi.presenter.PerhitunganGajiPresenter;
import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.view.PerhitunganGajiView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PerhitunganGajiActivity extends AppCompatActivity implements PerhitunganGajiView{

    private EditText editText;
    private Button button;
    private ProgressBar progressBar;
    private TableLayout tableLayout;
    private ImageView imageView;
    private TextView textView, textView1, textView2, textView3, textView4;
    private LinearLayout linearLayout;
    private PerhitunganGajiPresenter perhitunganGajiPresenter;
    private SessionManager sessionManager;
    private LihatGaji lihatGajis;
    Calendar calendar;
    String convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan_gaji);

        editText = findViewById(R.id.bulanGajian);
        button = findViewById(R.id.buttonLihatGaji);
        progressBar = findViewById(R.id.progressBarLihatGaji);
        imageView = findViewById(R.id.imageViewLihatGaji);
        tableLayout = findViewById(R.id.tableLayoutLihatGaji);
        textView = findViewById(R.id.lihatGajiTingkatKehadiran);
        textView1 = findViewById(R.id.lihatGajiJumlahJamKerja);
        textView2 = findViewById(R.id.lihatGajiPerhitunganGaji);
        textView3 = findViewById(R.id.lihatGajiBulanan);
        textView4 = findViewById(R.id.checkLihatGaji);
        linearLayout = findViewById(R.id.linearLayoutLihatGaji);
        perhitunganGajiPresenter = new PerhitunganGajiPresenter(this, this);
        sessionManager = new SessionManager(this);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int cyear = c.get(Calendar.YEAR);
                int cmonth = c.get(Calendar.MONTH);
                int cday = c.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(PerhitunganGajiActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                editText.setText((monthOfYear + 1)+"/"+year);
                                calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat sdf = new SimpleDateFormat("MMMM YYYY");
                                SimpleDateFormat sdfkirim = new SimpleDateFormat("YYYY-MM");
                                Date now = Calendar.getInstance().getTime();
                                sdf.format(now);

                                if (calendar.getTime().before(now)){
                                    editText.setText(sdf.format(calendar.getTime()));
                                    convert = sdfkirim.format(calendar.getTime());

                                }else{
                                    Toast.makeText(getApplicationContext(), "Buru-buru amat mau gajian nya", Toast.LENGTH_SHORT).show();
                                }
//                                int mMonth = monthOfYear + 1;
//                                int mYear = year;
                            }
                        },
                        cyear, cmonth, cday);
                datePickerDialog.setTitle("Silahkan masukkan bulan");
                datePickerDialog.getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
                datePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    private void requestData() {
        HashMap<String, String> user = sessionManager.getDataUser();
        Log.e("databulan", convert);
        perhitunganGajiPresenter.getData(user.get(SessionManager.TAG_ID), convert);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(LihatGaji lihatGaji) {
        this.lihatGajis = lihatGaji;
            imageView.setImageResource(R.drawable.money);
            linearLayout.setVisibility(View.VISIBLE);
            tableLayout.setVisibility(View.VISIBLE);
            textView.setText(lihatGajis.getMasuk());
            textView1.setText(lihatGajis.getTotaljam());
            textView2.setText(lihatGajis.getTotaljam()+" Jam ");
            textView3.setText(lihatGajis.getGaji());
            textView4.setVisibility(View.INVISIBLE);

    }

    @Override
    public void dataKosong(LihatGaji lihatGaji) {
        this.lihatGajis = lihatGaji;
        imageView.setImageResource(R.drawable.nomoney);
        tableLayout.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.VISIBLE);
    }
}
