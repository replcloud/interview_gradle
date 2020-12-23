package us.matthey.coco.algorithm.epi.ch19graphs;

import java.util.*;

public class Graphs {
    public static class MatchResult {
        public String winningTeam;
        public String losingTeam;

        public MatchResult(String winningTeam, String losingTeam) {
            this.winningTeam = winningTeam;
            this.losingTeam = losingTeam;
        }
    }

    public static boolean canTeamABeatTeamB(List<MatchResult> matches, String teamA, String teamB) {
        Set<String> visited = new HashSet<>();
        return isReachableDFS(buildGraph(matches), teamA, teamB, visited);
    }

    public static Map<String, Set<String>> buildGraph(List<MatchResult> matches) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (MatchResult match : matches) {
            Set<String> edges = graph.get(match.winningTeam);
            if (edges == null) {
                edges = new HashSet<>();
                graph.put(match.winningTeam, edges);
            }
            edges.add(match.losingTeam);
        }
        return graph;
    }

    public static boolean isReachableDFS(Map<String, Set<String>> graph, String curr, String dest, Set<String> visited) {
        if (curr.equals(dest)) {
            return true;
        } else if (visited.contains(curr) || graph.get(curr) == null) {
            return false;
        }
        visited.add(curr);
        for (String team : graph.get(curr)) {
            if (isReachableDFS(graph, team, dest, visited)) {
                return true;
            }
        }
        return false;
    }

    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            if (this.x != that.x || this.y != that.y) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public static enum Color {WHITE, BLACK};

        public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate s, Coordinate e) {
            List<Coordinate> path = new ArrayList<>();
            maze.get(s.x).set(s.y, Color.BLACK);
            path.add(s);
            if (!searchMazeHelper(maze, s, e, path)) {
                path.remove(path.size() - 1);
            }
            return path;
        }

        private static boolean searchMazeHelper(List<List<Color>> maze, Coordinate cur, Coordinate e, List<Coordinate> path) {
            if (cur.equals(e)) return true;
            final int[][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] s : SHIFT) {
                Coordinate next = new Coordinate(cur.x + s[0], cur.y + s[1]);
                if (isFeasible(next, maze)) {
                    maze.get(next.x).set(next.y, Color.BLACK);
                    path.add(next);
                    if (searchMazeHelper(maze, next, e, path)) {
                        return true;
                    }
                }
                path.remove(path.size() - 1);
            }
            return false;
        }

        private static boolean isFeasible(Coordinate cur, List<List<Color>> maze) {
            return cur.x >= 0 && cur.x < maze.size() && cur.y >= 0 && cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == Color.WHITE;
        }
    }


    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        System.out.println(m.get('a'));
    }
}