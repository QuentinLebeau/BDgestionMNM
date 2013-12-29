package vue;

import controleur.C_Abstrait;
import javax.swing.JPanel;

/**
 * V_Abstraite
 * modèle de vue
 * - un lien vers le contrôleur
 * chaque contrôleur a un lien vers une vue
 * @author nbourgeois
 * @version  20 novembre 2013
 */

public abstract class V_Abstraite extends JPanel{
    // associations
    protected C_Abstrait controleur=null;
    
    public V_Abstraite(C_Abstrait ctrl) {
        this.controleur = ctrl;
    }       
    
}
