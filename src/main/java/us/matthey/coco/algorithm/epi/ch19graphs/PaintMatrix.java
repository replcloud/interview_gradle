package us.matthey.coco.algorithm.epi.ch19graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PaintMatrix {
    public static void flipColorBFS(List<List<Boolean>> A, int x, int y) {
        final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean color = A.get(x).get(y);

        Queue<Graphs.Coordinate> q = new LinkedList<>();
        A.get(x).set(y, !color);
        q.add(new Graphs.Coordinate(x, y));

        while (!q.isEmpty()) {
            Graphs.Coordinate curr = q.element();
            for (int[] dir : DIRS) {
                Graphs.Coordinate next = new Graphs.Coordinate(curr.x + dir[0], curr.y + dir[1]);
                if (next.x >= 0 && next.x < A.size() && next.y >= 0 && next.y < A.get(next.x).size() && A.get(next.x).get(next.y) == color) {
                    A.get(next.x).set(next.y, !color);
                    q.add(next);
                }
                q.remove();
            }
        }
    }

    public static void flipColorDFS(List<List<Boolean>> A, int x, int y) {
        final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean color = A.get(x).get(y);

        A.get(x).set(y, !color);
        for (int[] dir : DIRS) {
            Graphs.Coordinate next = new Graphs.Coordinate(x + dir[0], y + dir[1]);
            if (next.x >=0 && next.x < A.size() && next.y >=0 && next.y < A.get(x).size() && A.get(next.x).get(next.y) == color) {
                flipColorDFS(A, next.x, next.y);
            }
        }
    }
}
