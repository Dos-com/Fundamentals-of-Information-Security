package com.example.fundamentals_of_information_security_project;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CipherLab {
    private static CipherLab sCipherLab;
    private List<MyCipher> mMyCiphers;

    public static CipherLab get(Context context){
        if (sCipherLab==null){
            sCipherLab = new CipherLab(context);
        }
        return sCipherLab;
    }

    private CipherLab(Context context){
        mMyCiphers = new ArrayList<>();

        mMyCiphers.add(new MyCipher("Blowfish"));
    }


    public List<MyCipher> getMyCiphers(){
        return mMyCiphers;
    }

    public MyCipher getCipher(UUID Id){
        for (int i = 0; i< mMyCiphers.size(); i++){
            if (mMyCiphers.get(i).getId().equals(Id)){
                return mMyCiphers.get(i);
            }
        }
        return null;
    }

}
