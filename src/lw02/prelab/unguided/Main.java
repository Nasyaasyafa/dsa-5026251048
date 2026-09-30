package lw02.prelab.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();
            orders.add(order);
        }

        scanner.close();

        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        for (int i = 0; i < orders.size(); i++) {
            queue.add(orders.get(i));
        }

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];

            String[] foodRecord = null;
            if (!food.equals("-")) {
                for (int i = 0; i < foodStock.size(); i++) {
                    if (foodStock.get(i)[0].equals(food)) {
                        foodRecord = foodStock.get(i);
                    }
                }
            }

            String[] drinkRecord = null;
            if (!drink.equals("-")) {
                for (int i = 0; i < drinkStock.size(); i++) {
                    if (drinkStock.get(i)[0].equals(drink)) {
                        drinkRecord = drinkStock.get(i);
                    }
                }
            }

            boolean foodAvailable = true;
            if (foodRecord != null && Integer.parseInt(foodRecord[1]) <= 0) {
                foodAvailable = false;
            }

            boolean drinkAvailable = true;
            if (drinkRecord != null && Integer.parseInt(drinkRecord[1]) <= 0) {
                drinkAvailable = false;
            }

            if (foodAvailable && drinkAvailable) {
                if (foodRecord != null) {
                    int qty = Integer.parseInt(foodRecord[1]);
                    foodRecord[1] = String.valueOf(qty - 1);
                }
                if (drinkRecord != null) {
                    int qty = Integer.parseInt(drinkRecord[1]);
                    drinkRecord[1] = String.valueOf(qty - 1);
                }
                successfulOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (int i = 0; i < successfulOrders.size(); i++) {
            String[] order = successfulOrders.get(i);
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (int i = 0; i < foodStock.size(); i++) {
            String[] record = foodStock.get(i);
            System.out.println(record[0] + " : " + record[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (int i = 0; i < drinkStock.size(); i++) {
            String[] record = drinkStock.get(i);
            System.out.println(record[0] + " : " + record[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}