package controleur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import modele.jdbc.FabriqueJdbc;
import modele.jdbc.Jdbc;

public class Main {

    public static void main(String[] args)  {

        String ficPropertiesJdbc = "MNMJdbc.properties";        // nom du fichier de properties
        Properties propertiesJdbc;                              // objet de propriétés (paramètres de l'appplication) pour Jdbc
        FileInputStream input;                                  // flux de lecture des properties
        C_Principal ctrlPrincipal;                              // référence vers le contrôleur principal

        // Si au moins un paramètre a été passé sur la ligne de commandes, le premier est le nom du fichier contenant les propriétés de connexion JDBC
        if (args.length > 0) {
            ficPropertiesJdbc = args[0];
        }
        
        // 1- Instancier le singleton de connexion Jdbc en fonction d'un fichier de paramètres
        // La classe Jdbc étant un singleton, la connexion s'obtient ainsi :
        // Jdbc.getInstance().getConnexion()
        // La connexion est utilisée dans les classes Dao
        try {
            FabriqueJdbc.creer(ficPropertiesJdbc);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - Fichier de propriétés JDBC introuvable", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - Erreur lors du chargement du fichier de propriétés JDBC", JOptionPane.ERROR_MESSAGE);
        }

       // 2 - ouvrir la connexion
        try {
            Jdbc.getInstance().connecter();
        } catch (ClassNotFoundException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - connexion à la BDD - pilote JDBC non trouvé", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Lanceur Main - connexion à la BDD", JOptionPane.ERROR_MESSAGE);
        }
        
        // Pour lancer l'application, instancier le contrôleur principal
        ctrlPrincipal = new C_Principal();
        ctrlPrincipal.action();
    }
}
