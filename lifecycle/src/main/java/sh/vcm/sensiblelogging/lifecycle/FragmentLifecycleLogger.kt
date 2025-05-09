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

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Logger

class FragmentLifecycleLogger(private val category: Category, private val channel: Int, private val separator: String) :
    FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        log(f, "onFragmentViewCreated")
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentStopped")
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        log(f, "onFragmentCreated")
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentResumed")
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        log(f, "onFragmentAttached")
    }

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        log(f, "onFragmentPreAttached")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentDestroyed")
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        log(f, "onFragmentSaveInstanceState")
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentStarted")
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentViewDestroyed")
    }

    override fun onFragmentPreCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        log(f, "onFragmentPreCreated")
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        log(f, "onFragmentActivityCreated")
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentPaused")
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        log(f, "onFragmentDetached")
    }

    private fun log(fragment: Fragment, method: String) {
        // Suppress because it's in fact nullable but uses androidx.annotation.Nullable annotation
        @Suppress("UNNECESSARY_SAFE_CALL")
        Logger.d(
            "${fragment.identifier()} $separator $method",
            mapOf(
                TYPE_FRAGMENT to fragment.identifier(),
                TYPE_ACTIVITY to fragment?.activity.identifier(),
                "method" to method
            ),
            category,
            channel
        )
    }

    private fun Fragment.identifier(): String = this.javaClass.simpleName
}
