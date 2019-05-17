package com.example.loginserverpractice01;

import android.support.v4.app.Fragment;

public abstract class BaseFragmentActivity extends Fragment{

    public abstract void bindView();

    public abstract void setupEvents();

    public abstract void setValues();


}
