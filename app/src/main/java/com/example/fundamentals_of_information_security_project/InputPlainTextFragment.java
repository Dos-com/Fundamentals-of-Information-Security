package com.example.fundamentals_of_information_security_project;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class InputPlainTextFragment extends DialogFragment {
    public static final String ARG_DATE = "date";
    public static final String EXTRA_PLAIN_TEXT = "com.example.criminalintentDatePickerFragment.plain_text";

    private TextView mPlainTextTextView;


    public static InputPlainTextFragment newInstance(){

        InputPlainTextFragment inputPlainTextFragment = new InputPlainTextFragment();

        return inputPlainTextFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input_plain_text,null);

        mPlainTextTextView = view.findViewById(R.id.plain_text);

        Log.d("TAG", "onCreateDialog: qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq "+mPlainTextTextView.getText());

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.input_plain_text)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text = mPlainTextTextView.getText().toString();

                        sendResult(Activity.RESULT_OK,text);
                    }
                })
                .create();
    }


    private void sendResult(int resultCode, String plainText){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_PLAIN_TEXT,plainText);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
