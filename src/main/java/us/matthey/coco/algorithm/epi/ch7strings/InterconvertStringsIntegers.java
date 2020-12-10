package us.matthey.coco.algorithm.epi.ch7strings;

public class InterconvertStringsIntegers {
    public static String intToString(int x) {
        boolean isNegative = x < 0 ? true : false;
        if (isNegative) x = -x;
        StringBuffer sb = new StringBuffer();
        do {
            sb.append((char) ('0' + x % 10));
            x /= 10;
        } while (x != 0);
        if (isNegative) sb.append("-");
        return sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        boolean isNegative = s.charAt(0) == '-';
        int res = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {
                res = res * 10 + s.charAt(i) - '0';
        }
        return isNegative ? -res : res;
    }
}
