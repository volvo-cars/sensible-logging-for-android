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

package sh.vcm.sensiblelogging.formatter

import sh.vcm.sensiblelogging.Line
import sh.vcm.sensiblelogging.Meta
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object SimpleFormatter : Formatter {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH)

    override fun format(line: Line, meta: Meta?): String {
        val stackTrace = line.throwable?.let {
            " exception[${it.message}]\n${it.stackTraceToString()}"
        } ?: ""
        return if (meta != null){
            "${dateFormatter.format(Date(line.timestamp))} [${line.level.name}] [${line.category.name}] ${meta.threadName} ${meta.fileName}:${meta.functionName}    -    ${line.message} ${line.parameters.entries.joinToString { "[${it.key}]:${it.value}" }}$stackTrace"
        } else {
            "${dateFormatter.format(Date(line.timestamp))} [${line.level.name}] [${line.category.name}]    -    ${line.message} ${line.parameters.entries.joinToString { "[${it.key}]:${it.value}" }}$stackTrace"
        }
    }

    private fun Throwable.stackTraceToString(): String {
        val sw = StringWriter()
        printStackTrace(PrintWriter(sw))
        return sw.toString()
    }
}
