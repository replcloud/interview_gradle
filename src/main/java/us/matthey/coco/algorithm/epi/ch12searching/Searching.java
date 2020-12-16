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
}
