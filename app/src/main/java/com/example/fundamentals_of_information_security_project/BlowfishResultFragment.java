package com.example.fundamentals_of_information_security_project;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class BlowfishResultFragment extends Fragment {
    public static final String ARG_KEY_WORD = "ARG_KEY_WORD.key_word";
    public static final String ARG_PLAIN_TEXT = "ARG_PLAIN_TEXT.plain_text";

    private TextView decodeTV, encodeTV;

    private String mKeyWord, mPlainText, mEncode, mDecode;

    private List<String> subKeyList, roundList;

    public static BlowfishResultFragment newInstance(String keyword, String plainText){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_KEY_WORD, keyword);
        bundle.putSerializable(ARG_PLAIN_TEXT, plainText);

        BlowfishResultFragment fragment = new BlowfishResultFragment();

        fragment.setArguments(bundle);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blowfish_result, container, false);

        decodeTV = view.findViewById(R.id.decode);
        encodeTV = view.findViewById(R.id.encode);


        mKeyWord = (String) getArguments().getSerializable(ARG_KEY_WORD);
        mPlainText = (String) getArguments().getSerializable(ARG_PLAIN_TEXT);



//        subKeyTV.setText(mKeyWord);
//        roundTV.setText(mPlainText);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();

        try {
            encode(mKeyWord, mPlainText);

            decodeTV.setText(mEncode);
            encodeTV.setText(mDecode);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void encode(String key, String plainText) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {


        byte[] decodedKey = Base64.getDecoder().decode(key);
        // create a key generator based upon the Blowfish cipher
        KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");

        // create a key
//        SecretKey secretkey = keygenerator.generateKey();
        SecretKey secretkey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "Blowfish");

        // create a cipher based upon Blowfish
        Cipher cipher = Cipher.getInstance("Blowfish");

        // initialise cipher to with secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);

        // get the text to encrypt

        // encrypt message
        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        // re-initialise the cipher to be in decrypt mode
        cipher.init(Cipher.DECRYPT_MODE, secretkey);

        // decrypt message
        byte[] decrypted = cipher.doFinal(encrypted);


        mEncode = "Encode: \n"+new String(encrypted);
        mDecode = "Decode: \n"+new String(decrypted);
    }
}
