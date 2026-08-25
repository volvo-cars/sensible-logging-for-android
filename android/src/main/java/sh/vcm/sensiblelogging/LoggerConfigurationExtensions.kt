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

import sh.vcm.sensiblelogging.channel.LogCatChannel
import sh.vcm.sensiblelogging.filter.AllowAllFilter
import sh.vcm.sensiblelogging.filter.Filter
import sh.vcm.sensiblelogging.formatter.Formatter
import sh.vcm.sensiblelogging.formatter.LogCatFormatterExtended

/**
 * Adds a [LogCatChannel] to this [Logger.Setup.Configuration].
 */
fun Logger.Setup.Configuration.addLogCatChannel(
    filter: Filter = AllowAllFilter,
    formatter: Formatter = LogCatFormatterExtended,
    default: Boolean = true
): Logger.Setup.Configuration {
    addChannel(LogCatChannel(formatter, filter, default))
    return this
}
