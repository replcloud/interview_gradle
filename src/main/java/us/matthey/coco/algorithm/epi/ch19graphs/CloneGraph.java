package us.matthey.coco.algorithm.epi.ch19graphs;

import java.util.*;

public class CloneGraph {
    public static class GraphVertex {
        public int label;
        public List<CloneGraph.GraphVertex> edges;

        public GraphVertex(int label) {
            this.label = label;
            edges = new ArrayList<>();
        }
    }
    public static GraphVertex cloneGraph(GraphVertex g) {
        if (g == null) return null;

        Map<GraphVertex, GraphVertex> vertexMap = new HashMap<>();
        Queue<GraphVertex> q = new LinkedList<>();
        q.add(g);
        vertexMap.put(g, new GraphVertex(g.label));
        while (!q.isEmpty()) {
            GraphVertex v = q.remove();
            for (GraphVertex e : v.edges) {
                if (!vertexMap.containsKey(e)) {
                    vertexMap.put(e, new GraphVertex(g.label));
                    q.add(e);
                }
                vertexMap.get(v).edges.add(vertexMap.get(e));
            }
        }
        return vertexMap.get(g);
    }
}
