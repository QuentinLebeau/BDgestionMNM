/**
 * ************************************************************************
 * Source File	: Sort.java Author : joliverie Project name : Non enregistr�*
 * Created : 11/12/2013 Modified : 11/12/2013 Description	: Definition of the
 * class Sort
*************************************************************************
 */
package modele.metier;

import java.awt.Image;
import java.util.*;

public class Sort extends Carte {
	//Inners Classifiers

	//Attributes
	//Attributes Association
    List<Effet> Sort_Effet;

	//Operations
    public Sort(List<Effet> Sort_Effet, int idCarte, String nom, Image image, Faction FactionCarte) {
        super(idCarte, nom, image, FactionCarte);
        this.Sort_Effet = Sort_Effet;
    }

    @Override
    public String toString() {
        return "Sort{" + "Sort_Effet=" + Sort_Effet + '}';
    }

    public List<Effet> getSort_Effet() {
        return Sort_Effet;
    }

    public void setSort_Effet(List<Effet> Sort_Effet) {
        this.Sort_Effet = Sort_Effet;
    }

} //End Class Sort

