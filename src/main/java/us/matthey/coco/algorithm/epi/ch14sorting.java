package us.matthey.coco.algorithm.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ch14sorting {
    public static List<Integer> intersactTwoSortedArraysBinarySearch(List<Integer> A, List<Integer> B) {
        /* Best solution if one set is much smaller then the other */
        List<Integer> intersactionAB = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if ((i == 0 || A.get(i) != A.get(i - 1)) && Collections.binarySearch(B, A.get(i)) >= 0) {
                intersactionAB.add(A.get(i));
            }
        }
        return intersactionAB;
    }

    public static List<Integer> intersactTwoSortedArraysTwoSimilar(List<Integer> A, List<Integer> B) {
        List<Integer> intersactionAB = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) == B.get(j) && (i == 0 || A.get(i) != A.get(i - 1))) {
                intersactionAB.add(A.get(i));
                i++;
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return intersactionAB;
    }

    public static void mergeTwoSortedArrays(List<Integer> A, int m, List<Integer> B, int n) {
        int a = m - 1, b = n - 1, writeIdx = m + n - 1;
        while (a >= 0 && b >= 0) {
            A.set(writeIdx--, A.get(a) > B.get(b) ? A.get(a--) : B.get(b--));
        }
        while (b >= 0) {
            A.set(writeIdx--, B.get(b--));
        }
    }

    public static class Name implements Comparable<Name> {
        String firstName;
        String lastName;

        @Override
        public int compareTo(Name name) {
            int cmpFist = firstName.compareTo(name.firstName);
            if (cmpFist != 0) return cmpFist;
            return lastName.compareTo(name.lastName);
        }
    }

    public static void EliminateDuplicate(List<Name> A) {
        Collections.sort(A);
        int result = 0;
        for (int first = 1; first < A.size(); first++) {
            if (!A.get(first).firstName.equals(A.get(result).firstName)) {
                A.set(++result, A.get(first));
            }
        }
        A.subList(++result, A.size()).clear();
    }

    public static class Event {
        public int start, end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Endpoint implements Comparable<Endpoint> {
        public int time;
        public boolean isStart;

        @Override
        public int compareTo(Endpoint e) {
            if (time != e.time) {
                return Integer.compare(time, e.time);
            }
            return isStart && !e.isStart ? -1 : !isStart && e.isStart ? 1 : 0;
        }

        Endpoint(int t, boolean is) {
            time  = t;
            isStart = is;
        }
    }

    public static int findMaxSimutaneousEvents(List<Event> A) {
        List<Endpoint> E = new ArrayList<>();
        for (Event event : A) {
            E.add(new Endpoint(event.start, true));
            E.add(new Endpoint(event.end, false));
        }
        Collections.sort(E);
        int maxNumSimutaneousEvents = 0, numSimutaneousEvents = 0;
        for (Endpoint e : E) {
            if (e.isStart) {
                ++numSimutaneousEvents;
                maxNumSimutaneousEvents = Math.max(maxNumSimutaneousEvents, numSimutaneousEvents);
            } else {
                --numSimutaneousEvents;
            }
        }
        return maxNumSimutaneousEvents;
    }

    public static class Interval {
        public int left, right;
        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    public static List<Interval> addInterval(List<Interval> disjointIntervals, Interval newInterval) {
        int i = 0;
        List<Interval> results = new ArrayList<>();
        while (i < disjointIntervals.size() && disjointIntervals.get(i).right < newInterval.left) {
            results.add(disjointIntervals.get(i++));
        }
        while (i < disjointIntervals.size()) {
            newInterval = new Interval(Math.min(newInterval.left, disjointIntervals.get(i).left), Math.max(newInterval.right, disjointIntervals.get(i).right));
            ++i;
        }
        results.add(newInterval);
        results.addAll(disjointIntervals.subList(i, disjointIntervals.size()));
        return results;
    }
}
