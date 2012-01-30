import java.util.ArrayList;


public class Matrice {
	protected ArrayList<ArrayList<Integer>> Tableau;
	
	public Matrice(){
		this.Tableau=new ArrayList<ArrayList<Integer>>();
	}
	
	public void initTableau() {
		for (int i=0;i<this.Tableau.size();i++) {
			for (int j=0;j<this.Tableau.get(i).size();j++) {
				this.Tableau.get(i).set(j,0);
			}
		}
	}
	
	public void copierTableau(ArrayList<ArrayList<Integer>> aCopier) {
		for (int j=0;j<aCopier.size();j++) {
			for (int i=0;i<aCopier.get(j).size();i++){
				this.Tableau.get(i).set(j,aCopier.get(i).get(j));
			}
			
		}
		
	}
}
