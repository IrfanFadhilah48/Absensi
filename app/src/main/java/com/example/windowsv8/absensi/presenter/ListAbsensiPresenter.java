package com.example.windowsv8.absensi.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.windowsv8.absensi.api.ApiClient;
import com.example.windowsv8.absensi.api.ApiInterface;
import com.example.windowsv8.absensi.model.ListAbsensi;
import com.example.windowsv8.absensi.model.ResponseListAbsensi;
import com.example.windowsv8.absensi.view.ListAbsensiView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAbsensiPresenter {

    private ListAbsensiView listAbsensiView;
    private ApiClient apiClient;
    private Context context;
    private List<ListAbsensi> listAbsensi;

    public ListAbsensiPresenter(ListAbsensiView listAbsensiView, Context context){
        this.listAbsensiView = listAbsensiView;
        this.context = context;
    }

    public void showData(){
        listAbsensiView.showLoading();
        apiClient.getClient()
                .create(ApiInterface.class)
                .getListAbsen()
                .enqueue(new Callback<ResponseListAbsensi>() {
                    @Override
                    public void onResponse(Call<ResponseListAbsensi> call, Response<ResponseListAbsensi> response) {
                        ResponseListAbsensi responseListAbsensi = response.body();
                        if (response.isSuccessful()){
                            if (responseListAbsensi != null) {
                                listAbsensi = responseListAbsensi.getData();
                                listAbsensiView.hideLoading();
                                listAbsensiView.getData(listAbsensi);
                            }
                            else {
                                listAbsensiView.hideLoading();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseListAbsensi> call, Throwable t) {
                        listAbsensiView.hideLoading();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
