package us.matthey.coco.algorithm.epi.ch6arrays;

import java.util.ArrayList;
import java.util.Arrays;
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
            } else if (A.get(equal).ordinal() == pivot.ordinal()) {
                equal++;
            } else {
                Collections.swap(A, equal, --larger);
            }
        }
    }

    public static List<Integer> addOne(List<Integer> A) {
        int n = A.size() - 1;
        A.set(n, A.get(n) + 1);
        for (int i = n; i > 0 && A.get(i) == 0; i--) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }
        if (A.get(0) == 10) {
            A.set(0, 0);
            A.add(0, 1);
        }
        return A;
    }

    public static List<Integer> mutiply(List<Integer> num1, List<Integer> num2) {
        int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));
        List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
        for (int i = num1.size() - 1; i >= 0; i--) {
            for (int j = num2.size() - 1; j >= 0; j--) {
                result.set(i + j + 1, num1.get(i) * num2.get(j));
                result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
                result.set(i + j + 1, result.get(i +j + 1) % 10);
            }
        }
        int first_not_zero = 0;
        while (first_not_zero < result.size() && result.get(first_not_zero) == 0) {
            first_not_zero++;
        }
        result = result.subList(first_not_zero, result.size());
        if (result.isEmpty()) {
            return Arrays.asList(0);
        }
        result.set(0, result.get(0) * sign);
        return result;
    }
}