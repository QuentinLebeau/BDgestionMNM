/**
 * ************************************************************************
 * Source File	: Faction.java Author : joliverie Project name : Non enregistr�*
 * Created : 11/12/2013 Modified : 11/12/2013 Description	: Definition of the
 * class Faction
*************************************************************************
 */
package modele.metier;

import java.util.*;

public class Faction {
	//Inners Classifiers

	//Attributes
    private int idFaction;
    private java.lang.String nom;
    private java.lang.String logo;

	//Operations
    public Faction(int idFaction, String nom, String logo) {
        this.idFaction = idFaction;
        this.nom = nom;
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Faction{" + "idFaction=" + idFaction + ", nom=" + nom + ", logo=" + logo + '}';
    }

    public int getIdFaction() {
        return idFaction;
    }

    public void setIdFaction(int idFaction) {
        this.idFaction = idFaction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

} //End Class Faction

