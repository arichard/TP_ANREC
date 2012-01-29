import java.util.ArrayList;


public class Matrice {
	protected Integer[][] Tableau;
	
	public Matrice(int n,int p){
		this.Tableau=new Integer[n][p];
	}
	
	public void initTableau() {
		for (int i=0;i<this.Tableau.length;i++) {
			for (int j=0;j<this.Tableau[i].length;j++) {
				this.Tableau[i][j]=0;
			}
		}
	}
	
	public void copierTableau(ArrayList<ArrayList<Integer>> aCopier) {
		for (int j=0;j<aCopier.size();j++) {
			for (int i=0;i<aCopier.get(j).size();i++){
				this.Tableau[i][j]=aCopier.get(j).get(i);
			}
			
		}
		
	}
}
