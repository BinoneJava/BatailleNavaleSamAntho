package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.bataille.metier.CoupException;

public class JpPlateau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton grille[][];
	ActionBoutonGrille actionBouton;
	final private JpJeu con;

	public JpPlateau() {
		this.actionBouton = new ActionBoutonGrille();
		this.grille = new JButton[10][10];
		this.initialisationGrille();
		this.con = (JpJeu) (this.getParent());
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
				grille[i][j].addActionListener(actionBouton);
			}
		}
	}

	class ActionBoutonGrille implements ActionListener {
		public ActionBoutonGrille() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("hey");
			try {
				con.getJeu().jouer(
						Integer.parseInt(e.getActionCommand())/10,
						Integer.parseInt(e.getActionCommand())%10,
						con.getJeu().getPlateauJoueurUn());
			} catch (CoupException e1) {
				e1.printStackTrace();
			}
		}
	}
}
