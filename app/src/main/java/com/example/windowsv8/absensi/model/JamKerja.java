package com.example.windowsv8.absensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JamKerja {

    @SerializedName("nomor")
    @Expose
    private String nomor;
    @SerializedName("tanggalawal")
    @Expose
    private String tanggalawal;
    @SerializedName("tanggalakhir")
    @Expose
    private String tanggalakhir;
    @SerializedName("jamawal")
    @Expose
    private String jamawal;
    @SerializedName("jamakhir")
    @Expose
    private String jamakhir;
    @SerializedName("jamkerja")
    @Expose
    private String jamkerja;

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTanggalawal() {
        return tanggalawal;
    }

    public void setTanggalawal(String tanggalawal) {
        this.tanggalawal = tanggalawal;
    }

    public String getTanggalakhir() {
        return tanggalakhir;
    }

    public void setTanggalakhir(String tanggalakhir) {
        this.tanggalakhir = tanggalakhir;
    }

    public String getJamawal() {
        return jamawal;
    }

    public void setJamawal(String jamawal) {
        this.jamawal = jamawal;
    }

    public String getJamakhir() {
        return jamakhir;
    }

    public void setJamakhir(String jamakhir) {
        this.jamakhir = jamakhir;
    }

    public String getJamkerja() {
        return jamkerja;
    }

    public void setJamkerja(String jamkerja) {
        this.jamkerja = jamkerja;
    }

}
