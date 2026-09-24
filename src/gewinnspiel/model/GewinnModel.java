package gewinnspiel.model;

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

    /**
     * Wertet eine Runde aus: +20 bei Treffer, +5 bei Abstand 1, sonst -10.
     * @param spielerZahl getippte Zahl (1-9)
     */
    public void berechneRunde(int spielerZahl) {
        if (spielerZahl < 1 || spielerZahl > 9) {
            throw new IllegalArgumentException("Zahl muss zwischen 1 und 9 liegen");
        }
        this.spielerZahl = spielerZahl;
        int abstand = Math.abs(spielerZahl - computerZahl);
        if (abstand == 0) {
            rundenErgebnis = 20;
        } else if (abstand == 1) {
            rundenErgebnis = 5;
        } else {
            rundenErgebnis = -10;
        }
        gesamtPunkte += rundenErgebnis;
    }

    /** @return true, wenn 100 oder mehr Punkte erreicht sind */
    public boolean hatGewonnen() {
        return gesamtPunkte >= SIEGPUNKTE;
    }

    /** @return true, wenn 0 oder weniger Punkte erreicht sind */
    public boolean hatVerloren() {
        return gesamtPunkte <= 0;
    }
}