package gewinnspiel;

import java.util.Random;

/**
 * Model: enthält den Spielzustand und die Spiellogik.
 * Kennt weder View noch Controller.
 */
public class GewinnModel {
    public static final int STARTPUNKTE = 30;
    public static final int SIEGPUNKTE = 100;

    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;
    private Random random = new Random();

    /** Erzeugt ein neues Spiel mit 30 Startpunkten. */
    public GewinnModel() {
        gesamtPunkte = STARTPUNKTE;
    }

    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    public int getComputerZahl() {
        return computerZahl;
    }

    public int getRundenErgebnis() {
        return rundenErgebnis;
    }

    /** Berechnet eine Zufallszahl von 1 bis 9 für den Computer. */
    public void berechneComputerZahl() {
        computerZahl = random.nextInt(9) + 1;
    }
}