package com.example.taolen.sharedpreferencesdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etProfession;
    private TextView txvName, txvProfession;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;

    //getPreferences with xyz.xml
    //if user uninstall the application, SharedPreferences file from data will be erased also
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etProfession = findViewById(R.id.etProfession);
        txvName = findViewById(R.id.txvName);
        txvProfession = findViewById(R.id.txvProfession);
        pageColorSwitch = findViewById(R.id.pageColorSwitch);
        pageLayout = findViewById(R.id.pageLayout);
        pageColorSwitch = findViewById(R.id.pageColorSwitch);
        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setPageColor(isChecked);
            }
        });
        //Retrieve the value from Activity Level SharedPreference;
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("green", false);
        pageColorSwitch.setChecked(isChecked);

    }

    private void setPageColor(boolean isChecked) { //save data in Activity Level SharedPreferences
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("green", isChecked);
        editor.apply();

        pageLayout.setBackgroundColor(isChecked ? Color.GREEN : Color.WHITE);
    }


    public void saveAccountData(View view) { //saving data to file xml
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.KEY_NAME, etName.getText().toString());
        editor.putString(Constants.KEY_PROFESSION, etProfession.getText().toString());
        editor.putInt(Constants.KEY_PROF_ID, Constants.KEY_ID_AUTO_INCREMENTED);
        editor.apply(); //commit the changes
    }

    public void loadAccountData(View view) { //loading from file xml
        Constants.KEY_ID_AUTO_INCREMENTED++;
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.KEY_NAME, "N/A"); //second parameter is what is displayed by default
        String profession = sharedPreferences.getString(Constants.KEY_PROFESSION, "N/A"); //second parameter is what is displayed by default
        Integer profId = sharedPreferences.getInt(Constants.KEY_PROF_ID, 0);
        txvName.setText(name);
        String profStr = profession + "id: " + profId;
        txvProfession.setText(profStr);
    }



    public void OpenSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
