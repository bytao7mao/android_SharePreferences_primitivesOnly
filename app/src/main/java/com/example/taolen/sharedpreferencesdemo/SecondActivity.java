package com.example.taolen.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by taoLen on 4/9/2018.
 */


//here we use getSharedPreferences("myFile", mode);
public class SecondActivity extends AppCompatActivity {
    private TextView txvNameSecond, txvProfessionSecond;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txvNameSecond = findViewById(R.id.txvNameSecond);
        txvProfessionSecond = findViewById(R.id.txvProfessionSecond);
    }

    public void loadAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.KEY_NAME, "N/A"); //second parameter is what is displayed by default
        String profession = sharedPreferences.getString(Constants.KEY_PROFESSION, "N/A"); //second parameter is what is displayed by default
        Integer profId = sharedPreferences.getInt(Constants.KEY_PROF_ID, 999);
        txvNameSecond.setText(name);
        String profStr = profession + " id: " + profId;
        txvProfessionSecond.setText(profStr);
    }

    public void clearAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void removeAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Constants.KEY_PROFESSION);
        editor.apply();
    }
}
