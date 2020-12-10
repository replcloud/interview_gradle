package us.matthey.coco.algorithm.epi.ch6arrays;

public class EPIArrays {
    public static void evenOdd(int[] A) {
        int nextEven = 0;
        int nextOdd = A.length - 1;
        while (nextEven < nextOdd) {
            if (A[nextEven] % 2 == 0) {
                nextEven++;
            } else {
                int tmp = A[nextOdd];
                A[nextOdd--] = A[nextEven];
                A[nextEven] = tmp;
            }
        }
    }
}
