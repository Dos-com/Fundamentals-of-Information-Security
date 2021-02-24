package com.example.fundamentals_of_information_security_project;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class CipherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);


        if (fragment == null) {
            fragment = new CipherListFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack("CipherListFragment")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            Fragment f = getSupportFragmentManager().findFragmentByTag("ResultCipherFragment");
            if (f != null){
                Fragment fragment = new CipherListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack("CipherListFragment")
                        .commit();
            }
            else {

                getSupportFragmentManager().popBackStack();
            }
        }
    }
}
