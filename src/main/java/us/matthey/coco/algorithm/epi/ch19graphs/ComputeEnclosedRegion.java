package us.matthey.coco.algorithm.epi.ch19graphs;

import java.util.*;

public class ComputeEnclosedRegion {
    public static void fillSurroundedRegions(List<List<Character>> board) {
        if (board.isEmpty()) {
            return;
        }

        List<List<Boolean>> visited = new ArrayList<>(board.size());
        for (int i = 0; i < board.size(); i++) {
            visited.add(new ArrayList<>(Collections.nCopies(board.get(i).size(), false)));
        }

        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).get(0) == 'W' && !visited.get(i).get(0)) {
                makeBounaryRegion(i, 0, board, visited);
            }
            if (board.get(i).get(board.get(i).size() - 1) == 'W' && !visited.get(i).get(board.get(i).size() - 1)) {
                makeBounaryRegion(i, board.get(i).size() - 1, board, visited);
            }
        }

        for (int j = 0; j < board.get(0).size(); j++) {
            if (board.get(0).get(j) == 'W' && !visited.get(0).get(j)) {
                makeBounaryRegion(0, j, board, visited);
            }
            if (board.get(board.size() - 1).get(j) == 'W' && !visited.get(board.size() - 1).get(j)) {
                makeBounaryRegion(board.size() - 1, j, board, visited);
            }
        }

        for (int i = 1; i < board.size(); i++) {
            for (int j = 1; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == 'W' && !visited.get(i).get(j)) {
                    board.get(i).set(j, 'B');
                }
            }
        }
    }

    private static void makeBounaryRegion(int i, int j, List<List<Character>> board, List<List<Boolean>> visited) {
        Queue<Graphs.Coordinate> q = new LinkedList<>();
        q.add(new Graphs.Coordinate(i, j));
        visited.get(i).set(j, true);
        while (!q.isEmpty()) {
            Graphs.Coordinate curr = q.poll(); //Same as remove()
            final int DIRS[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : DIRS) {
                Graphs.Coordinate next = new Graphs.Coordinate(curr.x + dir[0], curr.y + dir[1]);
                if (next.x >= 0 && next.x < board.size() && next.y >= 0 && next.y < board.get(i).size() && board.get(next.x).get(next.y) != 'W' && !visited.get(next.x).get(next.y)) {
                    visited.get(next.x).set(next.y, true);
                    q.add(next);
                }
            }
        }
    }
}
