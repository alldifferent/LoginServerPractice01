package com.example.loginserverpractice01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loginserverpractice01.R;
import com.example.loginserverpractice01.datas.Bank;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BankListAdapter extends ArrayAdapter<Bank> {

    Context mContext;
    List<Bank> mList;
    LayoutInflater inf;

    public BankListAdapter(Context context, List<Bank> list){

        super(context, R.layout.bank_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null){
            row = inf.inflate(R.layout.bank_list_item, null);
        }

        Bank bankData = mList.get(position);

        TextView bankNameTxt = row.findViewById(R.id.bankNameTxt);
        TextView codeNumTxt = row.findViewById(R.id.codeNumTxt);
        CircleImageView bankImg = row.findViewById(R.id.bankImg);
        String bankImgUrl = bankData.logo;

        Glide.with(mContext).load(bankImgUrl).into(bankImg);
        bankNameTxt.setText(bankData.name);
        codeNumTxt.setText(bankData.code);

        return row;
    }
}
