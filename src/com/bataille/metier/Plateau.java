package com.bataille.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bataille.application.BatailleNavaleException;
import com.bataille.util.FileUtil;

public class Plateau {
	private String joueur;
	private int longueur;
	private int largeur;

	private List<Navire> listeNav;
	private List<Case> casesOccupees;
	private boolean[][] coupsJoues;
	private boolean[][] casesTouchees;
	public Case[][] lstCases;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plateau other = (Plateau) obj;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		return true;
	}

	public Plateau(int longueur, int largeur) {
		super();

	}

	/**
	 * G�n�re le plateau pour un joueur : taille, initialisation des tableaux de
	 * coups jou�s et cases touch�es
	 * 
	 * @param longueur
	 * @param largeur
	 * @param joueur
	 */
	public Plateau(int longueur, int largeur, String joueur) {
		this.longueur = longueur;
		this.largeur = largeur;

		// v�rifier long max, larg max; pas z�ro/ pas n�gatif et minimum
		this.lstCases = new Case[longueur][largeur];
		// affectation des dimensions
		coupsJoues = new boolean[longueur][largeur];
		casesTouchees = new boolean[longueur][largeur];
		//
		listeNav = new ArrayList<Navire>();
		casesOccupees = new ArrayList<Case>();
		this.setJoueur(joueur);

	}

	/**
	 * Permet d'ajouter un navire (en principe au d�but du jeu) sur le plateau
	 * du joueur
	 * 
	 * @param n
	 */
	public void ajouterNavire(Navire n) {
		this.listeNav.add(n);
		 for (Case c : n.getCases()) { this.casesOccupees.add(c); }
	}

	public Case getNextCase(Case c,boolean isH,int i) {
		return null;

	}

	public Case getNextCaseSwing(Case c, int index, int cordonnees, int i) {
		Case cn = null;
		if (i == 0) {
			cn = new Case(cordonnees / 10, cordonnees % 10, false, "hey");
		} else {
			if (index == 1) {
				cn = new Case(c.getPosx(), c.getPosy()+1, c.isEstTouche(),
						c.getMotif());
			} else if(index == 2){
				cn = new Case(c.getPosx(), c.getPosy() - 1, c.isEstTouche(),
						c.getMotif());
			}else if(index == 3){
				cn = new Case(c.getPosx()+1, c.getPosy(), c.isEstTouche(),
						c.getMotif());
			}else{
				cn = new Case(c.getPosx()+1, c.getPosy(), c.isEstTouche(),
						c.getMotif());
			}
		}

		return cn;
	}

	private boolean isCollisionPlacement(int x, int y) {
		boolean isCollision = false;
		for (Case c : casesOccupees) {
			if (c.getPosx() == x && c.getPosy() == y) {
				isCollision = true;
			}
		}
		return isCollision;
	}

	/**
	 * Renvoie la liste des navires coul�s
	 * 
	 * @return la liste
	 */
	public List<Navire> sontCoules() {
		List<Navire> coules = new ArrayList<Navire>();
		for (Navire n : this.getListeNav()) {
			if (n.isEstCoule()) {
				coules.add(n);
			}
		}
		return coules;
	}

	/**
	 * Permet de jouer effectivement le coup aux coordonn�es choisies 1. le coup
	 * a-t-il �t� jou� avant ? 2. parcours de tous les navires du joueur pour
	 * trouver si la case correspond � une case occup�e 3. modification des
	 * �tats et tableaux : touch�, coul�, case jou�e
	 * 
	 * @param x
	 * @param y
	 * @return le navire touch� ou null si coup dans l'eau
	 */
	public Navire jouerCoup(int x, int y) {
		// 1 a t on d�j� jou� le coup
		int nbreTouche = 0;
		Navire navireTouche = null;
		if (!coupsJoues[x][y]) {

			// 2 on demande � la liste des navires qui est touche ? si oui maj
			// de l'�tat + coups jou�s
			for (Navire n : this.getListeNav()) {
				nbreTouche = 0;
				List<Case> cases = n.getCases();
				for (Case c : cases) {
					if (c.isEstTouche())
						nbreTouche++;
					if (c.getPosx() == x && c.getPosy() == y) {
						c.setEstTouche(true);
						nbreTouche++;
						navireTouche = n;
						casesTouchees[x][y] = true;
					}
				}
				if (nbreTouche == cases.size()) {
					n.setEstCoule(true);
				}

			}
			coupsJoues[x][y] = true;

		}
		return navireTouche;
	}

	public List<Navire> getListeNav() {
		return this.listeNav;
	}

	public void setListeNav(List<Navire> listeNav) {
		this.listeNav = listeNav;
		// this.randomiserPlacement(listeNav);
	}

	public List<Case> getCasesOccupees() {
		return this.casesOccupees;
	}

	public void setCasesOccupees(List<Case> casesOccupees) {
		this.casesOccupees = casesOccupees;
	}

	public int getLongueur() {
		return this.longueur;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public String getJoueur() {
		return this.joueur;
	}

	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}

	public boolean[][] getCoupsJoues() {
		return this.coupsJoues;
	}

	public void setCoupsJoues(boolean[][] coupsJoues) {
		this.coupsJoues = coupsJoues;
	}

	public void setCasesTouchees(boolean[][] casesTouchees) {
		this.casesTouchees = casesTouchees;
	}

	public boolean[][] getCasesTouchees() {
		return casesTouchees;
	}

	public void actualisationCases() {
		for (Case case11 : casesOccupees) {
			if (case11.isEstTouche()) {
				this.lstCases[case11.getPosx()][case11.getPosy()]
						.setEstTouche(true);
			}
		}
	}
}
