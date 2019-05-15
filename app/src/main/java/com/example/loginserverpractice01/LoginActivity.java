package com.example.loginserverpractice01;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginserverpractice01.databinding.ActivityLoginBinding;
import com.example.loginserverpractice01.utils.ConnectServer;
import com.example.loginserverpractice01.utils.ContextUtil;

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

                ContextUtil.setUserInputId(mContext, inputId);

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

                                        JSONObject data = json.getJSONObject("data");
                                        String token = data.getString("token");

                                        if (act.autoLoginCheckBox.isChecked()){

                                            ContextUtil.setUserToken(mContext, token);

                                        }

                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.putExtra("userToken", token);
                                        startActivity(intent);
                                        finish();

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

        String savedUserId = ContextUtil.getUserInputId(mContext);
        act.idEdt.setText(savedUserId);

        String savedToken = ContextUtil.getUserToken(mContext);
        Log.d("토큰값", savedToken);

    }

    @Override
    public void bindView() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_login);

    }
}
