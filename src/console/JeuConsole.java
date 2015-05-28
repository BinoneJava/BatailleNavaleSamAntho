package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bataille.metier.Case;
import com.bataille.metier.Navire;
import com.bataille.metier.Plateau;

public class JeuConsole {

	Plateau plateau;

	public void Accueil() {
		System.out.println("Bienvenue dans ce jeu de bataille navale ! \n");
	}

	public void AfficherGrille() {
		Case[][] cas = plateau.lstCases;
		System.out.print("  ");
		for (int i = 0; i < plateau.getLargeur(); i++) {
			System.out.print(i);
		}
		System.out.print('\n');
		for (int i = 0; i < plateau.getLargeur(); i++) {
			System.out.print(i + " ");
			for (int j = 0; j < plateau.getLargeur(); j++) {
				System.out.print(cas[i][j].getMotif());
			}
			System.out.print('\n');
		}
	}

	public void InitPlateau() {
		plateau = new Plateau(10, 10, "test");
		for (int i = 0; i < plateau.getLargeur(); i++) {
			for (int j = 0; j < plateau.getLargeur(); j++) {
				plateau.lstCases[i][j] = new Case(i, j, false, "~");
			}
		}
	}

	public void InitNavires() {
		Navire porteAvion = new Navire(1, 5, null, false, 5);
		Navire croiseur = new Navire(2, 4, null, false, 4);
		Navire contreTorpilleur = new Navire(3, 3, null, false, 3);
		Navire sousMarin = new Navire(4, 3, null, false, 3);
		Navire torpilleur = new Navire(5, 2,null, false, 2);
		
		plateau.ajouterNavire(porteAvion);
		plateau.ajouterNavire(croiseur);
		plateau.ajouterNavire(contreTorpilleur);
		plateau.ajouterNavire(sousMarin);
		plateau.ajouterNavire(torpilleur);
		
		plateau.ranedomiserPlacement(plateau.getListeNav());

	}

	public void ChangerMotifGrille(int i, int j) {
		if (i < 0 || i > plateau.getLargeur() || j < 0 || j > plateau.getLargeur()){
			throw new IllegalArgumentException("Les coordonnées doivent se situer entre 0 et 9 !");
		}
		if (plateau.lstCases[i][j].isEstTouche()
				&& plateau.getCasesOccupees().contains(plateau.lstCases[i][j])) {
			plateau.lstCases[i][j].setMotif("X");
		} else {
			plateau.lstCases[i][j].setMotif("O");
		}
	}
	
	public Plateau getPlateau(){
		return this.plateau;
	}
	
	public int ScoreMax(List<Navire> lstn){
		int score = 0;
		for (Navire n : lstn){
			score = score + n.getValeurScore();
		}
		return score;
	}
}