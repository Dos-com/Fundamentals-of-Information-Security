package com.example.fundamentals_of_information_security_project;

import android.os.Bundle;

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

}
