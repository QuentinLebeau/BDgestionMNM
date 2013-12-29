package modele.dao;

import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;

/**
 * Classe DAO pour la classe Partie
 */
public class DaoPartie implements DaoInterface<Partie, Integer> {

    /**
     * Non implémenté
     */
    @Override
    public int create(Partie unePartie) throws Exception {
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
    public Partie getOne(Integer idPartie) throws DaoException {
        Partie result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM partie"
                + " LEFT OUTER JOIN joueur j1 on p.JOUEUR1=j1.IDJOUEUR"
                + " LEFT OUTER JOIN joueur j2 on p.JOUEUR2=j2.IDJOUEUR"
                        + "WHERE IDPARTIE=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idPartie);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoPartie::getOne : erreur requete SELECT : " + ex.getMessage());
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
    public ArrayList<Partie> getAll() throws DaoException {
        ArrayList<Partie> result = new ArrayList<Partie>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM partie p"
                + " LEFT OUTER JOIN joueur j1 on p.JOUEUR1=j1.IDJOUEUR"
                + " LEFT OUTER JOIN joueur j2 on p.JOUEUR2=j2.IDJOUEUR";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Partie unePartie = chargerUnEnregistrement(rs);
                result.add(unePartie);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoPartie::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Partie objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementPartie Instancie un objet joueur avec les
     * valeurs lues dans la base de données La jointure avec la table PRESENCE
     * n'est pas effectuée
     *
     * @param rs enregistrement de la table Partie courant
     * @return un objet Partie, dont la liste des "présences" n'est pas
     * renseignée
     * @throws DaoException
     */
    
    //récupérer les joueur trouvé pour les insérer dans la partie
    private Partie chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Partie partie = new Partie(null,null,null,null);
            partie.setIdPartie(rs.getInt("IDPARTIE"));
            partie.setTour(rs.getInt("TOUR"));
            
            if(rs.getInt("JOUEUR1") != 0) {
                Joueur joueur1 = new Joueur(null, null, null, null, null);
                joueur1.setIdJoueur(rs.getInt("j1.IDJOUEUR"));
                joueur1.setPseudo(rs.getString("j1.PSEUDO"));
                partie.setJoueur1(joueur1);
            }       
            if(rs.getInt("JOUEUR2") != 0) {
                Joueur joueur2 = new Joueur(null, null, null, null, null);
                joueur2.setIdJoueur(rs.getInt("j2.IDJOUEUR"));
                joueur2.setPseudo(rs.getString("j2.PSEUDO"));
                partie.setJoueur2(joueur2);
            }
            return partie;
        } catch (SQLException ex) {
            throw new DaoException("DaoPartie - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
}
