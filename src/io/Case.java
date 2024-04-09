package io;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objets.pieces.abstract_class.Piece;

/**
 * La classe Case représente une case du plateau de jeu. Chaque case peut afficher une pièce et
 * avoir une couleur de fond spécifique.
 */
public class Case extends JLabel {

    private int fontSize = 22; // Taille de police par défaut
    private Font font = new Font("Arial", Font.PLAIN, fontSize); // Police par défaut
    private Color bgCase; // Couleur de fond de la case
    private String nameCase; // Nom de la case
    private String displayCase; // Contenu affiché sur la case
    private boolean side; // Indique si la case est un côté du plateau
    private Color fontColor = Color.BLACK; // Couleur de police par défaut

    /**
     * Constructeur d'une nouvelle case.
     *
     * @param color La couleur de fond de la case.
     * @param name Le nom de la case.
     * @param indice L'indice de la case.
     */
    public Case(Color color, String name, int indice) {
        super();
        this.bgCase = color;
        this.nameCase = name;
        this.displayCase = "";
        this.side = false;
    }

    /**
     * Définit la case comme étant un côté du plateau.
     */
    public void setSide() {
        this.side = true;
        this.font = new Font("Arial", Font.PLAIN, fontSize);
    }

    /**
     * Obtient l'indicateur indiquant si la case est un côté du plateau.
     *
     * @return true si la case est un côté, false sinon.
     */
    public Boolean getSide() {
        return this.side;
    }

    /**
     * Obtient le nom de la case.
     *
     * @return Le nom de la case.
     */
    public String getName() {
        return this.nameCase;
    }

    /**
     * Définit le contenu affiché sur la case comme étant le nom de la case. Utile pour les cases au
     * bord du plateau.
     * 
     */
    public void setNameAsDisplay() {
        this.displayCase = this.nameCase;
    }

    /**
     * Efface le contenu affiché sur la case.
     */
    public void clearDisplay() {
        if (!this.side) {
            this.displayCase = "";
            this.font = new Font("Arial", Font.PLAIN, fontSize);
        }
    }

    /**
     * Met à jour le contenu affiché sur la case en fonction de la pièce à ajouter.
     *
     * @param pieceToAdd La pièce à ajouter à la case.
     */
    public void updateDisplay(Piece pieceToAdd) {
        addStringToDisplay(pieceToAdd.getToPrint());
        if (displayCase.length() > 2) {
            if (!pieceToAdd.getPlayer().equals(this.fontColor)) {
                // Si deux pièces d'une couleur différente sont sur la même case, elle prend la
                // couleur noir.
                this.fontColor = Color.BLACK;
            }
            // On réduit la taille de la police si plusieurs pièces sont sur la case.
            this.font = new Font("Arial", Font.PLAIN, fontSize / displayCase.length() + 7);
        } else {
            this.fontColor = pieceToAdd.getPlayer();
        }
    }

    /**
     * Ajoute une chaîne de caractères au contenu affiché sur la case. Cela permet notament
     * d'ajouter plusieurs pieces sur la même case.
     *
     * @param toAdd La chaîne de caractères à ajouter.
     */
    public void addStringToDisplay(String toAdd) {
        StringBuilder contentString = new StringBuilder();
        contentString.append(this.displayCase);
        if (!this.displayCase.equals("")) {
            contentString.append(" ");
        }
        contentString.append(toAdd);
        this.displayCase = contentString.toString();
    }

    /**
     * Configure les propriétés visuelles de la case (couleur de fond, texte, police, etc.).
     */
    public void set() {
        setOpaque(true);
        setBackground(bgCase);
        setHorizontalAlignment(SwingConstants.CENTER);
        setText(displayCase);
        setFont(font);
        setForeground(fontColor);
    }
}
