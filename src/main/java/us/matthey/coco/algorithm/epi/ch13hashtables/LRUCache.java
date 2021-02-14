package us.matthey.coco.algorithm.epi.ch13hashtables;

import us.matthey.coco.algorithm.epi.ch10binarytrees.BinaryTree;

import java.util.*;

public class LRUCache {
    LinkedHashMap<Integer, Integer> isbnToPrice;

    LRUCache(final int capacity) {
        this.isbnToPrice = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
                return this.size() > capacity;
            }
        };
    }

    public Integer lookup(Integer key) {
        if (!isbnToPrice.containsKey(key)) {
            return null;
        }
        return isbnToPrice.get(key);
    }

    public Integer insert(Integer key, Integer value) {
        Integer currentValue = isbnToPrice.get(key);
        if (!isbnToPrice.containsKey(key)) {
            isbnToPrice.put(key, value);
            return value; // TODO:
        } else {
            return currentValue; // TODO:
        }
    }

    public Integer erase(Object key) {
        return isbnToPrice.remove(key);
    }

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        Set<BinaryTree<Integer>> hash = new HashSet<>();
        while (node0 != null || node1 != null) {
            if (node0 != null) {
                if (!hash.add(node0)) {
                    return node0;
                }
                node0 = node0.parent;
            }
            if (node1 != null) {
                if (!hash.add(node1)) {
                    return node1;
                }
                node1 = node1.parent;
            }
        }
        throw new IllegalArgumentException("node0 and node0 are not in the same tree");
    }

    public static int findNearestRepition(List<String> paragraph) {
        Map<String, Integer> wordToLatestIndex = new HashMap<>();
        int nearestRepeatedDistance = Integer.MAX_VALUE;
        for (int i = 0; i < paragraph.size(); i++) {
            if (wordToLatestIndex.containsKey(paragraph.get(i))) {
                nearestRepeatedDistance = Math.min(nearestRepeatedDistance, i - wordToLatestIndex.get(paragraph.get(i)));
            }
            wordToLatestIndex.put(paragraph.get(i), i);
        }
        return nearestRepeatedDistance;
    }

    public static class Subarray {
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph, Set<String> keywords) {
        Map<String, Integer> keywordsToCover = new HashMap<>();
        for (String keyword : keywords) {
            keywordsToCover.put(keyword, keywordsToCover.containsKey(keyword) ? keywordsToCover.get(keyword) + 1 : 1);
        }
        Subarray result = new Subarray(-1, -1);
        int remainingToCover = keywords.size();
        for (int left = 0, right = 0; right < paragraph.size(); right++) {
            Integer keywordCount = keywordsToCover.get(paragraph.get(right));
            if (keywordCount != null) {
                keywordsToCover.put(paragraph.get(right), --keywordCount);
                if (keywordCount >= 0) --remainingToCover;
            }
            while (remainingToCover == 0) {
                if ((result.start == -1 && result.end == -1) || right - left < result.end - result.start) {
                    result.start = left;
                    result.end = right;
                }
                keywordCount = keywordsToCover.get(paragraph.get(left));
                if (keywordCount != null) {
                    keywordsToCover.put(paragraph.get(left), ++keywordCount);
                    if (keywordCount > 0) ++remainingToCover;
                }
                ++left;
            }
        }
        return result;
    }

    private static Integer getValueForFirstEntry(LinkedHashMap<String, Integer> m) {
        Integer result = null;
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            result = entry.getValue();
            break;
        }
        return result;
    }

    public static Subarray findSmallestSubarrayCoveringSubset(Iterator<String> iter, List<String> queryStrings) {
        LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();
        for (String s: queryStrings) {
            dict.put(s, null);
        }
        int numStringsFromQueryStringsSeenSoFar = 0;
        Subarray res = new Subarray(-1, -1);
        int idx = 0;
        while (iter.hasNext()) {
            String s = iter.next();
            if (dict.containsKey(s)) {
                Integer it = dict.get(s);
                if (it == null) {
                    numStringsFromQueryStringsSeenSoFar++;
                    dict.remove(s);
                    dict.put(s, idx);
                }
            }
            if (numStringsFromQueryStringsSeenSoFar == queryStrings.size()) {
                if ((res.start == -1 && res.end == -1) || idx - getValueForFirstEntry(dict) < res.end - res.start) {
                    res.start = getValueForFirstEntry(dict);
                    res.end = idx;
                }
            }
            ++idx;
        }
        return res;
    }
}
