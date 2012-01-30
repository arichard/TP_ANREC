
/**
 * 
 * Algorithme k-means
 * TP ANREC
 * @author Antoine RICHARD
 * @author Marc LAURENT
 * 
 */

public class kmeans {
	public static void main(String[] args) {
	Matrice X = new Matrice();
	}
	/*
	On a donc au d�part p variables X1,X2,...,Xp et n individus. La composante j de l'individu i est not�e Xi,j et se trouve � la j-�me colonne de la i-�me ligne.

	Algorithme du k-means :

	Param�tres en entr�e : Matrice X(n,p), entier naturel k
	Param�tres en sortie : Matrice n lignes, p+1 colonnes o� la derni�re colonne traduit l'affectation du vecteur individu � un des k clusters

	Debut

	// 1�re it�ration
	// S�lectionner k individus (au hasard par ex) � partir de la matrice X. Ces k individus sont les repr�sentants des k clusters

	CheckChange <-- (true,true,...,true) (vecteur de n lignes)

	// Cr�ation et remplissage de la matrice M contenant nos donn�es et l'attribution d'un individu � un cluser (derni�re colonne)
	M = matrice(n,p+1)
	Pour i=0 � n
		Pour j=0 � p
			M(i,j) = X(i,j)
		Fin pour
	Fin pour
	Pour i=0 � n
		M(i,p+1)=0
	Fin pour

	// matrice des centres de gravit� des k clusters
	K = matrice(k,p)

	Tant que (CheckChange != (false,false,...,false))

	// Affecter les n individus aux k clusters :

	Pour i=0 � n-1
		VecteurDistance <-- (0,0)
		distance <-- 0
		OldCluster <-- -1
			
		Pour j=0 � k-1
			// Calcul de la distance euclidienne entre l'individu X(j) et le barycentre du cluster j K(j)
			distance <-- |X(j)-K(j)|
			// On va voir si la nouvelle distance est plus petite que la plus petite de toutes les distances pr�c�dentes
			Si j=1 alors
				VecteurDistance(x) = distance
				VecteurDistance(y) = j
			Sinon si ((distance) < VecteurDistance(x)) alors
				VecteurDistance(x) = distance
				VecteurDistance(y) = j
			Fin si
		Fin pour
			
		// Attribuer l'individu � son (nouveau) cluster
		// cad ajouter � la derni�re colonne de la matrice M l'indice du cluster
		OldCluster <-- M(i,p+1)
		M(i,p+1) <-- VecteurDistance(y)
		// Regarder si l'indice du cluster a chang�
		// Si oui, CheckChange(i) = true | Si non, CheckChange(i) = false
		Boolean CheckChange(i) <-- isDifferent(Oldcluster,VecteurDistance(y))
			
	Fin pour

	// Calcul des k nouveaux centres de gravit�

	Pour j=0 � k-1
		Nbindividus <-- 0
		Somme <-- (0,0,...,0) //(1 ligne, p colonnes)
			Pour i=0 � n-1
				Si (M(i,p+1) = j) alors
					Pour l=0 � p-1
						Somme(l) = Somme(l) + M(i,l)
						NbIndividus++;
					Fin pour
				Fin si
			Fin pour
		Pour l=0 � p-1
			K(j,l) <-- Somme(l) / NbIndividus
		Fin pour
	Fin pour

	Fin Tant que

	Fin
	*/

}
