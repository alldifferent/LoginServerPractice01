package com.example.loginserverpractice01.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class Bank {

    public int id;
    public String code;
    public String name;
    public String logo;

    public static Bank getBankFromJson(JSONObject bankJson){

        Bank bankOject = new Bank();

        try {
            bankOject.id = bankJson.getInt("id");
            bankOject.code = bankJson.getString("code");
            bankOject.name = bankJson.getString("name");
            bankOject.logo = bankJson.getString("logo");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bankOject;
    }


}
