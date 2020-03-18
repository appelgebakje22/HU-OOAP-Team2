package Domain;

import java.util.ArrayList;
import java.util.Calendar;

public class Transactie {

	private ArrayList<TransactieRegel> regels = new ArrayList<>();
	private int transactieNr;
	private Calendar datum;
	private Calendar tijd;
	private String plaats;
	private double btwTotaal;
	private String transactieType;

	public Transactie(int transactieNr, Calendar datum, Calendar tijd, String plaats, double btwTotaal, String transactieType) {
		this.transactieNr = transactieNr;
		this.datum = datum;
		this.tijd = tijd;
		this.plaats = plaats;
		this.btwTotaal = btwTotaal;
		this.transactieType = transactieType;
	}

	public double totaal() {
		double alleRegelPrijs = regels.stream().mapToDouble(regel -> regel.getProduct().getPrijs() * regel.getAantal()).sum();
		return alleRegelPrijs + btwTotaal;
	}
}
