package com.example.fundamentals_of_information_security_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultCipherFragment extends Fragment {
    private EditText mPlainTextEditText,mEncryptedTextEditText, mDecryptedTextEditText, mEncryptedTextBytesEditText,mDecryptedTextBytesEditText;

    public static final String ARG_RSA = "rsa";
    public static final String ARG_PLAIN_TEXT = "arg_plain_text";

    private String mPlainText;
    private RSA mRSA;



    public static ResultCipherFragment newInstanceRSA(RSA rsa, String plain_text){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_RSA, rsa);
        bundle.putSerializable(ARG_PLAIN_TEXT, plain_text);

        ResultCipherFragment resultCipherFragment = new ResultCipherFragment();
        resultCipherFragment.setArguments(bundle);

        return resultCipherFragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().getSerializable(ARG_RSA) != null){
            mRSA = (RSA) getArguments().getSerializable(ARG_RSA);
            mPlainText = (String) getArguments().getSerializable(ARG_PLAIN_TEXT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_of_cipher,container, false);


        mPlainTextEditText = view.findViewById(R.id.plain_text);
        mEncryptedTextEditText = view.findViewById(R.id.encrypted_text);
        mDecryptedTextEditText = view.findViewById(R.id.decrypted_text);
        mEncryptedTextBytesEditText = view.findViewById(R.id.encrypted_text_bytes);
        mDecryptedTextBytesEditText = view.findViewById(R.id.decrypted_text_bytes);

        if (mRSA !=null){

            mPlainTextEditText.setText(mPlainText);
            mEncryptedTextEditText.setEnabled(false);


            byte[] encrypted = mRSA.encrypt(mPlainText.getBytes());
            mEncryptedTextBytesEditText.setText(bytesToString(encrypted));

            mEncryptedTextEditText.setText(bytesToStringChar(encrypted));


            byte[] decrypted = mRSA.decrypt(encrypted);
            mDecryptedTextBytesEditText.setText(bytesToString(decrypted));

            mDecryptedTextEditText.setText(bytesToStringChar(decrypted));

        }

        return view;
    }



    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b) + " ";
        }
        return test;
    }



    private static String bytesToStringChar(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += (char)b;
        }
        return test;
    }


}
