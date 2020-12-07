package us.matthey.coco.algorithm.epi.ch5;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class SmallestNonconstructibleValueTest {
    @Test
    public void testSmallestNonconstructibleValue() {
        int res = SmallestNonconstructibleValue.smallestNonconstructibleValue(Arrays.asList(1,2,3));
        System.out.println(res);
    }
}