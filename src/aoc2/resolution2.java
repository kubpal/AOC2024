package aoc2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class resolution2 {
    static File data = new File("src/aoc2/data");
    static int safeCounter = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ints = line.split(" ");
                ArrayList<Integer> diffs = new ArrayList<>();
                for (int i = 0; i < ints.length - 1; i++) {
                    int diff = Integer.parseInt(ints[i]) - Integer.parseInt(ints[i + 1]);
                    diffs.add(diff);
                }
                if (checkSafety(diffs)) {
                    safeCounter++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(safeCounter);
    }

    public static boolean checkSafety(ArrayList<Integer> diffs) {
        boolean safety = true;
        if ((diffs.get(0) < 0)) {
            for (Integer d : diffs) {
                if (d >= 0 || d < -3) {
                    safety = false;
                    break;
                }
            }
        } else if (diffs.get(0) > 0) {
            for (Integer d : diffs) {
                if (d <= 0 || d > 3) {
                    safety = false;
                    break;
                }
            }
        } else {
            safety = false;
        }
        return safety;
    }


}
