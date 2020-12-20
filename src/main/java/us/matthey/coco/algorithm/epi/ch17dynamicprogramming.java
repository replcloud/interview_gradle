package us.matthey.coco.algorithm.epi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ch17dynamicprogramming {
    private static Map<Integer, Integer> cache = new HashMap<>();

    public static int fibonaci(int n) {
        if (n <= 1) {
            return n;
        } else if (!cache.containsKey(n)) {
            cache.put(n, fibonaci(n - 1) + fibonaci(n - 2));
        }
        return cache.get(n);
    }

    public static int findMaxinumSubarray(List<Integer> A) {
        int minSum = 0, sum = 0, maxSum = 0;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
            if (sum - minSum > maxSum) {
                maxSum = sum - minSum;
            }
            if (sum < minSum) {
                minSum = sum;
            }
        }
        return maxSum;
    }

    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        int[][] numCombinationsForScore = new int[individualPlayScores.size()][finalScore + 1];
        for (int i = 0; i < individualPlayScores.size(); i++) {
            numCombinationsForScore[i][0] = 1;
            for (int j = 1; j < finalScore; j++) {
                int withoutThisPlay = i - 1 >= 0 ? numCombinationsForScore[i - 1][j] : 0;
                int withThisPlay = j >= individualPlayScores.get(i) ? numCombinationsForScore[i][j-individualPlayScores.get(i)] : 0;
            }
        }
        return numCombinationsForScore[individualPlayScores.size()-1][finalScore];
    }
}
