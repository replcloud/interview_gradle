package us.matthey.coco.algorithm.epi.ch6arrays;

import java.util.Collections;
import java.util.List;

public class DutchFlagPartition {
    public static enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);
        int smaller = 0, equal = 0, larger = A.size();
        while (equal < larger) {
            if (A.get(equal).ordinal() < pivotIndex) {
                Collections.swap(A, smaller++, equal++);
            } else if(A.get(equal).ordinal() == pivot.ordinal()) {
                equal++;
            } else {
                Collections.swap(A, equal, --larger);
            }
        }
    }
}