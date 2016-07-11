package datastructure;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;

import algorithm.Skyline;

/**
 * Created by shadow3x3x3 on 2016/7/11. Record all Edges and Nodes and supply some method for Graph
 */
public class Graph {
    private static final int NOT_EXIST = -1;

    private ArrayList<Integer> nodes;
    private ArrayList<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(int node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        if (!isContainEdge(edge)) {
            edges.add(edge);
            if (isContainNode(edge.src())) {
                addNode(edge.src());
            }
            if (isContainNode(edge.dst())) {
                addNode(edge.dst());
            }
        } else {
            System.out.println("The same Edge(" + edge.src() + " - " + edge.dst() +
                    ") has been added. But fine. It's just ignore. =)");
        }
    }

    public void showAllEdges() {
        System.out.println("Edges in Graph:");
        for (Edge edge : edges) {
            System.out.println("Edge ID: " + edge.id() + " -> Connect: " +
                    edge.src() + " - " + edge.dst() + "  attrs: " + edge.attrs());
        }
    }

    public HashMap<ArrayList, ArrayList> BruteForce(int src, int dst) {
        HashMap<ArrayList, ArrayList> skylinePathWithAttrs = new HashMap<>();
        ArrayList<ArrayList<Float>> pathsAttrs = new ArrayList<>();

        // Find All Path between src and dst
        ArrayList<ArrayList<Integer>> paths =
                enumerate(new Stack<>(), new HashSet<>(), src, dst, new ArrayList<>());

        // calculate paths' attrs
        pathsAttrs.addAll(paths.stream().map(this::getAttrs).collect(Collectors.toList()));

        // Skyline Query with paths' attrs
        ArrayList<Integer> skylineIndex = Skyline.skylineQuery(pathsAttrs);

        // Index to path
        for (int index : skylineIndex) {
            skylinePathWithAttrs.put(paths.get(index), pathsAttrs.get(index));
        }

        return skylinePathWithAttrs;
    }

    private ArrayList<ArrayList<Integer>> enumerate(Stack<Integer> path, HashSet<Integer> onPath,
                                                    int src, int dst, ArrayList<ArrayList<Integer>> paths) {
        path.push(src);
        onPath.add(src);
        if (src == dst) {
            paths.add(new ArrayList<>(path));
        } else {
            getNeighbors(src).stream().filter(n -> !onPath.contains(n)).forEach(n -> {
                enumerate(path, onPath, n, dst, paths);
            });
        }

        path.pop();
        onPath.remove(src);
        return paths;
    }

    private ArrayList<Integer> getNeighbors(int node) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            int tempNode = checkNeighbor(edge, node);
            if (tempNode != NOT_EXIST) {
                neighbors.add(tempNode);
            }
        }
        return neighbors;
    }

    private int checkNeighbor(Edge edge, int node) {
        if (node == edge.src()) {
            return edge.dst();
        } else if (node == edge.dst()) {
            return edge.src();
        }
        return NOT_EXIST;
    }

    public ArrayList<Float> getAttrs(ArrayList<Integer> path) {
        ArrayList<Float> attrs = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {

            attrs = i == 0 ? getAttrs(path.get(i), path.get(i + 1)) :
                    aggregate(attrs, getAttrs(path.get(i), path.get(i + 1)));
        }

        return attrs;
    }

    private ArrayList<Float> getAttrs(int src, int dst) {
        for (Edge edge : edges) {
            if (isSameEdge(edge, new Edge(src, dst))) {
                return edge.attrs();
            }
        }
        throw new InvalidParameterException(
                "no edge between" + src + "and" + dst);
    }

    private ArrayList<Float> aggregate(ArrayList<Float> path1, ArrayList<Float> path2) {
        ArrayList<Float> aggregateAttrs = new ArrayList<>();

        if (path1.size() != path2.size()) {
            throw new InvalidParameterException("path1 and path2's size are not the Same!");
        }

        for (int i = 0; i < path1.size(); i++) {
            aggregateAttrs.add(path1.get(i) + path2.get(i));
        }

        return aggregateAttrs;
    }

    private boolean isContainNode(int confirmNode) {
        for (int node : nodes) {
            if (node == confirmNode) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainEdge(Edge confirmEdge) {
        for (Edge edge : edges)
            if (isSameEdge(edge, confirmEdge)) {
                return true;
            }
        return false;
    }

    private boolean isSameEdge(Edge edge1, Edge edge2) {
        if ((edge1.src() == edge2.src()) && (edge1.dst() == edge2.dst())) {
            return true;
        } else if ((edge1.dst() == edge2.src()) && (edge1.src() == edge2.dst())) {
            return true;
        }
        return false;
    }


}
