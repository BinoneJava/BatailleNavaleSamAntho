package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.bataille.metier.Navire;
import com.bataille.metier.Plateau;
import com.bataille.metier.Case;

public class JpPlateau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton grille[][];
	int id;
	ActionBoutonGrille actionBouton;
	Ihm fenetre;

	public JpPlateau(Ihm fenetre, int id) {
		this.id = id;
		this.actionBouton = new ActionBoutonGrille();
		this.grille = new JButton[10][10];
		this.initialisationGrille();
		this.fenetre = fenetre;
	}

	/**
	 * Méthode pour initaliaser la grille.
	 */
	public void initialisationGrille() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.grille[i][j] = new JButton(Integer.toString(i)
						+ Integer.toString(j));
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.add(grille[i][j]);
				grille[i][j].setBackground(Color.BLUE);
				grille[i][j].addActionListener(actionBouton);
			}
		}
	}

	class ActionBoutonGrille implements ActionListener {
		public ActionBoutonGrille() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * System.out.println("hey"); try { con.getJeu().jouer(
			 * Integer.parseInt(e.getActionCommand())/10,
			 * Integer.parseInt(e.getActionCommand())%10,
			 * con.getJeu().getPlateauJoueurUn()); } catch (CoupException e1) {
			 * e1.printStackTrace(); }
			 */
			JButton source = (JButton) e.getSource();
			source.setText("X");
			JDialog choix = new JDialog();
			choix.setSize(new Dimension(400, 300));
			choix.setLayout(new BorderLayout());
			JLabel quest = new JLabel("Vers où diriger le bateau ?",
					JLabel.CENTER);
			choix.add(quest, BorderLayout.NORTH);
			JPanel center = new JPanel(new GridLayout(4, 1));
			choix.add(center, BorderLayout.CENTER);
			ButtonGroup grpRadio = new ButtonGroup();

			JRadioButton haut = new JRadioButton("Vers le Nord");
			JRadioButton bas = new JRadioButton("Vers le Sud");
			JRadioButton gauche = new JRadioButton("Vers l'Ouest");
			JRadioButton droite = new JRadioButton("Vers l'Est");
			grpRadio.add(haut);
			grpRadio.add(bas);
			grpRadio.add(gauche);
			grpRadio.add(droite);
			center.add(haut);
			center.add(bas);
			center.add(gauche);
			center.add(droite);
			JButton val = new JButton("valider");
			choix.add(val, BorderLayout.SOUTH);
			choix.setVisible(true);
		}
	}

	public void actualiserGrille() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (fenetre.getJeu().getPlateauId(id).lstCases[i][j]
						.isEstTouche()
						&& fenetre
								.getJeu()
								.getPlateauId(id)
								.getCasesOccupees()
								.contains(
										fenetre.getJeu().getPlateauId(id).lstCases[i][j])) {
					grille[i][j].setBackground(Color.GREEN);
				} else {
					grille[i][j].setBackground(Color.RED);
				}
			}
		}
		for (Case cassee : fenetre.getJeu().getPlateauId(id).getCasesOccupees()) {
			this.grille[cassee.getPosx()][cassee.getPosy()].setBackground(Color.BLACK);
		}

	}

	public List<Case> placerBateauGrille(Navire nav, Plateau plat,
			int indexChoix, int cordonees) {
		ArrayList<Case> lc = new ArrayList<Case>();
		Case c = null;
		for (int i = 0; i < nav.getTaille(); i++) {
			c = plat.getNextCaseSwing(c, indexChoix, cordonees, i);
			lc.add(c);
		}
		return lc;
	}
}