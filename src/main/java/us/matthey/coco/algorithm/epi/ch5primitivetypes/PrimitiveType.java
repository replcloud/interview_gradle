package us.matthey.coco.algorithm.epi.ch5primitivetypes;

public class PrimitiveType {
    public static short countBits(int x) {
        /* Time complexcity is O(n)
        * >> preserves the sign
        * >>>, it ignores the sign
        */
        short numBits = 0;
        while (x != 0) {
            numBits += x & 1;
            x >>= 1;
        }
        return numBits;
    }

    /* x &= x - 1 drops the lawest set bit of x
    x &= ~(x - 1) the lowest bit of x that is 1
     */
    public static short parity1(long x) {
        short res = 0;
        while (x != 0) {
            res ^= (x & 1);
            x >>>= 1;
        }
        return res;
    }

    public static short parity2(long x) {
        short res = 0;
        while (x != 0) {
            res ^= 1;
            x &= x - 1; // Drop the lowest bit of x
        }
        return res;
    }

    public static short parity3(long x) {
        for (int i=32; i>=1; i>>=1) {
            x ^= x >>> i;
        }
        return (short) (x & 0x1);
    }

    public static long swapBits(long x, int i, int j) {
        if (((x >>> i) & 1) != ((x >>> j) & 1)) {
            long bitMask = (1L << i) | (1L << j);
            x ^= bitMask;
        }
        return x;
    }

    public static long reverseBits(long x) {
        final int WORLD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        // TODOx`
        return 0;
    }
}

