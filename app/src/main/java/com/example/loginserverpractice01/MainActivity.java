package com.example.loginserverpractice01;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loginserverpractice01.databinding.ActivityLoginBinding;
import com.example.loginserverpractice01.databinding.ActivityMainBinding;
import com.example.loginserverpractice01.utils.ConnectServer;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

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

    }

    @Override
    public void bindView() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
