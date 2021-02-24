package com.example.fundamentals_of_information_security_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

public class CipherListFragment extends Fragment {
    private RecyclerView mCipherRecyclerView;
    private CipherAdapter mCipherAdapter;
    private UUID updateCipherId;

    public static final int REQUEST_PLAIN_TEXT = 0;

    public static final String DIALOG_PLAIN_TEXT = "plain_text";

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_PLAIN_TEXT){
            String plain_text = (String) data.getSerializableExtra(InputPlainTextFragment.EXTRA_PLAIN_TEXT);
            RSA rsa = new RSA();

            ResultCipherFragment resultCipherFragment =ResultCipherFragment.newInstanceRSA(rsa, plain_text);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,resultCipherFragment, "ResultCipherFragment")
                    .commit();


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_ciphers, container, false);
        mCipherRecyclerView = view.findViewById(R.id.cipher_recycler_view);
        mCipherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CipherLab cipherLab = CipherLab.get(getActivity());
        List<MyCipher> myCiphers = cipherLab.getMyCiphers();


        if (mCipherAdapter == null) {

            mCipherAdapter = new CipherAdapter(myCiphers);
            mCipherRecyclerView.setAdapter(mCipherAdapter);
        } else if (updateCipherId != null) {
            mCipherAdapter.notifyItemChanged(myCiphers.indexOf(cipherLab.getCipher(updateCipherId)));
        }

    }

    private class CipherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private MyCipher mMyCipher;

        public CipherHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_cipher, parent, false));
            itemView.setOnClickListener(this::onClick);

            mTextView = itemView.findViewById(R.id.cipher_name);
        }

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            if (mMyCipher.getName().equals("RSA")){
                InputPlainTextFragment dialog =InputPlainTextFragment.newInstance();
                dialog.setTargetFragment(CipherListFragment.this, REQUEST_PLAIN_TEXT);
                dialog.show(fragmentManager, DIALOG_PLAIN_TEXT);
            }
        }

        public void bind(MyCipher myCipher) {
            mMyCipher = myCipher;
            mTextView.setText(mMyCipher.getName());
        }

    }

    private class CipherAdapter extends RecyclerView.Adapter<CipherHolder> {
        private List<MyCipher> mMyCiphers;

        public CipherAdapter(List<MyCipher> myCiphers) {
            mMyCiphers = myCiphers;
        }

        @NonNull
        @Override
        public CipherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CipherHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CipherHolder holder, int position) {
            MyCipher myCipher = mMyCiphers.get(position);
            holder.bind(myCipher);
        }

        @Override
        public int getItemCount() {
            return mMyCiphers.size();
        }
    }
}
