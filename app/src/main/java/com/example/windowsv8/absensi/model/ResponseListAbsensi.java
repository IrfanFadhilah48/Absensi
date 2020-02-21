package com.example.windowsv8.absensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListAbsensi {

    @SerializedName("data")
    @Expose
    private List<ListAbsensi> data = null;

    public List<ListAbsensi> getData() {
        return data;
    }

    public void setData(List<ListAbsensi> data) {
        this.data = data;
    }
}
