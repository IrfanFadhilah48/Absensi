package com.example.windowsv8.absensi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.model.JamKerja;
import com.example.windowsv8.absensi.presenter.ListJamKerjaPresenter;
import com.example.windowsv8.absensi.view.ListJamKerjaView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListJamKerjaActivity extends AppCompatActivity implements ListJamKerjaView {

    private ListJamKerjaPresenter listJamKerjaPresenter;
    private TableRow tableRow;
    private TableLayout tableLayout;
    private List<JamKerja> jamKerjasa = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView textView, textView1, textView2, textView3, textView4, textView5;
    private LinearLayout linearLayout, linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    public static final String tanggalawal = "2019-02-25";
    public static final String id_karyawan = "0000000000002";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jam_kerja);

        tableLayout = findViewById(R.id.tableListKaryawan);
        progressBar = findViewById(R.id.progressBarListJamKerja);
        listJamKerjaPresenter = new ListJamKerjaPresenter(this, this);
        listJamKerjaPresenter.getData(id_karyawan, tanggalawal);

    }

    private void showData() {
        Log.e("panjang", String.valueOf(jamKerjasa.size()));

        for (int i = 0; i < jamKerjasa.size(); i++){
            tableRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,0,0);
            tableRow.setLayoutParams(lp);
//            tableRow.setBackgroundResource(R.drawable.table_row);


            textView = new TextView(this);
            textView1 = new TextView(this);
            textView2 = new TextView(this);
            textView3 = new TextView(this);
            textView4 = new TextView(this);

            textView.setBackgroundResource(R.drawable.cell_shape);
            textView1.setBackgroundResource(R.drawable.cell_shape);
            textView2.setBackgroundResource(R.drawable.cell_shape);
            textView3.setBackgroundResource(R.drawable.cell_shape);
            textView4.setBackgroundResource(R.drawable.cell_shape);


            lp.width = 153;
            lp.height = 60;

            linearLayout = new LinearLayout(this);
            linearLayout1 = new LinearLayout(this);
            linearLayout2 = new LinearLayout(this);
            linearLayout3 = new LinearLayout(this);
            linearLayout4 = new LinearLayout(this);

            linearLayout.setLayoutParams(lp);
//
            textView.setLayoutParams(new TableRow.LayoutParams(152, 60));
            textView1.setLayoutParams(new TableRow.LayoutParams(210, 60));
            textView2.setLayoutParams(new TableRow.LayoutParams(225, 60));
            textView3.setLayoutParams(new TableRow.LayoutParams(225, 60));
            textView4.setLayoutParams(new TableRow.LayoutParams(219, 60));


            DateFormat input = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat output = new SimpleDateFormat("dd/MM");
            try {
                Date dateInput = input.parse(jamKerjasa.get(i).getTanggalawal());
                textView.setText(jamKerjasa.get(i).getNomor());
                textView1.setText(output.format(dateInput));
                textView2.setText(jamKerjasa.get(i).getJamawal());
                textView3.setText(jamKerjasa.get(i).getJamakhir());
                textView4.setText(jamKerjasa.get(i).getJamkerja());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tableRow.addView(textView);
            tableRow.addView(textView1);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            tableRow.addView(textView4);
            tableLayout.addView(tableRow, i,lp);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        tableLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(List<JamKerja> jamKerjas) {
        jamKerjasa.clear();
        jamKerjasa.addAll(jamKerjas);
        showData();
        Log.e("Datanya",jamKerjasa.toString());
    }
}
