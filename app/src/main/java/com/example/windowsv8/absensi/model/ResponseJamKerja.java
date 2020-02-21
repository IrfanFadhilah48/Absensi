package com.example.windowsv8.absensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseJamKerja {

    @SerializedName("data")
    @Expose
    private List<JamKerja> data = null;

    public List<JamKerja> getData() {
        return data;
    }

    public void setData(List<JamKerja> data) {
        this.data = data;
    }
}
