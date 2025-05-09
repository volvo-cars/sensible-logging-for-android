/* *
 * Copyright 2022 Volvo Cars Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sh.vcm.sensiblelogging

import sh.vcm.sensiblelogging.channel.Channel
import sh.vcm.sensiblelogging.channel.LogCatChannel
import sh.vcm.sensiblelogging.channel.StandardOutChannel
import sh.vcm.sensiblelogging.filter.AllowAllFilter
import sh.vcm.sensiblelogging.filter.Filter
import sh.vcm.sensiblelogging.formatter.Formatter
import sh.vcm.sensiblelogging.formatter.LogCatFormatterExtended
import sh.vcm.sensiblelogging.formatter.SimpleFormatter
import sh.vcm.sensiblelogging.processor.LogProcessor
import sh.vcm.sensiblelogging.util.Constants.DEFAULT_CATEGORY
import sh.vcm.sensiblelogging.util.Constants.DEFAULT_CHANNELS
import sh.vcm.sensiblelogging.util.Constants.DEFAULT_CHANNEL_LIST
import sh.vcm.sensiblelogging.util.Constants.DEFAULT_STACK_DEPTH
import sh.vcm.sensiblelogging.util.Constants.EMPTY_PARAMS

object Logger {
    private val processor: LogProcessor = LogProcessor()

    @JvmStatic
    fun v(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.VERBOSE, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun v(
        message: String,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.VERBOSE, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun v(
        message: String,
        category: Category = DEFAULT_CATEGORY,
    ) = processor.log(Level.VERBOSE, message, false, category, DEFAULT_CHANNEL_LIST, null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun v(
        message: String,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.VERBOSE, message, false, DEFAULT_CATEGORY, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun d(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.DEBUG, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun d(
        message: String,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.DEBUG, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun d(
        message: String,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.DEBUG, message, false, DEFAULT_CATEGORY, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun i(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.INFO, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun i(
        message: String,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.INFO, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun i(
        message: String,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.INFO, message, false, DEFAULT_CATEGORY, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun w(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun w(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun w(
        message: String,
        throwable: Throwable? = null,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun w(
        message: String,
        throwable: Throwable? = null,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, DEFAULT_CATEGORY, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun w(
        message: String,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun w(
        message: String,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.WARN, message, false, DEFAULT_CATEGORY, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun e(
        message: String,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), null, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun e(
        message: String,
        throwable: Throwable? = null,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun e(
        message: String,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), throwable, parameters, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun e(
        message: String,
        throwable: Throwable? = null,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, DEFAULT_CATEGORY, channels.toList(), throwable, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun e(
        message: String,
        category: Category = DEFAULT_CATEGORY,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, category, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun e(
        message: String,
        vararg channels: Int = DEFAULT_CHANNELS
    ) = processor.log(Level.ERROR, message, false, DEFAULT_CATEGORY, channels.toList(), null, EMPTY_PARAMS, DEFAULT_STACK_DEPTH)

    @JvmStatic
    fun log(
        level: Level = Level.DEBUG,
        message: String,
        preFormattedMessage: Boolean,
        category: Category = DEFAULT_CATEGORY,
        channels: List<Int> = DEFAULT_CHANNEL_LIST,
        throwable: Throwable? = null,
        parameters: Map<String, String> = EMPTY_PARAMS,
        stackDepth: Int = DEFAULT_STACK_DEPTH
    ) = processor.log(level, message, preFormattedMessage, category, channels, throwable, parameters, stackDepth)

    @JvmStatic
    @Deprecated("Method moved into Log.Setup")
    fun addChannels(vararg channels: Channel) {
        processor.addChannels(channels.toList())
    }

    @JvmStatic
    @Deprecated("Method moved into Log.Setup")
    fun removeChannels(vararg channels: Channel) {
        processor.removeChannels(channels.toSet())
    }

    object Setup {
        @JvmStatic
        fun addChannels(channels: List<Channel>) {
            processor.addChannels(channels)
        }

        @JvmStatic
        fun removeChannels(vararg channels: Channel) {
            processor.removeChannels(channels.toSet())
        }

        @JvmStatic
        fun clearChannels() {
            processor.clearChannels()
        }

        class Configuration {
            private val channels = mutableListOf<Channel>()

            fun addLogCatChannel(
                filter: Filter = AllowAllFilter,
                formatter: Formatter = LogCatFormatterExtended,
                default: Boolean = true
            ): Configuration {
                channels += LogCatChannel(formatter, filter, default)
                return this
            }

            fun addStandardOutChannel(
                filter: Filter = AllowAllFilter,
                formatter: Formatter = SimpleFormatter,
                default: Boolean = false
            ): Configuration {
                channels += StandardOutChannel(formatter, filter, default)
                return this
            }

            fun addChannel(channel: Channel): Configuration {
                channels += channel
                return this
            }

            fun create(): List<Channel> {
                return channels
            }
        }
    }
}
