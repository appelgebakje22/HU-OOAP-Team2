package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class VerhuurProduct extends Product {
	private double verhuurPrijs;
	private double borg;
	private static ArrayList<Product> alleVerhuurProducten = new ArrayList<>();

	private ArrayList<Exemplaar> exemplaren = new ArrayList<>();

	public VerhuurProduct(double verhuurPrijs, double borg, int productNr, String omschrijving, double prijs){
		super(productNr, omschrijving, prijs);
		this.verhuurPrijs = verhuurPrijs;
		this.borg = borg;
	}

	public TreeMap<Exemplaar, Object> geefBeschikbareExemplaren() {
		TreeMap<Exemplaar, Object> resultaat = new TreeMap<>();
		for (Exemplaar exemplaar : exemplaren) {
			if(exemplaar.isVerhuurd()){
				resultaat.put(exemplaar, exemplaar.geefDetails());
			}
		}
		return resultaat;
	}

	public static List<String> geefAlle() {
		return alleVerhuurProducten.stream().map(Product::getOmschrijving).collect(Collectors.toList());
	}

	public double getVerhuurPrijs() {
		return verhuurPrijs;
	}

	public double getBorg() {
		return borg;
	}

	public static ArrayList<Product> getAlleVerhuurProducten() {
		return alleVerhuurProducten;
	}

	public ArrayList<Exemplaar> getExemplaren() {
		return exemplaren;
	}
}