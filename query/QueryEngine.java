package query;

import core.Graph;
import core.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QueryEngine {
    private final Graph graph;
    private final Traversal traversal;

    public QueryEngine(Graph graph) {
        this.graph = graph;
        this.traversal = new Traversal(graph);
    }

    public boolean runCLI(Scanner scan) {
        System.out.print("> ");
        String[] input = scan.nextLine().trim().split("\\s+");
        switch (input[0]) {
            case "ADD":
                try {
                    if (input[1].equals("NODE")) {
                        String id = input[2];
                        Map<String, String> props = processProps(input, 3);
                        graph.addNode(id, props);
                    } else if (input[1].equals("EDGE")) {
                        String fromId = input[2];
                        String toId = input[3];
                        String label = input[4];
                        Map<String, String> props = processProps(input, 5);
                        graph.addEdge(fromId, toId, label, props);
                    } else {
                        throw new IllegalArgumentException("Not a valid command");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Not a valid command");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error " + e.getMessage());
                }
                break;
            case "BFS":
                try {
                    String startNodeId = input[1];
                    int depthLimit = Integer.parseInt(input[2]);
                    System.out.println(traversal.bfs(startNodeId, depthLimit));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error: Not a valid command");
                }
                break;
            case "GET":
                try {
                    String nodeId = input[2];
                    if (input[1].equals("NODE")) {
                        Node node = graph.getNode(nodeId);
                        if (node == null) {
                            System.out.println("Error: Node not found");
                        } else {
                            System.out.println(nodeId + ": " + node.getProps());
                        }
                    } else if (input[1].equals("NEIGHBORS")) {
                        String label = input[3];
                        List<Node> nodes = graph.getNeighbors(nodeId, label);
                        List<String> nodeIds = new ArrayList<>();
                        for (Node node : nodes) {
                            nodeIds.add(node.getId());
                        }
                        System.out.println(nodeIds);
                    } else {
                        throw new IllegalArgumentException("Not a valid command");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Not a valid command");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "EXIT":
                return false;
            default:
                System.out.println("Error: Unrecognized command");
                break;
        }
        return true;
    }

    private Map<String, String> processProps(String[] arr, int idx) {
        Map<String, String> props = new HashMap<>();
        for (int i = idx; i < arr.length; i++) {
            int eqIdx = arr[i].indexOf('=');
            if (eqIdx == -1) {
                throw new IndexOutOfBoundsException();
            }
            props.put(arr[i].substring(0, eqIdx), arr[i].substring(eqIdx + 1));
        }
        return props;
    }

    // public static void main(String[] args) {
    // QueryEngine e = new QueryEngine(new Graph());
    // Scanner scan = new Scanner(System.in);
    // while (e.runCLI(scan)) {

    // }
    // scan.close();
    // System.out.println(e.graph.getEdges("a").get(0).getTargetId());
    // }
}
