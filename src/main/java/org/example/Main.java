package org.example;
import org.example.kiosk.Kiosk;

/**
 * Main.java
 * Entry point of the program
 * Creates a Kiosk instance and starts the program
 */

public class Main {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();
        kiosk.start();
    }
}

