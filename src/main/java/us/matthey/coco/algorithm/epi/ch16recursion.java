package us.matthey.coco.algorithm.epi;

import java.util.*;

public class ch16recursion {
    private static final int NUM_PEGS = 3;

    public static void computeTowerHanoi(int numRings) {
        List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            pegs.add(new LinkedList<Integer>());
        }
        for (int i = numRings; i >= 1; i--) {
            pegs.get(0).addFirst(i);
        }
        computeTowerHanoiSteps(numRings, pegs, 0, 1, 2);
    }

    private static void computeTowerHanoiSteps(int numRingsToMove, List<Deque<Integer>> pegs, int fromPeg, int toPeg, int usePeg) {
        if (numRingsToMove > 0) {
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, fromPeg, usePeg, toPeg);
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).remove());
            System.out.println("Moved " + pegs.get(toPeg).peekFirst() + " from peg " + fromPeg + " to peg " + toPeg);
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, usePeg, toPeg, fromPeg);
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> result = new ArrayList<>();
        directedPermutations(0, A, result);
        return result;
    }

    private static void directedPermutations(int i, List<Integer> A, List<List<Integer>> result) {
        if (i == A.size() - 1) {
            result.add(new ArrayList<>(A));
            return;
        }
        for (int j = i; j < A.size(); ++j) {
            Collections.swap(A, i, j);
            directedPermutations(i + 1, A, result);
            Collections.swap(A, i, j);
        }
    }

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        List<List<Integer>> powerSet = new ArrayList<>();
        directedPowerSet(inputSet, 0, new ArrayList<Integer>(), powerSet);
        return powerSet;
    }

    private static void directedPowerSet(List<Integer> inputSet, int toBeSelected, ArrayList<Integer> selectedSoFar, List<List<Integer>> powerSet) {
        if (toBeSelected == inputSet.size()) {
            powerSet.add(new ArrayList<>(selectedSoFar));
            return;
        }
        selectedSoFar.add(inputSet.get(toBeSelected));
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
        selectedSoFar.remove(selectedSoFar.size() - 1);
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
    }


    public static void main(String[] args) {
        computeTowerHanoi(4);
    }
}
