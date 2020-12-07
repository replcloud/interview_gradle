package us.matthey.coco.algorithm.epi.ch5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallestNonconstructibleValue {
    public static int smallestNonconstructibleValue(List<Integer> A) {
        Collections.sort(A);
        int maxConstructibleValue = 0;
        for (int a: A) {
            if (a > maxConstructibleValue + 1) break;
            maxConstructibleValue += a;
        }
        return maxConstructibleValue + 1;
    }
}
