package com.example.loginserverpractice01.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

    private static final String prefName= "UserLoginPref";

    private static final String USER_INPUT_ID = "USER_INPUT_ID";

    public static void setUserInputId(Context context, String userInputId){

        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        pref.edit().putString(USER_INPUT_ID, userInputId);

    }

    public static String getUserInputId(Context context){

        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        return pref.getString(USER_INPUT_ID, "");

    }

}
