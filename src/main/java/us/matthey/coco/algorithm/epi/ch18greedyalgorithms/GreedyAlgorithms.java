package us.matthey.coco.algorithm.epi.ch18greedyalgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyAlgorithms {
    public static int changeMaking(int cents) {
        final int[] COINS = {100, 50, 25, 10, 5, 1};
        int numCoins = 0;
        for (int i = 0; i < COINS.length; i++) {
            numCoins += cents / COINS[i];
            cents %= COINS[i];
        }
        return numCoins;
    }

    private static class PairedTasks {
        public Integer task1;
        public Integer task2;

        public PairedTasks(Integer task1, Integer task2) {
            this.task1 = task1;
            this.task2 = task2;
        }
    }

    public static List<PairedTasks> optimumTaskAssignment(List<Integer> taskDurations) {
        Collections.sort(taskDurations);
        List<PairedTasks> optimumAssignments = new ArrayList<>();
        for (int i = 0, j = taskDurations.size() - 1; i < j; i++, j++) {
            optimumAssignments.add(new PairedTasks(taskDurations.get(i), taskDurations.get(j)));
        }
        return optimumAssignments;
    }
}
