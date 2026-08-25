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

package sh.vcm.sensiblelogging.util

import sh.vcm.sensiblelogging.Category

object Constants {
    val DEFAULT_CATEGORY = Category("Default")
    internal val DEFAULT_CHANNELS = IntArray(0)
    internal val DEFAULT_CHANNEL_LIST = DEFAULT_CHANNELS.asList()
    internal val EMPTY_PARAMS: Map<String, String> = emptyMap()
    internal const val DEFAULT_STACK_DEPTH = 4
}
