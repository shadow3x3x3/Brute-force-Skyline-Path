package ioutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shadow3x3x3 on 2016/7/11. Input Util is for Graph data(Edges) processing.
 */
public class InputUtil {
    private String filePath;

    public InputUtil(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<ArrayList<Float>> readEdge() throws IOException {
        ArrayList<ArrayList<Float>> edges = new ArrayList<>();

        List<String> allLines = Files.readAllLines(Paths.get(filePath));
        edges.addAll(allLines.stream().map(this::getEdge).collect(Collectors.toList()));

        return edges;
    }

    private ArrayList<Float> getEdge(String line) {
        ArrayList<Float> edge = new ArrayList<>();

        String[] readStrings = line.split(" ");
        edge.clear();
        for (String readString : readStrings) {
            edge.add(Float.valueOf(readString));
        }
        return edge;
    }

}
