package lw04.unguided;

import java.util.Scanner;

public class Main {

    static void bubbleSort(int[] arr, int fromPass, int toPass) {
        int n = arr.length;
        for (int i = fromPass; i < toPass; i++) {
            for (int j = 0; j <= n - 2 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        bubbleSort(parcels, 0, 2);

        int[] batteryDied = new int[n];
        for (int i = 0; i < n; i++) {
            batteryDied[i] = parcels[i];
        }
        bubbleSort(parcels, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(batteryDied);
        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println("==== Search Results ====");
        for (int t = 0; t < m; t++) {
            int target = targets[t];
            int low = 0;
            int high = n - 1;
            int steps = 0;
            int foundIndex = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == target) {
                    foundIndex = mid;
                    break;
                } else if (parcels[mid] > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (foundIndex >= 0) {
                System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
            } else {
                System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}