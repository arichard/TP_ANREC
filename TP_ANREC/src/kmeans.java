import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * Algorithme k-means TP ANREC
 * 
 * @author Antoine RICHARD
 * @author Marc LAURENT
 * 
 */

public class kmeans {

	protected ArrayList<ArrayList<Double>> Matrice;
	protected int k;

	/**
	 * Constructeur par défaut
	 */
	public kmeans() {
	}

	/**
	 * Constructeur
	 * 
	 * @param Matrice
	 * @param k
	 */
	public kmeans(ArrayList<ArrayList<Double>> Matrice, int k) {
		this.Matrice = Matrice;
		this.k = k;
	}

	/**
	 * Méthode de lancement de l'algorithme
	 * 
	 * @param similarite
	 *            : true si on souhaite avoir le calcul de la norme de la
	 *            matrice de similarité
	 * @return ArrayList<ArrayList<Double>> : matrice où chaque ligne est un
	 *         vecteur individu et la dernière colonne le numéro du cluster
	 *         auquel appartient ledit individu
	 */
	public ArrayList<ArrayList<Double>> lancerAlgorithme(boolean similarite) {
		// On va regarder si le nombre de clusters souhaité n'est pas
		// supérieur au nombre d'individus
		if (this.k > this.Matrice.size()) {
			System.out
					.println("Votre k est trop grand compare a votre nombre d'individus");
			return this.Matrice;
		} else {
			// On peut mettre l'algo ici :)

			// On initialise noyauxClusters qui va contenir les représentants de
			// mes k clusters
			ArrayList<ArrayList<Double>> noyauxClusters = new ArrayList<ArrayList<Double>>();
			// Je choisis aléatoirement mes k premiers représentants
			while (noyauxClusters.size() < this.k) {
				int random = (int) (this.Matrice.size() * Math.random());
				// On ajoute l'individu à nos représentants de clusters s'il
				// n'en fait pas déjà partie
				if (!noyauxClusters.contains(this.Matrice.get(random))) {
					noyauxClusters.add(this.Matrice.get(random));
				}
			}

			// Voila, notre noyauxClusters contient les k individus distincts
			// représentant les k premiers clusters

			// On initialise le vecteur de n lignes CheckChange qui va tester si
			// l'algo tourne encore ou bien si on est
			// tombé sur un équilibre des clusters

			ArrayList<Boolean> CheckChange = new ArrayList<Boolean>(
					this.Matrice.size());
			for (int i = 0; i < this.Matrice.size(); i++) {
				CheckChange.add(true);
			}

			/*
			 * Création et remplissage de la matrice M contenant nos données
			 * et l'attribution d'un individu à un cluster (dernière colonne)
			 */
			ArrayList<ArrayList<Double>> M = new ArrayList<ArrayList<Double>>();

			for (int i = 0; i < this.Matrice.size(); i++) {
				M.add(this.Matrice.get(i)); // Nos données
				M.get(i).add(0.); // L'appartenance à un cluster
			}

			// On lance la boucle déterminant l'appartenance pour chaque
			// individu à un cluster

			ArrayList<Integer> NbIndividusMatrix = new ArrayList<Integer>();
			for (int h = 0; h < this.k; h++) {
				NbIndividusMatrix.add(0);
			}

			while (CheckChange.contains(true)) {
				// On affecte les n individus aux k clusters
				for (int i = 0; i < this.Matrice.size(); i++) {
					// On définit des variables propres à chaque individu
					ArrayList<Double> VecteurDistance = new ArrayList<Double>();
					VecteurDistance.add(0.);
					VecteurDistance.add(0.);
					// On va calculer les distances de chaque individu à chaque
					// noyau de cluster
					for (int j = 0; j < this.k; j++) {
						Double distance = 0.;
						for (int p = 0; p < this.Matrice.get(0).size(); p++) {
							distance += Math.pow(M.get(i).get(p)
									- noyauxClusters.get(j).get(p), 2);
						}
						distance = Math.sqrt(distance);
						// Si j=1 alors on prend la distance pour la mettre dans
						// VecteurDistance
						if (j == 0) {
							VecteurDistance.set(0, distance);
							VecteurDistance.set(1, 0.);
						}
						// Sinon on va voir si la nouvelle distance est plus
						// petite que celle stockée dans VecteurDistance
						else if (distance < VecteurDistance.get(0)) {
							VecteurDistance.set(0, distance);
							VecteurDistance.set(1, (double) j);
						}
					}

					// VecteurDistance contient maintenant l'indice du cluster
					// auquel devrait appartenir l'individu (et sa
					// distance par rapport au noyau de ce cluster)
					// Il faut donc maintenant attribuer l'individu à son
					// nouveau cluster
					Double OldCluster = M.get(i).get(
							this.Matrice.get(0).size() - 1);
					M.get(i).set(this.Matrice.get(0).size() - 1,
							VecteurDistance.get(1));
					// On regarde maintenant si l'indice du cluster a changé
					// et on rentre le résultat dans CheckChange
					CheckChange.set(i,
							(OldCluster - VecteurDistance.get(1)) != 0);
				}

				// On va maintenant calculer les k nouveaux centres de gravité
				for (int j = 0; j < this.k; j++) {
					// Nbindividus va representer le nombre d'individus dans le
					// cluster j
					int Nbindividus = 0;
					// Somme(p) va comprendre la somme des variables Xp pour
					// tous
					// les individus appartenant au cluster j
					ArrayList<Double> Somme = new ArrayList<Double>();
					for (int h = 0; h < this.Matrice.get(0).size(); h++) {
						Somme.add((double) 0);
					}
					for (int i = 0; i < this.Matrice.size(); i++) {
						// Si l'individu i appartient au cluster j
						if (M.get(i).get(this.Matrice.get(0).size() - 1) == j) {
							Nbindividus++;
							// On l'ajoute  la somme
							for (int g = 0; g < this.Matrice.get(0).size(); g++) {
								Somme.set(g, Somme.get(g) + M.get(i).get(g));
							}
						}
					}
					// On divise la somme par le nombre total d'individus pour
					// retomber sur un vrai barycentre
					for (int g = 0; g < this.Matrice.get(0).size(); g++) {
						Somme.set(g, Somme.get(g) / Nbindividus);
					}
					// On peut maintenant remplacer l'individu j de
					// noyauxClusters
					noyauxClusters.set(j, Somme);
				}

			}
			if (similarite == true) {
				this.similarity(M, noyauxClusters, NbIndividusMatrix);
			}

			return M;
		}
	}

