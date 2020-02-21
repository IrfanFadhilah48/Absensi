package com.example.windowsv8.absensi.utils;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windowsv8.absensi.R;
import com.example.windowsv8.absensi.presenter.AbsensiPresenter;
import com.example.windowsv8.absensi.sharedpreferences.SessionManager;
import com.example.windowsv8.absensi.ui.AbsensiActivity;
import com.example.windowsv8.absensi.ui.FingerprintActivity;
import com.example.windowsv8.absensi.ui.MainActivity;
import com.example.windowsv8.absensi.view.AbsensiView;

public class FingerprintAuthenticationDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener, FingerprintHandler.Callback, AbsensiView{

    private Button mCancelButton;
    private Button mSecondDialogButton;
    private View mFingerprintContent;
    private View mBackupContent;
    private EditText mPassword;
    private CheckBox mUseFingerprintFutureCheckBox;
    private TextView mPasswordDescriptionTextView;
    private TextView mNewFingerprintEnrolledTextView;
    private AbsensiPresenter absensiPresenter;
    private SessionManager sessionManager;

//    private Stage mStage = Stage.FINGERPRINT;

    private FingerprintManager.CryptoObject mCryptoObject;
    private FingerprintHandler mFingerprintUiHelper;
    private FingerprintActivity mActivity;
    private String id_karyawan;
    private static final String key_id_karyawan = "id_karyawan";

    private InputMethodManager mInputMethodManager;
    private SharedPreferences mSharedPreferences;

    public static FingerprintAuthenticationDialogFragment newInstance(String id_karyawan) {
        FingerprintAuthenticationDialogFragment f = new FingerprintAuthenticationDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
//        args.putString("id_karyawan", id_karyawan);
        args.putString(key_id_karyawan, id_karyawan);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        id_karyawan = getArguments().getString(key_id_karyawan);
        setRetainInstance(true);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Absensi");
        View v = inflater.inflate(R.layout.fingerprint_layout_container, container, false);
        mCancelButton = v.findViewById(R.id.cancel_button);
//        mSecondDialogButton = v.findViewById(R.id.second_dialog_button);
        mFingerprintContent = v.findViewById(R.id.fingerprint_container);
        mFingerprintUiHelper = new FingerprintHandler(
                mActivity.getSystemService(FingerprintManager.class),
                (ImageView) v.findViewById(R.id.fingerprint_icon),
                (TextView) v.findViewById(R.id.fingerprint_status),
                this);
        mCancelButton.setText(R.string.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFingerprintUiHelper.startAuthentication(mCryptoObject);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FingerprintActivity)getActivity();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFingerprintUiHelper.stopAuthentication();
    }

    public void setCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        mCryptoObject = cryptoObject;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public void onAuthenticated() {
//        sessionManager = new SessionManager(mActivity);
//        absensiPresenter = new AbsensiPresenter(this, mActivity, sessionManager);
//        absensiPresenter.callAbsen(id_karyawan);
        dismiss();
        mActivity.startActivity(new Intent(getActivity(), AbsensiActivity.class));
        mActivity.finish();
    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void successful() {
        mActivity.finish();
        mActivity.overridePendingTransition(0,0);
        mActivity.startActivity(mActivity.getIntent());
        mActivity.overridePendingTransition(0,0);
    }
}
