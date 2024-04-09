package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Nuage;

/**
 * La classe NuageMet.
 */
public class NuageMet extends Nuage {

    /**
     * Constructeur d'un nouveau NuageMet.
     *
     * @param caseDepart La position initiale du NuageMet.
     */
    public NuageMet(String caseDepart) {
        super("Nm", caseDepart);
        this.player = Color.CYAN;
    }

}
