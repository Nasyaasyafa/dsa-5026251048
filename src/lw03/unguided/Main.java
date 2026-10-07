package lw03.unguided;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Set<String> terdaftar = new HashSet<>();
 
        while (input.hasNext()) {
            String id = input.next();
            terdaftar.add(id); 
        }
        input.close();
 
        Scanner inputCek = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        Set<String> sudahCheckIn = new HashSet<>();
        int ditolak = 0;
 
        System.out.println("===== Event Check-In Results =====");
        while (inputCek.hasNext()) {
            String id = inputCek.next();
 
            if (terdaftar.contains(id) == false) {
                System.out.println(id + ": Rejected (not registered)");
                ditolak++;
            } else if (sudahCheckIn.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                ditolak++;
            } else {
                sudahCheckIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        inputCek.close();
 
        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + terdaftar.size());
        System.out.println("Successful check-ins: " + sudahCheckIn.size());
        System.out.println("Absent students: " + (terdaftar.size() - sudahCheckIn.size()));
        System.out.println("Rejected attempts: " + ditolak);
    }
}