package gewinnspiel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View: stellt die Oberfläche dar. Enthält keine Spiellogik.
 */
public class GewinnView extends JFrame {
    public static final String CMD_EINGABE = "eingabe";
    public static final String CMD_NOCHMAL = "nochmal";
    private static final String START_TEXT = "Tippe eine Zahl von 1 bis 9";

    private JLabel lblRunde = erzeugeLabel();
    private JLabel lblGesamt = erzeugeLabel();
    private JTextField txtSpieler = new JTextField();
    private JTextField txtComputer = new JTextField();
    private JButton btnNochmal = new JButton("Noch einmal!");

    public GewinnView(int startPunkte) {
        super("Zahlen-Gewinnspiel (v1.0)");
        lblRunde.setText(START_TEXT);
        lblGesamt.setText("Gesamtpunkte: " + startPunkte);
        txtComputer.setEditable(false);
        formatiereTextfeld(txtSpieler);
        formatiereTextfeld(txtComputer);

        // oben: Überschriften und Anzeige-Labels
        JPanel oben = new JPanel(new GridLayout(3, 2, 10, 5));
        oben.add(erzeugeUeberschrift("Rundenergebnis:"));
        oben.add(erzeugeUeberschrift("Gesamtpunkte:"));
        oben.add(lblRunde);
        oben.add(lblGesamt);
        oben.add(erzeugeUeberschrift("Deine Zahl:"));
        oben.add(erzeugeUeberschrift("Computer:"));

        // Mitte: die beiden großen Textfelder
        JPanel mitte = new JPanel(new GridLayout(1, 2, 10, 5));
        mitte.add(txtSpieler);
        mitte.add(txtComputer);

        // unten: Button mittig
        JPanel unten = new JPanel();
        unten.add(btnNochmal);

        setLayout(new BorderLayout(5, 5));
        add(oben, BorderLayout.NORTH);
        add(mitte, BorderLayout.CENTER);
        add(unten, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 300);
        setLocationRelativeTo(null);
    }

    /** Erzeugt ein Anzeige-Label mit weißem Hintergrund. */
    private static JLabel erzeugeLabel() {
        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        return label;
    }

    /** Erzeugt eine kleine Überschrift. */
    private static JLabel erzeugeUeberschrift(String text) {
        return new JLabel(text, SwingConstants.CENTER);
    }

    /** Große, zentrierte Schrift und weißer Hintergrund für ein Textfeld. */
    private static void formatiereTextfeld(JTextField feld) {
        feld.setHorizontalAlignment(JTextField.CENTER);
        feld.setFont(new Font("SansSerif", Font.BOLD, 24));
        feld.setBackground(Color.WHITE);
    }

    /** Registriert den Controller als Listener für Textfeld und Button. */
    public void setController(ActionListener listener) {
        txtSpieler.setActionCommand(CMD_EINGABE);
        txtSpieler.addActionListener(listener);
        btnNochmal.setActionCommand(CMD_NOCHMAL);
        btnNochmal.addActionListener(listener);
    }

    /** @return der Text aus dem Eingabefeld */
    public String getSpielerEingabe() {
        return txtSpieler.getText();
    }

    /** Aktualisiert die Oberfläche nach einer ausgewerteten Runde. */
    public void zeigeRunde(int computerZahl, int rundenErgebnis, int gesamt, boolean gewonnen, boolean verloren) {
        txtComputer.setText(String.valueOf(computerZahl));
        lblRunde.setText(rundenText(rundenErgebnis, gewonnen, verloren));
        lblGesamt.setText(String.valueOf(gesamt));
    }

    /** @return Text für das Rundenergebnis-Label */
    private String rundenText(int rundenErgebnis, boolean gewonnen, boolean verloren) {
        if (gewonnen) {
            return "Gewonnen";
        }
        if (verloren) {
            return "Verloren";
        }
        if (rundenErgebnis > 0) {
            return "+" + rundenErgebnis;
        }
        return String.valueOf(rundenErgebnis);
    }

    /** Löscht die Rundendaten, der Gesamtpunktestand bleibt stehen. */
    public void zuruecksetzen() {
        txtSpieler.setText("");
        txtComputer.setText("");
        lblRunde.setText(START_TEXT);
    }

    /** Zeigt eine Meldung in einem Dialog an. */
    public void zeigeMeldung(String text) {
        JOptionPane.showMessageDialog(this, text);
    }
}