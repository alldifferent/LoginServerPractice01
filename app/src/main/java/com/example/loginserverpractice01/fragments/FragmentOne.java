package com.example.loginserverpractice01.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginserverpractice01.BaseFragmentActivity;
import com.example.loginserverpractice01.MainActivity;
import com.example.loginserverpractice01.R;
import com.example.loginserverpractice01.adapters.BankListAdapter;
import com.example.loginserverpractice01.databinding.FragmentOneBinding;
import com.example.loginserverpractice01.datas.Bank;
import com.example.loginserverpractice01.utils.ConnectServer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends BaseFragmentActivity {

    FragmentOneBinding binding;
    BankListAdapter bankListAdapter;
    List<Bank> bankList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        setupEvents();
        setValues();
    }

    @Override
    public void bindView() {

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {


        bankListAdapter = new BankListAdapter(getActivity(), bankList);
        binding.bankList.setAdapter(bankListAdapter);



    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).showList(bankList, bankListAdapter);

    }
}
