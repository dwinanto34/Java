package standard_classes;

import java.util.Scanner;

public class ScannerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Hello " + name);
    }
}
