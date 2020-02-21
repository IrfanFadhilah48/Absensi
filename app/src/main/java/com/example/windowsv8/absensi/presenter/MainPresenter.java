package com.example.windowsv8.absensi.presenter;

import android.content.Context;

import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.view.MainView;

public class MainPresenter {

    private MainView mainView;
    private Context context;
    private SessionManager sessionManager;

    public MainPresenter(MainView mainView, Context context, SessionManager sessionManager){
        this.mainView = mainView;
        this.context = context;
        this.sessionManager = sessionManager;
    }

    public void logout(){
        mainView.showLoading();
        sessionManager = new SessionManager(context);
        sessionManager.closeSession();
        mainView.hideLoading();
        mainView.nextActivity();
    }
}
