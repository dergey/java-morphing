package com.sergey.zhuravlev.utils;

import java.util.*;

public class MemorizedRandom {

    private final Random random;
    private final Map<Integer, Set<Integer>> randomPool;

    public MemorizedRandom() {
        random = new Random();
        randomPool = new HashMap<>();
    }

    public Integer nextInt(Object o, int bound) {
        if (!randomPool.containsKey(o.hashCode())) {
            randomPool.put(o.hashCode(), new HashSet<>());
        }
        Set<Integer> lastRandom = randomPool.get(o.hashCode());
        while (true) {
            if (lastRandom.size() >= bound) {
                reset(o);
            }
            Integer value = random.nextInt(bound);
            if (!lastRandom.contains(value)) {
                lastRandom.add(value);
                return value;
            }
        }
    }

    public void reset(Object o) {
        if (randomPool.containsKey(o.hashCode())) {
            randomPool.get(o.hashCode()).clear();
        }
    }

}
