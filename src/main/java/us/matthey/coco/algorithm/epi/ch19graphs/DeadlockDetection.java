package us.matthey.coco.algorithm.epi.ch19graphs;

import java.util.List;

public class DeadlockDetection {

    public static class GraphVertex {
        public static enum Color {WHITE, GREY, BLACK}

        public Color color;
        public List<GraphVertex> edges;
    }

    public static boolean isDeadLocked(List<GraphVertex> G) {
        for (GraphVertex vertex : G) {
            if (vertex.color == GraphVertex.Color.WHITE && hasCycle(vertex, null)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycle(GraphVertex cur, GraphVertex pre) {
        if (cur.color == GraphVertex.Color.GREY) return true;
        cur.color = GraphVertex.Color.GREY;
        for (GraphVertex next : cur.edges) {
            if (next != pre && next.color != GraphVertex.Color.BLACK) {
                if (hasCycle(next, cur)) {
                    return true;
                }
            }
        }
        cur.color = GraphVertex.Color.BLACK;
        return false;
    }
}
