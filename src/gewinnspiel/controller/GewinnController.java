package gewinnspiel.controller;

import gewinnspiel.model.GewinnModel;
import gewinnspiel.view.GewinnView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller: nimmt Benutzeraktionen entgegen, ruft das Model auf
 * und aktualisiert die View.
 */
public class GewinnController implements ActionListener {
    private GewinnModel model;
    private GewinnView view;

    public GewinnController() {
        model = new GewinnModel();
        view = new GewinnView(model.getGesamtPunkte());
        view.setController(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (GewinnView.CMD_EINGABE.equals(cmd)) {
            spieleRunde();
        } else if (GewinnView.CMD_NOCHMAL.equals(cmd)) {
            view.zuruecksetzen();
        }
    }

    /** Spielt eine Runde, sofern die Eingabe gültig ist. */
    private void spieleRunde() {
        if (model.hatGewonnen() || model.hatVerloren()) {
            view.zeigeMeldung("Das Spiel ist bereits vorbei.");
            return;
        }
        int zahl = pruefeEingabe(view.getSpielerEingabe());
        if (zahl == 0) {
            view.zeigeMeldung("Bitte eine ganze Zahl von 1 bis 9 eingeben!");
            return;
        }
        model.berechneComputerZahl();
        model.berechneRunde(zahl);
        view.zeigeRunde(model.getComputerZahl(), model.getRundenErgebnis(),
                model.getGesamtPunkte(), model.hatGewonnen(), model.hatVerloren());
    }

    /**
     * Prüft die Eingabe.
     * @return Zahl von 1-9, oder 0 wenn die Eingabe ungültig oder leer ist
     */
    private int pruefeEingabe(String text) {
        if (text == null) {
            return 0;
        }
        try {
            int z = Integer.parseInt(text.trim());
            if (z >= 1 && z <= 9) {
                return z;
            }
            return 0;
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}