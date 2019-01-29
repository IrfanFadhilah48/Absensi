package com.example.windowsv8.absensi.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.windowsv8.absensi.api.ApiClient;
import com.example.windowsv8.absensi.api.ApiInterface;
import com.example.windowsv8.absensi.model.ResponseLogin;
import com.example.windowsv8.absensi.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter{

    private LoginView loginView;
    private ApiClient apiClient;
    Context context;

    public LoginPresenter(LoginView loginView, Context context){
        this.loginView = loginView;
        this.context = context;
    }

    public void checkData(String id_karyawan, String password){
        loginView.showLoading();
        apiClient.getClient()
                .create(ApiInterface.class)
                .loginKaryawan(id_karyawan, password)
                .enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess().equals(1)){
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                loginView.nextActivity();
                                loginView.hideLoading();
                            }else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                loginView.hideLoading();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        loginView.hideLoading();
                    }
                });
    }
}
