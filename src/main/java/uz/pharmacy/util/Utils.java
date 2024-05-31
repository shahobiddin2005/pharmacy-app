package uz.pharmacy.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public interface Utils {
    Set<String> ids = new HashSet<>();
    Scanner scanner = new Scanner(System.in);
    Scanner strScanner = new Scanner(System.in);

    static int getInt(String msg) {
        try {
            System.out.println(msg);
            return scanner.nextInt();

        } catch (Exception e) {
            scanner.nextLine();
            return getInt(msg);
        }
    }

    static String getText(String msg) {
        System.out.println(msg);
        return strScanner.nextLine();
    }
    Random random = new Random();
    static String generateId() {
        String id = String.valueOf(random.nextInt(1000, 9999));
        if (ids.add(id)){
            return id;
        }
        return generateId();
    }
}
