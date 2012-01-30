import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * Algorithme k-means TP ANREC
 * 
 * @author Antoine RICHARD
 * @author Marc LAURENT
 * 
 */

public class kmeans {

	protected Matrice X;
	protected int k;

	/**
	 * Constructeur par d�faut
	 */
	public kmeans() {
	}

	/**
	 * Constructeur
	 * 
	 * @param X
	 * @param k
	 */
	public kmeans(Matrice X, int k) {
		this.X = X;
		this.k = k;
	}

	/**
	 * M�thode de lancement de l'algorithme
	 * 
	 * @return
	 */
	public Matrice lancerAlgorithme() {
		// On va regarder si le nombre de clusters souhaitﾎ n'est pas
		// supﾎrieur au nombre d'individus
		if (this.k > this.X.Tableau.get(0).size()) {
			System.out
					.println("Votre k est trop grand comparﾎ ﾈ votre nombre d'individus");
			return this.X;
		} else {
			// On peut mettre l'algo ici :)

			// noyauxClusters va contenir les reprﾎsentants de mes k premiers
			// clusters
			Matrice noyauxClusters = new Matrice();
			// Je choisis alﾎatoirement mes k premiers reprﾎsentants
			for (int i = 0; i < this.k; i++) {
				int random = (int) (this.X.Tableau.get(0).size() * Math
						.random());
				System.out.println(random);
			}
			return this.X;
		}
	}

	/**
	 * M�thode prenant en param�tre un fichier texte contenant un tableau de
	 * donn�es et en sortant une ArrayList<ArrayList<Double>>
	 * 
	 * @param filePath
	 * @return
	 */
	public ArrayList<ArrayList<Double>> traitementTxt(String filePath) {

		ArrayList<ArrayList<Double>> matrice = null;

		try {
			// Crﾎation du flux buffﾎrisﾎ sur un FileReader,
			// immﾎdiatement suivi
			// par un try/finally, ce qui permet de ne fermer le flux QUE s'il
			// le reader est correctement instanciﾎ (ﾎvite les
			// NullPointerException)
			BufferedReader buffLignes = new BufferedReader(new FileReader(
					filePath));
			BufferedReader buffDonnees = new BufferedReader(new FileReader(
					filePath));
			String line;

			try {
				String[] temp;
				int index = 0;
				int nbLignes = 0;

				// on rﾎcupﾏre d'abord le nombre de lignes
				// afin d'initialiser notre "matrice" ﾈ la bonne taille
				while ((line = buffLignes.readLine()) != null) {
					nbLignes++;
				}

				// initialisation de la matrice
				matrice = new ArrayList<ArrayList<Double>>(nbLignes);

				// Lecture du fichier ligne par ligne. Cette boucle se termine
				// quand la mﾎthode retourne la valeur null
				while ((line = buffDonnees.readLine()) != null) {
					temp = line.split("\\t");
					matrice.get(index) = new ArrayList<Double>(temp.length);
					for (int i = 0; i < temp.length; i++) {

						matrice.get(index).add(Double.parseDouble(temp[i]));
					}
					index++;
				}

			} finally {
				buffLignes.close();
				buffDonnees.close();
			}
		} catch (IOException ioe) {
			// erreur de fermeture des flux
			System.out.println("Erreur --" + ioe.toString());
		}

		return matrice;
	}

	/**
	 * main
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> testTxt;
		kmeans testAlgo = new kmeans();
		testTxt = testAlgo.traitementTxt("exemple1.txt");

		/*
		 * Matrice X = new Matrice(4, 4); ArrayList<Integer> B = new
		 * ArrayList<Integer>(); B.set(0, 1); B.set(1, 2);
		 * ArrayList<ArrayList<Integer>> A = new
		 * ArrayList<ArrayList<Integer>>(4); A.set(0, B); X.initTableau();
		 * System.out.println(X.Tableau[3][3]); X.copierTableau(A);
		 * System.out.println(X.Tableau[0][1]);
		 */

	}

}
