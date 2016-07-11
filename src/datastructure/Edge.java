package datastructure;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by shadow3x3x3 on 2016/7/11. Record Edge
 */
public class Edge {
    private int id;
    private int src;
    private int dst;
    private ArrayList<Float> attrs;

    public Edge() {
        this.id = -1;
    }

    public Edge(int src, int dst) {
        this.src = src;
        this.dst = dst;
    }

    public Edge(int id, int src, int dst, ArrayList<Float> attrs) {
        isSameNodes(src, dst);

        this.id = id;
        this.src = src;
        this.dst = dst;
        this.attrs = attrs;
    }

    public Edge(ArrayList<Float> readEdge) {
        isSameNodes(readEdge.get(1), readEdge.get(2));
        this.attrs = new ArrayList<>();

        this.id = readEdge.get(0).intValue();
        this.src = readEdge.get(1).intValue();
        this.dst = readEdge.get(2).intValue();
        for (int i = 3; i < readEdge.size(); i++) {
            this.attrs.add(readEdge.get(i));
        }
    }


    public int id() {
        return this.id;
    }

    public int dst() {
        return this.dst;
    }

    public int src() {
        return this.src;
    }

    public ArrayList<Float> attrs() {
        return this.attrs;
    }

    private void isSameNodes(float src, float dst) {
        if (src == dst) {
            throw new InvalidParameterException("src and dst are the Same!");
        }
    }
}
