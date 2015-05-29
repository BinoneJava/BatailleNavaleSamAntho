/**
 * 
 */
package ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe intérmédiaire qui permet de récupérer 
 * @author Samuel
 *
 */
public class JpJoueur extends JPanel {

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
	public JpJoueur(Ihm mapage) {
		this.setLayout(new BorderLayout());
		c = new GridBagConstraints();
		ancetre = mapage;
		panelCentre = new JPanel();
		JComboBox<String> choixDif = new JComboBox<String>();
		choixDif.addItem("Facile");
		choixDif.addItem("Difficile");
		jtNom = new JTextField(10);
		jbValider = new JButton("Valider");
		panelCentre = new JPanel(new GridBagLayout());
		this.add(panelCentre, BorderLayout.CENTER);
		c.gridx = 1;
		c.gridy = 1;
		panelCentre.add(new JLabel("Votre nom "),c);
		c.gridx = 2;
		panelCentre.add(jtNom,c);
		c.gridx = 1;
		c.gridy = 2;
		panelCentre.add(new JLabel("Difficulté :"), c);
		c.gridx = 2;
		panelCentre.add(choixDif,c);
		c.gridy = 3;
		c.gridx = 1;
		panelCentre.add(jbValider,c);
		jbValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nom = jtNom.getText();
				ancetre.setNom(nom);
				ancetre.changerPage(2);
			}
		});
	}
	public String getNom(){
		return this.nom;
	}
	
	public JTextField getJTextField(){
		return this.jtNom;
	}

}
