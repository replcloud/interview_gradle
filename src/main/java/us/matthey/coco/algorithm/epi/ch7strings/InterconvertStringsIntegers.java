package us.matthey.coco.algorithm.epi.ch7strings;

import java.util.ArrayList;
import java.util.List;

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

    public static String convertBase(String s, int b1, int b2) {
        boolean isNegative = s.startsWith("-");
        int x = 0;
        for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
            x *= b1;
            x += Character.isDefined(s.charAt(i)) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
        }
        return isNegative ? "-" : "" + (x == 0 ? "0" : constructFromBase(x, b2));
    }

    private static String constructFromBase(int x, int base) {
        return x == 0 ? "" : constructFromBase(x / base, base) + (char) (x % base >= 10 ? 'A' + x % base - 10 : '0' + x % base);
    }

    public static int ssDecodeColID(final String col) {
        int ret = 0;
        for (int i = 0; i < col.length(); i++) {
            ret = ret * 26 + col.charAt(i) - 'A' + 1;
        }
        return ret;
    }

    public static int replaceAndRemove(int size, char[] s) {
        int writeIdx = 0, aCount = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] == 'b') {
                s[writeIdx++] = s[i];
            }
            if (s[i] == 'a') {
                ++aCount;
            }
        }

        int curIdx = writeIdx - 1;
        writeIdx = writeIdx + aCount - 1;

        int finalSize = writeIdx + 1;

        while (curIdx >= 0) {
            if (s[curIdx] == 'a') {
                s[writeIdx--] = 'd';
                s[writeIdx--] = 'd';
            } else {
                s[writeIdx--] = s[curIdx];
            }
            --curIdx;
        }
        return finalSize;
    }

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
                ++i;
            }
            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
                ++j;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            ++i;
            ++j;
        }
        return true;
    }

    public static void reverseWords(char[] input) {
        reverse(input, 0, input.length);
        int start = 0, end;
        while ((end = find(input, ' ', start)) != -1) {
            reverse(input, start, end);
            start = end + 1;
        }
        reverse(input, start, input.length);
    }

    private static void reverse(char[] array, int start, int stopIndex) {
        if (start >= stopIndex) return;
        int last = stopIndex - 1;
        for (int i = start; i < start + (last - start) / 2; i++) {
            char tmp = array[i];
            array[i] = array[last - i + start];
            array[last - i + start] = tmp;
        }
    }

    public static int find(char[] array, char c, int start) {
        for (int i = start; i < array.length; i++) {
            if (array[i] == c) return i;
        }
        return -1;
    }

    public static List<String> phoneMnemonic(String phoneNumber) {
        char[] partialMnemonic = new char[phoneNumber.length()];
        List<String> mnemonic = new ArrayList<>();
        phoneMnemonicHelper(phoneNumber, 0, partialMnemonic, mnemonic);
        return mnemonic;
    }

    private static final String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQR", "WXYZ"};

    private static void phoneMnemonicHelper(String phoneNumber, int digit, char[] partialMnemonic, List<String> mnemonic) {
        if (digit == phoneNumber.length()) {
            mnemonic.add(new String(partialMnemonic));
        } else {
            for (int i = 0; i < MAPPING[phoneNumber.charAt(digit) - '0'].length(); i++) {
                char c = MAPPING[phoneNumber.charAt(digit) - '0'].charAt('i');
                partialMnemonic[digit] = c;
                phoneMnemonicHelper(phoneNumber, digit + 1, partialMnemonic, mnemonic);
            }
        }
    }

    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestSoFar = 0, lastIndex = maxAdvanceSteps.size() - 1;
        for (int i = 0; i <= furthestSoFar && furthestSoFar <= lastIndex; i++) {
            furthestSoFar = Math.max(furthestSoFar, i + maxAdvanceSteps.get(i));
        }
        return furthestSoFar >= lastIndex;
    }
}
