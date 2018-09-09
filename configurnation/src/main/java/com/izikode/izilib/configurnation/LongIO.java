/*
 * Copyright 2018 Fanie Veizis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class LongIO extends IO<Long> {

    LongIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String variation, Long value, boolean instantly) {
        if (value == null) {
            clear(variation, instantly);
            return;
        }

        SharedPreferences.Editor editor = preferences.edit().putLong(preferenceName(variation), value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Long get(String variation) {
        if (!exist(variation)) {
            return null;
        }

        return preferences.getLong(preferenceName(variation), 0);
    }

}
