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

package sh.vcm.sensiblelogging.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Logger

internal open class LifecycleLogger(
    private val identifier: String,
    private val type: String,
    private val separator: String,
    private val category: Category,
    private val channel: Int
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        log("onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        log("onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        log("onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        log("onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        log("onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        log("onDestroy")
    }

    private fun log(method: String) {
        Logger.d(
            "$identifier $separator $method",
            mapOf(
                type to identifier,
                "method" to method
            ),
            category,
            channel
        )
    }
}
