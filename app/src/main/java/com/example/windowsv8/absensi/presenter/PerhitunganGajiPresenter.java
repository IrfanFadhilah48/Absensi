package com.example.windowsv8.absensi.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.windowsv8.absensi.api.ApiClient;
import com.example.windowsv8.absensi.api.ApiInterface;
import com.example.windowsv8.absensi.model.LihatGaji;
import com.example.windowsv8.absensi.model.ResponseJamKerja;
import com.example.windowsv8.absensi.model.ResponseLihatGaji;
import com.example.windowsv8.absensi.view.PerhitunganGajiView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerhitunganGajiPresenter {

    private PerhitunganGajiView perhitunganGajiView;
    private Context context;
    private ApiClient apiClient;
    private LihatGaji lihatgaji;

    public PerhitunganGajiPresenter(PerhitunganGajiView perhitunganGajiView, Context context){
        this.perhitunganGajiView = perhitunganGajiView;
        this.context = context;
    }

    public void getData(String id_karyawan, String bulan){
        perhitunganGajiView.showLoading();
        apiClient.getClient()
                .create(ApiInterface.class)
                .getGajiBulanan(id_karyawan, bulan)
                .enqueue(new Callback<ResponseLihatGaji>() {
                    @Override
                    public void onResponse(Call<ResponseLihatGaji> call, Response<ResponseLihatGaji> response) {
                        ResponseLihatGaji responseLihatGaji = response.body();
                        lihatgaji = responseLihatGaji.getData();
                        if (response.isSuccessful()) {
                            if (response.body().getHasil().equals(1)) {
                                Log.e("tetser", response.body().getHasil().toString());
                                perhitunganGajiView.hideLoading();
                                perhitunganGajiView.showData(lihatgaji);
                            } else {
                                perhitunganGajiView.hideLoading();
                                perhitunganGajiView.dataKosong(lihatgaji);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLihatGaji> call, Throwable t) {
                        perhitunganGajiView.hideLoading();
                    }
                });
    }
}