	/**
	 * Méthode affichant un nuage de points d'une matrice traitée avec la
	 * bibliothèque JFreechart
	 * 
	 * @param matrice
	 *            : matrice résultant du traitement avec l'algorithme k-means
	 * @param fileUser
	 *            : nom du fichier PNG souhaité en sortie
	 */
	public void graphique(ArrayList<ArrayList<Double>> matrice,
			String fileTitleUser) {
		// variables locales
		int i, j, cluster;
		XYSeries series[] = new XYSeries[this.k];
		String name, title, fileName;

		// création des différentes séries selon le nombre de cluster souhaité
		for (i = 0; i < this.k; i++) {
			name = "Cluster n° " + i;
			series[i] = new XYSeries(name);
		}

		// récupérer les coordonnées de chaque individu
		for (j = 0; j < matrice.size(); j++) {
			// on récupère le numéro du cluster
			cluster = (int) matrice.get(j).get(matrice.get(j).size() - 1)
					.intValue();
			// on récupère les coordonnées X et Y de l'individu
			// et on les ajoute à la bonne série
			series[cluster].add(matrice.get(j).get(0), matrice.get(j).get(1));
		}

		// création du XYDataset contenant toutes les séries
		XYSeriesCollection xyDataset = new XYSeriesCollection();
		for (i = 0; i < this.k; i++) {
			xyDataset.addSeries(series[i]);
		}

		// création du graphique
		title = "Algo k-means / k = " + this.k;
		JFreeChart chart = ChartFactory.createScatterPlot(title, "X", "Y",
				xyDataset, PlotOrientation.VERTICAL, false, false, false);

		// affichage du graphique
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);

