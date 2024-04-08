import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.management.StringValueExp;
import javax.swing.*;

import objets.pieces.Glace;
import objets.pieces.abstract_class.*;
import objets.plateau.PlateauLogique;

public class Logger {
    private Vector<String> completeLog;
    private int turn, scorePlayer1, scorePlayer2;
    private final JTextArea logTextArea;
    private boolean resteNuages;

    public Logger() {
        completeLog = new Vector<>();
        completeLog.add("LISTE DES COUPS :\n");
        turn = 1;
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        resteNuages = false;

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
    }

    private String computeScore(PlateauLogique plateau_logique) {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        resteNuages = false;
        for (Piece piece : plateau_logique.getPieces()) {
            if ((!resteNuages) && (piece instanceof Nuage)){
                resteNuages = !resteNuages; 
            }
            if (piece instanceof Vehicule) {
                if (piece.getPlayer().equals(Color.GREEN)) {
                    scorePlayer1 += ((Vehicule) piece).getCaptures();
                } else {
                    scorePlayer2 += ((Vehicule) piece).getCaptures();
                }
            }
        }
        return scorePlayer1 + "-" + scorePlayer2;
    }

    public void newLogEntery(String turnLog, PlateauLogique plateau_logique) {
        computeScore(plateau_logique);
        String entry = String.valueOf(turn++) + ". " + turnLog + " - " + computeScore(plateau_logique);
        completeLog.add(entry);
        updateTextArea();
    }

    public void updateTextArea() {
        StringBuilder logBuilder = new StringBuilder();
        for (String entry : completeLog) {
            logBuilder.append(entry).append("\n");
        }
        logTextArea.setText(logBuilder.toString().trim());
    }

    public void showLogWindow() {
        JFrame frame = new JFrame("Log");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JScrollPane(logTextArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(160, 500); 
        frame.setVisible(true);
    }

    public Boolean checkIfGameIsOver(){
        if ((!resteNuages) || (scorePlayer1 > 15) || (scorePlayer2 > 15)){
            return true; 
        }
        return false; 
    }
}
