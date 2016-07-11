package algorithm;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Objects;

import datastructure.SkylineDefinition;

/**
 * Created by ShengWei on 2016/7/11. For Skyline calculating. (Path)
 */
public class Skyline {

    public Skyline() {
    }

    public static ArrayList<Integer> skylineQuery(ArrayList<ArrayList<Float>> pathsAttrs) {
        ArrayList<Integer> skylinePathsIndex = new ArrayList<>();

        for (int i = 0; i < pathsAttrs.size(); i++) {
            boolean beDominateFlag = false;
            compare:
            for (int j = 0; j < pathsAttrs.size(); j++) {
                if (i != j) {
                    switch (dominate(pathsAttrs.get(i), pathsAttrs.get(j))) {
                        case DOMINATE:
                            // do nothing
                            break;
                        case BE_DOMINATED:
                            beDominateFlag = true;
                            break compare;
                        case NON_DOMINATE:
                            // do nothing
                            break;
                    }
                }
            }

            if (!beDominateFlag) {
                skylinePathsIndex.add(i);
            }
        }
        return skylinePathsIndex;
    }

    public static SkylineDefinition dominate(ArrayList<Float> path1Attrs, ArrayList<Float> path2Attrs) {
        int flag = 0;
        int checkFlag = path1Attrs.size();

        if (path1Attrs.size() != path2Attrs.size()) {
            throw new InvalidParameterException("path1 and path2's size are not the Same!");
        } else {
            for (int i = 0; i < path1Attrs.size(); i++) {
                flag = (path1Attrs.get(i) > path2Attrs.get(i)) ? flag + 1 : flag;
                flag = (path1Attrs.get(i) < path2Attrs.get(i)) ? flag - 1 : flag;
                checkFlag = (Objects.equals(path1Attrs.get(i), path2Attrs.get(i))) ? checkFlag - 1 : checkFlag;
            }
        }

        if (flag == checkFlag) {
            return SkylineDefinition.BE_DOMINATED;
        } else if (flag == 0 - checkFlag) {
            return SkylineDefinition.DOMINATE;
        }
        return SkylineDefinition.NON_DOMINATE;
    }


}
