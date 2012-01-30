import java.util.ArrayList;


public class Test {
	public static void main(String[] args) {
		Matrice X = new Matrice();
		ArrayList<Double> premiereColonne = new ArrayList<Double>();
		premiereColonne.add(18.);
		premiereColonne.add(20.);
		premiereColonne.add(13.);
		premiereColonne.add(14.);
		ArrayList<Double> deuxiemeColonne = new ArrayList<Double>();
		deuxiemeColonne.add(13.);
		deuxiemeColonne.add(12.25);
		deuxiemeColonne.add(10.55);
		deuxiemeColonne.add(10.85);
		ArrayList<Double> troisiemeColonne = new ArrayList<Double>();
		premiereColonne.add(5.);
		premiereColonne.add(2.);
		premiereColonne.add(3.);
		premiereColonne.add(4.);
		ArrayList<Double> quatriemeColonne = new ArrayList<Double>();
		deuxiemeColonne.add(0.75);
		deuxiemeColonne.add(0.25);
		deuxiemeColonne.add(0.55);
		deuxiemeColonne.add(0.85);		
		X.Tableau.add(premiereColonne);
		X.Tableau.add(deuxiemeColonne);
		X.Tableau.add(troisiemeColonne);
		X.Tableau.add(quatriemeColonne);
		kmeans monAlgorithme = new kmeans(X,5);
		monAlgorithme.lancerAlgorithme();
	}

}
