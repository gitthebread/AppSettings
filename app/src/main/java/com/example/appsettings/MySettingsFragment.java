package com.example.appsettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class MySettingsFragment extends PreferenceFragmentCompat{

    Context context;
    String[] colorValues;
    String[] colorTitles;
    int index;

    public MySettingsFragment(Context _context) {
        this.context = _context;
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        colorValues = getResources().getStringArray(R.array.prefListValue);
        colorTitles = getResources().getStringArray(R.array.prefListTitle);


        ListPreference displayedColor = (ListPreference) findPreference("displayedColor");
        displayedColor.setEntryValues(colorValues);
        CharSequence currentColor = displayedColor.getEntry();
        displayedColor.setSummary(currentColor);

        EditTextPreference displayedNumber = (EditTextPreference) findPreference("displayedNumber");
        String currentNumber = displayedNumber.getText();
        displayedNumber.setSummary(currentNumber);

        displayedNumber.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("displayedNumber", String.valueOf(newValue));
                editor.apply();
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });

        displayedColor.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("displayedColor", String.valueOf(newValue));
                editor.apply();
                index = displayedColor.findIndexOfValue(String.valueOf(newValue));
                preference.setSummary(colorTitles[index]);
                return true;
            }
        });
    }
}