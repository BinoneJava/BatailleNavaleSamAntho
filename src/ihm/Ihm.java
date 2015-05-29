package ihm;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

import com.bataille.application.Jeu;
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
	Jeu jeu;

	public Ihm() {
		this.init();
		jmenu = new JMenu("kokokok");
		tabJPanels = new JPanel[5];
		tabJPanels[0] = new Accueil(this);
		tabJPanels[1] = new JpJoueur(this);
		tabJPanels[2] = new JpJeu(this);
		tabJPanels[3] = new Contact(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1100,700));
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
	
	public void quitter(){
		this.setVisible(false);
	    this.dispose();
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
	public void init(){
		jeu = new Jeu(10, 10, this.getNom());
		jeu.setScore(0);
		jeu.genererJeu();
	}
	public Jeu getJeu(){
		return this.jeu;
	}
	
}
