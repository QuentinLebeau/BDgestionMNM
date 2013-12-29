/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import modele.dao.DaoCarte;
import modele.dao.DaoException;
import modele.dao.DaoHero;
import modele.dao.DaoJoueur;
import modele.jdbc.FabriqueJdbc;
import modele.jdbc.Jdbc;
import modele.metier.Carte;
import modele.metier.Faction;
import modele.metier.Hero;

/**
 *
 * @author btssio
 */
public class testDao {
        static DaoJoueur daoJoueur = new DaoJoueur();
         static DaoHero daoHero = new DaoHero();
         static DaoCarte daoCarte = new DaoCarte();

    // test de lecture des enregistrements de la table PRESENCE pour un équipier donné
    public static boolean testverification() throws DaoException {
        boolean ok = true;
        int idjoueur = daoJoueur.verification("quentin", "quentin");
        System.out.println("idJoueur :"+idjoueur);

        return ok;
    }
        public static boolean testHero() throws DaoException {
            boolean ok = true;
        List<Hero> lesHeros = daoHero.getAll();
        for (Hero unHero : lesHeros){
        System.out.println(unHero.toString());
        }
        return ok;
    }
        
         public static boolean testAjoutCarte() throws DaoException {
            boolean ok = true;
            int cle = 0;
            Faction faction = new Faction(1,"test",null);
            Carte carte = new Carte (0,"test",null,faction);
            cle = daoCarte.ajouter(carte);
            

        System.out.println("cle : " + cle);
        
        return ok;
    }
         public static boolean testAjoutHero() throws DaoException {
            boolean ok = true;
            int cle = 0;
            Faction faction = new Faction(1,"test",null);
            Hero hero = new Hero (0,10,10,10,10,"test",null,faction);
            cle = daoHero.ajouter(hero);
            

        System.out.println("cle : " + cle);
        
        return ok;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException, DaoException {
        System.out.println("Tests unitaires DAO");
        FabriqueJdbc.creer("MNMJdbc.properties");
        Jdbc.getInstance().connecter();


        System.out.println("\nTest DAO verif connexion");
        if (testverification()) {
            System.out.println("+++ Réussite");
        } else {
            System.out.println("--- Echec");
        }
        
        
          System.out.println("\nTest DAO Lire Hero");
        if (testHero()) {
            System.out.println("+++ Réussite");
        } else {
            System.out.println("--- Echec");
        }
        
                
          System.out.println("\nTest DAO ajout Carte");
        if (testAjoutCarte()) {
            System.out.println("+++ Réussite");
        } else {
            System.out.println("--- Echec");
        }
        
        System.out.println("\nTest DAO ajout Hero");
        if (testAjoutHero()) {
            System.out.println("+++ Réussite");
        } else {
            System.out.println("--- Echec");
        }
  
  

        System.out.println("\nDéconnexion");
        Jdbc.getInstance().deconnecter();

    }
}
