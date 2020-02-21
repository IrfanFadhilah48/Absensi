package com.example.windowsv8.absensi.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.utils.FingerprintAuthenticationDialogFragment;
import com.example.windowsv8.absensi.utils.FingerprintHandler;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FingerprintActivity extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "absensi";
    private Cipher cipher;
    private Button button, cancelButton;
    private static final String DIALOG_FRAGMENT_TAG = "myFragment";
    private static final String id_karyawan = "0000000000002";
    private KeyGenerator keyGenerator = null;
//    private FingerprintManager fingerprintManager;
//    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        button = findViewById(R.id.buttonFingerprint);
        cancelButton = findViewById(R.id.cancel_button);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        try{
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/"+KeyProperties.BLOCK_MODE_CBC+"/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchPaddingException|NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try{
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }


        try{
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException|NoSuchProviderException e) {
            e.printStackTrace();
        }

        FingerprintManager fingerprintManager = (FingerprintManager)getSystemService(FINGERPRINT_SERVICE);
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);

        if (!fingerprintManager.isHardwareDetected()){
            Toast.makeText(getApplicationContext(), "Perangkat Anda tidak terdapat fitur Fingerprint", Toast.LENGTH_SHORT).show();
        }else {
            if(!fingerprintManager.hasEnrolledFingerprints()){
                Toast.makeText(getApplicationContext(), "Daftarkan sidik jari anda pada Perangkat anda", Toast.LENGTH_SHORT).show();
            }else {
                if (!keyguardManager.isKeyguardSecure()){
                    Toast.makeText(getApplicationContext(), "Aktifkan keamanan layar kunci di Pengaturan", Toast.LENGTH_SHORT).show();
                }else {
                    generateKey();
//                    if (chiperInit()){
//                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
////                        FingerprintHandler handler = new FingerprintHandler(this);
////                        handler.startAuthentication(fingerprintManager, cryptoObject);
//                    }
                }
            }
        }
        if(chiperInit()){
            FingerprintAuthenticationDialogFragment fragment = new FingerprintAuthenticationDialogFragment().newInstance(id_karyawan);
            fragment.setCryptoObject(new FingerprintManager.CryptoObject(cipher));
            fragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        }else {
            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
        }
//        button.setOnClickListener(new CallFingerprint(cipher, KEY_NAME));
    }

    private boolean chiperInit() {
//        try{
//            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/"+KeyProperties.BLOCK_MODE_CBC+"/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
//        } catch (NoSuchPaddingException|NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        try{
            keyStore.load(null);
            SecretKey secretKey = (SecretKey)keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (CertificateException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return false;
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void generateKey() {
//        try{
//            keyStore = KeyStore.getInstance("AndroidKeyStore");
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//
//        KeyGenerator keyGenerator = null;
//        try{
//            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
//        } catch (NoSuchAlgorithmException|NoSuchProviderException e) {
//            e.printStackTrace();
//        }

        try{
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    private class CallFingerprint implements View.OnClickListener{

        Cipher mChiper;
        String mKeyname;

        private CallFingerprint(Cipher cipher, String keyName){
            mChiper = cipher;
            mKeyname = keyName;
        }


        @Override
        public void onClick(View v) {
            if(chiperInit()){
                FingerprintAuthenticationDialogFragment fragment = new FingerprintAuthenticationDialogFragment().newInstance(id_karyawan);
                fragment.setCryptoObject(new FingerprintManager.CryptoObject(mChiper));
                fragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
            }else {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
            }
        }
    }
}
