package com.example.windowsv8.absensi.api;

import com.example.windowsv8.absensi.model.ResponseAbsensi;
import com.example.windowsv8.absensi.model.ResponseJamKerja;
import com.example.windowsv8.absensi.model.ResponseLihatGaji;
import com.example.windowsv8.absensi.model.ResponseListAbsensi;
import com.example.windowsv8.absensi.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginkaryawan.php")
    Call<ResponseLogin> loginKaryawan(@Field("id_karyawan") String id_karyawan, @Field("password") String password);

    @FormUrlEncoded
    @POST("waktuawal.php")
    Call<ResponseAbsensi> absenKaryawan(@Field("id_karyawan") String id_karyawan);

    @FormUrlEncoded
    @POST("catatwaktu.php")
    Call<ResponseAbsensi> selesaiAbsenKaryawan(@Field("id_karyawan") String id_karyawan);

    @FormUrlEncoded
    @POST("listjamkerja.php")
    Call<ResponseJamKerja> getListJamKerja(@Field("id_karyawan") String id_karyawan, @Field("akhirbulan") String tanggalAkhir);

    @FormUrlEncoded
    @POST("lihatgajibulanan.php")
    Call<ResponseLihatGaji> getGajiBulanan(@Field("id_karyawan") String id_karyawan, @Field("bulan") String gajiBulan);

    @GET("listabsensikaryawan.php")
    Call<ResponseListAbsensi> getListAbsen();
}
