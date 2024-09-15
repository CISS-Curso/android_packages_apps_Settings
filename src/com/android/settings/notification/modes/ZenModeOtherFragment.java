/*
 * Copyright (C) 2024 The Android Open Source Project
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

package com.android.settings.notification.modes;

import static android.service.notification.ZenPolicy.PRIORITY_CATEGORY_ALARMS;
import static android.service.notification.ZenPolicy.PRIORITY_CATEGORY_EVENTS;
import static android.service.notification.ZenPolicy.PRIORITY_CATEGORY_MEDIA;
import static android.service.notification.ZenPolicy.PRIORITY_CATEGORY_REMINDERS;
import static android.service.notification.ZenPolicy.PRIORITY_CATEGORY_SYSTEM;

import android.app.settings.SettingsEnums;
import android.content.Context;
import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;

import java.util.ArrayList;
import java.util.List;

/**
 * Mode >  Alarms & Other Interruptions
 */
public class ZenModeOtherFragment extends ZenModeFragmentBase {

    @Override
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        List<AbstractPreferenceController> controllers = new ArrayList<>();
        controllers.add(new ZenModeOtherPreferenceController(
                context, "modes_category_alarm", mBackend));
        controllers.add(new ZenModeOtherPreferenceController(
                context, "modes_category_media", mBackend));
        controllers.add(new ZenModeOtherPreferenceController(
                context, "modes_category_system", mBackend));
        controllers.add(new ZenModeOtherPreferenceController(
                context, "modes_category_reminders", mBackend));
        controllers.add(new ZenModeOtherPreferenceController(
                context, "modes_category_events", mBackend));
        return controllers;
    }

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.modes_other_settings;
    }

    @Override
    public int getMetricsCategory() {
        // TODO: b/332937635 - make this the correct metrics category
        return SettingsEnums.NOTIFICATION_ZEN_MODE_PRIORITY;
    }
}
