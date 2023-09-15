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

public class CustomSettings extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private static final String TAG = CustomSettings.class.getSimpleName();

    public static final String KEY_PHOTOS_SPOOF = "persist.sys.pixelprops.gphotos";

    private SwitchPreference mPhotosSpoofingSwitch;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.addPreferencesFromResource(R.xml.main);

        SharedPreferences prefs = getActivity().getSharedPreferences("main", Activity.MODE_PRIVATE);

        mPhotosSpoofingSwitch = findPreference(KEY_PHOTOS_SPOOF);
        mPhotosSpoofingSwitch.setChecked(SystemProperties.getBoolean(KEY_PHOTOS_SPOOF, false));
        mPhotosSpoofingSwitch.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mPhotosSpoofingSwitch) {
            SystemProperties.set(KEY_PHOTOS_SPOOF, String.valueOf(newValue));
            return true;
        }

        return false;
    }

}
