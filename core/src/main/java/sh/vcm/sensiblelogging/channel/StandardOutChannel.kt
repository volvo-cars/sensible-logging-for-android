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

import sh.vcm.sensiblelogging.Level
import sh.vcm.sensiblelogging.Line
import sh.vcm.sensiblelogging.Meta
import sh.vcm.sensiblelogging.filter.Filter
import sh.vcm.sensiblelogging.formatter.Formatter

class StandardOutChannel(
    private val formatter: Formatter,
    override val filter: Filter,
    override val default: Boolean = false,
) : DebugChannel() {

    companion object {
        const val ID = 3
        const val id = "StandardOut"
    }

    override val id = ID

    override fun print(line: Line, meta: Meta) {
        val formattedMessage = if (line.preFormatted) line.message else formatter.format(line, meta)
        if (line.level.ordinal < Level.ERROR.ordinal) {
            System.out.println(formattedMessage)
        } else {
            System.err.println(formattedMessage)
        }
    }
}
