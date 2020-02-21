package com.example.windowsv8.absensi.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.presenter.AbsensiPresenter;
import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.ui.MainActivity;

import javax.security.auth.callback.Callback;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    Context context;
    String data;
    AbsensiPresenter absensiPresenter;
    SessionManager sessionManager;

    private static final long ERROR_TIMEOUT_MILLIS = 1600;
    private static final long SUCCESS_DELAY_MILLIS = 1300;
    private CancellationSignal cancellationSignal;
    private final FingerprintManager mFingerprintManager;
    private final ImageView mIcon;
    private final TextView mErrorTextView;
    private final Callback mCallback;
    private boolean mSelfCancelled;

    public FingerprintHandler(FingerprintManager fingerprintManager
            , ImageView imageView, TextView textView, Callback callback){
        mFingerprintManager = fingerprintManager;
        mIcon = imageView;
        mErrorTextView = textView;
        mCallback = callback;

    }
//    public FingerprintHandler(Context context){
//        this.context = context;
//    }

    public void startAuthentication(FingerprintManager.CryptoObject cryptoObject){

        cancellationSignal = new CancellationSignal();
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
//            return;
//        }

        mSelfCancelled = false;
        mFingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }
//    public void startAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
//
//        cancellationSignal = new CancellationSignal();
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
//            return;
//        }
//
//        mSelfCancelled = false;
//        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
//    }

    public void stopAuthentication(){
        if (cancellationSignal != null){
            mSelfCancelled = true;
            cancellationSignal.cancel();
            cancellationSignal = null;
        }
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
//        super.onAuthenticationError(errorCode, errString);
//        Toast.makeText(context, "Autentikasi Fingerprint Error\n" + errString, Toast.LENGTH_SHORT).show();
        if (!mSelfCancelled){
            showError(errString);
            mIcon.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCallback.onError();

                }
            }, ERROR_TIMEOUT_MILLIS);
        }
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
//        super.onAuthenticationHelp(helpCode, helpString);
//        Toast.makeText(context, "Autentikasi Fingerprint Error\n" + helpString, Toast.LENGTH_SHORT).show();
        showError(helpString);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
//        super.onAuthenticationSucceeded(result);
//        context.startActivity(new Intent(context, MainActivity.class));
//        ((Activity)context).finish();
        mErrorTextView.removeCallbacks(mResetErrorTextRunnable);
        mIcon.setImageResource(R.drawable.ic_fingerprint_success);
        mErrorTextView.setTextColor(mErrorTextView.getResources().getColor(R.color.colorPrimary, null));
        mErrorTextView.setText(mErrorTextView.getResources().getText(R.string.fingerprint_success));
        mErrorTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCallback.onAuthenticated();
            }
        }, SUCCESS_DELAY_MILLIS);
    }

    @Override
    public void onAuthenticationFailed() {
//        super.onAuthenticationFailed();
//        Toast.makeText(context, "Autentikasi Fingerprint Gagal\n" + "Silahkan Ulangi Kembali", Toast.LENGTH_SHORT).show();
        showError(mIcon.getResources().getString(R.string.fingerprint_not_recognized));
    }

    private void showError(CharSequence error) {
        mIcon.setImageResource(R.drawable.ic_fingerprint_error);
        mErrorTextView.setText(error);
        mErrorTextView.setTextColor(
                mErrorTextView.getResources().getColor(R.color.oren, null));
        mErrorTextView.removeCallbacks(mResetErrorTextRunnable);
        mErrorTextView.postDelayed(mResetErrorTextRunnable, ERROR_TIMEOUT_MILLIS);
    }

    private Runnable mResetErrorTextRunnable = new Runnable() {
        @Override
        public void run() {
            mErrorTextView.setTextColor(
                    mErrorTextView.getResources().getColor(R.color.black, null));
            mErrorTextView.setText(
                    mErrorTextView.getResources().getString(R.string.fingerprint_hint));
            mIcon.setImageResource(R.drawable.ic_fp_40px);
        }
    };

    public interface Callback{
        void onAuthenticated();
        void onError();
    }
}
