package gewinnspiel;

import gewinnspiel.view.GewinnView;

/**
 * Startklasse des Zahlen-Gewinnspiels.
 */
public class Main {
    public static void main(String[] args) {
        new GewinnView(30).setVisible(true);
    }
}