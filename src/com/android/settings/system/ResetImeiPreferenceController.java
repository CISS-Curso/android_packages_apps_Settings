/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.system;

import android.content.Context;
import android.util.Log;
import android.os.SystemProperties;
import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class ResetImeiPreferenceController extends BasePreferenceController {

    private static final String TAG = "ResetImeiController";

    public ResetImeiPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        String currentImei = getCurrentImei();
        if ("Not Set".equals(currentImei)) {
            preference.setSummary(R.string.reset_imei_placeholder);
        } else {
            preference.setSummary(mContext.getString(R.string.reset_imei_summary, currentImei));
        }
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference.getKey().equals(getPreferenceKey())) {
            String newImei1 = generateImei();
            String newImei2 = generateImei();
            SystemProperties.set("persist.debug.random_imei_1", newImei1);
            SystemProperties.set("persist.debug.random_imei_2", newImei2);
            preference.setSummary(mContext.getString(R.string.reset_imei_summary, newImei1 + "-" + newImei2));
            return true;
        }
        return false;
    }

    private String getCurrentImei() {
        String imei1 = SystemProperties.get("persist.debug.random_imei_1", "Not Set");
        String imei2 = SystemProperties.get("persist.debug.random_imei_2", "Not Set");
        return imei1 + imei2;
    }

    private String generateImei() {
        StringBuilder imei = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            imei.append((int) (Math.random() * 10));
        }
        return imei.toString();
    }
}
