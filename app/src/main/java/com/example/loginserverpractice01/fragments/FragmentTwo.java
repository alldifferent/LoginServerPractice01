package com.example.loginserverpractice01.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginserverpractice01.BaseFragmentActivity;
import com.example.loginserverpractice01.R;
import com.example.loginserverpractice01.databinding.FragmentTwoBinding;

public class FragmentTwo extends BaseFragmentActivity {

    FragmentTwoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void bindView() {

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }
}
