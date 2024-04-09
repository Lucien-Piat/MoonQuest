package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Nuage;

public class NuageMet extends Nuage {

    public NuageMet(String caseDepart) {
        super("Nm", caseDepart);
        this.player = Color.CYAN;
    }

}
