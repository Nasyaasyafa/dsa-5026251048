package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("washes.txt");
        if (!file.exists()) {
            file = new File("src/lw01/unguided/washes.txt");
        }

        WashService[] washes;
        int[] unitsPerWash;

        try (Scanner scanner = new Scanner(file)) {
            int total = scanner.nextInt();
            washes = new WashService[total];
            unitsPerWash = new int[total];

            for (int i = 0; i < total; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equals("MOTORCYCLE")) {
                    washes[i] = new MotorcycleWash(id, days);
                } else if (type.equals("CAR")) {
                    washes[i] = new CarWash(id, days);
                } else {
                    throw new IllegalArgumentException("Unknown type: " + type);
                }
                unitsPerWash[i] = units;
            }
        } catch (FileNotFoundException e) {
            System.out.println("washes.txt not found");
            return;
        }

        for (int i = 0; i < washes.length; i++) {
            WashService wash = washes[i];
            int charge = wash.calculateCharge(unitsPerWash[i]);
            System.out.println(wash.getId() + " | " + wash.label() + " | " + charge);
        }
    }
}