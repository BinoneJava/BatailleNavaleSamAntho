package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private Jeu jeu;
	private String nom;

	public JpJeu(Ihm z) {
		this.setLayout(new BorderLayout());
		JPanel centreLayout = new JPanel(new GridLayout(0, 2));
		this.grille = new JpPlateau();
		this.grille2 = new JpPlateau();
		this.jeu = new Jeu(10, 10, nom);
		b2 = new JButton("Placer les bateaux !");
		this.add(new JLabel(nom), BorderLayout.WEST);
		this.add(centreLayout, BorderLayout.CENTER);

		centreLayout.add(grille);

		centreLayout.add(grille2);

		this.add(b2, BorderLayout.NORTH);
		this.add(new JLabel(nom), BorderLayout.WEST);
		this.add(new JLabel("Ordinateur"), BorderLayout.EAST);
		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				z.changerPage(0);
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public Jeu getJeu() {
		return jeu;
	}
}
