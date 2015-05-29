package com.bataille.application;

import java.util.ArrayList;
import java.util.List;

import com.bataille.metier.Case;
import com.bataille.metier.CoupException;
import com.bataille.metier.Navire;
import com.bataille.metier.Plateau;
import com.bataille.util.FileUtil;

/**
 * @author V902458
 *
 */
public class Jeu  {

	private Plateau plateauJoueurUn;
	private Plateau plateauJoueurDeux;
	private int x;
	private int y;
	private int score;	
	private Plateau[] plateaux;
	

	private int nbreCoups;
	
	
	private Plateau goodPlateau(Plateau p ) {
		if (p.equals(plateauJoueurUn)) return plateauJoueurUn;
		if (p.equals(plateauJoueurDeux)) return plateauJoueurDeux;
		return null;
	}
	
	
	/**
	 * Constructeur permettant de cr�er un plateau de jeu et de donner un nom de joueur au joueur humain.
	 * on peut noter que par d�faut dans ce constructeur, on cr�e un plateau pour l'ordinateur
	 * @param hauteur
	 * @param largeur
	 * @param nomJoueur
	 */
	public Jeu(int hauteur, int largeur, String nomJoueur){
		this.plateaux = new Plateau[2];
		plateaux[0]=this.plateauJoueurUn = new Plateau(hauteur, largeur, nomJoueur);
		plateaux[1]=this.plateauJoueurDeux = new Plateau(hauteur, largeur, "Ordinateur");	
		
	}
	
	

	/**
	 * Permet de jouer un coup sur un plateau donn�. Le joueur A fait un coup x,y....
	 * @param x
	 * @param y
	 * @param plateau
	 * @return Navire : le navire touch� ou coul�, ou alors null si rien ne s'est pass�
	 * @throws CoupException
	 */
	public Navire jouer(int x, int y, Plateau plateau) throws CoupException {
		//controler que le coup est permis
		isCoupAutorise(x, y, plateau);
		Navire n = this.goodPlateau(plateau).jouerCoup(x,y);
		
		// actualiser les coupes jou�s et les cases touch�es
		return n;
	}
	
	/**
	 * Permet de v�rifier que les coordonn�es du coup jou� sont bien dans la taille de l'aire de jeu
	 * @param x
	 * @param y
	 * @param plateau
	 * @throws CoupException
	 */
	private void isCoupAutorise(int x, int y, Plateau plateau) throws CoupException{
		int cx = this.goodPlateau(plateau).getLongueur();
		int cy = this.goodPlateau(plateau).getLargeur();
		if (x > cx || y > cy) {
			throw new CoupException("coup non autoris� ");
		}
	}
	
	/**
	 * r�cup�re la liste des bateaux coul�s d'un Plateau/Joueur
	 * @param plateau
	 * @return
	 */
	List<Navire> sontCoules(Plateau plateau) {
		
		return this.goodPlateau(plateau).sontCoules();
		
	}
	
	/**
	 * r�cup�re la liste des navires d'un plateau/joueur
	 * @param plateau
	 * @return
	 */
	List<Navire> tousNavires(Plateau plateau) {
		return this.goodPlateau(plateau).getListeNav();
	}
	
	@Deprecated
	public void genererJeu(){
		int nbreNavires = 5;
		int nbreNavires1CASE = 0;
		int nbreNavires2CASE = 1;
		int nbreNavires3CASE = 2;
		int nbreNavires4CASE = 4;
		int nbreNavires5CASE = 5;
		int cpt;
		int idNavire =0;
		Navire n = null;
		List<Case> lst =null;
		List<Navire> listeNav = new ArrayList<Navire>();
		for (int i=0; i< nbreNavires; i++){
			for (cpt =0; cpt <nbreNavires1CASE; cpt++){
				idNavire++;
				n = new Navire(idNavire,1,lst,false,100);
				listeNav.add(n);
			}
			for (cpt =0; cpt <nbreNavires2CASE; cpt++){
				idNavire++;
				n = new Navire(idNavire,2,lst,false,200);
				listeNav.add(n);
			}
			for (cpt =0; cpt <nbreNavires3CASE; cpt++){
				idNavire++;
				n = new Navire(idNavire,3,lst,false,300);
				listeNav.add(n);
			}
			for (cpt =0; cpt <nbreNavires4CASE; cpt++){
				idNavire++;
				n = new Navire(idNavire,4,lst,false,400);
				listeNav.add(n);
			}
			for (cpt =0; cpt <nbreNavires5CASE; cpt++){
				idNavire++;
				n = new Navire(idNavire,5,lst,false,500);
				listeNav.add(n);
			}
			
		}	
		plateauJoueurUn.setListeNav(listeNav);
		plateauJoueurDeux.setListeNav(listeNav);	
			
		
		
		//d�finir nombre navires, type navire, taille navire
	}
	
	
	@Deprecated
	Plateau initialiserLeJeu(){
		
		//demander la taille du jeu, le nom de joueur et initaliser le score.
		return null;
	}	
	
	public Plateau getPlateauJoueurUn() {
		return plateauJoueurUn;
	}

	
	public void setPlateauJoueurUn(Plateau plateauJoueurUn) {
		this.plateauJoueurUn = plateauJoueurUn;
	}

	
	
	public Plateau getPlateauJoueurDeux() {
		return plateauJoueurDeux;
	}

	
	public void setPlateauJoueurDeux(Plateau plateauJoueurDeux) {
		this.plateauJoueurDeux = plateauJoueurDeux;
	}

	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	
	public int getNbreCoups() {
		return nbreCoups;
	}

	
	public void setNbreCoups(int nbreCoups) {
		this.nbreCoups = nbreCoups;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public Plateau getPlateauId(int id){
		return this.plateaux[id];
	}


}
