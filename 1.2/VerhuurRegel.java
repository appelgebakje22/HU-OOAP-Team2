package Domain;

import java.util.Calendar;

public class VerhuurRegel extends TransactieRegel {

	private Calendar eindDatum;
	private int aantalDagen;

	private Exemplaar exemplaar;

	public VerhuurRegel(Calendar eindDatum, int aantalDagen, Product product, int aantal, int regelTotaal) {
		super(product,aantal,regelTotaal);
		this.eindDatum = eindDatum;
		this.aantalDagen = aantalDagen;
	}

	public VerhuurRegel(Exemplaar exemplaar, Calendar eindDatum, int aantalDagen, Product product, int aantal, int regelTotaal) {
		super(product,aantal,regelTotaal);
		this.exemplaar = exemplaar;
		this.eindDatum = eindDatum;
		this.aantalDagen = aantalDagen;
	}

	public Calendar geefPeriode() {
		return eindDatum;
	}

	public void setExemplaar(Exemplaar exemplaar) {
		this.exemplaar = exemplaar;
	}

	public int getAantalDagen() {
		return aantalDagen;
	}

	public Exemplaar getExemplaar() {
		return exemplaar;
	}
}
