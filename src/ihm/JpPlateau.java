package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bataille.metier.Case;
import com.bataille.metier.Navire;
import com.bataille.metier.Plateau;

public class JpPlateau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[][] grille;
	int id;
	ActionBoutonGrille actionBouton;
	Ihm fenetre;
	int nbrNav;
	List<Navire> lsNavires;
	Plateau plateau;

	public JpPlateau(Ihm fenetre, int id) {
		this.id = id;
		nbrNav = 0;
		this.actionBouton = new ActionBoutonGrille();
		this.grille = new JButton[10][10];
		this.fenetre = fenetre;
		this.lsNavires = fenetre.getJeu().getPlateauId(id).getListeNav();
		this.plateau = fenetre.getJeu().getPlateauId(id);
	}

	public JButton[][] getGrille() {
		return this.grille;
	}

	/**
	 * M�thode pour initaliaser la grille.
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
			JButton source = (JButton) e.getSource();
			JDialog choix = new JDialog();
			choix.setSize(new Dimension(400, 300));
			choix.setLayout(new BorderLayout());
			JLabel quest = new JLabel("Vers o� diriger le bateau ?",
					JLabel.CENTER);
			choix.add(quest, BorderLayout.NORTH);
			JPanel center = new JPanel(new GridLayout(4, 1));
			choix.add(center, BorderLayout.CENTER);
			JComboBox<String> choixPos = new JComboBox<String>();
			choixPos.addItem("Vers le Nord");
			choixPos.addItem("Vers le Sud");
			choixPos.addItem("Vers l'Ouest");
			choixPos.addItem("Vers l'Est");
			center.add(choixPos);
			int aPlacer = lsNavires.size() - nbrNav;
			center.add(new JLabel("Vous �tez entrain de poser un bateau de "
					+ lsNavires.get(nbrNav).getTaille() + " cases"));
			center.add(new JLabel("Il vous reste" + (aPlacer)
					+ "bateaux a placer"));
			JButton val = new JButton("valider");
			choix.add(val, BorderLayout.SOUTH);
			choix.setVisible(true);
			System.out.println(nbrNav);
			val.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					/*
					 * if (choixPos.getSelectedIndex() == 0 && nbrNav!=5) { for
					 * (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) {
					 * if (this.grille[i][j] == source) { for (int k = 0; k < 5;
					 * k++) { this.grille[i - k][j].setText("X"); } } } }
					 * nbrNav++; } if (choixPos.getSelectedIndex() ==
					 * 1&&nbrNav!=5) { for (int i = 0; i < 10; i++) { for (int j
					 * = 0; j < 10; j++) { if (this.grille[i][j] == source) {
					 * for (int k = 0; k < 5; k++) { this.grille[i +
					 * k][j].setText("X"); } } } } nbrNav++; } if
					 * (choixPos.getSelectedIndex() == 2&&nbrNav!=5) { for (int
					 * i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) { if
					 * (this.grille[i][j] == source) { for (int k = 0; k < 5;
					 * k++) { this.grille[i][j-k].setText("X"); } } } }
					 * nbrNav++; } if (choixPos.getSelectedIndex() ==
					 * 3&&nbrNav!=5) { for (int i = 0; i < 10; i++) { for (int j
					 * = 0; j < 10; j++) { if (this.grille[i][j] == source) {
					 * for (int k = 0; k < 5; k++) {
					 * this.grille[i][j+k].setText("X"); } } } } nbrNav++; }
					 */
					ajouterNavire(fenetre.getJeu().getPlateauId(id)
							.getListeNav().get(nbrNav),
							choixPos.getSelectedIndex(),
							Integer.valueOf(source.getText()));

					choix.setVisible(false);
					nbrNav++;
					System.out.println(nbrNav);
				}
			});
		}
	}

	public void actualiserGrille() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (plateau.lstCases[i][j].isEstTouche()
						&& fenetre.getJeu().getPlateauId(id).getCasesOccupees()
								.contains(plateau.lstCases[i][j])) {
					grille[i][j].setBackground(Color.RED);
				} else {
					grille[i][j].setBackground(Color.GRAY);
				}
			}
		}
		if (id == 0) {
			for (Case cassee : plateau.getCasesOccupees()) {
				this.grille[cassee.getPosx()][cassee.getPosy()]
						.setBackground(Color.BLACK);
			}
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

	public void ajouterNavire(Navire n, int choix, int cordonnes) {
		n.setCases(placerBateauGrille(n, fenetre.getJeu().getPlateauId(id),
				choix, cordonnes));
		fenetre.getJeu().getPlateauId(id).getListeNav().add(n);
		for (Case c : n.getCases()) {
			fenetre.getJeu().getPlateauId(id).getCasesOccupees().add(c);
		}
	}

	public void placerBateaux() {
		while (lsNavires.size() - nbrNav > 0) {
			
		}
	}
}