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

	public JpPlateau(Ihm fenetre, int id) {
		this.id = id;
		nbrNav = 0;
		this.actionBouton = new ActionBoutonGrille();
		this.grille = new JButton[10][10];
		this.initialisationGrille();
		this.fenetre = fenetre;
	}
	public JButton[][] getGrille(){
		return this.grille;
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
			JComboBox<String> choixPos = new JComboBox<String>();
			choixPos.addItem("Vers le Nord");
			choixPos.addItem("Vers le Sud");
			choixPos.addItem("Vers l'Ouest");
			choixPos.addItem("Vers l'Est");
			center.add(choixPos);
			JButton val = new JButton("valider");
			choix.add(val, BorderLayout.SOUTH);
			choix.setVisible(true);
			val.addActionListener(new ActionListener() {


				@Override
				public void actionPerformed(ActionEvent e) {
					/*if (choixPos.getSelectedIndex() == 0 && nbrNav!=5) {
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (this.grille[i][j] == source) {
									for (int k = 0; k < 5; k++) {
										this.grille[i - k][j].setText("X");
									}
								}
							}
						}
						nbrNav++;
					}
					if (choixPos.getSelectedIndex() == 1&&nbrNav!=5) {
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (this.grille[i][j] == source) {
									for (int k = 0; k < 5; k++) {
										this.grille[i + k][j].setText("X");
									}
								}
							}
						}
						nbrNav++;
					}
					if (choixPos.getSelectedIndex() == 2&&nbrNav!=5) {
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (this.grille[i][j] == source) {
									for (int k = 0; k < 5; k++) {
										this.grille[i][j-k].setText("X");
									}
								}
							}
						}
						nbrNav++;
					}
					if (choixPos.getSelectedIndex() == 3&&nbrNav!=5) {
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (this.grille[i][j] == source) {
									for (int k = 0; k < 5; k++) {
										this.grille[i][j+k].setText("X");
									}
								}
							}
						}
						nbrNav++;
					}*/
					ajouterNavire(fenetre.getJeu().getPlateauId(id)
							.getListeNav().get(nbrNav),
							choixPos.getSelectedIndex(),
							Integer.valueOf(source.getText()));

					choix.setVisible(false);
					nbrNav++;
				}
			});
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
					grille[i][j].setBackground(Color.RED);
				} else {
					grille[i][j].setBackground(Color.GRAY);
				}
			}
		}
		if (id == 0) {
			for (Case cassee : fenetre.getJeu().getPlateauId(id)
					.getCasesOccupees()) {
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
}