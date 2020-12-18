package us.matthey.coco.algorithm.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ch14sorting {
    public static List<Integer> intersactTwoSortedArraysOneSmallOneBig(List<Integer> A, List<Integer> B) {
        /* Best solution if one set is much smaller then the other */
        List<Integer> intersactionAB = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if ((i == 0 || A.get(i) != A.get(i - 1)) && Collections.binarySearch(B, A.get(i)) >= 0) {
                intersactionAB.add(A.get(i));
            }
        }
        return intersactionAB;
    }

    public static List<Integer> intersactTwoSortedArraysTwoSimilar(List<Integer> A, List<Integer> B) {
        List<Integer> intersactionAB = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) == B.get(j) && (i==0 || A.get(i) != A.get(i-1))) {
                intersactionAB.add(A.get(i));
                i++;
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return intersactionAB;
    }
}
