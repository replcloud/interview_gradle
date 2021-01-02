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
                result.set(i + j + 1, result.get(i + j + 1) % 10);
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

    public static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) return 0;
        int writeIndex = 1;
        for (int i = 1; i < A.size(); i++) {
            if (!A.get(writeIndex - 1).equals(A.get(i))) {
                A.set(writeIndex++, A.get(i));
            }
        }
        return writeIndex;
    }

    public static double computeMaxPorift(List<Double> prices) {
        double minPrice = Double.MAX_VALUE, maxProfit = 0;
        for (Double price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }

    public static double buyAndSellStockTwice(List<Double> prices) {
        double minPriceSoFar = Double.MAX_VALUE, maxTotalProfit = 0;
        List<Double> firstBuySellProfit = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfit.add(maxTotalProfit);
        }
        double maxPriceSoFar = Double.MAX_VALUE;
        for (int i = prices.size() - 1; i > 0; i--) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i) + firstBuySellProfit.get(i - 1));
        }
        return maxTotalProfit;
    }

    public static List<Integer> generatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
        isPrime.set(0, false);
        isPrime.set(1, false);
        for (int p = 2; p <= n; p++) {
            if (isPrime.get(p)) {
                primes.add(p);
                for (int j = p; j <= n; j += p) {
                    isPrime.set(p, false);
                }
            }
        }
        return primes;
    }

    public static List<Integer> generatePrimes2(int n) {
        final int size = (int) Math.floor(0.5 * (n - 3)) + 1;
        List <Integer> primes = new ArrayList<>();
        primes.add(2);
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size, true));
        for (int i=0; i<size; i++) {
            if (isPrime.get(i)) {
                int p = (i * 2) + 3;
                primes.add(p);
                for (long j=(i * i * 2) + 6 * i + 3; j < size; j+=p) {
                    isPrime.set((int)j, false);
                }
            }
        }
        return primes;
    }

    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        for (int i=0; i < A.size(); i++) {
            int next = i;
            while (perm.get(next) >= 0) {
                Collections.swap(A, i, perm.get(next));
                int temp = perm.get(next);
                perm.set(next, perm.get(next) - perm.size());
                next = temp;
            }
        }
        for (int i=0; i< perm.size(); i++) {
            perm.set(i, perm.get(i) + perm.size());
        }
    }

    public static void main(String[] args) {
        List<Double> A = Arrays.asList(12.0, 11.0, 13.0, 9.0, 12.0, 8.0, 14.0, 13.0, 15.0);
        System.out.print(buyAndSellStockTwice(A));
    }

}