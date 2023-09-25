/*
 * Copyright (C) 2023 The 2by2 Project
 * SPDX-License-Identifier: Apache-2.0
 */

package jp.project2by2.CustomSettings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.Vibrator;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

import java.util.Arrays;

import jp.project2by2.CustomSettings.R;
import jp.project2by2.CustomSettings.utils.Utils;
import jp.project2by2.CustomSettings.preference.SystemSettingListPreference;

public class CustomSettings extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private static final String TAG = "2byTricks";

    private ContentResolver mContentResolver;

    public static final String KEY_STATUS_BAR_BATTERY_STYLE = "status_bar_battery_style";
    public static final String KEY_STATUS_BAR_SHOW_BATTERY_PERCENT = "status_bar_show_battery_percent";

    private static final int STATUS_BAR_BATTERY_STYLE_TEXT = 2;

    private SystemSettingListPreference mStatusBarBattery;
    private SystemSettingListPreference mStatusBarBatteryShowPercent;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.addPreferencesFromResource(R.xml.main);

        SharedPreferences prefs = getActivity().getSharedPreferences("main", Activity.MODE_PRIVATE);

        mContentResolver = getActivity().getContentResolver();

        mStatusBarBatteryShowPercent = findPreference(KEY_STATUS_BAR_SHOW_BATTERY_PERCENT);
        mStatusBarBattery = findPreference(KEY_STATUS_BAR_BATTERY_STYLE);
        mStatusBarBattery.setOnPreferenceChangeListener(this);
        enableStatusBarBatteryDependents(mStatusBarBattery.getIntValue(0));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        int value = Integer.parseInt((String) newValue);
        String key = preference.getKey();

        switch (key) {
            case KEY_STATUS_BAR_BATTERY_STYLE:
                Log.d(TAG, "Set battery style: " + value);
                enableStatusBarBatteryDependents(value);
                break;
        }

        return true;
    }

    private void enableStatusBarBatteryDependents(int batteryIconStyle) {
        mStatusBarBatteryShowPercent.setEnabled(batteryIconStyle != STATUS_BAR_BATTERY_STYLE_TEXT);
    }

}