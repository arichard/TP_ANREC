import java.util.ArrayList;


public class Matrice {
	protected ArrayList<ArrayList<Double>> Tableau;
	
	public Matrice(){
		this.Tableau=new ArrayList<ArrayList<Double>>();
	}
	
	
	public void copierTableau(ArrayList<ArrayList<Double>> aCopier) {
		for (int j=0;j<aCopier.size();j++) {
			for (int i=0;i<aCopier.get(j).size();i++){
				this.Tableau.get(i).set(j,aCopier.get(i).get(j));
			}
			
		}
		
	}
}
