package com.example.windowsv8.absensi.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.windowsv8.absensi.api.ApiClient;
import com.example.windowsv8.absensi.api.ApiInterface;
import com.example.windowsv8.absensi.model.ResponseAbsensi;
import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.view.AbsensiView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsensiPresenter {

    private AbsensiView absensiView;
    private ApiClient apiClient;
    private Context context;
    private SessionManager sessionManager;

    public AbsensiPresenter(AbsensiView absensiView, Context context, SessionManager sessionManager){
        this.absensiView = absensiView;
        this.context = context;
        this.sessionManager = sessionManager;
    }

    public void callAbsen(String id_karyawan){
        absensiView.showLoading();
        sessionManager = new SessionManager(context);

        apiClient.getClient()
                .create(ApiInterface.class)
                .absenKaryawan(id_karyawan)
                .enqueue(new Callback<ResponseAbsensi>() {
                    @Override
                    public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess().equals(1)){
                                sessionManager.updateSession("1");
                                absensiView.hideLoading();
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                absensiView.successful();
                            }else {
                                absensiView.hideLoading();
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void closeAbsen(String id_karyawan){
        absensiView.showLoading();
        sessionManager = new SessionManager(context);

        apiClient.getClient()
                .create(ApiInterface.class)
                .selesaiAbsenKaryawan(id_karyawan)
                .enqueue(new Callback<ResponseAbsensi>() {
                    @Override
                    public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess().equals(1)){
                                sessionManager.updateSession("0");
                                absensiView.hideLoading();
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                absensiView.successful();
                            }else {
                                absensiView.hideLoading();
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        absensiView.hideLoading();
                    }
                });
    }
}
