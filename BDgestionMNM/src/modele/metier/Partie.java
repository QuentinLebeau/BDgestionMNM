/**************************************************************************
* Source File	:  Partie.java
* Author                   :  joliverie  
* Project name         :  Non enregistr�* Created                 :  11/12/2013
* Modified   	:  11/12/2013
* Description	:  Definition of the class Partie
**************************************************************************/


package modele.metier;


import java.util.*;



public  class Partie  
{ 
	//Inners Classifiers
	

	//Attributes
    
	private java.lang.Integer idPartie;
	private java.lang.Integer tour;
        private Joueur joueur1;
        private Joueur joueur2;
                	
	
    public Partie(Integer idPartie, Integer tour, Joueur joueur1, Joueur joueur2) {
        this.idPartie = idPartie;
        this.tour = tour;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    @Override
    public String toString() {
        return "Partie de " + joueur1.getPseudo() ;
    }

    public Integer getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Integer idPartie) {
        this.idPartie = idPartie;
    }

    public Integer getTour() {
        return tour;
    }

    public void setTour(Integer tour) {
        this.tour = tour;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
		
		
	
	

} //End Class Partie


