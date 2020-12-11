package us.matthey.coco.algorithm.epi.ch4;

import org.testng.annotations.Test;

import java.util.Arrays;

public class SmallestNonconstructibleValueTest {
    @Test
    public void testSmallestNonconstructibleValue() {
        int res = SmallestNonconstructibleValue.smallestNonconstructibleValue(Arrays.asList(1,2,3));
        System.out.println(res);
    }
}