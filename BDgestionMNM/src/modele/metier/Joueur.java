/**************************************************************************
* Source File	:  Joueur.java
* Author                   :  joliverie  
* Project name         :  Non enregistr�* Created                 :  11/12/2013
* Modified   	:  11/12/2013
* Description	:  Definition of the class Joueur
**************************************************************************/


package modele.metier;


import java.util.*;



public  class Joueur  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer idJoueur;
		private 
	 java.lang.String pseudo;
                private String mdp;
	
	//Attributes Association
	
		Partie Comporte;
	 
		Deck DeckJoueur;
	 
	
	
	//Operations

    public Joueur(Integer idJoueur, String pseudo, String mdp, Partie Comporte, Deck DeckJoueur) {
        this.idJoueur = idJoueur;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.Comporte = Comporte;
        this.DeckJoueur = DeckJoueur;
    }

    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", pseudo=" + pseudo + ", mdp=" + mdp + ", Comporte=" + Comporte + ", DeckJoueur=" + DeckJoueur + '}';
    }
    

    public Integer getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Integer idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Partie getComporte() {
        return Comporte;
    }

    public void setComporte(Partie Comporte) {
        this.Comporte = Comporte;
    }

    public Deck getDeckJoueur() {
        return DeckJoueur;
    }

    public void setDeckJoueur(Deck DeckJoueur) {
        this.DeckJoueur = DeckJoueur;
    }
		
		
	
	

} //End Class Joueur


