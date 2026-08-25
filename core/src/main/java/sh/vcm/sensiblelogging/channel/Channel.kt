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

package sh.vcm.sensiblelogging.channel

import sh.vcm.sensiblelogging.Line
import sh.vcm.sensiblelogging.Meta
import sh.vcm.sensiblelogging.filter.Filter

abstract class Channel {
    abstract val filter: Filter
    abstract val id: Int
    abstract val default: Boolean
}

abstract class DebugChannel : Channel() {
    fun printFiltered(line: Line, meta: Meta) {
        if (filter.matches(line)) {
            print(line, meta)
        }
    }

    abstract fun print(line: Line, meta: Meta)
}

abstract class ReleaseChannel : Channel() {
    fun printFiltered(line: Line) {
        if (filter.matches(line)) {
            print(line)
        }
    }

    abstract fun print(line: Line)
}
