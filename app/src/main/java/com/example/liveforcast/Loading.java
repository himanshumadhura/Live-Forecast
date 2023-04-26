package com.example.liveforcast;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;

public class Loading {
    Dialog load;

    public Loading(Context c) {
        load = new Dialog(c);
    }

    public void showDialog(){
        load.setContentView(R.layout.loading);
        load.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        load.getWindow().setDimAmount((float) 0.2);
        load.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        load.setCancelable(false);
        load.show();
    }

    public void hideDialog(){
        load.dismiss();
    }
}
