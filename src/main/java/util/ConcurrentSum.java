package util;

import pojo.Laureate;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ConcurrentSum extends RecursiveTask<BigInteger> {
    private final static int THRESHOLD = 100;
    private final List<Laureate> list;

    public ConcurrentSum(List<Laureate> list) {
        this.list = list;
    }

    @Override
    protected BigInteger compute() {
        var size = list.size();
        if (size <= THRESHOLD) {
            return sequentialSum(list);
        }

        var x = new ConcurrentSum(list.subList(0, size / 2));
        var y = new ConcurrentSum(list.subList(size / 2, size));
        x.fork();
        y.fork();
        var xResult = x.join();
        var yResult = y.join();

        return yResult.add(xResult);
    }

    public static BigInteger sequentialSum(List<Laureate> list1) {
        var acc = BigInteger.ZERO;
        for (Laureate person : list1) {
            acc = acc.add(BigInteger.valueOf(person.getAge()));
        }
        return acc;
    }

    public static BigInteger concurrentSum(List<Laureate> list2) {
        var commonPool = ForkJoinPool.commonPool();
        return commonPool.invoke(new ConcurrentSum(list2));
    }

}