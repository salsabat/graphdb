package query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import core.Graph;
import core.Node;
import core.Edge;

public class Traversal {

    private class Pair<T1, T2> {
        T1 fst;
        T2 snd;

        Pair(T1 fst, T2 snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }

    private final Graph graph;

    public Traversal(Graph graph) {
        this.graph = graph;
    }

    public List<String> bfs(String startNodeId, int depthLimit) {
        if (graph.getNode(startNodeId) == null) {
            throw new IllegalArgumentException("The start node does not exist in the graph");
        }
        if (depthLimit < 0) {
            depthLimit = 0;
        }

        Queue<Pair<String, Integer>> frontier = new LinkedList<>();
        frontier.add(new Pair<String, Integer>(startNodeId, 0));

        Set<String> visited = new HashSet<>();

        while (!frontier.isEmpty()) {
            Pair<String, Integer> currElt = frontier.poll();
            if (currElt.snd > depthLimit) {
                continue;
            }
            visited.add(currElt.fst);
            for (Edge edge : graph.getEdges(currElt.fst)) {
                if (!visited.contains(edge.getTargetId())) {
                    frontier.add(new Pair<>(edge.getTargetId(), currElt.snd + 1));
                }
            }
        }

        return List.copyOf(visited);
    }
}
