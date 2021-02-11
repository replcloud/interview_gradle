package us.matthey.coco.algorithm.epi.ch12searching;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Searching {
    public static class Student {
        public String name;
        public double gradePointAverage;

        public Student(String name, double gradePointAverage) {
            this.name = name;
            this.gradePointAverage = gradePointAverage;
        }
    }

    private static final Comparator<Student> compGPA = new Comparator<Student>() {
        @Override
        public int compare(Student a, Student b) {
            if (a.gradePointAverage != b.gradePointAverage) {
                return Double.compare(a.gradePointAverage, b.gradePointAverage);
            } else {
                return a.name.compareTo(b.name);
            }
        }
    };

    public static boolean searchStudent(List<Student> students, Student target, Comparator<Student> compGPA) {
        return Collections.binarySearch(students, target, compGPA) >= 0;
    }

    public static int searchFirstOfK(List<Integer> A, int k) {
        int left = 0, right = A.size() - 1, result = -1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > k) {
                right = mid - 1;
            } else if (A.get(mid) == k) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static int searchEntryEqualToItsIndex(List<Integer> A) {
        int left = 0, right = A.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int difference = A.get(mid) - mid;
            if (difference == 0) {
                return mid;
            } else if (difference > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int searchSmallest(List<Integer> A) {
        int left = 0, right = A.size() - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > A.get(right)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int searchSmallesRepeated(List<Integer> A) {
        return searchSmallesHelper(A, 0, A.size() - 1);
    }

    private static int searchSmallesHelper(List<Integer> A, int left, int right) {
        if (left == right) return left;
        int mid = left + ((right - left) / 2);
        if (A.get(mid) > A.get(right)) {
            return searchSmallesHelper(A,mid + 1, right);
        } else if (A.get(mid) < A.get(right)) {
            return searchSmallesHelper(A,left, mid);
        } else {
            int leftResult = searchSmallesHelper(A, left, mid);
            int rightResult = searchSmallesHelper(A, mid+1, right);
            return A.get(rightResult) < A.get(leftResult) ? rightResult : leftResult;
        }
    }

    public static int squareRoot(int k) {
        long left = 0, right = k;
        while (left <= right) {
            long mid = left + ((right - left) / 2);
            long midSquared= mid * mid;
            if (midSquared <= k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int)left - 1;
    }

    public static double squareRootDouble(double x) {
        double left, right;
        if (x < 1.0) {
            left = x;
            right = 1.0;
        } else {
            left = 1.0;
            right = x;
        }
        while (compare(left, right) == Ordering.SMALLER) {
            double mid = left + 0.5 * (right - left);
            double midSquared = mid * mid;
            if (compare(midSquared, x) == Ordering.EQUAL) {
                return mid;
            } else if (compare(midSquared, x) == Ordering.LARGER) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private enum Ordering {SMALLER, EQUAL, LARGER}

    private static Ordering compare(double a, double b) {
        final double EPSILON = 0.00001;
        double diff = (a- b) / b;
        return diff < -EPSILON ? Ordering.SMALLER : (diff > EPSILON ? Ordering.LARGER : Ordering.EQUAL);
    }
}
