package us.matthey.coco.algorithm.epi.ch4;

import java.util.Collections;
import java.util.List;

public class Rearrange {
    /* Iterative refinement */
    public static void rearrange(List<Integer> A) {
        for (int i = 1; i < A.size(); i++) {
            System.out.println(i);
            if (i % 2 == 1 && A.get(i - 1) > A.get(i) || i % 2 == 0 && A.get(i - 1) < A.get(i)) {
                Collections.swap(A, i - 1, i);
            }
        }
    }
}
