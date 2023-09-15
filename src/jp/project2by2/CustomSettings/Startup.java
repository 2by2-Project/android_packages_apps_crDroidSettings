/*
 * Copyright (C) 2023 The 2by2 Project
 * SPDX-License-Identifier: Apache-2.0
 */

package jp.project2by2.CustomSettings;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

public class Startup extends BroadcastReceiver {

    private static final String TAG = Startup.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
            // Do Something.
    }

}
