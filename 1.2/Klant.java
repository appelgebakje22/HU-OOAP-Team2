package Domain;

public class Klant {

	private String naam;
	private String adres;
	private String telefoonNr;

	public Klant(String naam, String adres, String telefoonNr) {
		this.naam = naam;
		this.adres = adres;
		this.telefoonNr = telefoonNr;
	}

	public String getNaam() {
		return naam;
	}

	public String getAdres() {
		return adres;
	}

	public String getTelefoonNr() {
		return telefoonNr;
	}
}
