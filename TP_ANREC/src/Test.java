import java.util.ArrayList;


public class Test {
	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> X = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> premiereColonne = new ArrayList<Double>();
		premiereColonne.add(18.);
		premiereColonne.add(20.);
		ArrayList<Double> deuxiemeColonne = new ArrayList<Double>();
		deuxiemeColonne.add(13.);
		deuxiemeColonne.add(12.25);
		ArrayList<Double> troisiemeColonne = new ArrayList<Double>();
		troisiemeColonne.add(5.);
		troisiemeColonne.add(2.);
		ArrayList<Double> quatriemeColonne = new ArrayList<Double>();
		quatriemeColonne.add(0.75);
		quatriemeColonne.add(0.25);
		ArrayList<Double> cinquiemeColonne = new ArrayList<Double>();
		cinquiemeColonne.add(10.75);
		cinquiemeColonne.add(15.25);	
		ArrayList<Double> sixiemeColonne = new ArrayList<Double>();
		sixiemeColonne.add(18.75);
		sixiemeColonne.add(20.);
		X.add(premiereColonne);
		X.add(deuxiemeColonne);
		X.add(troisiemeColonne);
		X.add(quatriemeColonne);
		X.add(cinquiemeColonne);
		X.add(sixiemeColonne);
		kmeans monAlgorithme = new kmeans(X,3);
		ArrayList<ArrayList<Double>> Salut = monAlgorithme.lancerAlgorithme();
		System.out.println("\n");
		System.out.println("Matrice Salut :");
		for (int i=0;i<Salut.size();i++) {
			System.out.println(Salut.get(i).get(0)+" "+Salut.get(i).get(1)+" "+Salut.get(i).get(2));
		}
		monAlgorithme.graphique(Salut, "salut");
	}

}
