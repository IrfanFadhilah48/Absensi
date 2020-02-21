package com.example.windowsv8.absensi.view;

import com.example.windowsv8.absensi.model.LihatGaji;

import java.util.List;

public interface PerhitunganGajiView {
    void showLoading();
    void hideLoading();
    void showData(LihatGaji lihatGaji);
    void dataKosong(LihatGaji lihatGaji);
}
