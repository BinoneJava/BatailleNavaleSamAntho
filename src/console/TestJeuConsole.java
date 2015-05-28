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

		System.out.println(jc.getPlateau().getCasesOccupees());
		//tant que le score max n'est pas atteint
		while (score != jc.ScoreMax(jc.getPlateau().getListeNav())) {
			jc.AfficherGrille();
			System.out.println("Entrez l'abscisse du prochain coup : ");
			Scanner abs = new Scanner(System.in);
			int i = abs.nextInt();
			System.out.println("Entrez l'ordonnée du prochain coup : ");
			Scanner ord = new Scanner(System.in);
			int j = ord.nextInt();
			
			jc.getPlateau().jouerCoup(i, j);
			jc.getPlateau().actualisationCases();
			jc.ChangerMotifGrille(i, j);
		}
	}
}
