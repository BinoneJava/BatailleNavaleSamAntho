package ihm;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
/**
 * Fenêtre du jeu de bataille navale.
 * @author Samuel
 *
 */
public class Ihm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel tabJPanels[];
	JMenu jmenu;
	String nom;

	public Ihm() {
		jmenu = new JMenu("kokokok");
		tabJPanels = new JPanel[5];
		tabJPanels[0] = new Accueil(this);
		tabJPanels[1] = new JpJoueur(this);
		tabJPanels[2] = new JpJeu(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(700,700));
		//this.setResizable(false);
		this.setTitle("Bataille navale");
		this.changerPage(0);
		this.setVisible(true);
	}

	/**
	 * Méthode pour Changer le panel courant affiché
	 * 
	 * @param num
	 *            le numéro du panel.
	 */
	public void changerPage(int num) {
		this.setContentPane(this.tabJPanels[num]);
		this.repaint();
		this.revalidate();
	}
	public JPanel[] getTabPanel(){
		return this.tabJPanels;
	}
	public String getNom(){
		return nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}

}
