package com.example.windowsv8.absensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLihatGaji {

    @SerializedName("hasil")
    @Expose
    private Integer hasil;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private LihatGaji data;

    public Integer getHasil() {
        return hasil;
    }

    public void setHasil(Integer hasil) {
        this.hasil = hasil;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LihatGaji getData() {
        return data;
    }

    public void setData(LihatGaji data) {
        this.data = data;
    }


}
