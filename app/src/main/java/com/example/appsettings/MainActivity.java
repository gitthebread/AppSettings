package com.example.appsettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtview;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtview = findViewById(R.id.txtview);


        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String mCount = mPreferences.getString("displayedNumber", "0");
        String color = mPreferences.getString("displayedColor", "#FFFFFF");

        int mColor = Color.parseColor(color);
        txtview.setText(mCount);
        txtview.setBackgroundColor(mColor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*super.onPostResume();

        setContentView(R.layout.activity_main);

        txtview = findViewById(R.id.txtview);*/

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String mCount = mPreferences.getString("displayedNumber", "0");
        String color = mPreferences.getString("displayedColor", "#FFFFFF");

        int mColor = Color.parseColor(color);
        txtview.setText(mCount);
        txtview.setBackgroundColor(mColor);
    }
}