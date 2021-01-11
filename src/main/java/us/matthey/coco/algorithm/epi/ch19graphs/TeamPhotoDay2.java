package us.matthey.coco.algorithm.epi.ch19graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TeamPhotoDay2 {
    public static class GraphVertex {
        public List<GraphVertex> edges = new ArrayList<>();
        public int maxDistance = 1;
        public boolean visited = false;
    }

    public static int findLargestNumberTeams(List<GraphVertex> G) {
        Deque<GraphVertex> orderedVertices = buildToplogicalOrdering(G);
        return findLongestPath(orderedVertices);
    }

    public static Deque<GraphVertex> buildToplogicalOrdering(List<GraphVertex> G) {
        Deque<GraphVertex> orderedVertices = new LinkedList<>();
        for (GraphVertex g : G) {
            if (!g.visited) {
                DFS(g, orderedVertices);
            }
        }
        return orderedVertices;
    }

    public static int findLongestPath(Deque<GraphVertex> orderedVertices) {
        int maxDistance = 0;
        while (!orderedVertices.isEmpty()) {
            GraphVertex u = orderedVertices.peekFirst();
            maxDistance = Math.max(maxDistance, u.maxDistance);
            for (GraphVertex v : u.edges) {
                v.maxDistance = Math.max(v.maxDistance, u.maxDistance + 1);
            }
            orderedVertices.removeFirst()
        }
        return maxDistance;
    }

    public static void DFS(GraphVertex cur, Deque<GraphVertex> orderedVertices) {
        cur.visited = true;
        for (GraphVertex next : cur.edges) {
            if (!next.visited) {
                DFS(next, orderedVertices);
            }
        }
        orderedVertices.addFirst(cur);
    }
}
