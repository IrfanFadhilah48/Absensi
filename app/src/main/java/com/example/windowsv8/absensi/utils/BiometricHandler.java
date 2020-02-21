package com.example.windowsv8.absensi.utils;

//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.hardware.biometrics.BiometricPrompt;
//import android.hardware.fingerprint.FingerprintManager;
//import android.os.Build;
//import android.os.CancellationSignal;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.ActivityCompat;
//
//import java.security.Permission;


public class BiometricHandler{

}
//public class BiometricHandler extends BiometricPrompt.AuthenticationCallback {
//
//    private Context context;
//
//    public BiometricHandler(Context context){
//        super();
//        this.context = context;
//    }
//
//    public void startAuthentication(BiometricPrompt fingerprintManager, BiometricPrompt.CryptoObject cryptoObject){
//        CancellationSignal cancellationSignal = new CancellationSignal();
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED)
//            return;
//        fingerprintManager.authenticate(cryptoObject, cancellationSignal, context.getMainExecutor(), this);
//    }
//
//    @Override
//    public void onAuthenticationError(int errorCode, CharSequence errString) {
//        super.onAuthenticationError(errorCode, errString);
//    }
//
//    @Override
//    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
//        super.onAuthenticationHelp(helpCode, helpString);
//    }
//
//    @Override
//    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
//        super.onAuthenticationSucceeded(result);
//    }
//
//    @Override
//    public void onAuthenticationFailed() {
//        super.onAuthenticationFailed();
//    }
//}
