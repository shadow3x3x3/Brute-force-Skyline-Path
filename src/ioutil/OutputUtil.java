package ioutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shadow3x3x3 on 2016/7/12.
 * Output Util is for creating skyline path result txt.
 */
public class OutputUtil {
    private static final String filePath = "result/";
    private String fileName;

    public OutputUtil() {
        this.fileName = LocalDate.now().toString() + ".txt";
    }

    public OutputUtil(String fileName) {
        this.fileName = LocalDate.now().toString()
                + "_" + fileName + ".txt";
    }

    public void writeSkylinePathToResult(HashMap<ArrayList, ArrayList> skylinePathWithAttrs) throws IOException {
        File resultFile = new File(filePath + fileName);
        FileWriter fileWriter = new FileWriter(resultFile, false);

        for (ArrayList path : skylinePathWithAttrs.keySet()) {
            fileWriter.write(path.toString() + " => "
                    + skylinePathWithAttrs.get(path) + System.getProperty("line.separator"));
        }


        fileWriter.close();
    }
}
