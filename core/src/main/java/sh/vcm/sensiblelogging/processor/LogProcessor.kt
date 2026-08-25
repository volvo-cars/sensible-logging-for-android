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

package sh.vcm.sensiblelogging.processor

import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Level
import sh.vcm.sensiblelogging.Line
import sh.vcm.sensiblelogging.channel.Channel
import sh.vcm.sensiblelogging.channel.DebugChannel
import sh.vcm.sensiblelogging.channel.ReleaseChannel
import sh.vcm.sensiblelogging.util.MetaDataFactory

internal class LogProcessor {

    private val channels = ArrayList<Channel>()

    @Volatile
    private var channelsArray = emptyArray<Channel>()

    fun addChannels(channels: List<Channel>) {
        synchronized(this.channels) {
            this.channels.addAll(channels)
            channelsArray = this.channels.toTypedArray()
        }
    }

    fun removeChannels(channels: Set<Channel>) {
        synchronized(this.channels) {
            this.channels.removeAll(channels.toSet())
            channelsArray = this.channels.toTypedArray()
        }
    }

    fun clearChannels() {
        synchronized(this.channels) {
            channels.clear()
            channelsArray = this.channels.toTypedArray()
        }
    }

    fun log(
        level: Level,
        message: String,
        preFormattedMessage: Boolean,
        category: Category,
        channels: List<Int>,
        throwable: Throwable?,
        parameters: Map<String, String>,
        stackDepth: Int
    ) {
        channelsArray
            .filter { channel -> channel.default || channels.contains(channel.id) }
            .takeIf { it.isNotEmpty() }
            ?.let { channelList ->
                val line = Line(
                    timestamp = System.currentTimeMillis(),
                    category = category,
                    level = level,
                    message = message,
                    preFormatted = preFormattedMessage,
                    throwable = throwable,
                    parameters = parameters
                )
                channelList
                    .filterIsInstance<ReleaseChannel>()
                    .forEach { it.printFiltered(line) }
                channelList
                    .filterIsInstance<DebugChannel>()
                    .filter { it.filter.matches(line) }
                    .takeIf { it.isNotEmpty() }
                    ?.let { debugChannels ->
                        val meta = MetaDataFactory.create(stackDepth)
                        debugChannels.forEach { channel ->
                            channel.printFiltered(line, meta)
                        }
                    }
            }

    }
}
