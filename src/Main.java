import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import datastructure.Edge;
import datastructure.Graph;
import ioutil.InputUtil;
import ioutil.OutputUtil;

/**
 * Created by shadow3x3x3 on 2016/7/11. Implement Brute Force for Skyline Path Query in Graph
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final String FILE_PATH = "data/test-edge.txt";
//        final String FILE_PATH = "data/2_goEdge.txt";
        final int SRC = 0;
        final int DST = 5;

        Graph graph = new Graph();

        InputUtil inputUtil = new InputUtil(FILE_PATH);
        ArrayList<ArrayList<Float>> edges = inputUtil.readEdge();
        for (ArrayList<Float> edge : edges) {
            graph.addEdge(new Edge(edge));
        }

//        graph.showAllEdges();
        HashMap<ArrayList, ArrayList> skylinePath = graph.BruteForce(SRC, DST);
        System.out.println(skylinePath);

        OutputUtil outputUtil = new OutputUtil(Integer.toString(SRC) + "to" + Integer.toString(DST));
        outputUtil.writeSkylinePathToResult(skylinePath);
    }
}
