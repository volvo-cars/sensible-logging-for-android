/*
 * Copyright 2022 Volvo Cars Corporation
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

package sh.vcm.sensiblelogging.filter

import android.content.SharedPreferences
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.util.Constants.DEFAULT_CATEGORY

class SharedPreferencesCategoryFilter(
    private val preferences: SharedPreferences,
    private val stringArrayPreferenceKey: String,
    defaultCategories: Set<Category> = setOf(DEFAULT_CATEGORY)
) : CategoryFilter() {

    private lateinit var _categories: List<Category>
    private val _defaultCategories: Set<String> = defaultCategories.map { it.name }.toSet()

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == stringArrayPreferenceKey) {
            readValue()
        }
    }

    init {
        readValue()
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override val categories: List<Category>
        get() = _categories

    private fun readValue() {
        _categories = preferences.getStringSet(stringArrayPreferenceKey, _defaultCategories)
            ?.map { Category(it) }
            ?.toList()
            .orEmpty()
    }
}
