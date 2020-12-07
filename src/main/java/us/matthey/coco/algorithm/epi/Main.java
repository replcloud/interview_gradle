package us.matthey.coco.algorithm.epi;

import us.matthey.coco.algorithm.epi.ch5.java.SmallestNonconstructibleValue;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int res = SmallestNonconstructibleValue.smallestNonconstructibleValue(Arrays.asList(1,2));
        System.out.println(res);
        res = SmallestNonconstructibleValue.smallestNonconstructibleValue(Arrays.asList(1,2,3));
        System.out.println(res);
        res = SmallestNonconstructibleValue.smallestNonconstructibleValue(Arrays.asList(1, 2, 2, 4, 12, 15));
        System.out.println(res);
    }
}
