/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package ru.alexeylisyutenko.maximumsubarray;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import ru.alexeylisyutenko.maximumsubarray.bruteforce.BruteforceMaximumSubarrayFinder;
import ru.alexeylisyutenko.maximumsubarray.divideandconquer.DivideAndConquerMaximumSubarrayFinder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class MyBenchmark {

    final int[] array = randomIntArray(500, 31337);
    final MaximumSubarrayFinder bruteForceMaximumSubarrayFinder = new BruteforceMaximumSubarrayFinder();
    final MaximumSubarrayFinder divideAndConquerMaximumSubarrayFinder = new DivideAndConquerMaximumSubarrayFinder();

    private static int[] randomIntArray(int size, long seed) {
        Random random = new Random(seed);
        return random.ints(size, -1000, 1000).toArray();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
    public MaximumSubarrayInfo bruteForce() {
        return bruteForceMaximumSubarrayFinder.find(array);
    }

    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public MaximumSubarrayInfo divideAndConquer() {
        return divideAndConquerMaximumSubarrayFinder.find(array);
    }

}
