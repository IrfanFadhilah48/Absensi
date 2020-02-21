package com.example.windowsv8.absensi.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.windowsv8.absensi.api.ApiClient;
import com.example.windowsv8.absensi.api.ApiInterface;
import com.example.windowsv8.absensi.model.JamKerja;
import com.example.windowsv8.absensi.model.ResponseAbsensi;
import com.example.windowsv8.absensi.model.ResponseJamKerja;
import com.example.windowsv8.absensi.view.ListJamKerjaView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListJamKerjaPresenter {

    private ListJamKerjaView listJamKerjaView;
    private ApiClient apiClient;
    private Context context;
    private List<JamKerja> jamKerjas;

    public ListJamKerjaPresenter(ListJamKerjaView listJamKerjaView, Context context){
        this.listJamKerjaView = listJamKerjaView;
        this.context = context;
    }

    public void getData(String id_karyawan, String tanggal){

        listJamKerjaView.showLoading();
        apiClient.getClient()
                .create(ApiInterface.class)
                .getListJamKerja(id_karyawan, tanggal)
                .enqueue(new Callback<ResponseJamKerja>() {
                    @Override
                    public void onResponse(Call<ResponseJamKerja> call, Response<ResponseJamKerja> response) {
                        ResponseJamKerja responseJamKerja = response.body();

                        if (responseJamKerja != null && responseJamKerja.getData() != null){
                            jamKerjas = responseJamKerja.getData();
                            listJamKerjaView.hideLoading();
                            listJamKerjaView.showData(jamKerjas);
                        }else {
                            listJamKerjaView.hideLoading();
                            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseJamKerja> call, Throwable t) {
                        listJamKerjaView.hideLoading();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
