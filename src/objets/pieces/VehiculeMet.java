package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Vehicule;

/**
 * La classe VehiculeEau.
 */
public class VehiculeMet extends Vehicule {

    /**
     * Constructeur d'un nouveau VehiculeEau.
     *
     * @param player La couleur du joueur propriétaire du véhicule.
     * @param caseDepart La position initiale du VehiculeEau.
     */
    public VehiculeMet(Color player, String caseDepart) {
        super("Vm", caseDepart);
        this.player = player;
    }
}
