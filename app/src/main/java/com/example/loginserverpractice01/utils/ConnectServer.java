package com.example.loginserverpractice01.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectServer {

    private static final String BASE_URL = "http://delivery-dev-389146667.ap-northeast-2.elb.amazonaws.com";

    public interface JsonResponseHandler{
        void onResponse(JSONObject json);
    }


    public static void postRequestLogin(Context context, String user_id, String user_password, final JsonResponseHandler handler){

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", user_id)
                .add("password", user_password)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/auth")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseContent = response.body().string();
                Log.d("서버 응답 정보", responseContent);

                try {
                    JSONObject json = new JSONObject(responseContent);
                    if (handler != null){
                        handler.onResponse(json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public static void getRequestInfo(Context context, String token, final JsonResponseHandler handler){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpUrl = HttpUrl.parse(BASE_URL + "/v2/me_info").newBuilder();
        String requestUrl = httpUrl.build().toString();

        Request request = new Request.Builder()
                .header("X-Http-Token", token)
                .url(requestUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseContext = response.body().string();
                Log.d("서버 응답 정보", responseContext);

                try {
                    JSONObject json = new JSONObject(responseContext);
                    if (handler != null){
                        handler.onResponse(json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void getResponesInfoBank(Context context, final JsonResponseHandler handler){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpUrl = HttpUrl.parse(BASE_URL + "/info/bank").newBuilder();
        String requestUrl = httpUrl.build().toString();

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseContent = response.body().string();
                Log.d("서버 응답 정보", responseContent);

                try {
                    JSONObject json = new JSONObject(responseContent);
                    if (handler != null){
                        handler.onResponse(json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }



}
