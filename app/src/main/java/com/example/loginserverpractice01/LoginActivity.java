package com.example.loginserverpractice01;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginserverpractice01.databinding.ActivityLoginBinding;
import com.example.loginserverpractice01.utils.ConnectServer;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        act.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputId = act.idEdt.getText().toString();
                String inputPw = act.passwordEdt.getText().toString();

                ConnectServer.postRequestLogin(mContext, inputId, inputPw, new ConnectServer.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                int code = 0;
                                try {
                                    code = json.getInt("code");
                                    if (code == 200){

                                        Toast.makeText(mContext, "로그인 성공했습니다.", Toast.LENGTH_SHORT).show();

                                    }else {
                                        String message = json.getString("message");
                                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                        alert.setTitle("로그인 실패 알림");
                                        alert.setMessage(message);
                                        alert.setPositiveButton("확인", null);
                                        alert.show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });

                    }
                });

            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindView() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }
}