package ihm;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bataille.application.Jeu;

/**
 * Classe qui contient le panel du jeu.
 * 
 * @author Samuel
 *
 */
public class JpJeu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JpPlateau grille;
	private JpPlateau grille2;
	private JButton menu = new JButton("Menu ! ");
	private JButton b2;
	private String nom;
	private JLabel spectre;
	

	public JpJeu(Ihm z) {
		this.spectre = new JLabel("Maintenant Cliquez sur une case !");
		this.setLayout(new BorderLayout());
		JPanel nord = new JPanel(new GridLayout(0,3));
		this.add(nord, BorderLayout.NORTH);
		JPanel centreLayout = new JPanel(new GridLayout(0, 2));
		this.grille = new JpPlateau(z,0);
		this.grille2 = new JpPlateau(z,1);
		b2 = new JButton("Placez les bateaux !");
		this.add(centreLayout, BorderLayout.CENTER);
		nord.add(new JLabel("Vous"));
		nord.add(b2);
		nord.add(new JLabel("Ordinateur"));

		centreLayout.add(grille);

		centreLayout.add(grille2);
		
		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				z.changerPage(0);
			}
		});
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nord.add(spectre,BorderLayout.SOUTH);
				
				//grille.actualiserGrille();
				//grille2.actualiserGrille();
				//System.out.println(z.getJeu().getPlateauJoueurUn().getCasesOccupees());
				//System.out.println(z.getJeu().getPlateauJoueurUn().getListeNav());
			}
		});
	}
}
