/**
 * 
 */
package ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

/**
 * Classe intérmédiaire qui permet de récupérer 
 * @author Samuel
 *
 */
public class JPJoueur extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;
	private JTextField jtNom;
	private JButton jbValider;
	private String nom;
	private Ihm ancetre;
	private JPanel panelCentre;
	/**
	 * Contructeur du panel JPJoeur, où on demande le nom d'utilisateur et la difficulté
	 */
	public JPJoueur(Ihm mapage) {
		this.setLayout(new BorderLayout());
		c = new GridBagConstraints();
		ancetre = mapage;
		JComboBox<String> choixDif = new JComboBox<String>();
		choixDif.addItem("Facil");
		choixDif.addItem("Difficil");
		jtNom = new JTextField("Votre nom");
		jbValider = new JButton("Valider");
		panelCentre = new JPanel(new GridLayout(2,2));
		this.add(panelCentre, BorderLayout.CENTER);
		panelCentre.add(jtNom);
		panelCentre.add(new JLabel("Difficulté :"), c);
		panelCentre.add(choixDif);
		panelCentre.add(jbValider);
		jbValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nom = (String) choixDif.getSelectedItem();
				ancetre.changerPage(2);
			}
		});
	}
	public String getNom(){
		return this.nom;
	}

}
