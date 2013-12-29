package modele.dao;

import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;

/**
 * Classe DAO pour la classe Faction
 *
 * @version 22 novembre 2013
 * @author nbourgeois
 */
public class DaoFaction implements DaoInterface<Faction, Integer> {

    /**
     * Non implémenté
     */
    @Override
    public int create(Faction unFaction) throws Exception {
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
    public Faction getOne(Integer idFaction) throws DaoException {
        Faction result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM faction WHERE IDFACTION=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idFaction);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoFaction::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'factions de la table
     * faction
     */
    @Override
    public ArrayList<Faction> getAll() throws DaoException {
        ArrayList<Faction> result = new ArrayList<Faction>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM faction";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Faction unFaction = chargerUnEnregistrement(rs);
                result.add(unFaction);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoFaction::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Faction objetMetier) throws Exception {
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
     * chargerUnEnregistrementFaction Instancie un objet faction avec les
     * valeurs lues dans la base de données La jointure avec la table PRESENCE
     * n'est pas effectuée
     *
     * @param rs enregistrement de la table Faction courant
     * @return un objet Faction, dont la liste des "présences" n'est pas
     * renseignée
     * @throws DaoException
     */
    private Faction chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Faction faction = new Faction(0,null,null);
            faction.setIdFaction(rs.getInt("IDFACTION"));
            faction.setNom(rs.getString("NOM"));
            faction.setLogo(rs.getString("LOGO"));
            
  
            return faction;
        } catch (SQLException ex) {
            throw new DaoException("DaoFaction - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
}
