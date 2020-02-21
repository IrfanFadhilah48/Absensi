package com.example.windowsv8.absensi.view;

import com.example.windowsv8.absensi.model.ListAbsensi;

import java.util.List;

public interface ListAbsensiView {
    void showLoading();
    void hideLoading();
    void getData(List<ListAbsensi> listAbsensis);
}
