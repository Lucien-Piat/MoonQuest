package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Nuage;

/**
 * La classe NuageEau représente un type spécifique de nuage dans le jeu.
 */
public class NuageEau extends Nuage {

    /**
     * Constructeur d'un nouveau NuageEau.
     *
     * @param caseDepart La position initiale du NuageEau.
     */
    public NuageEau(String caseDepart) {
        super("No", caseDepart);
        this.player = Color.BLUE;
    }
}
