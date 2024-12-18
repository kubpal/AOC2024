package aoc5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class resolution5 {
    static File data = new File("src/aoc5/data");
    static Map<Integer, Set<Integer>> rules = new HashMap<>();
    static ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
    static ArrayList<Integer> incorrectUpdates = new ArrayList<>();


    public static void main(String[] args) {
        readData(data);
        System.out.println("First part result: " + sumCorrectUpdatesMiddlePages());
        System.out.println("Second part result: " + sumFixedUpdates());
    }

    public static void readData(File data) {
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line;
            boolean secondPart = false;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    secondPart = true;
                    continue;
                }
                if (!secondPart) {
                    String[] ruleElements = line.split("\\|");
                    if (rules.containsKey(Integer.parseInt(ruleElements[0]))) {
                        rules.get(Integer.parseInt(ruleElements[0])).add(Integer.parseInt(ruleElements[1]));
                    } else {
                        rules.put(Integer.parseInt(ruleElements[0]), new HashSet<>(List.of(Integer.parseInt(ruleElements[1]))));
                    }
                }
                if (secondPart) {
                    String[] updateElements = line.split(",");
                    updates.add(new ArrayList<>());
                    for (String s : updateElements) {
                        updates.get(counter).add(Integer.parseInt(s));
                    }
                    counter++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int sumCorrectUpdatesMiddlePages() {
        int counter = 0;
        for (ArrayList<Integer> update : updates) {
            if (checkOneUpdate(update)) {
                counter += update.get((update.size() - 1) / 2);
            } else {
                incorrectUpdates.add(updates.indexOf(update));
            }
        }
        return counter;
    }

    public static boolean checkOneUpdate(ArrayList<Integer> update) {
        boolean correct = true;
        for (int j = 1; j < update.size(); j++) {
            int pageNumber = update.get(j);
            if (rules.containsKey(pageNumber)) {
                Set<Integer> specificPageRules = rules.get(pageNumber);
                for (Integer pageRule : specificPageRules) {
                    if (update.contains(pageRule)) {
                        int indexOfRulePage = update.indexOf(pageRule);
                        if (indexOfRulePage < j) {
                            correct = false;
                            break;
                        }
                    }
                }
            }
        }
        return correct;
    }

    public static int sumFixedUpdates() {
        int sum = 0;
        for (Integer incorrectUpdateIndex : incorrectUpdates) {

            while (!checkOneUpdate(updates.get(incorrectUpdateIndex))) {
                fixUpdate(incorrectUpdateIndex);
            }
            ArrayList<Integer> update = updates.get(incorrectUpdateIndex);
            sum += update.get((update.size() - 1) / 2);

        }
        return sum;
    }

    public static void fixUpdate(int index) {
        ArrayList<Integer> update = updates.get(index);
        for (int i = 1; i < update.size(); i++) {
            int pageNumber = update.get(i);
            if (rules.containsKey(pageNumber)) {
                Set<Integer> specificPageRules = rules.get(pageNumber);
                for (Integer pageRule : specificPageRules) {
                    if (update.contains(pageRule)) {
                        int indexOfRulePage = update.indexOf(pageRule);
                        if (indexOfRulePage < i) {
                            updates.get(index).remove(indexOfRulePage);
                            updates.get(index).add(i, pageRule);
                            break;
                        }
                    }
                }
            }
        }
    }
}
