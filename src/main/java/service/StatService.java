package service;

import pojo.Laureate;
import util.ConcurrentSum;

import java.util.List;

public class StatService {
    public static long calcAvgAgeSequentially(List<Laureate> list) {
        return ConcurrentSum.sequentialSum(list).longValue() / list.size();
    }

    public static long calcAvgAgeConcurrently(List<Laureate> list) {
        return ConcurrentSum.concurrentSum(list).longValue() / list.size();
    }

    public static long calcAvgAgeSequentiallyBenchmarked(List<Laureate> list) {
        long before = System.currentTimeMillis();
        long result = calcAvgAgeSequentially(list);
        System.out.println(
                "Sequential execution took " + (System.currentTimeMillis() - before) + "ms."
        );
        return result;
    }

    public static long calcAvgAgeConcurrentlyBenchmarked(List<Laureate> list) {
        long before = System.currentTimeMillis();
        long result = calcAvgAgeConcurrently(list);
        System.out.println(
                "Concurrent execution took " + (System.currentTimeMillis() - before) + "ms."
        );
        return result;
    }
}
