package modele.dao;

import java.awt.Image;
import java.io.IOException;
import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe DAO pour la classe Sort
 *
 * @version 22 novembre 2013
 * @author nbourgeois
 */
public class DaoSort implements DaoInterface<Sort, Integer> {

    DaoFaction daoFaction = new DaoFaction();
    DaoCarte daoCarte = new DaoCarte();

    /**
     * Non implémenté
     */
    @Override
    public int create(Sort unSort) throws Exception {
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
    public Sort getOne(Integer idSort) throws DaoException {
        Sort result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM sort WHERE IDCARTE=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idSort);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoSort::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'sorts de la table sort
     */
    @Override
    public ArrayList<Sort> getAll() throws DaoException {
        ArrayList<Sort> result = new ArrayList<Sort>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM sort s"
                + " INNER JOIN carte c ON s.IDCARTE=c.IDCARTE";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Sort unSort = chargerUnEnregistrement(rs);
                result.add(unSort);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoSort::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Sort objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int ajouter(Sort sort) throws DaoException {
        Carte carte = new Carte(0, sort.getNom(), sort.getImage(), sort.getFactionCarte());
        int cle = daoCarte.ajouter(carte);

        ResultSet rs = null; // ResultSet devant contenir le dernier ID généré ou vide
        int nb;

        // préparer la requête
        String requete = "INSERT INTO sort (`IDCARTE`) VALUES (? );";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);

            ps.setInt(1, cle);
            nb = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoSort::ajouter : erreur requete INSERT : " + ex.getMessage());
        }
        return (cle);
    }

    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementSort Instancie un objet sort avec les valeurs lues
     * dans la base de données La jointure avec la table PRESENCE n'est pas
     * effectuée
     *
     * @param rs enregistrement de la table Sort courant
     * @return un objet Sort, dont la liste des "présences" n'est pas renseignée
     * @throws DaoException
     */
    private Sort chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            
            Sort sort = new Sort(null, 0, null, null,null);
            sort.setIdCarte(rs.getInt("IDCARTE"));
            sort.setNom(rs.getString("NOM"));
            
            
            
           if(rs.getString("IMAGE")!=null){
          
               sort.setImage((Image) javax.imageio.ImageIO.read( (rs.getBlob("IMAGE")).getBinaryStream() ));
            }


            sort.setFactionCarte(daoFaction.getOne(rs.getInt("IDFACTION")));

            return sort;
        } catch (SQLException ex) {
            throw new DaoException("DaoSort - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        } catch (IOException ex) {
          throw new DaoException("DaoSort - chargerUnEnregistrement : pb blob\n" + ex.getMessage());
        }
    }
}
