package com.example.loginserverpractice01;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loginserverpractice01.adapters.BankListAdapter;
import com.example.loginserverpractice01.adapters.PagerAdapter;
import com.example.loginserverpractice01.databinding.ActivityLoginBinding;
import com.example.loginserverpractice01.databinding.ActivityMainBinding;
import com.example.loginserverpractice01.datas.Bank;
import com.example.loginserverpractice01.utils.ConnectServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        String userToken = getIntent().getStringExtra("userToken");

        ConnectServer.getRequestInfo(mContext, userToken, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int code = 0;
                        try {
                            code = json.getInt("code");

                            if (code == 200){

                                JSONObject data = json.getJSONObject("data");
                                JSONObject user = data.getJSONObject("user");
                                JSONObject bank_code = user.getJSONObject("bank_code");


                                String name = user.getString("name");
                                String email = user.getString("email");
                                String profile_image = user.getString("profile_image");
                                String billing_account = user.getString("billing_account");
                                String bankName = bank_code.getString("name");
                                String logo = bank_code.getString("logo");
                                int balance = user.getInt("balance");



                                Glide.with(mContext).load(profile_image).into(act.userImg);
                                act.userNameTxt.setText(name);
                                act.bankNameTxt.setText(bankName);
                                act.emailTxt.setText(email);
                                Glide.with(mContext).load(logo).into(act.bankImg);
                                act.accountTxt.setText(billing_account);
                                act.pointTxt.setText(String.format("%,d P",balance));


                            }else {

                                String message = json.getString("message");
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        act.viewPager.setOffscreenPageLimit(2);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 2);
        act.viewPager.setAdapter(pagerAdapter);


    }

    @Override
    public void bindView() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }


    public void showList(List<Bank> banklist, BankListAdapter bankListAdapter){

        ConnectServer.getResponesInfoBank(mContext, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            int code = json.getInt("code");

                            if (code == 200){

                                JSONObject data = json.getJSONObject("data");
                                JSONArray banks = data.getJSONArray("banks");

                                banklist.clear();

                                for (int i = 0; i < banks.length(); i++){

                                    JSONObject bank = banks.getJSONObject(i);

                                    Bank bankOject = Bank.getBankFromJson(bank);
                                    banklist.add(bankOject);
                                }

                                bankListAdapter.notifyDataSetChanged();


                            }else {
                                String message = json.getString("message");
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });

    }
}
