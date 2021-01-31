/*
 * The Alphanum Algorithm is an improved sorting algorithm for strings
 * containing numbers.  Instead of sorting numbers in ASCII order like
 * a standard sort, this algorithm sorts numbers in numeric order.
 *
 * The Alphanum Algorithm is discussed at http://www.DaveKoelle.com
 *
 * Released under the MIT License - https://opensource.org/licenses/MIT
 *
 * Copyright 2007-2017 David Koelle
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.monun.kotlin.util

import java.util.*

/**
 * This is an updated version with enhancements made by Daniel Migowski,
 * Andre Bogus, and David Koelle. Updated by David Koelle in 2017.
 *
 * To use this class:
 * Use the static "sort" method from the java.util.Collections class:
 * Collections.sort(your list, new AlphanumComparator());
 */
object AlphanumComparator : Comparator<String?> {
    private fun isDigit(ch: Char): Boolean {
        return ch.toInt() in 48..57
    }

    /** Length of string is passed in for improved efficiency (only need to calculate it once)  */
    private fun getChunk(s: String, slength: Int, marker: Int): String {
        var m = marker
        val chunk = StringBuilder()
        var c = s[m]
        chunk.append(c)
        m++
        if (isDigit(c)) {
            while (m < slength) {
                c = s[m]
                if (!isDigit(c)) break
                chunk.append(c)
                m++
            }
        } else {
            while (m < slength) {
                c = s[m]
                if (isDigit(c)) break
                chunk.append(c)
                m++
            }
        }
        return chunk.toString()
    }

    override fun compare(s1: String?, s2: String?): Int {
        if (s1 == null || s2 == null) {
            return 0
        }
        var thisMarker = 0
        var thatMarker = 0
        val s1Length = s1.length
        val s2Length = s2.length
        while (thisMarker < s1Length && thatMarker < s2Length) {
            val thisChunk = getChunk(s1, s1Length, thisMarker)
            thisMarker += thisChunk.length
            val thatChunk = getChunk(s2, s2Length, thatMarker)
            thatMarker += thatChunk.length

            // If both chunks contain numeric characters, sort them numerically
            var result: Int
            if (isDigit(thisChunk[0]) && isDigit(thatChunk[0])) {
                // Simple chunk comparison by length.
                val thisChunkLength = thisChunk.length
                result = thisChunkLength - thatChunk.length
                // If equal, the first different number counts
                if (result == 0) {
                    for (i in 0 until thisChunkLength) {
                        result = thisChunk[i] - thatChunk[i]
                        if (result != 0) {
                            return result
                        }
                    }
                }
            } else {
                result = thisChunk.compareTo(thatChunk)
            }
            if (result != 0) return result
        }
        return s1Length - s2Length
    }
}