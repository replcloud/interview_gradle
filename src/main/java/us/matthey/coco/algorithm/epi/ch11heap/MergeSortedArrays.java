package us.matthey.coco.algorithm.epi.ch11heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSortedArrays {
    private static class ArrayEntry {
        public Integer value;
        public Integer arrayId;

        public ArrayEntry(Integer value, Integer arrayId) {
            this.value = value;
            this.arrayId = arrayId;
        }
    }

    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static List<Integer> mergeSortedArray(List<List<Integer>> sortedArrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, new Comparator<ArrayEntry>() {
            @Override
            public int compare(ArrayEntry o1, ArrayEntry o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });
        List<Integer> heads = new ArrayList<>(sortedArrays.size());
        for (int i=0; i<sortedArrays.size(); i++) {
            if (sortedArrays.get(i).size() > 0) {
                minHeap.add(new ArrayEntry(sortedArrays.get(i).get(0), i));
                heads.add(1);
            } else {
                heads.add(0);
            }
        }

        List<Integer> results = new ArrayList<>();
        ArrayEntry headEntry;
        while ((headEntry = minHeap.poll()) != null) {
            results.add(headEntry.value);
            List<Integer> smallestArray = sortedArrays.get(headEntry.arrayId);
            //...
        }
    }
}
