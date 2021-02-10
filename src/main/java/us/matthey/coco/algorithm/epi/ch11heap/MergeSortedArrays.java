package us.matthey.coco.algorithm.epi.ch11heap;

import java.util.*;

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
            int smallestArrayHead = heads.get(headEntry.arrayId);
            if (smallestArrayHead < smallestArray.size()) {
                minHeap.add(new ArrayEntry(smallestArray.get(smallestArrayHead), headEntry.arrayId));
                heads.set(headEntry.arrayId, heads.get(headEntry.arrayId) + 1);
            }
        }
        return results;
    }

    public static List<Integer> sortKIncreasingDescreasingArray(List<Integer> A) {
        List<List<Integer>> sortedSubarrays = new ArrayList<>();
        SubarrayType subarrayType = SubarrayType.INCREASING;
        int startIdx = 0;
        for (int i = 1; i < A.size(); i++) {
            if (i == A.size() || A.get(i - 1) < A.get(i) && subarrayType == SubarrayType.DECREASING || A.get(i - 1) >= A.get(i) && subarrayType == SubarrayType.INCREASING) {
                List<Integer> subList = A.subList(startIdx, i);
                if (subarrayType == SubarrayType.DECREASING) {
                    Collections.reverse(subList);
                }
                sortedSubarrays.add(subList);
                startIdx = i;
                subarrayType = (subarrayType == SubarrayType.INCREASING ? SubarrayType.DECREASING: SubarrayType.INCREASING);
            }
        }
        return mergeSortedArray(sortedSubarrays);
    }

    private static enum SubarrayType {INCREASING, DECREASING}

    public static void sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k + 1 && sequence.hasNext(); i++) {
            minHeap.add(sequence.next());
        }
        while (sequence.hasNext()) {
            minHeap.add(sequence.next());
            Integer smallest = minHeap.remove();
            System.out.print(smallest);
        }
        while (!minHeap.isEmpty()) {
            Integer smallest = minHeap.remove();
            System.out.print(smallest);
        }
    }

    public static class Star implements Comparable<Star> {
        private double x, y, z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distance() {return Math.sqrt(x * x + y * y + z * z);}

        @Override
        public int compareTo(Star rhs) {
            return Double.compare(this.distance(), rhs.distance());
        }
    }

    public static List<Star> findClosestKStarts(int k, Iterator<Star> stars) {
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        while (stars.hasNext()) {
            Star star = stars.next();
            maxHeap.add(star);
            if (maxHeap.size() == k + 1) {
                maxHeap.remove();
            }
        }
        List<Star> orderedStar = new ArrayList<Star>(maxHeap);
        Collections.sort(orderedStar);
        return orderedStar;
    }
}
