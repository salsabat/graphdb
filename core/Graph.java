package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, Node> nodes;
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.nodes = new HashMap<>();
        this.adjacencyList = new HashMap<>();
    }

    public Graph(Map<String, Node> nodes, Map<String, List<Edge>> adjacencyList) {
        this.nodes = nodes;
        this.adjacencyList = new HashMap<>(adjacencyList);
    }

    public boolean addNode(String id, Map<String, String> props) {
        if (nodes.containsKey(id)) {
            return false;
        }
        nodes.put(id, new Node(id, props));
        adjacencyList.put(id, new ArrayList<Edge>());
        return true;
    }

    public void addEdge(String fromId, String toId, String label, Map<String, String> properties) {
        if (!nodes.containsKey(fromId) || !nodes.containsKey(toId)) {
            throw new IllegalArgumentException("The start node and/or the end node does not exist");
        }
        adjacencyList.get(fromId).add(new Edge(fromId, toId, label, properties));
    }

    public Node getNode(String id) {
        return nodes.get(id);
    }

    public List<Edge> getEdges(String nodeId) {
        if (!nodes.containsKey(nodeId)) {
            throw new IllegalArgumentException("That node does not exist");
        }
        return adjacencyList.get(nodeId);
    }

    public List<Node> getNeighbors(String nodeId, String edgeLabel) {
        List<Node> filteredNodes = new ArrayList<Node>();
        for (Edge edge : this.getEdges(nodeId)) {
            if (edge.getLabel().equals(edgeLabel)) {
                filteredNodes.add(nodes.get(edge.getTargetId()));
            }
        }
        return filteredNodes;
    }

}
