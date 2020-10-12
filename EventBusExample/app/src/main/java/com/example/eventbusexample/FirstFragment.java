package com.example.eventbusexample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.EventBus;

public class FirstFragment extends Fragment {
    public FirstFragment() {
        // Required empty public constructor
    }
    /**
     * Create a new instance of this fragment
     *
     * @return A new instance of fragment FirstFragment.
     */
    public static FirstFragment newInstance() {
        return new FirstFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText inputEditText = view.findViewById(R.id.textInputText);
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                RxBus.getInstance().publish(charSequence.toString());
            }

            @Override public void afterTextChanged(Editable editable) {

            }
        });
    }
}
