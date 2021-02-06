package com.example.fundamentals_of_information_security_project;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BlowfishInputDataFragment extends Fragment {
    private EditText keywordET, plainTextET;
    private Button encodeBtn;
    private String mKeyWord, mPlainText;
    public static BlowfishInputDataFragment newInstance(){
        BlowfishInputDataFragment blowfishInputDataFragment = new BlowfishInputDataFragment();

        return blowfishInputDataFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blowfish_input_data, container, false);

        keywordET = view.findViewById(R.id.blowfish_key);
        plainTextET = view.findViewById(R.id.blowfish_plain_text);
        encodeBtn = view.findViewById(R.id.encodeBtn);

        keywordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mKeyWord = charSequence.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        plainTextET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPlainText = charSequence.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        encodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=0;
                if (keywordET==null || keywordET.equals("")){
                    showToast(R.string.empty_key_word);
                    i++;
                }
                if (mPlainText==null || mPlainText.equals("")){
                    showToast(R.string.empty_plain_text);
                    i++;
                }
                if (i==0){
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                    Fragment fragment = fragmentManager.findFragmentById(R.id.blowfish_result_fragment);
                    if (fragment==null){
                        fragment = BlowfishResultFragment.newInstance(mKeyWord,mPlainText);

                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .addToBackStack("BlowfishResultFragment")
                                .commit();
                    }

                }


            }
        });
        return view;
    }


    private void showToast(int id){
        Toast.makeText(getActivity(),id,Toast.LENGTH_SHORT).show();
    }

}
