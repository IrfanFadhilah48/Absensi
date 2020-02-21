package com.example.windowsv8.absensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LihatGaji {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_karyawan")
    @Expose
    private String idKaryawan;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("masuk")
    @Expose
    private String masuk;
    @SerializedName("totaljam")
    @Expose
    private String totaljam;
    @SerializedName("gaji")
    @Expose
    private String gaji;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMasuk() {
        return masuk;
    }

    public void setMasuk(String masuk) {
        this.masuk = masuk;
    }

    public String getTotaljam() {
        return totaljam;
    }

    public void setTotaljam(String totaljam) {
        this.totaljam = totaljam;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }
}
