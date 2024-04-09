package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Vehicule;

public class VehiculeMet extends Vehicule {
    public VehiculeMet(Color player, String caseDepart) {
        super("Vm", caseDepart);
        this.player = player;
    }
}
