package us.matthey.coco.algorithm.leetcode;

public class ReverseBits {
    public static int reverseBits(int n) {
        int res = 0;
        for (int i=0; i<32; i++) {
            res = (res << 1) + (n & 0x0001);
            n >>>= 1;
        }
        return res;
    }

    public static int reverseBitsFaster(int n) {
        /* https://leetcode.com/problems/reverse-bits/discuss/54741/O(1)-bit-operation-C%2B%2B-solution-(8ms)
        for 8 bit binary number abcdefgh, the process is as follow:
        abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
        */
        int res = (n << 16) | (n >>> 16);
        res = (n & 0xff00ff00) >>> 8 | (n & 0x00ff00ff) << 8;
        res = (n & 0xf0f0f0f0) >>> 4 | (n & 0xf0f0f0f0) << 4;
        res = (n & 0xcccccccc) >>> 2 | (n & 0x33333333) << 2;
        res = (n & 0xaaaaaaaa) >>> 1 | (n & 0x55555555) << 1;
        return res;
    }
}
