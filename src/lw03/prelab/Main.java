package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        problem1();
        problem2();
        problem3();
    }

    private static void problem1() throws Exception {
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            if (line.startsWith("ADD ")) {
                String song = line.substring(4);
                playlist.add(song);
            } else if (line.startsWith("INSERT ")) {
                String rest = line.substring(7);
                int spaceIndex = rest.indexOf(' ');
                int index = Integer.parseInt(rest.substring(0, spaceIndex));
                String song = rest.substring(spaceIndex + 1);
                playlist.add(index, song);
            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring(7);
                playlist.remove(song);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    private static void problem2() throws Exception {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }

            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String participant : participants) {
            System.out.println(number + ". " + participant);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    private static void problem3() throws Exception {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (scanner.hasNext()) {
            String type = scanner.next();
            String product = scanner.next();
            int quantity = scanner.nextInt();

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}