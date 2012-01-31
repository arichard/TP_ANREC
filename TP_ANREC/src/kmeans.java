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
	 * @return
	 */
	public ArrayList<ArrayList<Double>> lancerAlgorithme() {
		// On va regarder si le nombre de clusters souhaité n'est pas
		// supérieur au nombre d'individus
		if (this.k > this.Matrice.size()) {
			System.out
					.println("Votre k est trop grand compare a votre nombre d'individus");
			return this.Matrice;
		} else {
			System.out.println("Matrice X :");
			for (int i = 0; i < this.Matrice.size(); i++) {
				System.out.println(this.Matrice.get(i).get(0) + " "
						+ this.Matrice.get(i).get(1));
			}
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
			System.out.println("\n");
			System.out.println("Matrice noyauxClusters :");
			for (int i = 0; i < noyauxClusters.size(); i++) {
				System.out.println(noyauxClusters.get(i).get(0) + " "
						+ noyauxClusters.get(i).get(1));
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
			System.out.println("\n");
			System.out.println("Matrice CheckChange :");
			System.out.println(CheckChange.get(0) + " " + CheckChange.get(1)
					+ " " + CheckChange.get(2) + " " + CheckChange.get(3) + " "
					+ CheckChange.get(4) + " " + CheckChange.get(5));

			/*
			 * Création et remplissage de la matrice M contenant nos données
			 * et l'attribution d'un individu à un cluster (dernière colonne)
			 */
			ArrayList<ArrayList<Double>> M = new ArrayList<ArrayList<Double>>();

			for (int i = 0; i < this.Matrice.size(); i++) {
				M.add(this.Matrice.get(i)); // Nos données
				M.get(i).add(0.); // L'appartenance à un cluster
			}
			System.out.println("\n");
			System.out.println("Matrice M :");
			for (int i = 0; i < M.size(); i++) {
				System.out.println(M.get(i).get(0) + " " + M.get(i).get(1)
						+ " " + M.get(i).get(2));
			}
			// On lance la boucle déterminant l'appartenance pour chaque
			// individu à un cluster

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
						System.out.println("\n Distance de " + i
								+ " par rapport  " + j + " :" + distance);
					}
					System.out.println("VecteurDistance :"
							+ VecteurDistance.get(0) + " "
							+ VecteurDistance.get(1));

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
				System.out.println("\n");
				System.out.println("Matrice CheckChange :");
				System.out.println(CheckChange.get(0) + " "
						+ CheckChange.get(1) + " " + CheckChange.get(2) + " "
						+ CheckChange.get(3) + " " + CheckChange.get(4) + " "
						+ CheckChange.get(5));
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
			return M;
		}
	}

	/**
	 * Méthode prenant en paramètre un fichier texte contenant un tableau de
	 * données et en sortant une ArrayList<ArrayList<Double>>
	 * 
	 * @param filePath
	 * @return
	 */
	public ArrayList<ArrayList<Double>> traitementTxt(String filePath, int nbLignesSauter) {

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
				for (int i = 0; i < temp.length; i++) {
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
		kmeans testKmeans = new kmeans();
		ArrayList<ArrayList<Double>> result = testKmeans.traitementTxt("ListeDesMoyennes.txt",1);
		for(int i=0;i<result.size();i++) {
			System.out.println("Ligne " + i);
			for(int j=0;j<result.get(i).size();j++) {
				System.out.println(result.get(i).get(j));
			}
		}

	}

}
