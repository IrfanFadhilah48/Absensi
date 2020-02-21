package com.example.windowsv8.absensi.ui;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.presenter.AbsensiPresenter;
import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.utils.FingerprintHandler;
import com.example.windowsv8.absensi.view.AbsensiView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AbsensiActivity extends AppCompatActivity implements AbsensiView {

    private AppCompatButton button, button2;
    private AbsensiPresenter presenter;
    private ProgressBar progressBar;
    public static final String a = "0000000000004";
    private SessionManager sessionManager;
    private KeyStore keyStore;
    private static final String KEY_NAME = "absensi";
    private Cipher cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        button = findViewById(R.id.buttonAbsensi);
        button2 = findViewById(R.id.buttonAbsensi2);
        progressBar = findViewById(R.id.progressBarAbsensi);
        presenter = new AbsensiPresenter(this, this, sessionManager);
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        if (user.get(SessionManager.TAG_CONDITION).equals("1")){
            button.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.VISIBLE);
        }else {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.INVISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callData();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeData();
            }
        });
    }

    private void closeData() {
        presenter.closeAbsen(a);
    }

    private void callData() {
        fingerPrintAutentikasi();
//        presenter.callAbsen(a);
    }

    private void fingerPrintAutentikasi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

            return;
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
                    if (chiperInit()){
                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
//                        FingerprintHandler handler = new FingerprintHandler(this);
//                        handler.startAuthentication(fingerprintManager, cryptoObject);
                    }
                }
            }
        }
    }

    private boolean chiperInit() {
        try{
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/"+KeyProperties.BLOCK_MODE_CBC+"/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchPaddingException |NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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
        try{
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator = null;
        try{
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }

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


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
//        button.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
//        button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void successful() {
        finish();
        overridePendingTransition(0,0);
        startActivity(getIntent());
        overridePendingTransition(0,0);
    }

}
