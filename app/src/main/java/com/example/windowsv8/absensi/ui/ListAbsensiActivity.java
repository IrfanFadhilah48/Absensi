package com.example.windowsv8.absensi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.model.ListAbsensi;
import com.example.windowsv8.absensi.presenter.ListAbsensiPresenter;
import com.example.windowsv8.absensi.view.ListAbsensiView;

import java.util.ArrayList;
import java.util.List;

public class ListAbsensiActivity extends AppCompatActivity implements ListAbsensiView {

    private ListAbsensiPresenter listAbsensiPresenter;
    private List<ListAbsensi> listAbsensi = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView textView, textView1, textView2, textView3;
    private TableLayout tableLayout, tableLayoutHeading;
    private TableRow tableRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_absensi_karyawan);

        progressBar = findViewById(R.id.progressBarListAbsensi);
        tableLayout = findViewById(R.id.tableLayoutAbsensiContent);
        tableLayoutHeading = findViewById(R.id.tableLayoutAbsensi);
        listAbsensiPresenter = new ListAbsensiPresenter(this, this);
        listAbsensiPresenter.showData();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.INVISIBLE);
        tableLayoutHeading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        tableLayoutHeading.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void getData(List<ListAbsensi> listAbsensis) {
        listAbsensi.clear();
        listAbsensi.addAll(listAbsensis);
        showData();
        Log.e("data", listAbsensi.toString());
    }

    private void showData() {
        for (int i = 0; i < listAbsensi.size(); i++){
            tableRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,0,0);
            tableRow.setLayoutParams(lp);

            textView = new TextView(this);
            textView1 = new TextView(this);
            textView2 = new TextView(this);
            textView3 = new TextView(this);

            textView.setBackgroundResource(R.drawable.cell_shape);
            textView1.setBackgroundResource(R.drawable.cell_shape);
            textView2.setBackgroundResource(R.drawable.cell_shape);
            textView3.setBackgroundResource(R.drawable.cell_shape);

            textView.setLayoutParams(new TableRow.LayoutParams(129, 60));
            textView1.setLayoutParams(new TableRow.LayoutParams(342, 60));
            textView2.setLayoutParams(new TableRow.LayoutParams(375, 60));
            textView3.setLayoutParams(new TableRow.LayoutParams(195, 60));

            textView.setGravity(Gravity.CENTER);
            textView1.setGravity(Gravity.CENTER);
            textView2.setGravity(Gravity.CENTER);
            textView3.setGravity(Gravity.CENTER);

            textView.setText(String.valueOf(listAbsensi.get(i).getNomor()));
            textView1.setText(listAbsensi.get(i).getIdKaryawan());
            textView2.setText(listAbsensi.get(i).getNama());
            if (listAbsensi.get(i).getKondisi().equals("1")){
                textView3.setText("MASUK");
                textView3.setTextColor(getResources().getColor(R.color.hijau));
            }else if (listAbsensi.get(i).getKondisi().equals("0")){
                textView3.setText("BELUM");
                textView3.setTextColor(getResources().getColor(R.color.merah));
            }else {
                textView3.setText("IZIN");
                textView3.setTextColor(getResources().getColor(R.color.kuning));
            }

            tableRow.addView(textView);
            tableRow.addView(textView1);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            tableLayout.addView(tableRow, i, lp);
        }
    }
}
