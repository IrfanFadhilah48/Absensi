package com.example.windowsv8.absensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListAbsensi {
    @SerializedName("nomor")
    @Expose
    private Integer nomor;
    @SerializedName("id_karyawan")
    @Expose
    private String idKaryawan;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kondisi")
    @Expose
    private String kondisi;

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }
}
