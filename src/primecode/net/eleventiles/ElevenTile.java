package primecode.net.eleventiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nagendralimbu
 * @author A.M.King - referenced Iterative Deepening lecture slides.
 */
public class ElevenTile {

    private final String startString;
    private final String destString;
    private final String fileName;

    /**
     * Constructor that creates a puzzle with start and end configuration
     *
     * @param states
     */
    public ElevenTile(String[] states) {
        assert states.length == 2;
        startString = states[0];
        destString = states[1];
        fileName = startString + destString + ".txt";
    }

    /**
     * Method that initiates the search for destination configuration.
     */
    public void configureTiles() {
        LinkedList<String> allConfig = iterativeDeepening(startString, destString);
        writeToFile(allConfig);
    }

    /**
     * Method that explores different configurations to get 
     * to the destination until the depth is greater than 0.
     * @param startString
     * @param destString
     * @param depth
     * @return
     */
    private LinkedList<String> depthFirst(String startString, String destString, int depth) {
        if (startString.equals(destString)) { // destination configuration has been reachd
            LinkedList<String> route = new LinkedList<>();
            route.add(destString);
            return route;
        } else if (depth == 0) {
            return null;
        } else {
            LinkedList<String> nextConfigs = nextConfigs(startString);
            for (String next : nextConfigs) {
                LinkedList<String> route = depthFirst(next, destString, depth - 1);
                if (route != null) {
                    route.addFirst(startString);
                    return route;
                }
            }
            return null;
        }
    }

    /**
     * Method that finds out all the possible configurations 
     * that can be reached from the blank tile in a single move
     * @param string
     * @return
     */
    public LinkedList<String> nextConfigs(String string) {
        int blank = string.indexOf('_');
        int[] route = new int[4];
        route[0] = checkMove(blank, -1, 0); //can move left
        route[1] = blank - 4; // can move up
        route[2] = checkMove(blank, 1, 1); // can move right 
        route[3] = blank + 4; // can move down

        LinkedList<String> destPath = new LinkedList<>();
        for (int i : route) { //for each legal move create a new word and return it
            if (i >= 0 && i < 16 && (string.charAt(i) != '+')) { // check the move is valid
                StringBuilder sb = new StringBuilder(string);
                sb.setCharAt(i, '_'); // swap the characters
                sb.setCharAt(blank, string.charAt(i));
                destPath.add(sb.toString());
            }
        }
        return destPath;
    }

    /**
     * Method that searches for the destination configuration till it is found.
     * @param start
     * @param destination
     * @return
     */
    private LinkedList<String> iterativeDeepening(String start, String destination) {
        for (int maxDepth = 1;; maxDepth++) { // iterate it until destination config has been found
            System.out.println("DEPTH: " + maxDepth);
            LinkedList<String> config = depthFirst(start, destination, maxDepth);
            if (config != null) {
                return config;
            }
        }
    }

    /**
     * Method that checks if the right and left moves are valid.
     * @param start
     * @param move
     * @param flag
     * @return
     */
    private int checkMove(int start, int move, int flag) {
        if (flag == 0 && (start + 1 % 4 != 1)) { //check move left
            //cannot move left 
            return start + move;
        } else if (flag == 1 && (start + 1) % 4 != 0) { // check move right
            return start + move;
        }
        return -1;
    }

    /**
     * Method that prints all the path from start to end configuration.
     * @param allConfig
     */
    private void print(LinkedList<String> allConfig) {
        List<List<String>> allPath = new LinkedList<>();
        for (String s : allConfig) {
            allPath.add(getString(s));
        }

        for (int i = 0; i < 4; i++) {
            for (List<String> list : allPath) {
                System.out.print(list.get(i) + " ");
            }
            System.out.print(System.getProperty("line.separator"));
        }
    }

    /**
     * Method that writes all the path from start to end configuration to a text file.
     * @param allConfig
     */
    private void writeToFile(LinkedList<String> allConfig) {
        List<List<String>> allPath = new LinkedList<>();
        File file = new File(fileName);
        try {
            allConfig.stream().forEach((s) -> {
                allPath.add(getString(s));
            });
            
            try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < 4; i++) {
                    for (Iterator<List<String>> it = allPath.iterator(); it.hasNext();) {
                        List<String> list = it.next();
                        buffer.write(list.get(i) + " ");
                    }
                    buffer.newLine();
                }
            }
        } catch (IOException E) {
            //
        }

    }

    /**
     * Method tha break the string into 4 seperate entry into an arraylist
     * and returns it.
     * @param string
     * @return
     */
    private List<String> getString(String string) {
        List<String> list = new ArrayList<>();
        int count = 0;
        String s = "";
        for (char c : string.toCharArray()) {
            count++;
            s += c;
            if (count % 4 < 1) {
                list.add(s);
                s = "";
            }
        }
        return list;
    }

}
