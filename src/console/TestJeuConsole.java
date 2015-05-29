package console;

import java.util.Random;
import java.util.Scanner;

public class TestJeuConsole {

	public static void main(String[] args) {

		JeuConsole jc = new JeuConsole();
		int score = 0;
		jc.InitPlateau();
		jc.InitNavires();
		jc.Accueil();

		//tant que le score max n'est pas atteint
		while (score != jc.ScoreMax(jc.getPlateau().getListeNav())) {
			jc.AfficherGrille();
			System.out.println("Entrez l'abscisse du prochain coup : ");
			Scanner abs = new Scanner(System.in);
			int i = abs.nextInt();
			System.out.println("Entrez l'ordonnée du prochain coup : ");
			Scanner ord = new Scanner(System.in);
			int j = ord.nextInt();
			
			try {
			jc.getPlateau().jouerCoup(i, j);
			jc.getPlateau().actualisationCases();
			jc.ChangerMotifGrille(i, j);
		} catch (IllegalArgumentException iae){
			System.out.println(iae.getMessage());
		}
			score = 0;
			jc.ActualisationScore(jc.getPlateau().getListeNav(), score);
			System.out.println("Votre score est de " + score + " pts ! \n");
}
	}
}
