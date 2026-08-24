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

package sh.vcm.sensiblelogging

import android.util.Log

fun Int.toLogLevel(): Level =
    when (this) {
        Log.VERBOSE -> Level.VERBOSE
        Log.DEBUG -> Level.DEBUG
        Log.INFO -> Level.INFO
        Log.WARN -> Level.WARN
        Log.ERROR -> Level.ERROR
        Log.ASSERT -> Level.ASSERT
        else -> throw IllegalStateException("Unknown priority: $this")
    }

fun Level.toLogPriority(): Int =
    when (this) {
        Level.VERBOSE -> Log.VERBOSE
        Level.DEBUG -> Log.DEBUG
        Level.INFO -> Log.INFO
        Level.WARN -> Log.WARN
        Level.ERROR -> Log.ERROR
        Level.ASSERT -> Log.ASSERT
    }
