package com.rj.processing.plasmasound;

/*
 * Copyright (C) 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */



import com.rj.processing.plasmasoundhd.PlasmaSound;
import com.rj.processing.plasmasoundhd.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlasmaThereminEffectsSettings extends Activity {
//
//    @Override
//    protected void onCreate(final Bundle icicle) {
//        super.onCreate(icicle);
//        getPreferenceManager().setSharedPreferencesName(
//                PlasmaSound.SHARED_PREFERENCES_AUDIO);
//        addPreferencesFromResource(R.xml.effectsettings);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    
//   
    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        this.setContentView(R.layout.effectsettings);
    }
    

}
