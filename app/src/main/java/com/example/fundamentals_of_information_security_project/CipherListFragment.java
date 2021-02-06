package com.example.fundamentals_of_information_security_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        Log.d("TAG", "updateUI: ssssssssssssssssssssssssssssssssssssssss"+myCiphers.size()+" name "+myCiphers.get(0).getName());

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
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.blowfish_input_data_fragment);
            if (fragment == null) {
                fragment = BlowfishInputDataFragment.newInstance();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
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