		// sauvegarde du graphique en fichier png
		fileName = fileTitleUser + ".png";
		try {
			ChartUtilities.saveChartAsPNG(new File(fileName), chart, 1000, 600);
		} catch (IOException ioe) {
			// erreur de fermeture des flux
			System.out.println("Erreur --" + ioe.toString());
		}

	}

	/**
	 * Calcul de similarité
	 * 
	 * @param M
	 * @param noyauxClusters
	 * @param NbIndividusMatrix
	 */
	public void similarity(ArrayList<ArrayList<Double>> M,
			ArrayList<ArrayList<Double>> noyauxClusters,
			ArrayList<Integer> NbIndividusMatrix) {

		// Calcul de l'inertie au sein d'un groupe et de l'inertie totale
		// intra-groupes
		ArrayList<Double> InertiaWithinGroupMatrix = new ArrayList<Double>();
		for (int h = 0; h < this.k; h++) {
			InertiaWithinGroupMatrix.add((double) 0);
		}
		Double distance;
		for (int i = 0; i < this.k; i++) { // Pour tous les groupes
			for (int j = 0; j < this.Matrice.size(); j++) { // On balaye tous
															// les individus
				if (M.get(j).get(this.Matrice.get(0).size() - 1) == i) {
					// Pour chaque individu appartenant au groupe correspondant
					for (int p = 0; p < this.Matrice.get(0).size(); p++) {
						// On calcule la distance au carré entre cet individu et
						// le barycentre du groupe en question
						distance = Math.pow(M.get(j).get(p)
								- noyauxClusters.get(i).get(p), 2);
						InertiaWithinGroupMatrix.set(i,
								InertiaWithinGroupMatrix.get(i) + distance);
					}
				}
			}
		}

		// Calcul de l'inertie totale Intra
		Double TotalInertiaWithinGroups = 0.;
		for (int i = 0; i < this.k; i++) {
			TotalInertiaWithinGroups += InertiaWithinGroupMatrix.get(i);
		}

		// On va calculer d'abord les coordonnées du barycentre global
		ArrayList<Double> Barycentre = new ArrayList<Double>();

		for (int h = 0; h < this.Matrice.get(0).size(); h++) {
			Barycentre.add((double) 0);
		}
		int Nbindividus = 0;
		for (int i = 0; i < this.Matrice.size(); i++) {
			Nbindividus++;
			for (int g = 0; g < this.Matrice.get(0).size(); g++) {
				Barycentre.set(g, Barycentre.get(g) + M.get(i).get(g));
			}
		}
		// On divise la somme par le nombre total d'individus pour
		// retomber sur un vrai barycentre
		for (int g = 0; g < this.Matrice.get(0).size(); g++) {
			Barycentre.set(g, Barycentre.get(g) / Nbindividus);
		}
		// Calcul de l'inertie totale Extra
		Double TotalInertiaBetweenGroups = 0.;
		for (int k = 0; k < this.k; k++) {
			Double distancek;
			for (int p = 0; p < this.Matrice.get(0).size(); p++) {
				// On calcule la distance au carré entre le barycentre du groupe
				// et le barycentre global
				distancek = Math.pow(
						noyauxClusters.get(k).get(p) - Barycentre.get(p), 2);
				TotalInertiaBetweenGroups += distancek;
			}
		}
		System.out.println("Inertie Total Intra Groupes : "
				+ TotalInertiaWithinGroups + "\n"
				+ "Inertie Total Inter Groupes : " + TotalInertiaBetweenGroups);
	}

	/**
	 * Méthode prenant en paramètre un fichier texte contenant un tableau de
	 * données et en sortant une ArrayList<ArrayList<Double>> méthode statique
	 * pour pouvoir l'appeler sans instancier d'objet kmeans
	 * 
	 * @param filePath
	 *            : chemin du fichier à traiter
	 * @return ArrayList<ArrayList<Double>> : chaque sous-liste est les
	 *         coordonnées d'un individu
	 */
	public static ArrayList<ArrayList<Double>> traitementTxt(String filePath,
			int nbLignesSauter, int firstColumn) {

		ArrayList<ArrayList<Double>> matrice = null;

		try {
			// création du flux bufferisé sur un FileReader
			FileReader fr = new FileReader(filePath);
			BufferedReader buffLignes = new BufferedReader(fr);
			String line1;
			String line2;
			String[] temp;
			int index = 0;
			int nbLignes = 0;

			// on récupère d'abord le nombre de lignes
			// afin d'initialiser notre "matrice" à la bonne taille
			while ((line1 = buffLignes.readLine()) != null) {
				nbLignes++;
			}

			// on ferme le premier buffer (calcul du nb de lignes)
			// et on ouvre un nouveau (récupération des données)
			buffLignes.close();
			fr = new FileReader(filePath);
			BufferedReader buffDonnees = new BufferedReader(fr);

			// initialisation de la matrice
			matrice = new ArrayList<ArrayList<Double>>(nbLignes);

			// lecture des 4 premières lignes pour les "éviter'
			for (int i = 0; i < nbLignesSauter; i++) {
				line2 = buffDonnees.readLine();
			}

			// lecture du fichier ligne par ligne
			// cette boucle se termine quand la méthode retourne la valeur null
			while ((line2 = buffDonnees.readLine()) != null) {
				// on découpe les lignes à chaque tabulation
				temp = line2.split("\\t");
				matrice.add(new ArrayList<Double>(temp.length));
				// on utilise le paramètre permettant de sauter les x premières
				// colonnes
				// dans le cas où le jeu de données à une colonne de "titre" par
				// exemple
				for (int i = firstColumn; i < temp.length; i++) {
					// on ajoute chaque valeur au bon endroit de la liste
					matrice.get(index).add(Double.parseDouble(temp[i]));
				}
				index++;
			}

			buffDonnees.close();

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

		kmeans test = new kmeans(traitementTxt("exemple2.txt", 0, 0), 8);
		test.graphique(test.lancerAlgorithme(true), "moyenne_2");

	}

}
