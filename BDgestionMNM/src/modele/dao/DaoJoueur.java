package modele.dao;

import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;

/**
 * Classe DAO pour la classe Joueur
 *
 * @version 22 novembre 2013
 * @author nbourgeois
 */
public class DaoJoueur implements DaoInterface<Joueur, Integer> {

    /**
     * Non implémenté
     */
    @Override
    public int create(Joueur unJoueur) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Lire un enregistrement d'après son identifiant
     *
     * @param identifiant métier de l'objet recherché
     * @return objet métier trouvé, ou null sinon
     * @throws Exception
     */
    @Override
    public Joueur getOne(Integer idJoueur) throws DaoException {
        Joueur result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM joueur WHERE IDJOUEUR=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idJoueur);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoJoueur::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'joueurs de la table
     * joueur
     */
    @Override
    public ArrayList<Joueur> getAll() throws DaoException {
        ArrayList<Joueur> result = new ArrayList<Joueur>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM joueur";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Joueur unJoueur = chargerUnEnregistrement(rs);
                result.add(unJoueur);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoJoueur::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Joueur objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public int verification(String login, String password) throws DaoException{
        int idJoueur = 0;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM joueur WHERE PSEUDO=? AND MDP=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                idJoueur=rs.getInt("idJoueur");
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoPraticien::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        
        return idJoueur;
    }

    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementJoueur Instancie un objet joueur avec les
     * valeurs lues dans la base de données La jointure avec la table PRESENCE
     * n'est pas effectuée
     *
     * @param rs enregistrement de la table Joueur courant
     * @return un objet Joueur, dont la liste des "présences" n'est pas
     * renseignée
     * @throws DaoException
     */
    private Joueur chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Joueur joueur = new Joueur(null,null,null,null,null);
            joueur.setIdJoueur(rs.getInt("IDJOUEUR"));
            joueur.setPseudo(rs.getString("PSEUDO"));
  
            return joueur;
        } catch (SQLException ex) {
            throw new DaoException("DaoJoueur - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
}
