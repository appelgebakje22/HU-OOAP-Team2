package Domain;

import java.util.Calendar;

public class VerhuurTransactie extends Transactie {

	private double borgTotaal;

	public VerhuurTransactie(double borgTotaal, int transactieNr, Calendar datum, Calendar tijd, String plaats, double btwTotaal, String transactieType) {
		super(transactieNr,datum, tijd,plaats,btwTotaal,transactieType);
		this.borgTotaal = borgTotaal;
	}

	public double geefAfrekenTotaal() {
		return super.totaal() + borgTotaal;
	}

	public void voegVerhuurRegelToe(Exemplaar exemplaar, Calendar eindDatum, int aantalDagen, Product product, int aantal){
		regels.add(new VerhuurRegel(exemplaar, eindDatum, aantalDagen, product, aantal, regels.size() + 1));
	}
}
