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

    String[] colorValues;
    String[] colorTitles;
    int index;
    String currentNumber;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        colorValues = getResources().getStringArray(R.array.prefListValue);
        colorTitles = getResources().getStringArray(R.array.prefListTitle);


        ListPreference displayedColor = (ListPreference) findPreference("displayedColor");
        if (displayedColor != null) {
            displayedColor.setEntryValues(colorValues);
            CharSequence currentColor = displayedColor.getEntry();
            displayedColor.setSummary(currentColor);
        }

        EditTextPreference displayedNumber = (EditTextPreference) findPreference("displayedNumber");
        if (displayedNumber != null) {
            currentNumber = displayedNumber.getText();
            displayedNumber.setSummary(currentNumber);
        }

        displayedNumber.setOnPreferenceChangeListener((preference, newValue) -> {
            preference.setSummary(String.valueOf(newValue));
            return true;
        });

        displayedColor.setOnPreferenceChangeListener((preference, newValue) -> {
            index = displayedColor.findIndexOfValue(String.valueOf(newValue));
            preference.setSummary(colorTitles[index]);
            return true;
        });
    }
}