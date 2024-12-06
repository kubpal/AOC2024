package aoc1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class resolution {
    static ArrayList<Integer> first = new ArrayList<>();
    static ArrayList<Integer> second = new ArrayList<>();
    static File data = new File("src/aoc1/data");

    public static void main(String[] args) {
        readData(data);
        segregate(first);
        segregate(second);

        System.out.println("First part:" + calculateDistance(first, second));
        System.out.println("Second part:" + calculateSimilarityScore(first, second));
    }

    public static void readData(File data) {
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ints = line.split(" {3}");
                int one = Integer.parseInt(ints[0]);
                first.add(one);
                int two = Integer.parseInt(ints[1]);
                second.add(two);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void segregate(ArrayList<Integer> list) {
        list.sort(null);
    }

    public static int calculateDistance(ArrayList<Integer> first, ArrayList<Integer> second) {
        int totalDistance = 0;
        if (first.size() == second.size()) {
            for (int i = 0; i < first.size(); i++) {
                int difference = Math.abs(first.get(i) - second.get(i));
                totalDistance += difference;
            }
            return totalDistance;
        } else {
            System.out.println("Data reading failed!");
            return 0;
        }
    }

    public static int calculateSimilarityScore (ArrayList<Integer> first, ArrayList<Integer> second){
        int similarityScore = 0;
        for (Integer i : first) {
            int temporaryScore = i * calculateOccurrences(i,second);
            similarityScore += temporaryScore;
        }
        return similarityScore;
    }

    public static int calculateOccurrences(int i, ArrayList<Integer> list){
        return Collections.frequency(list, i);
    }
}
