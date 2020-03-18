package Domain;

import java.util.ArrayList;
import java.util.Calendar;

public class Exemplaar {

	private ArrayList<VerhuurRegel> regels = new ArrayList<>();
	private Calendar aanschafDatum;
	private int serieNr;
	private boolean status;

	public Exemplaar(Calendar aanschafDatum, int serieNr, boolean status) {
		this.aanschafDatum = aanschafDatum;
		this.serieNr = serieNr;
		this.status = status;
	}

	public Object[] geefDetails() {
		return new Object[]{
				aanschafDatum,
				serieNr,
				status,
		};
	}

	public ArrayList<VerhuurRegel> getRegels() {
		return regels;
	}

	public Calendar getAanschafDatum() {
		return aanschafDatum;
	}

	public int getSerieNr() {
		return serieNr;
	}

	public boolean isVerhuurd() {
		return status;
	}
}
