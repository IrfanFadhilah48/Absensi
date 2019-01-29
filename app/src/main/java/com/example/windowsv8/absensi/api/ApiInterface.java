package com.example.windowsv8.absensi.api;

import com.example.windowsv8.absensi.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginkaryawan.php")
    Call<ResponseLogin> loginKaryawan(@Field("id_karyawan") String id_karyawan, @Field("password") String password);

}
