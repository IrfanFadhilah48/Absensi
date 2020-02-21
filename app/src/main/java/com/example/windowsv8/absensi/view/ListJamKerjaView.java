package com.example.windowsv8.absensi.view;

import com.example.windowsv8.absensi.model.JamKerja;

import java.util.List;

public interface ListJamKerjaView {
    void showLoading();
    void hideLoading();
    void showData(List<JamKerja> jamKerjas);
}
